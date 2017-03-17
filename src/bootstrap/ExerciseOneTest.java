package bootstrap;

import utils.iterators.FilterIterator;
import utils.iterators.MyTableIterator;
import utils.iterators.ReverseIterator;
import utils.predicates.GradeBetterThanPredicate;
import utils.predicates.GradeWorseThanPredicate;
import utils.predicates.IndexNumberGreaterThanPredicate;
import utils.predicates.IndexNumberPredicate;
import utils.student.AisdGrade;
import interfaces.IteratorInterface;
import utils.student.Student;
import utils.student.StudentGenerator;


/**
 * Created by Karol Pokomeda on 2017-03-08.
 * Executable class for realisation of goals described in the exercise list number 1 for AiSD
 */
public class ExerciseOneTest {
    public static void main(String[] args){
        /*
        generuj listę studentów
         */
        Student[] students = StudentGenerator.randomStudents(30);
        MyTableIterator<Student> studentList = new MyTableIterator<Student>(students);

        /*
        wyświetl wszystkich studentów
         */
        showList(studentList);

        System.out.println();

        /*
        wyświetl studenta o numerze indeksu 162520
         */
        IndexNumberPredicate indexPredicate = new IndexNumberPredicate(162520);
        FilterIterator<Student> indexIterator = new FilterIterator<Student>(studentList, indexPredicate);
        showList(indexIterator);

        System.out.println();

        /*
        wstaw studentowi o numerze indeksu 162520 ocenę celującą
         */
        gradeStudent(studentList, 162520, AisdGrade.CELUJACY);
        showList(indexIterator);

        System.out.println();

        /*
        Wyświetl średnią, studentów poniżej średniej i powyżej średniej
         */
        System.out.format("średnia ocen studentów : %.2f.%n", meanGrade(studentList));
        System.out.println("\tpowyżej średniej:");
        showList(aboveAverage(studentList));
        System.out.println("\tponiżej średniej:");
        showList(belowAverage(studentList));

        System.out.println();

        /*
        średnia ocen studentów, którzy zaliczyli
         */
        GradeBetterThanPredicate gradeBetterThan = new GradeBetterThanPredicate(AisdGrade.NIEDOSTATECZNY.getGrade());
        FilterIterator<Student> studentsPassed = new FilterIterator<Student>(studentList, gradeBetterThan);
        System.out.format("średnia ocen osób które zaliczyły : %.2f.%n", meanGrade(studentsPassed));

        System.out.println();

        /*
        Lista studentów, którzy nie zaliczyli
         */
        System.out.println("Studenci którzy nie zaliczyli");
        GradeWorseThanPredicate gradeWorseThan = new GradeWorseThanPredicate(AisdGrade.NIEDOSTATECZNY.getGrade());
        FilterIterator<Student> studentsFailed = new FilterIterator<Student>(studentList, gradeWorseThan);
        showList(studentsFailed);

        System.out.println();

        /*
        usunięcie z listy o numerze indeksu 162509
         */
        System.out.println("Lista bez studenta o numerze indeksu 162509");
        IndexNumberPredicate newIndexPredicate = new IndexNumberPredicate(162509);
        FilterIterator<Student> newIndexIterator = new FilterIterator<Student>(studentList, newIndexPredicate);
        removeAllObjects(newIndexIterator);
        showList(studentList);

        System.out.println();

        /*
        dodanie do listy  studenta na odpowiednim miejscu
         */
        System.out.println("Lista z nowym studentem o numerze indeksu 162509");
        addStudentInOrder(studentList, new Student("Grzegorz", "Brzęczyszczykiewicz", 162509));
        showList(studentList);

        System.out.println();

        /*
        sortuj według ocen
         */
        System.out.println("Lista posortowana według ocen");
        ReverseIterator<Student> reverseIterator = new ReverseIterator<Student>(studentList);
        bubbleSort(reverseIterator);
        showList(studentList);
    }

    /**
     * Static methods which iterates through the data structure and prints data in console
     * @param {IteratorInterface} iterator
     */
    public static void showList(IteratorInterface iterator){
        iterator.first();
        while(!iterator.isDone()){
            System.out.println(iterator.current());
            iterator.next();
        }
    }

    /**
     * Static method which calculates arithmetic mean of grades of Students in data structure
     * @param {IteratorInterface<Student>} studentList
     * @return arithmetic mean of grades
     */
    public static double meanGrade(IteratorInterface<Student> studentList) {
        double sum = 0.0;
        int counter = 0;
        studentList.first();
        while (!studentList.isDone()) {
            sum += studentList.current().getGrade();
            counter++;
            studentList.next();
        }
        return sum / counter;
    }

    /**
     * Static method which remove all iterable objects from data structure
     * @param {IteratorInterface} iterator with data structure
     */
    public static void removeAllObjects(IteratorInterface iterator) {
        iterator.first();
        while(!iterator.isDone()) iterator.deleteCurrent();
    }

    /**
     * Static method which adds new Student object after the first object with smaller index number
     * @param {IteratorInterface<Student>}studentList data structure containing Student objects
     * @param {Student }newStudent to be added to data structure
     */
    public static void addStudentInOrder(IteratorInterface<Student> studentList, Student newStudent){
        IndexNumberGreaterThanPredicate newIndexPredicate = new IndexNumberGreaterThanPredicate(newStudent.getIndexNumber());
        FilterIterator<Student> studentsWithBiggerIndex = new FilterIterator<Student>(studentList, newIndexPredicate);
        studentsWithBiggerIndex.first();
        studentList.addCurrent(newStudent);
    }

    /**
     * Static method which sorts data structure of Student objects, with ascending AiSD grades
     * @param {IteratorInterface<Student>} data structure of students which will be sorted
     */
    public static void bubbleSort(IteratorInterface<Student> studentList){
        boolean notSorted;
        do {
            notSorted = false;
            studentList.first();
            Student a = studentList.current();
            studentList.next();
            while (!studentList.isDone()){
                Student b =studentList.current();
                if (b.getGrade() < a.getGrade()){
                    studentList.previous();
                    studentList.deleteCurrent();
                    studentList.addNext(a);
                    notSorted = true;
                } else {
                    studentList.next();
                }
                a = b;
            }
        } while (notSorted);
    }

    /**
     * Method searches list for student with specified indexNumber and sets his grade to provided value
     * @param {IteratorInterface<Student>} studentList
     * @param {int} indexNumber
     * @param {AisdGrade} grade
     */
    public static void gradeStudent(IteratorInterface<Student> studentList, int indexNumber, AisdGrade grade){
        IndexNumberPredicate givenIndex = new IndexNumberPredicate(indexNumber);
        FilterIterator<Student> givenStudent = new FilterIterator<Student>(studentList, givenIndex);
        givenStudent.first();
        givenStudent.current().setGrade(grade);
    }

    /**
     * Method calculates mean grade for given list of students and returns list of students
     * with grades better than the average value
     * While calculating the average, the method does not take into account students which have not been assigned a grade
     * @param {IteratorInterface<Student>} studentList
     * @return {IteratorInterface<Student>} list of student with grade better than the average
     */
    public static IteratorInterface<Student> aboveAverage(IteratorInterface<Student> studentList){
        double meanGrade = meanGrade(
                new FilterIterator<Student>(
                        studentList,
                        new GradeBetterThanPredicate(AisdGrade.NIEOCENIONY.getGrade())));
        GradeBetterThanPredicate gradeBetter = new GradeBetterThanPredicate(meanGrade);
        return new FilterIterator<Student>(studentList, gradeBetter);
    }

    /**
     * Method calculates mean grade for given list of students and returns list of students
     * with grades worse than or equal to the average value
     * @param {IteratorInterface<Student>} studentList
     * @return {IteratorInterface<Student>} list of student with grade worse than or equal to the average
     */
    public static IteratorInterface<Student> belowAverage(IteratorInterface<Student> studentList){
        double meanGrade = meanGrade(
                new FilterIterator<Student>(
                        studentList,
                        new GradeBetterThanPredicate(AisdGrade.NIEOCENIONY.getGrade())));
        GradeWorseThanPredicate gradeWorse = new GradeWorseThanPredicate(meanGrade);
        return new FilterIterator<Student>(studentList, gradeWorse);
    }
}