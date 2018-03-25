package be.cegeka.vteam.axkeleton.scenario;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

abstract class AbstractScenario implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ApplicationContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        final CommandGateway bean = context.getBean(CommandGateway.class);
        with(bean);
    }

    abstract void with(CommandGateway gateway);
}
