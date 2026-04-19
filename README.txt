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


