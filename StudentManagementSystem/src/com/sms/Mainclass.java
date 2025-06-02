package com.sms;

import java.util.Scanner;

public class Mainclass {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.setAllStudents(FileHandler.loadFromFile());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                scanner.nextLine(); // clear invalid input
                continue;
            }

            switch (choice) {
                case 1: {
                    int id;
                    while (true) {
                        System.out.print("Enter ID: ");
                        if (scanner.hasNextInt()) {
                            id = scanner.nextInt();
                            scanner.nextLine();

                            if (manager.exists(id)) {
                                System.out.println("ID already exists! Please enter a unique ID.");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Invalid input! Please enter a valid integer ID.");
                            scanner.nextLine(); // clear invalid input
                        }
                    }

                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    manager.addStudent(new Student(id, name, email));
                    FileHandler.saveToFile(manager.getAllStudents());
                    System.out.println("Student added successfully!");
                    break;
                }

                case 2:
                    manager.viewAllStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    if (scanner.hasNextInt()) {
                        int searchId = scanner.nextInt();
                        scanner.nextLine();

                        Student found = manager.searchById(searchId);
                        System.out.println(found != null ? found : "Student not found");
                    } else {
                        System.out.println("Invalid ID input.");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    if (scanner.hasNextInt()) {
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();

                        boolean deleted = manager.deleteById(deleteId);
                        if (deleted) {
                            FileHandler.saveToFile(manager.getAllStudents());
                            System.out.println("Deleted successfully.");
                        } else {
                            System.out.println("Student not found!");
                        }
                    } else {
                        System.out.println("Invalid ID input.");
                        scanner.nextLine();
                    }
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    FileHandler.saveToFile(manager.getAllStudents());
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select between 1 and 5.");
            }
        }
    }
}
