package java2_project_verdejo_yan;

// @author Samuel Verdejo
// @author Jiaxin Yan
// date: April 3, 2023

//Employee model for data
public class Employee {
    private int id;
    private String name;
    private String job;
    private boolean fulltime;
    private String gender;

    public Employee() {}

    public Employee(int id, String name, String job, boolean fulltime, String gender) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.fulltime = fulltime;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Job Title: " + job + ", Fulltime: " + fulltime + ", Gender: " + gender;
    }
}
