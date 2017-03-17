package interfaces;

/**
 * Created by Karol Pokomeda on 2017-03-09.
 * Interface for creation of predicates; these predicates can be used in any circumstance when it is required to
 * have decision making automated, e.g data filtering, sorting, etc.
 */
public interface Predicate<T>{
    /**
     * Method which runs predefined test on the tested element
     * @param {T} testedElement of generic class T
     * @return {boolean} result of the test
     */
    boolean accept(T testedElement);
}
