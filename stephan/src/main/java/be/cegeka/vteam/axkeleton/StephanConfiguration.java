package be.cegeka.vteam.axkeleton;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventProcessor;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventhandling.saga.AnnotatedSagaManager;
import org.axonframework.eventhandling.saga.ResourceInjector;
import org.axonframework.eventhandling.saga.SagaRepository;
import org.axonframework.eventhandling.saga.repository.AnnotatedSagaRepository;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.jpa.JpaSagaStore;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.saga.SpringResourceInjector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stephan")
public class StephanConfiguration {

    @Bean("mySagaStore")
    SagaStore sagaStore(EntityManagerProvider entityManager) {
        return new JpaSagaStore(new XStreamSerializer(), entityManager);
    }

    @Bean("mySagaRepository")
    SagaRepository sagaRepository(@Qualifier("mySagaStore") SagaStore sagaStore, ResourceInjector injector) {
        return new AnnotatedSagaRepository(StephanSaga.class, sagaStore, injector);
    }

    @Bean
    public ResourceInjector injector() {
        return new SpringResourceInjector();
    }

    @Bean
    AnnotatedSagaManager eventHandlerInvoker(@Qualifier("mySagaRepository") SagaRepository sagaRepository) {
        return new AnnotatedSagaManager(StephanSaga.class, sagaRepository);
    }

    @Bean("myTokenStore")
    TokenStore tokenStore( EntityManagerProvider provider) {
        return new JpaTokenStore(provider, new XStreamSerializer());
    }

    @Bean
    EventProcessor eventProcessor(AnnotatedSagaManager sagaManager,
                                  EventStore eventBus,
                                  @Qualifier("myTokenStore") TokenStore tokenStore,
                                  TransactionManager transactionManager) {
        TrackingEventProcessor processor = new TrackingEventProcessor("myTrackingEventProcessor", sagaManager, eventBus, tokenStore,
            transactionManager);
        processor.start();
        return processor;
    }
}
