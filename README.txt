The packages that are assumed to be installed are python3, pip3, and the following python packages:
    pytorch
    snntorch
    numpy
    pandas

INSTALLATION METHODS

to install python and pip3
    sudo apt install python3
    sudo apt install python3-pip

The way you install the python packages is by either installing the requirements file or installing them individually.
requirements file:
    pip3 install -r requirements.txt

individually:
    pip3 install torch
    pip3 install snntorch
    pip3 install numpy
    pip3 install pandas

if python gives some error/warning about using a virtual enviroment / venv, then use the --break-system-packages flag.

EXAMPLES

Examples are in example{n}.rc and compile to Python output files.

NEW LANGUAGE FEATURES

1) LSM inheritance from existing LSM.py
   - Generated Python loads BaseLSM from LSM.py.
   - If auto-discovery fails, set LSM_PY_PATH to the absolute path of LSM.py.
   - User-defined LSM methods call the inherited method with the same name first, then run your custom body.

2) Readout declarations
   - Use readout to define output heads.
   Example:
       readout answers {
           type regression
       }

3) Dataset execution control
   - simulate supports pipeline entry and dataset encoding strategy.
   Example:
       simulate with train_data through reservoir {
           steps 100
           encoding rate
           encode_steps 32
           batch 8
       }

   Supported encoding values:
       rate
       latency
       preencoded

4) Flexible graph composition
   - Define LSMs, intermediate layers, and readouts.
   - Connect with weighted edges:
       connect reservoir -> hidden weight 0.8
       connect hidden -> answers

COMPILING

Use the build helper:
    ./build_and_compile.sh <input.rc> [output.py]

Or compile manually:
    CP=$(grep '^CLASSPATH=' "$(which antlr4)" | cut -d= -f2-):.
    antlr4 -Dlanguage=Java -visitor RDL.g4
    javac -cp "$CP" *.java
    java -cp "$CP" CompileRDL <input_file> <output_file>

The parser tree traversal and Python code generation are in RDLToPythonVisitor.java.
