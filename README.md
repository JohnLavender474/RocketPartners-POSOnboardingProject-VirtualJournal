# Rocket Partners - POS Onboarding Project - Virtual Journal

The POS Virtual Journal is a server application designed to receive and print logs from client applications. The server listens for incoming connections on a specified port and handles multiple client connections concurrently. This documentation provides an overview of how to set up and run the POS Virtual Journal.

## Features
- Listens for incoming connections on a specified port.
- Handles multiple client connections concurrently.
- Prints received logs to the console.
- Gracefully shuts down on application exit.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Gradle (optional, for building the project)

## Setup
1. Clone the Repository
```bash
git clone https://github.com/YourUsername/POSVirtualJournal.git
cd POSVirtualJournal
```
2. Compile the Project
If not using Maven, you can compile the project using the javac command:
``` bash
javac -d out src/main/java/com/rocketpartners/onboarding/possystem/*.java
```

## Usage

### Running the Server
To start the POS Virtual Journal server, run the Driver class. You can specify the port to listen on via command-line arguments. If no port is specified, the default port 12345 is used.

```bash
java -cp out com.rocketpartners.onboarding.possystem.Driver [port]
```

For example, to start the server on port 12345:
```bash
java -cp out com.rocketpartners.onboarding.possystem.Driver 12345
```

### Configuration
Port: The port number the server listens on. It can be specified as a command-line argument when starting the server. If not provided, it defaults to 12345.

### Shutdown
The POS Virtual Journal server can be gracefully shut down by pressing Ctrl+C in the terminal where it is running. The server is configured to handle shutdown signals and will close all client connections and the server socket properly.

## Detailed Class Descriptions
`Driver`
The Driver class is the main entry point for the POS Virtual Journal application. It starts the server and sets up a shutdown hook to ensure the server is properly stopped when the application exits.

`POSVirtualJournal`
The POSVirtualJournal class represents the server that listens for client connections on a specified port. It manages the lifecycle of the server socket and client handlers.

- Constructor: Initializes the server socket and starts listening on the specified port.
- run(): Waits for client connections and starts a new ClientHandler thread for each connection.
- stop(): Stops the server and shuts down all client handlers.

`ClientHandler`
The ClientHandler class handles communication with a single client. It reads messages from the client and prints them to the console.

- Constructor: Initializes the input stream from the client socket.
- run(): Continuously reads messages from the client and prints them until the connection is closed.
- shutDown(): Closes the client connection and the input stream.

`JournalPrinter`
The JournalPrinter class provides a static method for printing messages to the console.

- print(String message): Prints the specified message to the console.
 
