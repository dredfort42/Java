######################################################################################################
#                                                                                                    #
#   Instructions for compiling and starting sourcecode from the console (non-IDE).                   #
#   Instruction is written for the state where the console is opened in the project’s root folder.   #
#                                                                                                    #
######################################################################################################

# Create lib and target directories
mkdir lib target

# Download external libraries
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar

# Get source from JAR libraries
jar -xf lib/jcommander-1.82.jar

# Move source to target directory
mv com target

# Compiling sourcecode to target directory with compiled .class files
javac  -cp ./lib/jcommander-1.82.jar ./src/java/edu.school21.printer/*/*.java -d ./target

# Copy resources to destination directory
cp -rf ./src/resources ./target

# Create JAR archive
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

# Set rights
chmod 777 ./target/images-to-chars-printer.jar

# Run program from archive with specific parameters:
# parameter 1 - new color for white
# parameter 2 - new color for black
java -jar ./target/images-to-chars-printer.jar --white=RED --black=GREEN

# Clean
rm -rf ./target META-INF lib com

# TESTER
mkdir lib target
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
jar -xf lib/jcommander-1.82.jar
mv com target
javac  -cp ./lib/jcommander-1.82.jar ./src/java/edu.school21.printer/*/*.java -d ./target
cp -rf ./src/resources ./target
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .
chmod 777 ./target/images-to-chars-printer.jar
java -jar ./target/images-to-chars-printer.jar --white=RED --black=GREEN
rm -rf ./target META-INF lib com