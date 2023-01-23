package com.example.mylibrary;

public class Member {

    private String UserName;
    private String PassWord;
    private String FirstName;
    private String LastName;
    private Long mobile;
    private String email;
    private String password;

//    public String getUserName() {
//        return UserName;
//    }
//
//    public void setUserName(String userName) {
//        UserName = userName;
//    }
//
//    public String getPassWord() {
//        return PassWord;
//    }
//
//    public void setPassWord(String passWord) {
//        PassWord = passWord;
//    }

//    public Member(String userName, String passWord) {
//        UserName = userName;
//        PassWord = passWord;
//    }

    public Member(String firstName, String lastName, Long mobile, String email, String password) {
        FirstName = firstName;
        LastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}