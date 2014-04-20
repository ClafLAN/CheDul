package net.claflan.CheDul.logic;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

public class Event implements Serializable{

    private String name, description;
    private final UUID uuid;
    private Calendar dueDate;
    
    public Event(String name, String description, Calendar dueDate) {
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
    
    public Calendar getDueDate() {
        return dueDate;
    }
    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }
    
    public UUID getUUID() {
        return uuid;
    }
}
