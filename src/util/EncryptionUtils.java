package util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {
    public static String getMd5From(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;

        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(string.getBytes());
        byte[] keys = messageDigest.digest();

        return new BASE64Encoder().encode(keys);
    }
}
