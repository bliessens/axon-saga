package be.cegeka.vteam.axkeleton.model;

import be.cegeka.vteam.axkeleton.api.AggregateCreatedEvent;
import be.cegeka.vteam.axkeleton.api.CreateAggregateCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class SimpleAggregate {

    @AggregateIdentifier
    private String id;

    @CommandHandler
    public SimpleAggregate(CreateAggregateCommand command) {
        apply(new AggregateCreatedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void onEvent(AggregateCreatedEvent event) {
        this.id = event.getId();
    }
}
