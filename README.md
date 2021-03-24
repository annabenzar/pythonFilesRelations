# CES - Python Files Relations

Python Files Relations is a plugin which determines the connection between python files basen on imported libraries. One connection suggests that 2 python files start from the same sub functionality and it gives us an overview of all python files.

![GitHub](https://img.shields.io/github/license/baletiandrada/BENTII)


## Motivation

Our plugin generates one file, result.json, which contains 2 lists: with nodes and links. Nodes represent the python files from chosen project to be analyzed and one link is established between 2 python files sharing the same library or other python package. 



## Framework used

We developed this project in **Kotlin** using **IntelliJ Idea IDE**.



## Installation

Please download our **latest source code release** and the pythonRelations.zip which contains ces-python-files-relations.jar file. 



## Running

### Running in docker container (on your local machine):
1. Put the ces-python-files-relations.jar in ./target folder
2. Create ./project folder and add your project to be analyzed here
3. Run the following command:

```bash
docker run -v $PWD/results:/pythonFilesRelations/results -v $PWD/project/YOUR_PROJECT_FOLDER:/pythonFilesRelations/YOUR_PROJECT_FOLDER annaid/pythonrelrep results/result.json YOUR_PROJECT_FOLDER
```

### Running locally outside docker container:
1. Go to the folder when you downloaded the ces-python-files-relations.jar file
2. Run the following command:

```java
java -jar pythonRelations.jar result.json PATH_TO_YOUR_PROJECT_FOLDER
```


### Running in Dx Platform Tool:

1. Create a directory (choose any name) in ./dxplatform/plugins
2. Copy plugin-info.json and ces-python-files-relations.jar in the directory mention above 
3. In Dx Platform (Configure project -> Plugins) the plugin will appear and you may create a command. For 'Root folder' configuration field you must enter the local path to the project to be analyzed.




## Contributions
We are not open to contributions.
   
   
   
## License
[MIT](https://choosealicense.com/licenses/mit/)


