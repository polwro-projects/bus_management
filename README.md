# Bus management system

This project is one of the tasks to complete as an IO (Inżyneria Oprogramowania) project.

## Prerequisites

In order to use this project, you should install the following components on your system:

* CMake (the main build system)
* Java Runtime Environment
* Java Development Kit
* JUnit (optionally, if you want to build the tests too)

## Usage

### Building process

To build this project, run the following commands:

```bash
mkdir build && cd build
cmake -DAPP_ENTRY_POINT=Application ..
make
```

Or, if you want to build unit tests, you should run the same commands, but with some changes :

```bash
mkdir build && cd build
cmake -DUNIT_TESTS=true -DAPP_ENTRY_POINT=tests.TestRunner ..
make
```

### Running the executable

After the successful build process you can run the executable with the command below : 

```Bash
java -jar bus_management.jar
```

Or, if you are building unit tests, run the following command :

```Bash
java -jar tests_bus_management.jar
```
