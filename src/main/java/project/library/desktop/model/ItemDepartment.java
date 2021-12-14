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
public class ItemDepartment {
    private Long id;
    private String value;

    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    
    
    public ItemDepartment(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public ItemDepartment() {
    }
    
    
}
