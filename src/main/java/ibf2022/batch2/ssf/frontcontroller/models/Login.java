package ibf2022.batch2.ssf.frontcontroller.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.NotNull;

public class Login implements Serializable {
        
    @NotNull(message="must be at least 2 characters in length")
    private String username;

    @NotNull(message="must be at least 2 characters in length")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    } 

    public static Login create(String json) throws IOException{
        Login l = new Login();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            l.setUsername(o.getString("username"));
            l.setPassword(o.getString("password"));
        return l;  
        }          
    }
        
}
