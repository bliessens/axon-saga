package be.cegeka.vteam.axkeleton.step2;

import org.axonframework.eventhandling.EventHandlerInvoker;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.saga.AnnotatedSaga;
import org.axonframework.eventhandling.saga.AnnotatedSagaManager;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaRepository;
import org.axonframework.spring.stereotype.Saga;
import org.junit.Test;

import java.util.HashSet;
import java.util.function.Supplier;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class AnnotatedSageManagerTest {

    private final SagaRepository sagaRepository = mock(SagaRepository.class);

    private final EventHandlerInvoker invoker = new AnnotatedSagaManager<>(MySaga.class, sagaRepository);

    @Test
    public void testFindEventHandlerMethod() {
        assertTrue(invoker.hasHandler(asEventMessage(new MyEvent())));
    }

    @Test
    public void testSagaManager_Calls_EventHandlerMethod() throws Exception {
        final AnnotatedSaga annotatedSaga = mock(AnnotatedSaga.class);
        when(sagaRepository.find(any())).thenReturn(new HashSet<>());
        when(sagaRepository.createInstance(anyString(), any(Supplier.class))).thenReturn(annotatedSaga);

        invoker.handle(asEventMessage(new MyEvent()));

        verify(annotatedSaga).handle(any(EventMessage.class));

    }

    @Saga
    public static class MySaga {

        @SagaEventHandler(associationProperty = "blabla")
        public void onEvent(MyEvent event) {
        }
    }

    public static class MyEvent {
        public String getBlabla() {
            return "any";
        }
    }
}
