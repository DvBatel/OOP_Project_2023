# OOP_Project_2023
Bar-Ilan University Project, OOP 2023

#In order to run the files, simply type in the direct address on the command line(cmd) the following lines:

# write into sources.txt, the path to all java files inside src folder and any sub folder it may contain
find src -name "*.java" > sources.txt
# compile all files that are listed in sources.txt file (using the biuoop-1.4.jar file)
javac @sources.txt -d bin -cp biuoop-1.4.jar
