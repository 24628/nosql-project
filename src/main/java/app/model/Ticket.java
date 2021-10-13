package app.model;

import app.helpers.dateParser;
import java.time.LocalDateTime;


public class Ticket extends BaseModel{

    private LocalDateTime reported;
    private String incident;
    private String type;
    private String user_id;
    private String employee_id;
    private String priority;
    private LocalDateTime deadline;
    private String description;
    private String status;

    public Ticket(LocalDateTime reported, String incident, String type, String user_id, String employee_id, String priority, LocalDateTime deadline,
                  String description, String status) {
        this.reported = reported;
        this.incident = incident;
        this.type = type;
        this.user_id = user_id;
        this.employee_id = employee_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
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
        String[] array = {parser.toString(reported), incident, type, user_id, employee_id, priority, parser.toString(deadline), description};
        return array;
    }
}

