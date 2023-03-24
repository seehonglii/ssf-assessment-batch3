package ibf2022.batch2.ssf.frontcontroller.models;

import java.util.Random;

public class Authenticate {

    private String authId;
    private Login username;
    private Login password;     
    
    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String string) {
        this.authId = generateId(8);
    }

    public Login getUsername() {
        return username;
    }

    public void setUsername(Login username) {
        this.username = username;
    }

    public Login getPassword() {
        return password;
    }

    public void setPassword(Login password) {
        this.password = password;
    }

    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

    public void addAuthenticate(String message, String username) {
    }

    
    
}
