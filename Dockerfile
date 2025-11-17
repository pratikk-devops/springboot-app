#---------------------------Stage 1------------------------------------
#Base Image pull base image so that we can use maven to build .jar file
# Select maven image as per project
FROM maven:3.8.3-openjdk-17 AS builder

#set the workdir to the app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

#Build the app to generate jar file clean old packages and install mvn.Skips the tests during the Maven build phase to speed up image creation
RUN mvn clean install -DskipTests=true

#---------------------------Stage 2-------------------------------------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/target/pgtools.jar


## Specifies that the container listens on port 8080 at runtime
#i.e the port can be mapped with the host 
EXPOSE 8080

#run .jar file. executing .jar file using java command and jar file to specifyn we are executing a jar flar
#.jar file is executable file like app.java is source code and it is compiled , interpreted , executed
ENTRYPOINT ["java","-jar","/app/target/pgtools.jar"]


#-------------------------------------------------------------
