# Chess Scenario Generator
## About the Project
Welcome to the "Chess Scenario Generator", a Java application designed to experiment with various chess piece movement
scenarios and showcase their threatening capabilities. Our current focus is on the Knight chess piece, but with a view
to extend the project to accommodate all pieces and different board sizes.

The project aims to identify all possible positions of two opposing knights on a standard 8x8 chessboard such that they
threaten each other. It displays a simple visual representation of the chessboard and the corresponding positions of the
knights (see debug output).
Emphasis has been placed on ensuring that the code is easy to read and maintain. Maven is the build tool we chose.

We didn't incorporate a GUI, as our main focus is on the console output.
As the chessboard is modeled as an object, any changes relating to board size or additional chess pieces can be made,
indicating extensibility and flexibility of the project.

The algorithm generates various threat scenarios on a chessboard, showcasing the threatening
capabilities of different chess pieces. The threat scenarios are produced based on specific chess piece strategies and
are presented visually using a logging framework.

### Features
- Initializes an empty chessboard of a designated size.
- Registers whether a specific location fits within the boundaries of the board.
- Computes the number of threat positions on a particular chessboard to gauge risk levels.
- Formulates all possible threat scenarios based on a defined chess piece, persisting these scenarios for future
  reference.
- Provides an output of the current state of the chessboard

#### Currently, the chess pieces supported in the simulator include the Knight and the Rook only. An extension is kept in mind when developing this application
## Requirements
This is a Maven project, which means that it requires Maven for building, and JDK 17 for running. Dependencies such as
Project Lombok are managed using the Maven build tool.

## Building and Running The Project
Extract and import the project, then build and run the application using provided Maven tool inside IntelliJ IDEA or use
the following commands on the terminal:

#### To build the application
```
mvn clean install
```

#### To run the application
```
mvn exec:java -Dexec.mainClass=net.mirillas.chess.Main
```

### Running Chess Simulator (manually)
To initiate the Chess Simulator, run the following code resp. the Main Class:

```java
public class Main {
    public static void main(String[] args) {
        ChessSimulator chessSimulator = new ChessSimulator();
        chessSimulator.runSimulation();
    }
}
```

This will initiate a threat scenario for a Knight on a 8x8 chessboard by default. You can effortlessly replace the Rook
with other ChessPiece instances, such as the Knight, to generate different scenarios with various chess pieces.

- NOTE: set the loglevel in `resources/logback.xml` to `debug` to see log messages for every chess scenario.

### Testing
This project includes a suite of unit tests to guarantee the accuracy of the implemented strategies. These tests have
been built using the JUnit5 framework and can be executed using the test command:

```
mvn test
```
### Future Improvements

Several improvements are planned for future releases, including:

- Extension of available chess pieces to comprise Bishop, Queen, King, and Pawn, complete with their respective
  threatening strategies.
- Calculation of potential moves based on the current board state.
- Improvement of the rendering of the chessboard for enhanced visualization.
- Introduction of multiplayer scenarios on a single board.

## Detailed Work Log
Below is a breakdown of how time was spent during the development of this project:

- 3 hours: Test Driven Development for proof-of-concept with implementation of an additional Rook piece to better design
  and structure the code base.
- 14 hours: Implementing interfaces, creating and disregarding strategies, logging, refactoring, unit-tests.
- 2 hours: Creating a CLI for the app.
- 2 hours: Integration testing and documentation, UX and rudimentary input validation.
- 3 hours: clean-up.

## Bill of Materials
- net.mirillas.chess:chess-scenario-generator:jar:1.0-SNAPSHOT
    - org.projectlombok:lombok:jar:1.18.32
    - org.slf4j:slf4j-api:jar:2.0.13
    - ch.qos.logback:logback-classic:jar:1.5.6
        - ch.qos.logback:logback-core:jar:1.5.6
    - org.assertj:assertj-core:jar:3.26.0
        - net.bytebuddy:byte-buddy:jar:1.14.16
    - org.junit.jupiter:junit-jupiter-engine:jar:5.9.1
        - org.opentest4j:opentest4j:jar:1.2.0
        - org.junit.platform:junit-platform-commons:jar:1.9.1
        - org.apiguardian:apiguardian-api:jar:1.1.2:test
    - org.mockito:mockito-core:jar:5.12.0
        - net.bytebuddy:byte-buddy-agent:jar:1.14.15
        - org.objenesis:objenesis:jar:3.3

## License
This project is licensed under the GNU General Public License v3.0 - see
the [license](https://www.gnu.org/licenses/gpl-3.0.en.html) file for details.
