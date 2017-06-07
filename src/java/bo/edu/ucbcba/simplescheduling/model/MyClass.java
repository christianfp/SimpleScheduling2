/*
 * MyClass.java
 */

package bo.edu.ucbcba.simplescheduling.model;

import java.util.List;

/**
 *
 * @author Diego
 */
public class MyClass {

    private String code;
    private String title;
    private String description;
    private List<Integer> studentIds;

    public MyClass() {
    }

    public MyClass(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the studentIds
     */
    public List<Integer> getStudentIds() {
        return studentIds;
    }

    /**
     * @param studentIds the studentIds to set
     */
    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }
}
