APCS FINAL PROJECT – Student Management System

📌 Overview
This project is a Java-based Student Management System developed for the APCS Semester 1 Final Project. The application demonstrates object-oriented programming (OOP), graphical user interface (GUI) development, CRUD operations, JSON data persistence, and version control using GitHub.

The system allows a user to log in, access a dashboard, and manage student records through a simple and user-friendly interface.

---

🎯 Project Goals
The main goals of this project are:

- Create a Java application with a GUI.
- Implement a login system.
- Implement CRUD operations (Create, Read, Update, Delete).
- Store student data in a JSON file.
- Use an external library (Gson).
- Apply OOP principles.
- Upload and manage the project using GitHub.

---

🔑 Login Information
To access the system, use the following credentials:

Username: `admin`  
Password: `admin`

---

🖥️ Features

1️⃣ Login System
- Secure login screen.
- Validates username and password.
- Displays error message for incorrect credentials.

2️⃣ Dashboard
- Displays number of saved students.
- Provides navigation to CRUD system.
- Includes Save JSON button.
- Includes Logout button.

3️⃣ CRUD System
The system supports the following operations:

- Create – Add a new student (ID and Name).
- Read – Search for a student by ID.
- Update – Modify a student's name.
- Delete – Remove a student by ID.

4️⃣ JSON Persistence
- Student data is saved in `students.json`.
- Data remains saved even after closing the program.
- Uses the Gson library for JSON conversion.

---

🧠 Object-Oriented Design
The project uses multiple classes for clean structure:

- `Main.java` – Controls application flow and GUI.
- `Student.java` – Represents student data.
- `AuthService.java` – Handles login validation.
- `DataStore.java` – Handles saving and loading JSON data.

This modular structure improves readability and organization.

---

🛠️ Technologies Used

- Java
- Swing (GUI)
- Gson (External JSON library)
- GitHub (Version control)
- Visual Studio Code

---

📂 Project Structure

APCS-FINAL-PROJECT
│
├── lib
│ └── gson-2.10.1.jar
│
├── src/
│ ├── Main.java
│ ├── Student.java
│ ├── DataStore.java
│ └── AuthService.java
│
└── students.json



---

▶️ How to Run the Project

Step 1: Clone the repository

-git clone https://github.com/zxlyaxly/APCS-FINAL-PROJECT.git

Step 2: Open in VS Code

-Open the project folder in Visual Studio Code.

Step 3: Make sure Gson is inside lib

-lib/gson-2.10.1.jar

Step 4: Add VS Code library configuration

-Create .vscode/settings.json and add:

{
  "java.project.referencedLibraries": [
    "lib/**/*,jar"
  ]
}

Step 5: Run the application

Right-click Main.java

Click Run Java

Step 5: Run the application

Right-click Main.java

Click Run Java

🧪 Example Usage

Create Student

- Enter ID: 101

- Enter Name: Ali

- Click Create

Read Student

- Enter ID: 101

- Click Read

Update Student

- Enter ID: 101

- Change name

- Click Update

Delete Student

- Enter ID: 101

- Click Delete

Save Data

- Click Save JSON

- Data is written to students.json

⚠️ Validation Rules

. ID must be a number.
. Name cannot be empty.
. Duplicate IDs are not allowed.
. Error messages are shown instead of crashing.

📈 Future Improvements

. Add more student fields (age, grade, phone).
. Add multiple user accounts.
. Improve GUI design.
. Add encryption for login.
. Add search filters and sorting.

👨‍💻 Author

Name: Zyed Radwan Al Ashram
Grade: 11
Course: APCS
Year: 2025–2026




