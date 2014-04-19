package net.claflan.CheDul.logic;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Event implements Serializable{

    private String name, description;
    private final UUID uuid;
    private Date dueDate;
    
    public Event(String name, String description, Date dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        uuid = UUID.randomUUID();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public UUID getUUID() {
        return uuid;
    }
}
