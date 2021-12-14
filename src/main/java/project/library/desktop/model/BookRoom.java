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
public class BookRoom  extends AbstractClass{
   private Long id;
   private Long numberOrder;
   private Integer floor;
   private String number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(Long numberOrder) {
        this.numberOrder = numberOrder;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BookRoom{" + "id=" + id + ", numberOrder=" + numberOrder + ", floor=" + floor + ", number=" + number + '}';
    }

    
   
}
