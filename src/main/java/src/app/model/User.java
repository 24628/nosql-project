package src.app.model;

import java.util.Date;

public class User {

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
}
