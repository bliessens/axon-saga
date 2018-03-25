package be.cegeka.vteam.axkeleton.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public abstract class AbstractFileGroupEvent {

    public abstract String getId();

    public String getDestination() {
        return getId().substring(0, 3);
    }

}
