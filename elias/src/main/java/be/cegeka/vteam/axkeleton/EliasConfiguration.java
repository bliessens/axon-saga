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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("elias")
public class EliasConfiguration {
    @Bean
    public XStreamSerializer xStreamSerializer() {
        return new XStreamSerializer();
    }
    @Bean
    public TokenStore tokenStore(EntityManagerProvider entityManagerProvider, XStreamSerializer xStreamSerializer) {
        return new JpaTokenStore(entityManagerProvider, xStreamSerializer);
    }
    
    @Bean
    public SagaStore sagaStore(EntityManagerProvider entityManagerProvider, XStreamSerializer xStreamSerializer) {
        return new JpaSagaStore(xStreamSerializer, entityManagerProvider);
    }
    
    @Bean
    public SagaRepository sagaRepository(SagaStore sagaStore, ResourceInjector injector) {
        return new AnnotatedSagaRepository(EliasSaga.class, sagaStore, injector);
    }
    
    @Bean
    public AnnotatedSagaManager annotatedSagaManager(SagaRepository sagaRepository) {
        return new AnnotatedSagaManager(EliasSaga.class, sagaRepository);
    }
    
    @Bean
    public EventProcessor eventProcessor(AnnotatedSagaManager annotatedSagaManager, EventStore eventStore, TokenStore tokenStore, TransactionManager transactionManager) {
        TrackingEventProcessor trackingEventProcessor = new TrackingEventProcessor("myEventProcessor", annotatedSagaManager, eventStore, tokenStore, transactionManager);
        trackingEventProcessor.start();
        return trackingEventProcessor;
    }
}
