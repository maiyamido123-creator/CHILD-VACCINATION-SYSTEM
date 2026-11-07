package model;
import java.io.Serializable;

/** Represents one child vaccination record. */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private String childID;
    private String name;
    private String age;
    private String barangay;
    private String vaccineID;

    public String getChildID() { return childID; }
    public void setChildID(String value) { childID = value; }
    public String getName() { return name; }
    public void setName(String value) { name = value; }
    public String getAge() { return age; }
    public void setAge(String value) { age = value; }
    public String getBarangay() { return barangay; }
    public void setBarangay(String value) { barangay = value; }
    public String getVaccine_ID() { return vaccineID; }
    public void setVaccine_ID(String value) { vaccineID = value; }
    @Override public String toString() {
        return "Task{" + childID + ", " + name + ", " + age + ", " + barangay + ", " + vaccineID + "}";
    }
}
