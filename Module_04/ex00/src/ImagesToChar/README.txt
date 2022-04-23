######################################################################################################
#                                                                                                    #
#   Instructions for compiling and starting sourcecode from the console (non-IDE).                   #
#   Instruction is written for the state where the console is opened in the project’s root folder.   #
#                                                                                                    #
######################################################################################################

# Create target directory
mkdir target

# Compiling sourcecode to target directory with compiled .class files
javac  src/java/edu.school21.printer/*/*.java  -d ./target

# Run program with specific parameters:
# parameter 1 - symbol for white color
# parameter 2 - symbol for black color
# parameter 3 - path to image
java -classpath ./target src.java.edu.school21.printer.app.Program . 0 ./it.bmp

# Clean
rm -rf ./target/*
