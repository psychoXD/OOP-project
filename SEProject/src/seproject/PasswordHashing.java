package seproject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author reticent
 */
public class PasswordHashing {
    
    public String getHash(char [] pwd) throws NoSuchAlgorithmException
    {
        String p = new String(pwd);
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte [] result = md.digest(p.getBytes());
        
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < result.length; i++)
        {
             sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
    
    public String getHash(char [] pwd, byte [] salt) throws NoSuchAlgorithmException
    {
        String p = new String(pwd) + new String(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte [] result = md.digest(p.getBytes());
        
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < result.length; i++)
        {
             sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
    public String getHash(String pwd, byte [] salt) throws NoSuchAlgorithmException
    {
        String p = pwd + new String(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte [] result = md.digest(p.getBytes());
        
        
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < result.length; i++)
        {
             sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
    
    public byte [] createSalt()
    {
         // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        
        return salt;
    }
    
}
