package be.cegeka.vteam.axkeleton.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CompleteFileGroupCommand {

    @TargetAggregateIdentifier
    private String id;

    protected CompleteFileGroupCommand() {
    }

    public CompleteFileGroupCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
