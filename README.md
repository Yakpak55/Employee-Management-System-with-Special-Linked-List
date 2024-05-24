# Employee-Management-System-with-Special-Linked-List
 Developed a Java program to manage employee information using a custom linked list structure. 
 
 The project involved:

● Employee Class: Created the Employee class with private attributes id, name, salary, and numberOfDependents. Implemented getters and setters for each attribute, and a toString method to display employee details in a specific format. The id is calculated as the sum of the ASCII values of the characters in the employee's name.
● Constructor: Added a constructor to initialize name, salary, and numberOfDependents using passed parameters, and compute the id.
● Linked List Structure: Designed a special linked list (company) to store employees, handling collisions by linking employees with the same id in a secondary list.
● Node Class: Implemented the Node class to hold employee information and references to the next and below nodes, allowing the hierarchical structure of the linked list.
● Menu-Driven Operations: Provided a user interface with menu options to print employee names, add new employees, search for employees by name, find the highest net salary, delete employees by name, and exit the program.

Skills: Java · Object-Oriented Design · Algorithm Implementation · Software Development Lifecycle · Command-Line Interface (CLI) · Data Structures