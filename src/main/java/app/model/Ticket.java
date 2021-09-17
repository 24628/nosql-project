package app.model;

import java.util.Date;

public class Ticket {

    private Date reported;
    private String incident;
    private String type;
    private String user_id;
    private String priority;
    private Date deadline;
    private String description;

    public Ticket(Date reported, String incident, String type, String user_id, String priority, Date deadline, String description) {
        this.reported = reported;
        this.incident = incident;
        this.type = type;
        this.user_id = user_id;
        this.priority = priority;
        this.deadline = deadline;
        this.description = description;
    }

    public Ticket getTicket() {
        return this;
    }
}
