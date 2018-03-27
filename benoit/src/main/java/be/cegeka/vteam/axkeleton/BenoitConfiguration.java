package be.cegeka.vteam.axkeleton;

import be.cegeka.vteam.axkeleton.saga.BenoitSaga;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventProcessor;
import org.axonframework.eventhandling.SimpleEventHandlerInvoker;
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
@Profile("benoit")
public class BenoitConfiguration {

    @Bean
    public AnnotatedSagaManager<BenoitSaga> sagaManager(SagaRepository<BenoitSaga> repository) {
        return new AnnotatedSagaManager<>(BenoitSaga.class, repository);
    }

    @Bean
    public SagaRepository<BenoitSaga> sagaRepository(@Qualifier("jpaSagaStore") SagaStore sagaStore, ResourceInjector injector) {
        return new AnnotatedSagaRepository<>(BenoitSaga.class, sagaStore, injector);
    }

    @Bean("jpaSagaStore")
    public SagaStore jpaSagaStore(EntityManagerProvider provider, XStreamSerializer serializer) {
        return new JpaSagaStore(serializer, provider);
    }

    @Bean
    public XStreamSerializer xStreamSerializer() {
        return new XStreamSerializer();
    }

    @Bean
    public ResourceInjector injector() {
        return new SpringResourceInjector();
    }

    @Bean
    public TokenStore tokenStore(EntityManagerProvider provider, XStreamSerializer serializer) {
        return new JpaTokenStore(provider, serializer);
    }

    @Bean
    public EventProcessor sagaEventProcessor(AnnotatedSagaManager sagaManager, EventStore eventStore,
                                             TokenStore tokenStore, TransactionManager txManager) {

        final SimpleEventHandlerInvoker eventHandlerInvoker = new SimpleEventHandlerInvoker(sagaManager);
        final TrackingEventProcessor processor = new TrackingEventProcessor("sagaEventProcessor",
                eventHandlerInvoker, eventStore, tokenStore, txManager);
        processor.start();
        return processor;
    }
}
