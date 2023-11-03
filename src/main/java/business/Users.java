package business;

import java.util.Objects;

import java.util.Objects;

public class Users {
    //Properties
    private int id;
    private String name;
    private String email;
    private String username;
    private String  password;
    private int  phone;
    private String  address;
    private String  city;
    private String  postcode;
    private int user_type;
    private int disabled;
    // Consructors
    /*public Users(){}*/
    public Users(){
        this.id = 0;
        this.name = "";
        this.email = "";
        this.username = "";
        this.password = "";
        this.phone = 0;
        this.address = "";
        this.city = "";
        this.postcode = "";
        this.user_type = 0;
        this.disabled = 0;
    }

    public Users(String name, String email, String username, String password,
                 int phone, String address, String city, String postcode,
                 int user_type, int disabled) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.user_type = user_type;
        this.disabled = disabled;
    }
    // GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }
    // EQUALS AND HASHCODE METHODS
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





}

