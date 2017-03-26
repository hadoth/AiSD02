package bootstrap;

import utils.list.AddListDouble;
import utils.student.Student;
import utils.student.StudentGenerator;

/**
 * Created by Karol Pokomeda on 2017-03-25.
 */
public class ListTwoExTwoTest {
    public static void main(String[] args) {

        AddListDouble<Student> groupOne = new AddListDouble<>();
        AddListDouble<Student> groupTwo = new AddListDouble<>();
        AddListDouble<Student> groupThree = new AddListDouble<>();
        AddListDouble<Student> groupFour = new AddListDouble<>();

        for (int i = 0; i < 5; i++) {
            groupOne.add(StudentGenerator.randomStudent());
        }

        Student randomStudent = StudentGenerator.randomStudent();
        groupOne.add(randomStudent);

        for (int i = 0; i < 5; i++) {
            groupOne.add(StudentGenerator.randomStudent());
            groupTwo.add(StudentGenerator.randomStudent());
            groupThree.add(StudentGenerator.randomStudent());
            groupFour.add(StudentGenerator.randomStudent());
        }
        System.out.println("\nGroup one");
        ExerciseOneTest.showList(groupOne.iterator());
        System.out.println("\nGroup two");
        ExerciseOneTest.showList(groupTwo.iterator());
        System.out.println("\nGroup three");
        ExerciseOneTest.showList(groupThree.iterator());
        System.out.println("\nGroup four");
        ExerciseOneTest.showList(groupFour.iterator());

        groupOne.add(groupTwo);

        System.out.println("\nGroup one with two at the end");
        ExerciseOneTest.showList(groupOne.iterator());

        groupOne.insert(groupThree,3);

        System.out.println("\nGroup one with group three at index 3");
        ExerciseOneTest.showList(groupOne.iterator());

        groupOne.insertBefore(groupFour, randomStudent);

        System.out.println("\nGroup one with group four before student " + randomStudent.toString());
        ExerciseOneTest.showList(groupOne.iterator());
    }
}
