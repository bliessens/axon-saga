package be.cegeka.vteam.axkeleton.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CancelFileGroupCommand {

    @TargetAggregateIdentifier
    private String id;

    protected CancelFileGroupCommand() {
    }

    public CancelFileGroupCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
