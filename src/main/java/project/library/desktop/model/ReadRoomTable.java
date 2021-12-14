package project.library.desktop.model;

public class ReadRoomTable extends AbstractClass {
    private  Long id;
    private ReadRoom readRoom;
    private ReadTable readTable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReadRoom getReadRoom() {
        return readRoom;
    }

    public void setReadRoom(ReadRoom readRoom) {
        this.readRoom = readRoom;
    }

    public ReadTable getReadTable() {
        return readTable;
    }

    public void setReadTable(ReadTable readTable) {
        this.readTable = readTable;
    }

    @Override
    public String toString() {
        return "ReadRoomTable{" +
                "id=" + id +
                ", readRoom=" + readRoom +
                ", readTable=" + readTable +
                '}';
    }
}