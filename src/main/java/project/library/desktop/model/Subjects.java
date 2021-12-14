package project.library.desktop.model;

public class Subjects extends AbstractClass {
    private Long id;
    private Long number;
    private String subjectName;
    private String subjectInfo;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectInfo() {
        return subjectInfo;
    }

    public void setSubjectInfo(String subjectInfo) {
        this.subjectInfo = subjectInfo;
    }

    @Override
    public String toString() {
        return "Subjects{" + "id=" + id + ", number=" + number + ", subjectName=" + subjectName + ", subjectInfo=" + subjectInfo + '}';
    }

   
}
