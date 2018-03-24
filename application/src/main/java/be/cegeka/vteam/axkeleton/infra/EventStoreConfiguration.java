package be.cegeka.vteam.axkeleton.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
class EventStoreConfiguration {

    @Bean
    public EventStorageEngine eventStorageEngine(TransactionManager axonTransactionManager,
                                                 EntityManagerProvider entityManagerProvider,
                                                 JacksonSerializer eventSerializer,
                                                 DataSource dataSource) throws SQLException {

        return new JpaEventStorageEngine(eventSerializer, null, dataSource, entityManagerProvider, axonTransactionManager);
    }

    @Bean
    public JacksonSerializer eventSerializer() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new JacksonSerializer(objectMapper);
    }

    @Bean("eventBus")
    public EventStore eventStore(EventStorageEngine eventStorageEngine) {
        return new EmbeddedEventStore(eventStorageEngine);
    }

}
