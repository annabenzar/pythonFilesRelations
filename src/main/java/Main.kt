import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import java.nio.file.Files
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.bufferedReader
import kotlin.streams.toList

@ExperimentalPathApi
fun main(args: Array<String>){
    if(args.size != 2){
        print("Please provide both arguments")
        return
    }
    val outputFilePath = args[0]
    val baseFolderPath = args[1]

    val pythonFiles = Files.walk(File(baseFolderPath).toPath())
            .filter {Files.isRegularFile(it)}
            .filter {it.toString().contains(".py")}
            .toList()

    val fileModules = mutableListOf<PythonFile>()
    for (file in pythonFiles){
        //get java file name
        val fileName = file.toString().substringAfterLast("\\")

        //get libraries imported in java file
        val libraryList = mutableListOf<String>()
        val bufferedReader = file.bufferedReader()
        val text = bufferedReader.readLines()
        for(line in text){
            if(line.startsWith("import ")) {
                val libraryName = line.substringAfterLast(" ")
                libraryList.add(libraryName)
                //print(libraryName + "   " + "in file   " + fileName + '\n')
            }
        }
        //add java file module to fileModules list
        val fileModule = PythonFile(fileName, libraryList)
        fileModules.add(fileModule)
    }

    val nodes = mutableListOf<GraphNode>()
    for (file in fileModules){
        nodes.add(GraphNode(file.name))
    }

    val links = mutableListOf<GraphLink>()
    var index = 1
    for(module in fileModules){
        if(index<fileModules.size-1){
            for(library in module.libraries){
                for (i in index until fileModules.size){
                    if (fileModules[i].libraries.contains(library)
                            && !library.contains("os") && !library.contains("sys")){
                        val graphLink = GraphLink(module.name, fileModules[i].name)
                        links.add(graphLink)
                    }
                }
            }
            index++
        }
    }

    val graph = Graph(nodes, links)

    val result = Result(
            name = "CES - Python Files Relations",
            description = "Shows relations between Python Files By Sharing Libraries",
            entity = "Modules",
            visualTags = listOf("digraph", "hierarchical-edge-bundle", "forced-layered-graph"),
            content = graph,
            timestamp = System.currentTimeMillis()
    )

    jacksonObjectMapper().writerWithDefaultPrettyPrinter().writeValue(File(outputFilePath), listOf(result))

}

class PythonFile(
        val name: String,
        val libraries: List<String> = ArrayList(),
)

data class Graph(
        val nodes: List<GraphNode>,
        val links: List<GraphLink>
)

data class GraphNode(
        val name: String,
        val component: Number = 1
)

data class GraphLink(
        val source: String,
        val target: String,
        val value: Number = 1
)

data class Result(
        val name: String,
        val description: String,
        val visualTags: List<String>,
        val entity: String,
        val timestamp: Long,
        val content: Graph
)