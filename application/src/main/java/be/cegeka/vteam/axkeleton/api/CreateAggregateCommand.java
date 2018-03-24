package be.cegeka.vteam.axkeleton.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateAggregateCommand {

    @TargetAggregateIdentifier
    private final String id;

    public CreateAggregateCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
