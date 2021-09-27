package app.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket extends BaseModel{

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

    public String[] getTicketArray(){
        //SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        String[] array = {formatter.format(reported), incident, type, user_id, priority, formatter.format(deadline), description};
        return array;
    }
}

