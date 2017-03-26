package bootstrap;

import interfaces.IteratorInterface;
import utils.iterators.FilterIterator;
import utils.iterators.ReverseIterator;
import utils.list.StudentList;
import utils.predicates.GradeBetterThanPredicate;
import utils.predicates.GradeWorseThanPredicate;
import utils.predicates.IndexNumberPredicate;
import utils.student.AisdGrade;
import utils.student.Student;
import utils.student.StudentGenerator;
import utils.list.MyList;

/**
 * Created by Karol Pokomeda on 2017-03-14.
 */
public class ListTwoExOneTest {
    public static void main(String[] args){

        // generowanie danych testowych
        int studentNumber = 30;
        MyList<Student> studentList= new MyList<>();
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

        /*
        wyświetl studenta o numerze indeksu 162520
         */
        IndexNumberPredicate indexPredicate = new IndexNumberPredicate(162520);
        FilterIterator<Student> indexIterator = new FilterIterator<Student>(iterableList, indexPredicate);
        ExerciseOneTest.showList(indexIterator);

        System.out.println();

        /*
        wstaw studentowi o numerze indeksu 162520 ocenę celującą
         */
        ExerciseOneTest.gradeStudent(iterableList, 162520, AisdGrade.CELUJACY);
        ExerciseOneTest.showList(indexIterator);

        System.out.println();

        /*
        Wyświetl średnią, studentów poniżej średniej i powyżej średniej
         */
        System.out.format("średnia ocen studentów : %.2f.%n", ExerciseOneTest.meanGrade(iterableList));
        System.out.println("\tpowyżej średniej:");
        ExerciseOneTest.showList(ExerciseOneTest.aboveAverage(iterableList));
        System.out.println("\tponiżej średniej:");
        ExerciseOneTest.showList(ExerciseOneTest.belowAverage(iterableList));

        System.out.println();

        /*
        średnia ocen studentów, którzy zaliczyli
         */
        GradeBetterThanPredicate gradeBetterThan = new GradeBetterThanPredicate(AisdGrade.NIEDOSTATECZNY.getGrade());
        FilterIterator<Student> studentsPassed = new FilterIterator<Student>(iterableList, gradeBetterThan);
        System.out.format("średnia ocen osób które zaliczyły : %.2f.%n", ExerciseOneTest.meanGrade(studentsPassed));

        System.out.println();

        /*
        Lista studentów, którzy nie zaliczyli
         */
        System.out.println("Studenci którzy nie zaliczyli");
        GradeWorseThanPredicate gradeWorseThan = new GradeWorseThanPredicate(AisdGrade.NIEDOSTATECZNY.getGrade());
        FilterIterator<Student> studentsFailed = new FilterIterator<Student>(iterableList, gradeWorseThan);
        ExerciseOneTest.showList(iterableList);

        System.out.println();

        /*
        usunięcie z listy o numerze indeksu 162509
         */
        System.out.println("Lista bez studenta o numerze indeksu 162509");
        IndexNumberPredicate newIndexPredicate = new IndexNumberPredicate(162509);
        FilterIterator<Student> newIndexIterator = new FilterIterator<Student>(iterableList, newIndexPredicate);
        ExerciseOneTest.removeAllObjects(newIndexIterator);
        ExerciseOneTest.showList(iterableList);

        System.out.println();

        /*
        dodanie do listy  studenta na odpowiednim miejscu
         */
        System.out.println("Lista z nowym studentem o numerze indeksu 162509");
        ExerciseOneTest.addStudentInOrder(iterableList, new Student("Grzegorz", "Brzęczyszczykiewicz", 162509));
        ExerciseOneTest.showList(iterableList);

        System.out.println();

        /*
        sortuj według ocen
         */
        System.out.println("Lista posortowana według ocen");
        ReverseIterator<Student> reverseIterator = new ReverseIterator<Student>(iterableList);
        ExerciseOneTest.bubbleSort(reverseIterator);
        ExerciseOneTest.showList(iterableList);

        System.out.println();

        /*
        testuj czyszczenie listy
         */

        studentListManager.clear();
        System.out.println(studentListManager.isEmpty());
        System.out.println(studentListManager.size());
        iterableList.first();
        System.out.println(iterableList.isDone());
    }
}
