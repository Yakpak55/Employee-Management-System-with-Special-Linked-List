// Alejandro Ortega, Joshua Sanchez, Christopher Deluigi 
// HW6 
//12-1-2023

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Company company = new Company();

        while (true) {
            System.out.println("\nMenu:\n1) Print all the employees (Just the names)\n2) Add a new employee\n3) Search an employee by name\n4) Find the highest net salary\n5) Delete an employee by name:\n6) Exit program");
            System.out.print("Enter your choice (1-6): ");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    company.printAllEmployees();
                    break;
                case 2:
                    System.out.print("Enter the name of the employee: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the gross salary of the employee: ");
                    double grossSalary = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter the number of dependents of the employee: ");
                    int numOfDependents = scanner.nextInt();
                    scanner.nextLine();

                    Employee employee = new Employee(name, grossSalary, numOfDependents);
                    company.addEmployee(employee);

                    System.out.println("Employee added successfully.");
                    break;
                case 3:
                    System.out.print("Enter the name of the employee: ");
                    name = scanner.nextLine();
                    Node node = company.searchEmployee(name);

                    if (node != null) {
                        System.out.println("Found: " + node.getEmployee().getName());
                    } else {
                        System.out.println("Not Found.");
                    }
                    break;
                case 4:
                    company.findHighestNetSalary();
                    break;
                case 5:
                    System.out.print("Enter the name of the employee to delete: ");
                    name = scanner.nextLine();

                    boolean deleted = company.removeEmployee(name);
                    if (deleted) {
                        System.out.println("Employee deleted successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using our app!");
                    System.exit(0);
                default:
                    System.out.println("Try again.");
            }
        }
    }
}

class Employee {
    private int id;
    private String name;
    private double salary;
    private int numberOfDependents;

    public Employee(String name, double salary, int numberOfDependents) {
        this.name = name;
        this.salary = salary;
        this.numberOfDependents = numberOfDependents;
        this.id = getId();
    }

    public int getId() {
        int asciiSum = 0;
        for (int i = 0; i < name.length(); i++) {
            asciiSum += (int) name.charAt(i);
        }
        return asciiSum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(int numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public double getNetSalary() {
        return salary * 0.91 + numberOfDependents * 0.01 * salary;
    }

    @Override
    public String toString() {
        return "[" + id + "," + name + "," + getNetSalary() + "]";
    }
}

class Node {
    private Employee employee;
    public Node next;
    public Node below;

    public Node(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

class Company {
    private Node head;
    private Node tail;

    public Company() {
        head = null;
        tail = null;
    }

    public void addEmployee(Employee employee) {
        if (head == null) {
            head = new Node(employee);
            tail = head;
        } else {
            Node currentNode = head;
            while (currentNode.below != null) {
                currentNode = currentNode.below;
            }
            while (currentNode.next != null && currentNode.next.getEmployee().getName().compareTo(employee.getName()) <= 0) {
                currentNode = currentNode.next;
            }
            if (currentNode.getEmployee().getName().equals(employee.getName())) {
                Node newNode = new Node(employee);
                newNode.below = currentNode.below;
                currentNode.below = newNode;
            } else if (currentNode == head && currentNode.getEmployee().getName().compareTo(employee.getName()) > 0) {
                Node newNode = new Node(employee);
                newNode.next = currentNode;
                head = newNode;
            } else {
                Node newNode = new Node(employee);
                newNode.next = currentNode.next;
                currentNode.next = newNode;
            }
        }
    }

    public boolean removeEmployee(String name) {
        Node currentNode = head;
        Node previousNode = null;
        while (currentNode != null && !currentNode.getEmployee().getName().equals(name)) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        if (currentNode != null) {
            if (previousNode == null) {
                head = currentNode.next;
            } else {
                previousNode.next = currentNode.next;
            }
            return true;
        } else {
            return false;
        }
    }

    public Node searchEmployee(String name) {
        Node currentNode = head;
        while (currentNode != null && !currentNode.getEmployee().getName().equals(name)) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public void printAllEmployees() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.getEmployee().getName());
            if (currentNode.below != null) {
                Node belowNode = currentNode.below;
                while (belowNode != null) {
                    System.out.println(belowNode.getEmployee().getName());
                    belowNode = belowNode.next;
                }
            }
            currentNode = currentNode.next;
        }
    }



    public void findHighestNetSalary() {
        double maxNetSalary = 0;
        String maxNetSalaryName = "";
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.getEmployee().getNetSalary() > maxNetSalary) {
                maxNetSalary = currentNode.getEmployee().getNetSalary();
                maxNetSalaryName = currentNode.getEmployee().getName();
            }
            if (currentNode.below != null) {
                Node belowNode = currentNode.below;
                while (belowNode != null) {
                    if (belowNode.getEmployee().getNetSalary() > maxNetSalary) {
                        maxNetSalary = belowNode.getEmployee().getNetSalary();
                        maxNetSalaryName = belowNode.getEmployee().getName();
                    }
                    belowNode = belowNode.next;
                }
            }
            currentNode = currentNode.next;
        }
        System.out.println("Employee with the highest net salary: " + maxNetSalaryName + " ($" + maxNetSalary + ")");
    }
}
   