######################################################################################################
#                                                                                                    #
#   Instructions for compiling and starting sourcecode from the console (non-IDE).                   #
#   Instruction is written for the state where the console is opened in the project’s root folder.   #
#                                                                                                    #
######################################################################################################

# Compiling sourcecode to destination directory with compiled .class files
javac  src/java/edu.school21.printer/*/*.java  -d ./target



jar cf ./target/images-to-chars-printer.jar ./src/manifest.txt ./target
chmod 777 target/images-to-chars-printer.jar
java -jar target/images-to-chars-printer.jar . 0

# Copy resources to destination directory
cp -rf ./src/resources ./target

# Run program with specific parameters:
# parameter 1 - symbol for white color
# parameter 2 - symbol for black color
# parameter 3 - path to image
java -classpath ./target edu.school21.printer.app.Program . 0 ./it.bmp

# Clean
rm -rf ./target/*




/////
jar cf ./target/images-to-chars-printer.jar src/manifest.txt -C target .
chmod u+x target/images-to-chars-printer.jar

java -jar target/images-to-chars-printer.jar . 0

//////
# Compilation:
    # this command compiles .java files into .class files, put them into target directory
 mkdir target   # javac for java 8 DOES NOT create the directory, however it is required by subject.
 javac -d ./target/ src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/Logic.java

# Jar file creation:
    # this command packs .class files, resources and a manifest file into a single images-to-chars-printer.jar file
 jar cvfm ./target/images-to-chars-printer.jar src/manifest.txt -C target edu/ -C src/ resources

    # OPTIONAL: this command will display contents of the manifest file
# unzip -q -c ./target/images-to-chars-printer.jar META-INF/MANIFEST.MF
 cp -r src/resources target/.

# Launch jar application:
 java -jar target/images-to-chars-printer.jar . o   #path to file is not needed since it's already packaged