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
public class BookCase {
    private Long id;
    private String caseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    @Override
    public String toString() {
        return "BookCase{" + "id=" + id + ", caseName=" + caseName + '}';
    }
    
    
}
