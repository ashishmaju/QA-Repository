# Coding Assignment

This branch is specific to coding assignment only. Commit will be done on this in frequent manner estimated final commit 11 Sept 2020.


This file will let you know about the installation and required certificate to execute the test.

# Prerequisite

1. Your machine should have internet connection.
2. Your machine should have Java 1.8 installed.
3. Your machine should have any IDE (ex : Eclipse IDE,Intellij etc).
4. Your machine should have maven version 3.6.3 installed.
5. Your machine should have chrome browser installed with supported driver 83.4+.
6. All the sytem environment eg. JAVA_HOME,MAVEN_HOME and path should be configured.
7. GIT version tool setup in your system.(Assumption is that you already have config setup with your git).

# For Eclipse IDE

If you have eclipse IDE setup in your machine then clone the project from remote GIT to your local.
Follow these steps : 
1. Create any folder in your drive and Open git bash on that folder
2. type git clone https://github.com/ashishmaju/QA-Repository.git
3. Once all the files and folders are extracted in your local folder then copy path.
4. Open Eclipse IDE and select project from the file system and type the path.
5. Make sure all the file and folder are appeared to eclipse project heirarchy.
6. Once done right click on the root project folder select run as and select maven clean.
7. Then build the project by selecting maven build.


# If not having IDE
1. Open the command prompt and paste the file path copied earlier after cd.
2. once directory is set type command mvn clean
3. Once clean command is done again type mvn test

Once the execution is done go to the reports folder present in the cloned directory. Click on the ExecutionResults.html.

This way you will able to execute the scripts and see the results.


