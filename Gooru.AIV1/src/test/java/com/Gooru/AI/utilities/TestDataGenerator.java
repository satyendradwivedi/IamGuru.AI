package com.Gooru.AI.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;


public class TestDataGenerator {

    private static final Random random = new Random();

    // Generate random first name (letters only)
    public static String generateFirstName() {
        return "Smith" + getRandomLetters(3);  // e.g., "SmithJax"
    }

    // Generate random last name (letters only)
    public static String generateLastName() {
        return "Mac" + getRandomLetters(2); // e.g., "MacRa"
    }

    public static String generateEmail() {
        return "Smith_" + System.currentTimeMillis() + "@yopmail.com";
    }

    public static String generatePassword() {
        return "Test@" + (1000 + random.nextInt(9000)); // e.g., Test@8234
    }

    public static String generatePhoneNumber() {
        return "9" + (100000000L + random.nextInt(900000000)); // e.g., 9234567890
    }

    public static String generateDOB() {
        LocalDate dob = LocalDate.now().minusYears(15); // Ensures user is at least 13
        return dob.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public static String generateLearnerBio() {
        return "I am a tech enthusiast. I want to enroll into the IamGuru Application to explore new things.";
    }

    public static String getPlatformName() {
        return "Facebook";
    }

    public static String getPlatformUrl() {
        return "https://www.facebook.com";
    }

    public static String getQuality() {
        return "Leadership";
    }

    // 🔤 Helper: Generate a random alphabetic string
    private static String getRandomLetters(int length) {
        StringBuilder builder = new StringBuilder(length);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < length; i++) {
            builder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return builder.toString();
    }
}
