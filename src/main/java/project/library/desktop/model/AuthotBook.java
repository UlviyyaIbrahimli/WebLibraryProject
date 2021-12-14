package project.library.desktop.model;

public class AuthotBook extends AbstractClass {
    private Long id;
    private  Book book;
    private Author author;

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "AuthotBook{" +
                "id=" + id +
                ", book=" + book +
                ", author=" + author +
                '}';
    }
}
