## Group members
- [__Luigi Scibona__](https://github.com/Sciluxor) as Scilux/Sciluxor
- [__Alessandro Ruzzi__](https://github.com/alexruzzi98) as alexruzzi98
- [__Edoardo Piantoni__](https://github.com/edoardopiantoni) as edoardopiantoni

# Santorini
<img src="https://github.com/Sciluxor/ing-sw-2020-Scibona-Ruzzi-Piantoni/blob/master/wiki-assets/gamebox.png" width="400" alt="Scatola del Gioco" align="center" /></a>


The aim of the project is to implement the [Santorini](http://www.craniocreations.it/prodotto/santorini/) board game following the architectural model Model View Controller for the realization of the model according to the object-oriented programming paradigm, in this case with Java language. The final result completely covers the [rules](https://github.com/Sciluxor/ing-sw-2020-Scibona-Ruzzi-Piantoni/blob/master/wiki-assets/santorini_rules_en.pdf) defined by the game and allows you to interact with both a command line interface (CLI) and a graphical interface (GUI), the network has been managed with the traditional socket approach.

# Functionality

## Categories developed in the project:
1) **Complete Rules**
2) **CLI**
3) **GUI**
4) **Socket**
5) Advanced features:
    1) **Multiple games**
    2) **Advanced gods**

## The advanced features developed in detail are:

### Multiple games
Possibility to carry out multiple games simultaneously

### Advanced gods
We have implemented 5 advanced gods, in particular:
- #### N.16 Chronus
- #### N.20 Hera
- #### N.21 Hestia
- #### N.22 Hypnus
- #### N.30 Zeus

### Extra:
- **Tutorial**
- **Chat**
- **Music and sounds**

## Documentation
The following documentation includes the documents created for the design of the problem, the class diagrams in UML will be listed first, then the code documentation (javadoc) and finally the test coverage.

### UML
The following class diagrams represent the first, the initial model according to which the game should have been implemented, the second instead contains the diagrams of the final product.
- [Initial UML](https://github.com/Sciluxor/ing-sw-2020-Scibona-Ruzzi-Piantoni/blob/master/Deliverables/UML/Initial%20UML.jpg)
- [Final UML](https://github.com/Sciluxor/ing-sw-2020-Scibona-Ruzzi-Piantoni/tree/master/Deliverables/UML/Final%20UML)

### JavaDoc
The following documentation includes a description of the classes and methods used and can be consulted at the following address: [Javadoc](https://github.com/Sciluxor/ing-sw-2020-Scibona-Ruzzi-Piantoni/blob/master/Deliverables/JavaDoc/index.html)

### Coverage
As for the [coverage](https://github.com/Sciluxor/ing-sw-2020-Scibona-Ruzzi-Piantoni/blob/master/Deliverables/Coverage%20Report/index.html) of the tests as reported by IntelliJ are:
- __model__ 100% classes, 96% method, 96% line
- __controller__ 100% classes, 95% method, 92% line

## Jars
The following jars have been used for the delivery of the project, they allow the launch of the game according to the functionalities described in the introduction. Details for how to launch the system will be defined in the section called Executing jars. The folder where the client and server software is located at the following address: [Jars](https://github.com/Sciluxor/ing-sw-2020-Scibona-Ruzzi-Piantoni/tree/master/Deliverables/Jars).

### JARs generation
To generate tha Jars we used the Maven plugin with  ```
                                                    maven install
                                                    ```. 
They are created in the target folder with the names Client_CLI-jar-with-dependencies.jar, Client_GUI-jar-with-dependencies.jar and Server-jar-with-dependencies.jar and must be moved to the Jars folder.


## Execution of the JARs
### Client
The client runs by launching the desired jar to play from the command line or graphical interface. The following sections describe how to run the client in one way or another.

### CLI
For a better command-line gaming experience it is necessary to launch the client with a terminal that supports UTF-8 encoding and ANSI escapes. To launch the CLI client type the following command:
```
java -jar Client_CLI-jar-with-dependencies.jar
```

### GUI
To launch the GUI client type the following command:
```
java -jar Client_GUI-jar-with-dependencies.jar
```

### Server
By default, the server will be accessible through port 4700. To launch the server enter the following command:
```
java -jar Server-jar-with-dependencies.jar
```

## Game tips
That's the login, here you can choose your nickname (between 4 and 13 characters), the number of player, the port and the address to access the server.

<Login>**Login**, Game login</sub>
![Login]<img src="https://github.com/Sciluxor/ing-sw-2020-Scibona-Ruzzi-Piantoni/blob/master/wiki-assets/login.png" width="400" alt="Login" align="center" /></a>
