package be.cegeka.vteam.axkeleton.api;

public class FileGroupCreatedEvent extends AbstractFileGroupEvent {

    private String id;
    private int timestamp;

    protected FileGroupCreatedEvent() {
    }

    public FileGroupCreatedEvent(String id, int timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public int getTimestamp() {
        return timestamp;
    }

}
