import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Generate Report");
            System.out.println("7. Backup Records");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter GPA: ");
                    double gpa = sc.nextDouble();
                    service.addStudent(new Student(id, name, dept, gpa));
                    break;
                case 2:
                    System.out.print("Enter ID to search: ");
                    id = sc.nextLine();
                    Student s = service.searchStudent(id);
                    System.out.println(s != null ? s : "Student not found.");
                    break;
                case 3:
                    System.out.print("Enter ID to update: ");
                    id = sc.nextLine();
                    System.out.print("Enter New Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter New Department: ");
                    dept = sc.nextLine();
                    System.out.print("Enter New GPA: ");
                    gpa = sc.nextDouble();
                    service.updateStudent(id, name, dept, gpa);
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    id = sc.nextLine();
                    service.deleteStudent(id);
                    break;
                case 5:
                    service.displayAll();
                    break;
                case 6:
                    service.generateReport();
                    break;
                case 7:
                    service.backup();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    sc.close(); // close scanner at the end
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
