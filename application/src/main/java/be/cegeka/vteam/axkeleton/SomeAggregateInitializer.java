package be.cegeka.vteam.axkeleton;

import be.cegeka.vteam.axkeleton.api.CreateAggregateCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SomeAggregateInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final CommandGateway gateway;

    @Autowired
    public SomeAggregateInitializer(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        gateway.sendAndWait(new CreateAggregateCommand("a"));
        gateway.sendAndWait(new CreateAggregateCommand("b"));
    }
}
