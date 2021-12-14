package project.library.desktop.model;

import java.sql.Date;

public class AdvanceSearch {
    private Long id1;
    private Long id2;
    private Long id3;
    private Long id4;
    private String case1;
    private String case2;
    private String  case3;
    private  String case4;
    private String case5;
    private String  case6;
    private String case7;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCase7() {
        return case7;
    }

    public void setCase7(String case7) {
        this.case7 = case7;
    }

    public Long getId1() {
        return id1;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    public Long getId2() {
        return id2;
    }

    public void setId2(Long id2) {
        this.id2 = id2;
    }

    public Long getId3() {
        return id3;
    }

    public void setId3(Long id3) {
        this.id3 = id3;
    }

    public Long getId4() {
        return id4;
    }

    public void setId4(Long id4) {
        this.id4 = id4;
    }

    public String getCase1() {
        return case1;
    }

    public void setCase1(String case1) {
        this.case1 = case1;
    }

    public String getCase2() {
        return case2;
    }

    public void setCase2(String case2) {
        this.case2 = case2;
    }

    public String getCase3() {
        return case3;
    }

    public void setCase3(String case3) {
        this.case3 = case3;
    }

    public String getCase4() {
        return case4;
    }

    public void setCase4(String case4) {
        this.case4 = case4;
    }

    public String getCase5() {
        return case5;
    }

    public void setCase5(String case5) {
        this.case5 = case5;
    }

    public String getCase6() {
        return case6;
    }

    public void setCase6(String case6) {
        this.case6 = case6;
    }

    @Override
    public String toString() {
        return "AdvanceSearch{" +
                "id1=" + id1 +
                ", id2=" + id2 +
                ", id3=" + id3 +
                ", id4=" + id4 +
                ", case1='" + case1 + '\'' +
                ", case2='" + case2 + '\'' +
                ", case3='" + case3 + '\'' +
                ", case4='" + case4 + '\'' +
                ", case5='" + case5 + '\'' +
                ", case6='" + case6 + '\'' +
                ", case7='" + case7 + '\'' +
                '}';
    }
}
