package be.cegeka.vteam.axkeleton.scenario;

import be.cegeka.vteam.axkeleton.api.CompleteFileGroupCommand;
import be.cegeka.vteam.axkeleton.api.CreateFileGroupCommand;
import be.cegeka.vteam.axkeleton.api.DeliverFileGroupCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Goal of this scenario:
 * <p>
 * - cancel FileGroup 1 upon completion of FileGroup2
 * - delete saga upon delivery of FileGroup2
 */
@Component
class Scenario2 extends AbstractScenario {

    private static final String FILE_GROUP_1 = "sc2-" + UUID.randomUUID().toString();
    private static final String FILE_GROUP_2 = "sc2-" + UUID.randomUUID().toString();

    @Override
    void with(CommandGateway gateway) {
        // the first FileGroup
        System.out.println("FILE_GROUP_1: " + FILE_GROUP_1);
        System.out.println("FILE_GROUP_2: " + FILE_GROUP_2);
        gateway.sendAndWait(new CreateFileGroupCommand(FILE_GROUP_1, 1));

        // the 2nd and more recent FileGroup
        gateway.sendAndWait(new CreateFileGroupCommand(FILE_GROUP_2, 2));
        gateway.sendAndWait(new CompleteFileGroupCommand(FILE_GROUP_2));
        gateway.sendAndWait(new DeliverFileGroupCommand(FILE_GROUP_2));
    }
}
