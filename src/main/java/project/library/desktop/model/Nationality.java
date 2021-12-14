package project.library.desktop.model;

public class Nationality  extends AbstractClass{
    private long id;
    private String nationality;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Nationality() {
    }

    public Nationality(long id, String nationality) {
        this.id = id;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Nationality{" +
                "id=" + id +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
