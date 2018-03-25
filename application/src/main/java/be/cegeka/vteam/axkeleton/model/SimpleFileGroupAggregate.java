package be.cegeka.vteam.axkeleton.model;

import be.cegeka.vteam.axkeleton.api.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class SimpleFileGroupAggregate {

    @AggregateIdentifier
    private String id;
    private int timestamp;

    protected SimpleFileGroupAggregate() {
        // for spring
    }

    @CommandHandler
    public SimpleFileGroupAggregate(CreateFileGroupCommand command) {
        apply(new FileGroupCreatedEvent(command.getId(), command.getTimestamp()));
    }

    @CommandHandler
    public void handle(CompleteFileGroupCommand command) {
        apply(new FileGroupCompletedEvent(command.getId(), this.timestamp));
    }

    @CommandHandler
    public void handle(CancelFileGroupCommand command) {
        apply(new FileGroupCancelledEvent(command.getId(), this.timestamp));
    }

    @CommandHandler
    public void handle(DeliverFileGroupCommand command) {
        apply(new FileGroupDeliveredEvent(command.getId(), this.timestamp));
    }

    @EventSourcingHandler
    public void onEvent(FileGroupCreatedEvent event) {
        this.id = event.getId();
        this.timestamp = event.getTimestamp();
    }

    @EventSourcingHandler
    public void onEvent(FileGroupCompletedEvent event) {
    }

    @EventSourcingHandler
    public void onEvent(FileGroupCancelledEvent event) {
    }

    @EventSourcingHandler
    public void onEvent(FileGroupDeliveredEvent event) {
    }


}
