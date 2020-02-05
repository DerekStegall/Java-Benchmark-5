package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

class ViewVolunteers {

    public static ArrayList<VolunteerRequest> loadVolunteers() {
        try {
            FileInputStream fileStream = new FileInputStream("volunteers.ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            ArrayList<VolunteerRequest> volunteers = (ArrayList<VolunteerRequest>) os.readObject();
            os.close();
            return volunteers;
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<VolunteerRequest>();
        }
    }

    private static void volunteers(ArrayList<VolunteerRequest> allVolunteers, ArrayList<Object> groups) {
        for (VolunteerRequest volunteer : allVolunteers) {
            Map<String, String> person = volunteer.volunteer;
            String volunteerName = person.get("name") + " " + person.get("last-name");
            Set<Entry<String, String>> form = person.entrySet();

            for (Entry answers : form) {
                if (answers.getValue().equals("yes")) {
                    Object volunteeredGroup = answers.getKey();
                    groups.add(volunteeredGroup);
                }
            }
            System.out.println(volunteerName + " - " + person.get("phone-number") + " - " + person.get("email")
                    + " is interested in: " + groups.toString().replace("[", "").replace("]", ""));

        }
    }

    public static void main(String[] args) {
        ArrayList<VolunteerRequest> allVolunteers = loadVolunteers();
        Map<String, VolunteerRequest> access = new HashMap<>();
        ArrayList<Object> groups = new ArrayList<>();

        volunteers(allVolunteers, groups);

    }

}