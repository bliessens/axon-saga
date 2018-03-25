package be.cegeka.vteam.axkeleton.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileGroupCreatedEventTest {

    @Test
    public void testDestination() {
        final FileGroupCreatedEvent id = new FileGroupCreatedEvent("sc1-fsgdh", 2);

        assertEquals("sc1", id.getDestination());
    }
}