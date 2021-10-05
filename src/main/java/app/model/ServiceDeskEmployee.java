package app.model;

import java.util.Date;

public class ServiceDeskEmployee extends User{


    public ServiceDeskEmployee(String firstName, String lastName, String userType, String email, String phoneNumber, Date created_at, Date updated_at) {
        super(firstName, lastName, userType, email, phoneNumber, created_at, updated_at);
    }
}
