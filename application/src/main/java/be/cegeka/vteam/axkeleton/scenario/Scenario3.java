package be.cegeka.vteam.axkeleton.scenario;

import be.cegeka.vteam.axkeleton.api.CompleteFileGroupCommand;
import be.cegeka.vteam.axkeleton.api.CreateFileGroupCommand;
import be.cegeka.vteam.axkeleton.api.DeliverFileGroupCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

/**
 * nothing  new here I think
 */
//@Component
class Scenario3 extends AbstractScenario {

    private static final String FILE_GROUP_1 = "sc3-" + UUID.randomUUID().toString();
    private static final String FILE_GROUP_2 = "sc3-" + UUID.randomUUID().toString();

    @Override
    void with(CommandGateway gateway) {
        // the first FileGroup
        gateway.sendAndWait(new CreateFileGroupCommand(FILE_GROUP_1, 1));
        gateway.sendAndWait(new CompleteFileGroupCommand(FILE_GROUP_1));

        // the 2nd and more recent FileGroup
        gateway.sendAndWait(new CreateFileGroupCommand(FILE_GROUP_2, 2));
        gateway.sendAndWait(new CompleteFileGroupCommand(FILE_GROUP_2));
        gateway.sendAndWait(new DeliverFileGroupCommand(FILE_GROUP_2));
    }
}
