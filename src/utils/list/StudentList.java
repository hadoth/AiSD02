package utils.list;

import interfaces.IteratorInterface;
import interfaces.ListInterface;
import interfaces.Predicate;
import utils.iterators.FilterIterator;
import utils.student.Student;

import java.io.*;

/**
 * Created by Karol Pokomeda on 2017-03-20.
 */
public class StudentList implements Serializable, ListInterface<Student> {
    private MyList<Student> studentList;

    public StudentList(MyList<Student> studentList){
        super();
        this.studentList = studentList;
    }

    @Override
    public int size() {
        return this.studentList.size();
    }

    @Override
    public void insert(Student t, int index) throws IndexOutOfBoundsException {
        this.studentList.insert(t, index);
    }

    @Override
    public Student get(int index) throws IndexOutOfBoundsException {
        return this.studentList.get(index);
    }

    @Override
    public Student set(Student t, int index) throws IndexOutOfBoundsException {
        return this.studentList.set(t, index);
    }

    @Override
    public void add(Student t) {
        this.studentList.add(t);
    }

    @Override
    public Student delete(Student t) throws IndexOutOfBoundsException {
        return this.studentList.delete(this.indexOf(t));
    }

    @Override
    public Student delete(int index) throws IndexOutOfBoundsException {
        return this.studentList.delete(index);
    }

    @Override
    public boolean contains(Student t) {
        return this.studentList.contains(t);
    }

    @Override
    public int indexOf(Student t){
        return this.studentList.indexOf(t);
    }

    @Override
    public boolean isEmpty() {
        return this.studentList.isEmpty();
    }

    @Override
    public IteratorInterface<Student> iterator() {
        return this.studentList.iterator();
    }

    @Override
    public void clear() {
        this.studentList.clear();
    }

    public void showList(){
        IteratorInterface<Student> studentIterator = this.studentList.iterator();
        studentIterator.first();
        while (!studentIterator.isDone()){
            System.out.println(studentIterator.current());
            studentIterator.next();
        }
    }

    public void removeStudents(Predicate<Student> studentPredicate){
        FilterIterator<Student> unwantedStudentIterator = new FilterIterator<>(this.studentList.iterator(), studentPredicate);
        unwantedStudentIterator.first();
        while (!unwantedStudentIterator.isDone()){
            unwantedStudentIterator.deleteCurrent();
            unwantedStudentIterator.next();
        }
    }

    public boolean saveList(String path){
        File myFile = new File(path);
        try {
            myFile.createNewFile();

            FileOutputStream fileOut = new FileOutputStream(myFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            myFile.delete();
            return false;
        }
        return true;
    }

    public static StudentList loadList(String path){
        try{
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            StudentList result = (StudentList) objectIn.readObject();

            objectIn.close();
            fileIn.close();
            return result;
        } catch (IOException e){
            throw new IllegalArgumentException(e.getMessage());
        } catch (ClassNotFoundException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}