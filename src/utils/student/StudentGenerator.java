package utils.student;

/**
 * Created by Karol Pokomeda on 2017-03-08.
 * Class used for automatic generation of randomized Student objects
 */
public class StudentGenerator {
    private static final String[] SURNAMES =
            {"Mykita", "Sola", "Janczura", "Jaczyszyn", "Stolz",
                    "Cyculak", "Bemm", "Drozd", "Haller", "Hamera"};
    private static final String[] NAMES =
            {"Anna", "Maria", "Teresa", "Joanna", "Patrycja",
            "Patryk", "Przemek", "Piotr", "Protazy", "Paweł"};
    private static final AisdGrade[] GRADES =
            {AisdGrade.NIEOCENIONY, AisdGrade.NIEDOSTATECZNY, AisdGrade.DOSTATECZNY, AisdGrade.DOSTATECZNY_PLUS,
            AisdGrade.DOBRY, AisdGrade.DOBRY_PLUS, AisdGrade.BARDZO_DOBRY, AisdGrade.CELUJACY};

    /**
     * Creates student with surname and name randomly picked from prewritten list
     * @return {Student} randomly generated student
     */
    public static Student randomStudent(){
        return new Student(SURNAMES[(int)(Math.random()*SURNAMES.length)], NAMES[(int)(Math.random()*NAMES.length)]);
    }

    /**
     * @param {int} numberOfStudent - number of randomly generated studens
     * @return {Student[]} list of randomly generated students
     */
    public static Student[] randomStudents(int numberOfStudent){
        Student[] studentList = new Student[numberOfStudent];
        for (int i = 0; i < numberOfStudent; i++){
            studentList[i] = StudentGenerator.randomStudent();
            studentList[i].setGrade(GRADES[(int)(Math.random()*GRADES.length)]);
        }
        return studentList;
    }
}
