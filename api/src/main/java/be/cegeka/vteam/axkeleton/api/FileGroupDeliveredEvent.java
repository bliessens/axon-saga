package be.cegeka.vteam.axkeleton.api;

public class FileGroupDeliveredEvent extends AbstractFileGroupEvent {

    private String id;
    private int timestamp;

    protected FileGroupDeliveredEvent() {
    }

    public FileGroupDeliveredEvent(String id, int timestamp) {
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
