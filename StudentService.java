import java.io.*;
import java.util.*;

public class StudentService {
    private static final String OBJECT_FILE = "data/students.obj";
    private List<Student> students = new ArrayList<>();

    public StudentService() {
        FileManager.checkFile(OBJECT_FILE);
        loadStudents();
    }

    @SuppressWarnings("unchecked")
    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OBJECT_FILE))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OBJECT_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public void addStudent(Student s) {
        students.add(s);
        saveStudents();
        System.out.println("Student added successfully.");
    }

    public Student searchStudent(String id) {
        return students.stream().filter(s -> s.getStudentId().equals(id)).findFirst().orElse(null);
    }

    public void updateStudent(String id, String name, String dept, double gpa) {
        Student s = searchStudent(id);
        if (s != null) {
            s.setName(name);
            s.setDepartment(dept);
            s.setGpa(gpa);
            saveStudents();
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent(String id) {
        Student s = searchStudent(id);
        if (s != null) {
            students.remove(s);
            saveStudents();
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            // FIX: use lambda instead of ambiguous method reference
            students.forEach(s -> System.out.println(s));
        }
    }

    public void generateReport() {
        if (students.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }
        int total = students.size();
        double highest = students.stream().mapToDouble(Student::getGpa).max().orElse(0);
        double lowest = students.stream().mapToDouble(Student::getGpa).min().orElse(0);
        double average = students.stream().mapToDouble(Student::getGpa).average().orElse(0);

        System.out.println("Total Students: " + total);
        System.out.println("Highest GPA: " + highest);
        System.out.println("Lowest GPA: " + lowest);
        System.out.println("Average GPA: " + average);
    }

    public void backup() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(OBJECT_FILE));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("data/backup.dat"))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            System.out.println("Backup created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating backup: " + e.getMessage());
        }
    }
}
