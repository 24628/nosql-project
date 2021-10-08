package app.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Employee extends User {


    public Employee(String firstName, String lastName, String userType, String email, String phoneNumber, String location) {
        super(firstName, lastName, userType, email, phoneNumber, location);
    }
}
