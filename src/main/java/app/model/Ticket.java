package app.model;

import app.helpers.dateParser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket extends BaseModel{

    private Date reported;
    private String incident;
    private String type;
    private String user;
    private String priority;
    private Date deadline;
    private String description;
    private String status;

    public Ticket(Date reported, String incident, String type, String user, String priority, Date deadline,
                  String description, String status) {
        this.reported = reported;
        this.incident = incident;
        this.type = type;
        this.user = user;
        this.priority = priority;
        this.deadline = deadline;
        this.description = description;
        this.status = status;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user_id) {
        this.user = user_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ticket getTicket() {
        return this;
    }

    public Date getReported() {
        return reported;
    }

    public void setReported(Date reported) {
        this.reported = reported;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getTicketArray(){
        dateParser parser = new dateParser();
        String[] array = {parser.toString(reported), incident, type, user, priority, parser.toString(deadline), description};
        return array;
    }
}

