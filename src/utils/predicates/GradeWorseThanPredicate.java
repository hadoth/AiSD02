package utils.predicates;

import interfaces.Predicate;
import utils.student.Student;

/**
 * Created by Karol Pokomeda on 2017-03-09.
 * Predicate class to determine if student has grade worse than or equal to specified
 */
public class GradeWorseThanPredicate implements Predicate<Student> {
    private double grade;

    public GradeWorseThanPredicate(double grade){
        this.grade = grade;
    }

    public boolean accept(Student student) {return student.getGrade() <= this.grade;}
}

