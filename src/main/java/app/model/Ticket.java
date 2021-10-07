package app.model;

import app.helpers.dateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Ticket extends BaseModel{

    private LocalDateTime reported;
    private String incident;
    private String type;
    private String user;
    private String priority;
    private LocalDateTime deadline;
    private String description;
    private String status;

    public Ticket(LocalDateTime reported, String incident, String type, String user, String priority, LocalDateTime deadline,
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

    public LocalDateTime getReported() {
        return reported;
    }

    public void setReported(LocalDateTime reported) {
        this.reported = reported;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
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

