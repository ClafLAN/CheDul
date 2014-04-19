package net.claflan.CheDul.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.ZipFile;
import net.claflan.CheDul.logic.Event;

public class Schedule extends ZipFile {

    private final HashMap<UUID, Event> events = new HashMap();
    
    public Schedule(File file) throws IOException {
        super(file);
    }
    
    public void addEvent(Event event) {
        events.put(event.getUUID(), event);
    }
    public Event getEvent(UUID uuid) {
        return events.get(uuid);
    }
}
