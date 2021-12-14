package project.library.desktop.model;

public class BookSubject  extends AbstractClass{
    private Long id;
    private Book book;
    private Subjects subjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "BookSubject{" +
                "id=" + id +
                ", book=" + book +
                ", subjects=" + subjects +
                '}';
    }
}
