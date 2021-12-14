package project.library.desktop.model;

import project.library.desktop.model.AbstractClass;
/**
 * 
 * @ Ulviyye Ibrahimli
 */
public class BookShelf extends AbstractClass {

    private Long id;
    private Long number;
    private BookRoom room;
    private String shelfNumber;
    private Integer shelfFloor;
    private Integer bookCountInShelf;
    private Integer potentialCapacity;
    private Integer emptyCapacity;

    public Integer getEmptyCapacity() {
        return emptyCapacity;
    }

    public void setEmptyCapacity(Integer emptyCapacity) {
        this.emptyCapacity = emptyCapacity;
    }

    private Book book;

    public Integer getPotensialCapacity() {
        return potentialCapacity;
    }

    public void setPotensialCapacity(Integer potensialCapacity) {
        this.potentialCapacity = potensialCapacity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookRoom getRoom() {
        return room;
    }

    public void setRoom(BookRoom room) {
        this.room = room;
    }

    public String getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(String shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public Integer getShelfFloor() {
        return shelfFloor;
    }

    public void setShelfFloor(Integer shelfFloor) {
        this.shelfFloor = shelfFloor;
    }

    public Integer getBookCountInShelf() {
        return bookCountInShelf;
    }

    public void setBookCountInShelf(Integer bookCountInShelf) {
        this.bookCountInShelf = bookCountInShelf;
    }

    @Override
    public String toString() {
        return "BookShelf{" + "id=" + id + ", number=" + number + ", room=" + room + ", shelfNumber=" + shelfNumber + ", shelfFloor=" + shelfFloor + ", bookCountInShelf=" + bookCountInShelf + ", potentialCapacity=" + potentialCapacity + ", emptyCapacity=" + emptyCapacity + ", book=" + book + '}';
    }

}
