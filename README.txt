The packages that are assumed to be installed are python3, pip3, and the following python packages:
    pytorch
    snntorch
    numpy
    pandas


INSTALLATION METHODS

to install python and pip3
    sudo apt install python3 
    sudo apt install python3-pip

The way you install the python packages is by either installing the requirements file or installing them individually
requirements file:
    pip3 install -r requirements.txt

individually:
    pip3 install torch
    pip3 install snntorch
    pip3 install numpy
    pip3 install pandas

if python gives some error/warning about using a virtual enviroment / venv, then just use the --break-system-packages flag. 
This flag does works while installing packages individually. It should work with the requirements.

EXAMPLES

I've made 3 examples in example{n}.rc
these compile into output{n}.rc 

You can compile this yourself by either using the `build_and_compile.sh` file with the usage `./build_and_compile.sh <input> <output>`
or by compiling with antlr and running `CompileRDL.java` with the following.
    CP=$(grep '^CLASSPATH=' "$(which antlr4)" | cut -d= -f2-):.
    antlr4 -visitor RDL.g4
    javac -cp $CP *.java
    java -cp $CP CompileRDL.java <input_file> <output_file>

the "output" file of ideal_output2.py is the mostly handwritten version of how the output SHOULD look like once some of the current bugs are fixed
all of the outputs already traverse trees.
the traversal is done in RDLToPythonVisitor.java