package app.model;

import java.time.LocalDateTime;
import java.util.Date;

public class ServiceDeskEmployee extends User{


    public ServiceDeskEmployee(String firstName, String lastName, String userType, String email, String phoneNumber) {
        super(firstName, lastName, userType, email, phoneNumber);
    }
}
