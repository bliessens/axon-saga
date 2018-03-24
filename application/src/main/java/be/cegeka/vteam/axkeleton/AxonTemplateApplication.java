package be.cegeka.vteam.axkeleton;

import org.axonframework.eventhandling.saga.repository.jpa.SagaEntry;
import org.axonframework.eventhandling.tokenstore.jpa.TokenEntry;
import org.axonframework.eventsourcing.eventstore.jpa.DomainEventEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {DomainEventEntry.class, SagaEntry.class, TokenEntry.class})
public class AxonTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonTemplateApplication.class, args);
    }

}
