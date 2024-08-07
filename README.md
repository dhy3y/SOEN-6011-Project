# SOEN-6011-Project
 
## Project Overview
This project implements the tangent function in Java from scratch using the Maclaurin series. It includes a graphical user interface (GUI) created with JavaFX, adheres to established Java programming style, and ensures code quality through various tools. The project also emphasizes unit testing, version control, and accessibility.

## Objective
To implement the tangent function without using built-in or library functions related to the tangent function. The project includes:

- Creating a user-friendly GUI using JavaFX.
- Conforming to Java programming standards using Checkstyle.
- Debugging
- Ensuring code quality with SonarQube.
- Validating functionality through JUnit testing.
- Using semantic versioning.
- Designing an accessible interface.

## Setup and Installation

1. Clone the Repository
```git
git clone https://github.com/dhy3y/SOEN-6011-Project
cd tangent-function-java
```

2.Build the Project
```
javac TanFuncDeliverable3.java
javac GUIStarter.java
```

3.Run the Application
```
java GUIStarter.java
```

## Usage
- Launch the application.
- Input the value for which you want to calculate the tangent.
- Click the "Calculate" button to see the result.

## Code Quality and Testing
Checkstyle
```
mvn checkstyle:check
```

Static Code Analysis
```
mvn sonar:sonar
```

## Versioning
This project uses Semantic Versioning. For the versions available, see the tags on this repository.

## Conclusion
This project demonstrates a comprehensive approach to implementing a mathematical function in Java, with an emphasis on code quality, user interface design, and accessibility. The use of tools like Checkstyle, GDB, SonarQube, and JUnit ensures that the project meets high standards of programming practice and user experience.

