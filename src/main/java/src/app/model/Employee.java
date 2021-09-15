package src.app.model;

import java.util.Date;

public class Employee extends User {


    public Employee(String firstName, String lastName, String email, Float phoneNumber, Date created_at, Date updated_at) {
        super(firstName, lastName, email, phoneNumber, created_at, updated_at);
    }
}
