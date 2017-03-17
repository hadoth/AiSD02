package bootstrap;

import utils.student.AisdGrade;
import utils.student.Student;
import utils.student.StudentGenerator;
import utils.list.MyList;

/**
 * Created by Karol Pokomeda on 2017-03-14.
 */
public class ExerciseTwoTest {
    public static void main(String[] args){
        int studentNumber = 10;

        MyList<Student> studentList= new MyList<Student>();
        for (int i = 0; i < studentNumber; i++){
            studentList.add(StudentGenerator.randomStudent());
        }

        System.out.println(
                "Liczba studentów: " + studentList.size() + " " +
                "(oczekiwane: " + studentNumber + "); " +
                "wynik testu: " + (studentList.size() == studentNumber) + '\n');

        ExerciseOneTest.showList(studentList);

        ExerciseOneTest.gradeStudent(studentList, 162510, AisdGrade.BARDZO_DOBRY);
        System.out.println("\n" + studentList.get(6));

        ExerciseOneTest.showList(studentList);
        System.out.println();

        Student newStudent = new Student("Wiesław", "Jarząbek");

        ExerciseOneTest.showList(studentList);
        System.out.println();

        System.out.println("Jest Jarząbek? " + studentList.contains(newStudent) + " (" + studentList.indexOf(newStudent) + ")");
        System.out.println(studentList.get(5));
        Student oldStudent = studentList.set(newStudent, 5);
        System.out.println(studentList.get(5));
        System.out.println("Jest Jarząbek? " + studentList.contains(newStudent) + " (" + studentList.indexOf(newStudent) + ")");
        System.out.println(oldStudent);
        System.out.println();


        ExerciseOneTest.showList(studentList);
        System.out.println();

        studentList.clear();
        System.out.println(studentList.isEmpty());
        System.out.println(studentList.size());
        studentList.first();
        System.out.println(studentList.isDone());

    }
}
