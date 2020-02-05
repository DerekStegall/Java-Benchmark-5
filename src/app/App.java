package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

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

    private static void saveVolunteers(ArrayList<VolunteerRequest> volunteers) {
        try {
            FileOutputStream fileStream = new FileOutputStream("volunteers.ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(volunteers);
            os.close();
        } catch (IOException ex) {
            System.out.println("Failed to update volunteer.");
        }
    }

    private static Map<String, String> fillOutForm(Scanner scanner, Map<String, String> volunteer) {
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("\nLast Name: ");
        String lastName = scanner.nextLine();
        System.out.println("\nPhone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("\nEmail: ");
        String email = scanner.nextLine();
        System.out.println("\nAre you interested in volunteering on the Worship team ?: (yes/no) ");
        String worhsipTeam = scanner.nextLine();
        System.out.println("\nAre you interested in volunteering on the Welcome team ?: (yes/no) ");
        String welcomeTeam = scanner.nextLine();
        System.out.println("\nAre you interested in volunteering on the Production team ?: (yes/no) ");
        String productionTeam = scanner.nextLine();
        System.out.println("\nAre you interested in leading the Community Groups ?: (yes/no) ");
        String communityGroup = scanner.nextLine();
        System.out.println("\nAre you interested in teaching for the Children's Ministry?: (yes/no) ");
        String childrensGroup = scanner.nextLine();
        System.out.println("\nAre you interested in volunteering for the Student Ministry?: (yes/no) ");
        String studentsGroup = scanner.nextLine();
        System.out.println(
                "\nAre you interested in hearing more informations on other volunteer opportunities?: (Yes/no) ");
        String moreInfo = scanner.nextLine();
        System.out.println("\nThanks!\n We'll get back to you as soon as possible.");

        volunteer.put("name", firstName);
        volunteer.put("last-name", lastName);
        volunteer.put("phone-number", phoneNumber);
        volunteer.put("email", email);
        volunteer.put("worhsip-team", worhsipTeam);
        volunteer.put("welcome-team", welcomeTeam);
        volunteer.put("production-team", productionTeam);
        volunteer.put("community-team", communityGroup);
        volunteer.put("childrens-group", childrensGroup);
        volunteer.put("students-group", studentsGroup);
        volunteer.put("more-info", moreInfo);

        return volunteer;
    }

    public static void main(String[] args) {
        Map<String, String> volunteer = new HashMap<>();
        ArrayList<VolunteerRequest> volunteers = loadVolunteers();
        Scanner scanner = new Scanner(System.in);

        Map<String, String> signedVolunteer = fillOutForm(scanner, volunteer);
        volunteers.add(new VolunteerRequest(signedVolunteer));

        saveVolunteers(volunteers);
    }

}