package project.library.desktop.model;

public class ReadTable extends AbstractClass {
    private Long id;
    private Long number;
    private String TableNumber;
    private Integer withComputer;
    private ReadRoom readRoom;

    public ReadRoom getReadRoom() {
        return readRoom;
    }

    public void setReadRoom(ReadRoom readRoom) {
        this.readRoom = readRoom;
    }
    

    public Long getId() {
        return id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableNumber() {
        return TableNumber;
    }

    public void setTableNumber(String tableNumber) {
        TableNumber = tableNumber;
    }

    public Integer getWithComputer() {
        return withComputer;
    }

    public void setWithComputer(Integer withComputer) {
        this.withComputer = withComputer;
    }

    @Override
    public String toString() {
        return "ReadTable{" + "id=" + id + ", number=" + number + ", TableNumber=" + TableNumber + ", withComputer=" + withComputer + ", readRoom=" + readRoom + '}';
    }

    
}
