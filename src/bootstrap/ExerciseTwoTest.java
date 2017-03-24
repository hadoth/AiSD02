package bootstrap;

import interfaces.IteratorInterface;
import utils.list.StudentList;
import utils.student.AisdGrade;
import utils.student.Student;
import utils.student.StudentGenerator;
import utils.list.MyList;

/**
 * Created by Karol Pokomeda on 2017-03-14.
 */
public class ExerciseTwoTest {
    public static void main(String[] args){

        // generowanie danych testowych
        int studentNumber = 10;
        MyList<Student> studentList= new MyList<Student>();
        for (int i = 0; i < studentNumber; i++){
            studentList.add(StudentGenerator.randomStudent());
        }

        // generowanie iteratora
        StudentList studentListManager = new StudentList(studentList);
        IteratorInterface<Student> iterableList = studentListManager.iterator();

        System.out.println(
                "Liczba studentów: " + studentListManager.size() + " " +
                "(oczekiwane: " + studentNumber + "); " +
                "wynik testu: " + (studentListManager.size() == studentNumber) + '\n');

        studentListManager.showList();

        ExerciseOneTest.gradeStudent(iterableList, 162510, AisdGrade.BARDZO_DOBRY);
        System.out.println("\n" + studentListManager.get(6));

        studentListManager.showList();
        System.out.println();

        Student newStudent = new Student("Wiesław", "Jarząbek");

        studentListManager.showList();
        System.out.println();

        System.out.println("Jest Jarząbek? " + studentListManager.contains(newStudent) + " (" + studentListManager.indexOf(newStudent) + ")");
        System.out.println(studentListManager.get(5));
        Student oldStudent = studentListManager.set(newStudent, 5);
        System.out.println(studentListManager.get(5));
        System.out.println("Jest Jarząbek? " + studentListManager.contains(newStudent) + " (" + studentListManager.indexOf(newStudent) + ")");
        System.out.println(oldStudent);
        System.out.println();


        studentListManager.showList();
        System.out.println();

        studentListManager.clear();
        System.out.println(studentListManager.isEmpty());
        System.out.println(studentListManager.size());
        iterableList.first();
        System.out.println(iterableList.isDone());



    }
}
