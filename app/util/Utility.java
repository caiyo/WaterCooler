package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import play.Play;

public class Utility {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static final Random random = new SecureRandom();
    
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static String hashString(String string, String salt){
        String returnPassword=null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((string+salt).getBytes("UTF-8"));
            returnPassword = bytesToHex(md.digest());
            
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnPassword;
    }
    
    public static String generateHexString(int size){
        byte salt [] = new byte[size];
        random.nextBytes(salt);
        return bytesToHex(salt);
    }
    
    public static String getBaseURL(){
        String env = Play.application().configuration().getString("deploy_env");
        if (env == null || env.equals("local"))
            return "127.0.0.1:9000";
        else
            //TODO add production URL;
            return "";
    }
}
