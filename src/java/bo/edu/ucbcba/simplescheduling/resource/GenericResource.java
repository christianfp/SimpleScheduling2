/*
 * GenericResource.java
 */

package bo.edu.ucbcba.simplescheduling.resource;

import bo.edu.ucbcba.simplescheduling.model.MyClass;
import bo.edu.ucbcba.simplescheduling.model.Student;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Diego
 */
public class GenericResource {
    
    private static final Map<Integer, Student> studentMap = new HashMap<>();
    private static final Map<String, MyClass> myClassMap = new HashMap<>();
    
    public static void putStudent(Student student) {
        if (student != null) {
            getStudentMap().put(student.getStudentId(), student);
        }
    }
    public static void putClass(MyClass classe) {
        if (classe != null) {
            getClassMap().put(classe.getCode(), classe);
        }
    }
    
    public static Student getStudent(Integer studentId) {
        return getStudentMap().get(studentId);
    }
    public static MyClass getClass(String codeClass) {
        return getClassMap().get(codeClass);
    }
    
    public static boolean removeStudent(Integer studentId) {
        return getStudentMap().remove(studentId) != null;
    }
    public static boolean removeClass(String code) {
        return getClassMap().remove(code) != null;
    }

    /**
     * @return the studentMap
     */
    public static Map<Integer, Student> getStudentMap() {
        return studentMap;
    }
     public static Map<String, MyClass> getClassMap() {
        return myClassMap;
    }

}
