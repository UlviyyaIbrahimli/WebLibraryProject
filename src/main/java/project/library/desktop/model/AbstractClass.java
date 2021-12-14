package project.library.desktop.model;

import java.util.Date;

public class AbstractClass {
    private Date data_date;
    private Integer active;

    public Date getData_date() {
        return data_date;
    }

    public void setData_date(Date data_date) {
        this.data_date = data_date;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AbstractClass{" +
                "data_date=" + data_date +
                ", active=" + active +
                '}';
    }
}
