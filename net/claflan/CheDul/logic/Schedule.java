package net.claflan.CheDul.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Schedule implements Serializable {

    private final File fileOnDisk;
    private String owner;
    
    private final HashMap<UUID, Event> events = new HashMap();
    
    public Schedule(File fileOnDisk) {
        this.fileOnDisk = fileOnDisk;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getOwner() {
        return owner;
    }
    
    public void addEvent(Event event) {
        events.put(event.getUUID(), event);
    }
    public Event getEvent(UUID uuid) {
        return events.get(uuid);
    }
    
    public static Schedule readSchedule(File fileOnDisk) {
        Schedule schedule = new Schedule(fileOnDisk);
        
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(fileOnDisk))){
            schedule = (Schedule) oos.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return schedule;
    }
    public void writeSchedule() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileOnDisk))){
            oos.writeObject(this);
        } catch (IOException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
