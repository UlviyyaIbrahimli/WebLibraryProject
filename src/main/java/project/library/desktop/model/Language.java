/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.library.desktop.model;

/**
 *
 * @author user
 */
public class Language extends AbstractClass{
    private Long id;
    private String bookLanguage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    @Override
    public String toString() {
        return "Language{" + "id=" + id + ", bookLanguage=" + bookLanguage + '}';
    }
    
    
}
