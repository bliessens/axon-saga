package be.cegeka.vteam.axkeleton;

import be.cegeka.vteam.axkeleton.api.FileGroupCreatedEvent;
import be.cegeka.vteam.axkeleton.api.FileGroupDeliveredEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Saga
@SuppressWarnings("unused")
public class StephanSaga {

    private transient CommandGateway commandGateway;

    private List<String> ids = new ArrayList();

    @StartSaga
    @SagaEventHandler(associationProperty = "destination")
    public void onFileGroupCreated(FileGroupCreatedEvent event) {
        System.out.println(event.toString());
        ids.add(event.getId());
    }

    @SagaEventHandler(associationProperty = "destination")
    public void onFileGroupDelivered(FileGroupDeliveredEvent event) {
        System.out.println(event.toString());
        ids.remove(event.getId());
        if (ids.isEmpty()) {
            SagaLifecycle.end();
        }
    }

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
}
