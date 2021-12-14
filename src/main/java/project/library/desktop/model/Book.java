package project.library.desktop.model;

import java.util.Date;

public class Book {

    private Long id;
    private Long number;
    private Long isbn;
    private String title;
    private Language language;
    private String publisher;
    private String edition;
    private Long page;
    private String star;
    private BookCase bCase;
    private Date releaseDate;
    private String bAbstract;
    private Integer limitTime;
    private Integer limitDay;
    private Author author;
    private AuthotBook authotBook;
    private Subjects subjects;
    private BookSubject bookSubject;
    private BookRoom bookRoom;
    private BookShelf bookShelf;
    private Integer allBookCount;
    private Integer shelfNumber;
    private Long roomId;
    private  String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public BookCase getbCase() {
        return bCase;
    }

    public void setbCase(BookCase bCase) {
        this.bCase = bCase;
    }
    

    public Integer getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(Integer shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public Integer getAllBookCount() {
        return allBookCount;
    }

    public void setAllBookCount(Integer allBookCount) {
        this.allBookCount = allBookCount;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public AuthotBook getAuthotBook() {
        return authotBook;
    }

    public void setAuthotBook(AuthotBook authotBook) {
        this.authotBook = authotBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookRoom getBookRoom() {
        return bookRoom;
    }

    public void setBookRoom(BookRoom bookRoom) {
        this.bookRoom = bookRoom;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

 

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getbAbstract() {
        return bAbstract;
    }

    public void setbAbstract(String bAbstract) {
        this.bAbstract = bAbstract;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getLimitDay() {
        return limitDay;
    }

    public void setLimitDay(Integer limitDay) {
        this.limitDay = limitDay;
    }

 
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    public BookSubject getBookSubject() {
        return bookSubject;
    }

    public void setBookSubject(BookSubject bookSubject) {
        this.bookSubject = bookSubject;
    }

    public BookShelf getBookShelf() {
        return bookShelf;
    }

    public void setBookShelf(BookShelf roomShelf) {
        this.bookShelf = roomShelf;

    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", number=" + number + ", isbn=" + isbn + ", title=" + title + ", language=" + language + ", publisher=" + publisher + ", edition=" + edition + ", page=" + page + ", star=" + star + ", bCase=" + bCase + ", releaseDate=" + releaseDate + ", bAbstract=" + bAbstract + ", limitTime=" + limitTime + ", limitDay=" + limitDay + ", author=" + author + ", authotBook=" + authotBook + ", subjects=" + subjects + ", bookSubject=" + bookSubject + ", bookRoom=" + bookRoom + ", bookShelf=" + bookShelf + ", allBookCount=" + allBookCount + ", shelfNumber=" + shelfNumber + ", roomId=" + roomId + '}';
    }

   
   
  
   
   

}
