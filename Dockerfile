FROM openjdk:11

WORKDIR ./pythonFilesRelations

COPY ./target/ces-python-files-relations.jar ./pythonRelations.jar

ENTRYPOINT ["java","-jar","pythonRelations.jar"]