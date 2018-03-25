package be.cegeka.vteam.axkeleton.step1;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventHandlerInvoker;
import org.axonframework.eventhandling.SimpleEventHandlerInvoker;
import org.junit.Test;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;
import static org.junit.Assert.assertTrue;

public class EventHandlerInvokerTest {

    @Test
    public void testFind_EventHandlerMethod() {

        final EventHandlerInvoker invoker = new SimpleEventHandlerInvoker(new MyListener());

        assertTrue(invoker.hasHandler(asEventMessage(new MyEvent())));
    }

    @Test
    public void testInvoker_Calls_EventHandlerMethod() throws Exception {
        final MyListener listener = new MyListener();
        final EventHandlerInvoker invoker = new SimpleEventHandlerInvoker(listener);

        invoker.handle(asEventMessage(new MyEvent()));

        assertTrue(MyListener.invoked);
    }

    public static class MyListener {
        static boolean invoked = false;

        @EventHandler
        public void onEvent(MyEvent event) {
            invoked = true;
        }
    }

    public static class MyEvent {
    }
}
