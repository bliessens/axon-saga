package be.cegeka.vteam.axkeleton.api;

public class AggregateCreatedEvent {

    private final String id;

    public AggregateCreatedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
