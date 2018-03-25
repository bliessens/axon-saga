package be.cegeka.vteam.axkeleton.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class DeliverFileGroupCommand {

    @TargetAggregateIdentifier
    private String id;

    protected DeliverFileGroupCommand() {
    }

    public DeliverFileGroupCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
