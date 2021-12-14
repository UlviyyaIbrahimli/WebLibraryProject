package project.library.desktop.dao;

public class Item {
    private  Long id;
    private  String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Item(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
