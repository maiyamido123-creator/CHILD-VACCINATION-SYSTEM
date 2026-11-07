# System Documentation: Sample CRUD App

## 1. Introduction

This document provides a comprehensive overview of the Sample CRUD App, a simple task management application. The application allows users to create, read, update, and delete tasks. It is a desktop application built using Java Swing.

The primary purpose of this application is to demonstrate a basic implementation of the Model-View-Controller (MVC) architectural pattern and data persistence through serialization.

## 2. Features

*   **Create Task:** Add a new task with a description and a deadline.
*   **View Tasks:** View the list of all tasks with their details, including description, deadline, creation date, and status.
*   **Update Task:** Modify the description, deadline, and status of an existing task.
*   **Delete Task:** Remove a task from the list.
*   **Data Persistence:** All tasks are saved to a local file (`taskslist.ser`) and are reloaded when the application starts.

## 3. Application Architecture

The application follows the **Model-View-Controller (MVC)** design pattern to separate the application's concerns into three interconnected components.

*   **Model:** The Model represents the application's data and business logic. In this project, the Model consists of:
    *   `Task.java`: A simple Java object representing a single task.
    *   `TaskManager.java`: Manages the collection of tasks and handles data persistence (saving and loading tasks from a file).

*   **View:** The View is the user interface (UI) of the application. It displays the data from the Model to the user and sends user actions to the Controller. The View in this project is:
    *   `MainForm.java`: The main window of the application, built with Java Swing. It contains the form elements for user interaction.

*   **Controller:** The Controller acts as an intermediary between the Model and the View. It handles user input from the View, processes it (with the help of the Model), and updates the View accordingly. The Controller in this project is:
    *   `TaskController.java`: Contains the logic to handle user actions like adding, deleting, and updating tasks.

## 4. How to Build and Run

This project is a standard NetBeans project.

### Prerequisites

*   Java Development Kit (JDK) 8 or later
*   Apache NetBeans IDE

### Building the Project

1.  Open the project in NetBeans.
2.  Clean and build the project by clicking on **Run > Clean and Build Project (Shift + F11)**.
3.  This will compile the Java source files and create the necessary build artifacts in the `build` directory.

### Running the Project

1.  Once the project is built, run the application by clicking on **Run > Run Project (F6)**.
2.  This will start the application and display the main window.

## 5. File Descriptions

Here is a breakdown of the important files in the project:

*   `src/model/Task.java`: The data model for a single task. It's a serializable Java object with properties like `taskId`, `description`, `deadline`, `dateCreated`, and `status`.

*   `src/model/TaskManager.java`: Manages a `HashMap` of tasks. It's responsible for adding, deleting, finding, and updating tasks. It also handles saving and loading the tasks to/from the `taskslist.ser` file.

*   `src/controller/TaskController.java`: The controller that connects the UI (View) with the data (Model). It contains the application's logic for handling user actions.

*   `src/ui/Main.java`: The main entry point of the application. It initializes the Model, View, and Controller, and launches the application.

*   `src/ui/MainForm.java`: The main UI window of the application, created using the NetBeans GUI Builder.

*   `src/ui/TaskTableModel.java`: A custom `TableModel` for displaying the tasks in a `JTable` in the `MainForm`.

*   `src/util/IdGenerator.java`: A utility class for generating unique IDs for new tasks.

*   `taskslist.ser`: The file where the application's data (the list of tasks) is stored. This file is created in the root directory of the project.

## 6. How it Works (Code Logic)

### Application Startup

1.  The `main` method in `Main.java` is the entry point.
2.  It sets the application's look and feel to the native system's look and feel.
3.  It creates an instance of `TaskManager` (the Model).
4.  `taskManager.loadTasks()` is called to deserialize and load the list of tasks from `taskslist.ser` into the `tasks` `HashMap`.
5.  It creates an instance of `TaskController`, injecting the `TaskManager` instance.
6.  It creates an instance of `MainForm` (the View), injecting the `TaskController` instance.
7.  A window listener is added to the `MainForm` to call `taskManager.saveTasks()` when the application is closed. This ensures that any changes are saved.

### Adding a Task

1.  The user enters a task description and deadline in the `MainForm` and clicks the "Add" button.
2.  The `actionPerformed` event handler for the "Add" button in `MainForm.java` calls the `controller.handleAddTask()` method.
3.  `handleAddTask()` in `TaskController` validates the input.
4.  A new `Task` object is created.
5.  The `taskManager.addTask()` method is called to add the new task to the `HashMap`. This method also generates a unique ID for the task.
6.  `taskManager.saveTasks()` is called to serialize the updated `HashMap` and save it to `taskslist.ser`.
7.  The `controller` then calls `view.refreshTaskList()` to update the `JTable` in the UI with the new data.

### Deleting a Task

1.  The user selects a task in the `JTable` and clicks the "Delete" button.
2.  The `actionPerformed` event handler for the "Delete" button calls `controller.handleDeleteTask()`, passing the ID of the selected task.
3.  `handleDeleteTask()` in `TaskController` calls `taskManager.deleteTask()` to remove the task from the `HashMap`.
4.  `taskManager.saveTasks()` is called to persist the change.
5.  `view.refreshTaskList()` is called to update the UI.

### Updating a Task

1.  The user selects a task, which populates the input fields with the task's details.
2.  The user modifies the details and clicks the "Update" button.
3.  The `actionPerformed` event handler for the "Update" button calls `controller.handleUpdateTask()`.
4.  `handleUpdateTask()` in `TaskController` finds the corresponding `Task` object in the `HashMap` using `taskManager.findTask()`.
5.  The properties of the `Task` object are updated.
6.  `taskManager.saveTasks()` is called.
7.  `view.refreshTaskList()` is called to update the UI.
