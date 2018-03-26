package be.cegeka.vteam.axkeleton;

import be.cegeka.vteam.axkeleton.api.CancelFileGroupCommand;
import be.cegeka.vteam.axkeleton.api.FileGroupCompletedEvent;
import be.cegeka.vteam.axkeleton.api.FileGroupCreatedEvent;
import be.cegeka.vteam.axkeleton.api.FileGroupDeliveredEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Saga
public class EliasSaga {
    private transient CommandGateway gateway;
    
    private Set<String> ids = new HashSet<>();
    
    @StartSaga
    @SagaEventHandler(associationProperty = "destination")
    public void handle(FileGroupCreatedEvent event) {
        ids.add(event.getId());
    }
    
    @SagaEventHandler(associationProperty = "destination")
    public void handle(FileGroupCompletedEvent event) {
        for (String id : ids) {
            if(!id.equals(event.getId())) {
                gateway.sendAndWait(new CancelFileGroupCommand(id));
            }
        }
        
        ids.clear();
        
        ids.add(event.getId());
    }
    
    @SagaEventHandler(associationProperty = "destination")
    public void handle(FileGroupDeliveredEvent event) {
        ids.remove(event.getId());
        if(ids.isEmpty()) {
            SagaLifecycle.end();
        }
    }
    
    @Autowired
    public void setGateway(CommandGateway gateway) {
        this.gateway = gateway;
    }
}
