package app.model;

import java.util.Date;

public class User extends BaseModel{

    protected String firstName;
    protected String lastName;
    protected String email;
    protected Float phoneNumber;
    protected Date created_at;
    protected Date updated_at;

    public User(String firstName, String lastName, String email, Float phoneNumber, Date created_at, Date updated_at) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Float phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public User getUser(){
        return this;
    }
}
