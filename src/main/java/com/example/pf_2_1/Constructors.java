/*
Create the student class, as well as the attributes a student has (Name, StudentID)
*/

class Student {
    private String name;
    private int studentId;

    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }
}

/*
create the supervisor class, as well as the attributes a supervisor has (name)
*/

class Supervisor {
    private String name;

    public Supervisor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/*
create the projectgroup class and it's attributes (Projectname, students in the group, supervisor)
as well as conditions such as max members
*/
class ProjectGroup {
    private String name;
    private Student[] students;
    private int studentCount;
    private Supervisor supervisor;

    public ProjectGroup(String name, Supervisor supervisor) {
        this.name = name;
        this.supervisor = supervisor;
        this.students = new Student[6];
        this.studentCount = 0;
    }

    public boolean addStudent(Student student) {
        if (studentCount >= 6) {
            return false;
        }

        students[studentCount] = student;
        studentCount++;
        return true;
    }

    public String getName() {
        return name;
    }

    public void printGroup() {
        System.out.println("Project Group: " + name);
        System.out.println("Supervisor: " + supervisor.getName());
        System.out.println("Students:");

        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].getName());
        }
    }
}


class ProjectManager {
    private Student[] unassignedStudents;
    private int unassignedCount;

    public ProjectManager(int capacity) {
        unassignedStudents = new Student[capacity];
        unassignedCount = 0;
    }

    public void registerStudent(Student student) {
        if (unassignedCount < unassignedStudents.length) {
            unassignedStudents[unassignedCount] = student;
            unassignedCount++;
        } else {
            System.out.println("No more space for students.");
        }
    }

    public boolean assignStudent(Student student, ProjectGroup group) {
        // Find the student in the unassigned array
        for (int i = 0; i < unassignedCount; i++) {
            if (unassignedStudents[i] == student) {

                // Add student to the project group
                if (!group.addStudent(student)) {
                    System.out.println("Project group is full.");
                    return false;
                }

                // Remove student from the unassigned array
                for (int j = i; j < unassignedCount - 1; j++) {
                    unassignedStudents[j] = unassignedStudents[j + 1];
                }

                unassignedStudents[unassignedCount - 1] = null;
                unassignedCount--;

                return true;
            }
        }

        System.out.println("Student is not unassigned.");
        return false;
    }

    public void printUnassignedStudents() {
        System.out.println("Unassigned students:");

        for (int i = 0; i < unassignedCount; i++) {
            System.out.println(unassignedStudents[i].getName());
        }
    }
}


public class Main {
    public static void main(String[] args) {

        // Create the manager with room for 20 unassigned students
        ProjectManager manager = new ProjectManager(20);

        // Create students independently
        Student alice = new Student("Alice", 1001);
        Student bob = new Student("Bob", 1002);
        Student charlie = new Student("Charlie", 1003);
        Student david = new Student("David", 1004);
        Student daniel = new Student("Daniel", 1004);

        // Register students as unassigned
        manager.registerStudent(alice);
        manager.registerStudent(bob);
        manager.registerStudent(charlie);
        manager.registerStudent(david);

        manager.printUnassignedStudents();

        // Create project groups
        ProjectGroup group001 =
                new ProjectGroup("Air quality", new Supervisor("Shabab"));

        ProjectGroup group002 =
                new ProjectGroup("Water quality", new Supervisor("Mark"));

        // Assign students to groups
        manager.assignStudent(alice, group001);
        manager.assignStudent(bob, group001);
        manager.assignStudent(charlie, group002);

        System.out.println();

        // Display the results
        manager.printUnassignedStudents();

        System.out.println();

        group001.printGroup();

        System.out.println();

        group002.printGroup();
    }
}