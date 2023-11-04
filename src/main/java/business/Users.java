package business;

import java.util.Objects;

import java.util.Objects;


/**
 * @author Julie, Conor
 */
public class Users {
    //Properties
    private int id;
    private String name;
    private String email;
    private String username;
    private String  password;
    private String  phone;
    private String  address;
    private String  city;
    private String  postcode;
    private int user_type;
    private int disabled;
    // Consructors
    /*public Users(){}*/

    public Users(int id,String name, String email, String username, String password,
                 String phone, String address, String city, String postcode,
                 int user_type, int disabled) {
        this.id=id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final Users other = (Users) o;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }


        return true;
    }

    @Override
    public int hashCode() {
        int hash = 8;
        hash = 90 * hash + Objects.hashCode(this.id);
        hash = 90 * hash + Objects.hashCode(this.username);
        hash = 90 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", user_type=" + user_type +
                ", disabled=" + disabled +
                '}';
    }
}

