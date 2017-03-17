package utils.predicates;

import interfaces.Predicate;
import utils.student.Student;

/**
 * Created by Karol Pokomeda on 2017-03-09.
 * Predicate to filter Students with specific index number
 */
public class IndexNumberPredicate implements Predicate<Student> {
    private int indexNumber;

    /**
     * @param {int} indexNumber up to 6 digit positive integer
     */
    public IndexNumberPredicate (int indexNumber){
        this.indexNumber = indexNumber;
    }

    /**
     * Sets predicte index number used for comparison
     * @param {int} indexNumber
     */
    public void setIndexNumber(int indexNumber){
        this.indexNumber = indexNumber;
    }

    public boolean accept(Student testedStudent){
        return this.indexNumber == testedStudent.getIndexNumber();
    }
}