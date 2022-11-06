package services.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MakeHashPassword {
    private final String salt = "[B@41cf53f9";

    public  String makeSecurePassword(String passwordToHash) {
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(this.salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        return generatedPassword;
    }
}