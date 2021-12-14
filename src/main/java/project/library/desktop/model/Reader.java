package project.library.desktop.model;

import java.util.Date;

public class Reader extends AbstractClass {

    private Long id;
    private Long number;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String gender;
    private Date dob;
//    private String nationalityOld;
    private Nationality nationality;
    private Status status;
    private String identifyNumber;
    private String cardNumber;
    private Float penalty;
    private Integer activity;
    private Date startMemberDate;
    private Date pullMemberDate;
    private String countryOld;
    private Country country;
    private String city;
    private String  street;
    private  String houseNumber;
    private String homeNumber;
    private String eMail;
    private String phone1;
    private String phone2;
    private String homePhone;
    private String additionInfo;
    private Book book;
    private  String password;

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    

    public String getAdditionInfo() {
        return additionInfo;
    }

    public void setAdditionInfo(String additionInfo) {
        this.additionInfo = additionInfo;
    }
    

    public Long getId() {
        return id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Float getPenalty() {
        return penalty;
    }

    public void setPenalty(Float penalty) {
        
        this.penalty = penalty;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {

        this.activity = activity;
    }

    public Date getStartMemberDate() {
        return startMemberDate;
    }

    public void setStartMemberDate(Date startMemberDate) {
        this.startMemberDate = startMemberDate;
    }

    public Date getPullMemberDate() {
        return pullMemberDate;
    }

    public void setPullMemberDate(Date pullMemberDate) {
        this.pullMemberDate = pullMemberDate;
    }

    public String getCountryOld() {
        return countryOld;
    }

    public void setCountryOld(String countryOld) {
        this.countryOld = countryOld;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }


}
