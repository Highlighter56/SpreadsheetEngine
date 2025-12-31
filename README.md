# SpreadsheetEngine

A command-driven, text-based spreadsheet application written in Java, featuring persistent storage backed by a relational database.

This project allows users to interact with a spreadsheet-like grid through textual commands, supporting arithmetic operations, string concatenation, bulk cell updates, and persistent data storage across program runs.

## Overview

This project was built to apply concepts from systems programming, parsing, and database-backed persistence in a non-trivial application. It emphasizes clean separation of concerns, correctness, and extensibility, while remaining lightweight and easy to interact with.

The application presents a Swing-based graphical interface where users enter commands to manipulate a grid of cells, similar to a simplified spreadsheet engine.

## Features
- Text-based command interface
- 8×8 cell grid with spreadsheet-style addressing
- Numeric and string cell values
- Bulk operations over cell ranges
- Persistent storage using a relational database
- Graphical desktop window using Java Swing

## Supported Commands

Cells may be referenced using either numeric coordinates (1,1) or spreadsheet-style coordinates such as A1.

### Command Patterns
- set \<cell> to <cell | value>
- set \<cell> through \<cell> to <cell | value>
- add \<cell> and <cell | number>
- sub \<cell> and <cell | number>
- concat \<cell> and <cell | string>
- clear \<cell>
- clear [row]
- clear [column]
- clear

Instead of specifying one cell, all commands have the capability to apply on a range of cells, using the format \<cell> through \<cell>

### Examples
- set (1,1) to hello
- set A1 through C3 to 5
- add (1,1) and (2,2)
- concat B2 and abc
- clear [a]
- clear

## Usage
### Requirements
- Java (JDK 17 or later recommended)
- MySQL (or compatible relational database)
- JDBC driver for MySQL
### Setup Steps
- Clone the repository
- Create the required database and table
- Configure database credentials in a local properties file
- Run the program

Database credentials are loaded from a configuration file that is intentionally excluded from version control. An example configuration file is provided to illustrate the expected format.

## Technical Highlights & Design Notes
<ins>DAO Pattern</ins> - Database access is isolated in a CellDAO class, separating persistence logic from application logic.

<ins>JDBC Integration</ins> - The project uses JDBC with prepared statements for safe and structured database access.

<ins>Dirty Cell Optimization</ins> - Only modified (“dirty”) cells are written back to the database, minimizing unnecessary database writes.

<ins>Swing-Based UI</ins> - Java Swing is used to provide a standalone desktop window for interaction rather than relying on terminal-only input.

<ins>Command Parsing Engine</ins> - User input is parsed and validated before execution, allowing expressive and flexible commands.

<ins>Extensible Architecture</ins> - The design allows for future enhancements such as function support, undo operations, and alternative persistence backends.

## Future Plans
- Add support for cell functions (formulas)
- Improve error reporting within the GUI instead of the terminal
- Implement undo functionality
- Add command history navigation
- Provide an integrated help command
- Package the application as a standalone desktop executable
- Migrate to an embedded database (SQLite) to remove external dependencies
