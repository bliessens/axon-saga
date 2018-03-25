package be.cegeka.vteam.axkeleton.scenario;

import be.cegeka.vteam.axkeleton.api.CompleteFileGroupCommand;
import be.cegeka.vteam.axkeleton.api.CreateFileGroupCommand;
import be.cegeka.vteam.axkeleton.api.DeliverFileGroupCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

/**
 * Goal of this scenario:
 * <p>
 * - add necessary Spring bean for Saga's
 * - define a saga for the SimpleFileGroupAggregate
 * - when these events are processed by SagaManager, saga should be created and then removed
 */
//@Component
class Scenario1 extends AbstractScenario {

    private static final String FILE_GROUP_ID = "sc1-" + UUID.randomUUID().toString();

    @Override
    void with(CommandGateway gateway) {
        gateway.sendAndWait(new CreateFileGroupCommand(FILE_GROUP_ID, 1));
        gateway.sendAndWait(new CompleteFileGroupCommand(FILE_GROUP_ID));
        gateway.sendAndWait(new DeliverFileGroupCommand(FILE_GROUP_ID));
    }
}
