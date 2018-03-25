package be.cegeka.vteam.axkeleton.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateFileGroupCommand {

    @TargetAggregateIdentifier
    private String id;
    private int timestamp;

    protected CreateFileGroupCommand() {
    }

    public CreateFileGroupCommand(String id, int timestamp) {
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
