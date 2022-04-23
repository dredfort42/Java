######################################################################################################
#                                                                                                    #
#   Instructions for compiling and starting sourcecode from the console (non-IDE).                   #
#   Instruction is written for the state where the console is opened in the project’s root folder.   #
#                                                                                                    #
######################################################################################################

# Create target directory
mkdir target

# Compiling sourcecode to target directory with compiled .class files
javac  ./src/java/edu.school21.printer/*/*.java  -d ./target

# Copy resources to destination directory
cp -rf ./src/resources ./target

# Create JAR archive
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

# Set rights
chmod 777 ./target/images-to-chars-printer.jar

# Run program from archive with specific parameters:
# parameter 1 - symbol for white color
# parameter 2 - symbol for black color
java -jar ./target/images-to-chars-printer.jar . 0

# Clean
rm -rf ./target

# TESTER
rm -rf ./target
mkdir target
javac  ./src/java/edu.school21.printer/*/*.java -d ./target
cp -rf ./src/resources ./target
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .
chmod 777 ./target/images-to-chars-printer.jar
java -jar ./target/images-to-chars-printer.jar . 0
rm -rf ./target
