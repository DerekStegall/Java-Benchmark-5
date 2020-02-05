package app;

import java.io.Serializable;
import java.util.Map;

public class VolunteerRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    Map<String, String> volunteer;

    public VolunteerRequest(Map<String, String> volunteer) {
        this.volunteer = volunteer;
    }

}