package dev.juanes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileUtils {
    private static final String SHA_256 = "SHA-256";

    public static String convertToHash(File file) {
        return convertToHash(file, SHA_256);
    }

    public static String convertToHash(File file, String algorithm) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return convertToHash(fis.readAllBytes(), algorithm);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToHash(InputStream is) {
        return convertToHash(is, SHA_256);
    }

    public static String convertToHash(InputStream is, String algorithm) {
        try {
            return convertToHash(is.readAllBytes(), algorithm);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToHash(byte[] bytes) {
        return convertToHash(bytes, SHA_256);
    }

    public static String convertToHash(byte[] bytes, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(bytes);
            byte[] hashBytes = digest.digest();
            return bytesToHex(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
