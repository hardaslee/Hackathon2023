package hackathonSec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PsswrdGen {
	public static String printStrongNess(String input) {
        // Checking lower alphabet in string
        int n = input.length();
        boolean hasLower = false, hasUpper = false,
                hasDigit = false, specialChar = false;
        Set<Character> set = new HashSet<Character>(
                Arrays.asList('!', '@', '#', '$', '%', '^', '&',
                        '*', '(', ')', '-', '+'));
        for (char i : input.toCharArray()) {
            if (Character.isLowerCase(i))
                hasLower = true;
            if (Character.isUpperCase(i))
                hasUpper = true;
            if (Character.isDigit(i))
                hasDigit = true;
            if (set.contains(i))
                specialChar = true;
        }

        // Strength of password
        System.out.print("Password Strength:- ");

        if (hasDigit && hasLower && hasUpper && specialChar && (n >= 8)) {
            System.out.print("Strong\nPassword Saved\n");
        return "Strong";
        } else if ((hasLower || hasUpper || specialChar) && (n >= 6)) {
            System.out.print("Moderate");
            return "Moderate";
        } else
            System.out.print("Weak");
        return "Weak";
    }

    // Main Code

    public static String generatePassword(int minLength) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Define the character sets for different types of characters
        String allCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;:'\",.<>?";
        String characters = " ";

        // Generate the rest of the password
        for (int i = 0; i < minLength; i++) {
            characters = allCharacters;
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        // Shuffle the characters to make it more unpredictable
        return shuffleString(password.toString());
    }

    // Function to shuffle a string
    public static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = (int) (Math.random() * characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        System.out.println("..:: PASSWORD STRENGTH CHECKER ::..");
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a password:");
            String password = scan.nextLine();
            String strength = printStrongNess(password);

            if ("Strong".equals(strength)) {
                System.out.println("Password Strength is: " + strength);
                break; // Exit the loop if the password is strong
            } else {
                System.out.println("\nPassword is " + strength + ". Would you like US to generate a stronger password for you? (Y/N)");
                String response = scan.nextLine();
                if (response.equalsIgnoreCase("Y")) {
                    // Generate and display a strong password
                    int minLength = 8;
                    String generatedPassword = generatePassword(minLength);
                    System.out.println("Generated Password: " + generatedPassword + " \nYour New Password is :" +generatedPassword);
                    break;
                } else {
                    System.out.println("Please try again.");
                }
            }
        }
    }
}

