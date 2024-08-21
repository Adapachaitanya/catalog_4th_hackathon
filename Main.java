import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();
    }

    public static boolean verifyPassword(String enteredPassword, String storedHash) throws NoSuchAlgorithmException {
        return hashPassword(enteredPassword).equals(storedHash);
    }

    private List<String> correctPattern;

    public Main(List<String> correctPattern) {
        this.correctPattern = correctPattern;
    }

    public boolean verifyPattern(List<String> enteredPattern) {
        return correctPattern.equals(enteredPattern);
    }

    public String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    public boolean verifyOTP(String generatedOTP, String enteredOTP) {
        return generatedOTP.equals(enteredOTP);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        String storedHash = hashPassword("securePassword123");
        boolean isTextualPasswordValid = verifyPassword("securePassword123", storedHash);

        
        Main graphicalPassword = new Main(Arrays.asList("A1", "B2", "C3"));
        boolean isGraphicalPasswordValid = graphicalPassword.verifyPattern(Arrays.asList("A1", "B2", "C3"));

        
        Main otpSystem = new Main(null);
        String generatedOTP = otpSystem.generateOTP();
        System.out.println("Generated OTP: " + generatedOTP);  // Simulate sending OTP
        boolean isOTPValid = otpSystem.verifyOTP(generatedOTP, generatedOTP);  // Use the same OTP for testing

        if (isTextualPasswordValid && isGraphicalPasswordValid && isOTPValid) {
            System.out.println("Access Granted");
        } else {
            System.out.println("Access Denied");
        }
    }
}
