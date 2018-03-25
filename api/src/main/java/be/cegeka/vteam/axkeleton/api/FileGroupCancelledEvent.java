package be.cegeka.vteam.axkeleton.api;

public class FileGroupCancelledEvent extends AbstractFileGroupEvent {

    private String id;
    private int timestamp;

    protected FileGroupCancelledEvent() {
    }

    public FileGroupCancelledEvent(String id, int timestamp) {
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
