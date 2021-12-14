package project.library.desktop.model;

public class ReadRoom  extends AbstractClass{
    private Long id;
    private Long number;
    private String readRoomNumber;
    private Integer libFloor;
    private Integer tableCount;
    private Integer computerCount;
    private Integer chairCount;
   
        
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

    public String getReadRoomNumber() {
        return readRoomNumber;
    }

    public Integer getChairCount() {
        return chairCount;
    }

    public void setChairCount(Integer chairCount) {
        this.chairCount = chairCount;
    }

    public void setReadRoomNumber(String readRoomNumber) {
        this.readRoomNumber = readRoomNumber;
    }

       public Integer getLibFloor() {
        return libFloor;
    }

    public void setLibFloor(Integer libFloor) {
        this.libFloor = libFloor;
    }

    public Integer getTableCount() {
        return tableCount;
    }

    public void setTableCount(Integer tableCount) {
        this.tableCount = tableCount;
    }

    public Integer getComputerCount() {
        return computerCount;
    }

    public void setComputerCount(Integer withComputer) {
        this.computerCount = withComputer;
    }

    @Override
    public String toString() {
        return "ReadRoom{" + "id=" + id + ", number=" + number + ", readRoomNumber=" + readRoomNumber + ", libFloor=" + libFloor + ", tableCount=" + tableCount + ", computerCount=" + computerCount + ", chairCount=" + chairCount + '}';
    }

    
    

   

   
}
