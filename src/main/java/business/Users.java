package business;

import java.util.Objects;

import java.util.Objects;

public class Users {
    //Properties
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String  password;
    private String  phone;
    private String  address1;
    private String  address2;
    private String  address3;
    private String  city;
    private String  state;
    private String  country;
    private String  postcode;
    private int user_type;

    // Consructors
    public Users(){}

    public Users(int id, String  first_name, String  last_name, String  username, String  email, String  password, String  phone,
                 String  address1, String  address2, String  address3, String  city, String  state, String  country, String  postcode, int user_type) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.user_type = user_type;
    }
    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String  first_name) {
        this.first_name = first_name;
    }

    public String  getLast_name() {
        return last_name;
    }

    public void setLast_name(String  last_name) {
        this.last_name = last_name;
    }

    public String  getUsername() {
        return username;
    }

    public void setUsername(String  username) {
        this.username = username;
    }

    public String  getEmail() {
        return email;
    }

    public void setEmail(String  email) {
        this.email = email;
    }

    public String  getPassword() {
        return password;
    }

    public void setPassword(String  password) {
        this.password = password;
    }

    public String  getPhone() {
        return phone;
    }

    public void setPhone(String  phone) {
        this.phone = phone;
    }

    public String  getAddress1() {
        return address1;
    }

    public void setAddress1(String  address1) {
        this.address1 = address1;
    }

    public String  getAddress2() {
        return address2;
    }

    public void setAddress2( String address2) {
        this.address2 = address2;
    }

    public String   getAddress3() {
        return address3;
    }

    public void setAddress3(String  address3) {
        this.address3 = address3;
    }

    public String  getCity() {
        return city;
    }

    public void setCity(String  city) {
        this.city = city;
    }

    public String   getState() {
        return state;
    }

    public void setState(String  state) {
        this.state = state;
    }

    public String  getCountry() {
        return country;
    }

    public void setCountry(String  country) {
        this.country = country;
    }

    public String  getPostcode() {
        return postcode;
    }

    public void setPostcode(String  postcode) {
        this.postcode = postcode;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
    //To Method


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(email, users.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                ", user_type=" + user_type +
                '}';
    }

    //HashCode & Equals Methods

}

