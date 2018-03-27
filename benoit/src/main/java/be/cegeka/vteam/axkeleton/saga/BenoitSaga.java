package be.cegeka.vteam.axkeleton.saga;

import be.cegeka.vteam.axkeleton.api.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Saga
@Profile("benoit")
public class BenoitSaga {

    private static final Logger LOG = LoggerFactory.getLogger(BenoitSaga.class);

    private Map<String, Integer> fileGroupIds = new HashMap<>();

    private transient CommandGateway gateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "destination")
    public void onEvent(FileGroupCreatedEvent event) {
        LOG.info("Tracking {} FileGroups", this.fileGroupIds.size());
        this.fileGroupIds.put(event.getId(), event.getTimestamp());
    }

    @SagaEventHandler(associationProperty = "destination")
    public void onEvent(FileGroupCancelledEvent event) {
        LOG.info("FileGroup effectively cancelled");
        this.fileGroupIds.remove(event.getId());
    }

    @SagaEventHandler(associationProperty = "destination")
    public void onEvent(FileGroupCompletedEvent event) {
        LOG.info("A least 1 FileGroup is completed, {}", this.fileGroupIds.size() > 1 ? "must cancel FileGroup(s) with earlier timestamp" : "no other FileGroup are being tracked");
        int mostRecentTimestamp = this.fileGroupIds.get(event.getId());
        for (Map.Entry<String, Integer> entry : fileGroupIds.entrySet()) {
            if (entry.getValue() < mostRecentTimestamp) {
                LOG.info("Current timestamp is {}, cancelling FileGroup with timestamp {}", mostRecentTimestamp, entry.getValue());
                gateway.sendAndWait(new CancelFileGroupCommand(entry.getKey()));
            }
        }
    }

    @SagaEventHandler(associationProperty = "destination")
    public void onEvent(FileGroupDeliveredEvent event) {
        this.fileGroupIds.remove(event.getId());
        if (this.fileGroupIds.isEmpty()) {
            LOG.info("Last tracked FileGroup is delivered, ending saga!");
            SagaLifecycle.end();
        }
    }

    @Autowired
    public void setGateway(CommandGateway gateway) {
        this.gateway = gateway;
    }
}
