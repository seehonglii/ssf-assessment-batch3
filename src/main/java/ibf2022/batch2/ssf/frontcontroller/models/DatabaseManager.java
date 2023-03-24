package ibf2022.batch2.ssf.frontcontroller.models;

import ibf2022.batch2.ssf.frontcontroller.respositories.AuthenticationRepository;

public class DatabaseManager {
    private Login username; 
    private Authenticate authId;
    private AuthenticationRepository;
    
    public DatabaseManager(Login username, Authenticate authId) {
        this.username = username;
        this.authId = authId;

        //to extract all the authId with the same username from AuthenticationRepository

    } 

    
}
