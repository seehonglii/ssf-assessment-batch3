package ibf2022.batch2.ssf.frontcontroller.services;

import java.io.StringReader;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ibf2022.batch2.ssf.frontcontroller.models.Authenticate;
import ibf2022.batch2.ssf.frontcontroller.models.Login;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class AuthenticationService {

	public static final String AUTHENTICATE = "https://auth.chuklee.com/api/authenticate";
	
	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public Authenticate authenticate(String username, String password) throws Exception {
		JsonObject authPayload = Json.createObjectBuilder()
            .add("username", username)
            .add("password", password)
            .build();

		RequestEntity<JsonObject> req = RequestEntity.post(AUTHENTICATE)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(authPayload);

		ResponseEntity<String> resp;
		RestTemplate template = new RestTemplate();
		try {
			resp = template.exchange(req, String.class);
		} catch (Exception ex) {
			throw ex;
		}

		String payload = resp.getBody();
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject json = reader.readObject();

		Authenticate auth = new Authenticate();
		auth.setAuthId(json.getString("authId"));

		json.getJsonArray("authenticate").stream()
			.map(i -> i.asJsonObject())
			.forEach(i -> {
				auth.addAuthenticate(i.getString("message"), i.getString("username"));
			});

		return auth;
	}

	public boolean hasAuthenticated(String auth){
		if(null == auth)
			return false;
		return true; 
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		// Inject a cache manager
		@Autowired
		private DatabaseManager databaseManager;
		
		// Disable a user account for 30 minutes
		public void disableUser(String username) {
		  // Get the cache for storing user disable status
		  Database database = databaseManager.getDatabase("userDisableCache");
		  
		  // Calculate the timestamp for when the account should be re-enabled
		  long disableTimeMillis = System.currentTimeMillis() + 30 * 60 * 1000; 
		  
		  // Store the disable status and timestamp in the cache
		  database.put(username, disableTimeMillis);
		}
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		// Retrieve the locked user map from a persistent storage mechanism
		Map<String, LockedUser> lockedUsers = // Get the map from a database 

		// Check if the user is present in the locked user map
		if (lockedUsers.containsKey(username)) {
			// Calculate the time elapsed since the account was locked
			long lockedTime = lockedUsers.get(username).getTime();
			long elapsedTime = System.currentTimeMillis() - lockedTime;
	
			// Check if the elapsed time is less than the duration for which the account was locked
			if (elapsedTime < LOCK_DURATION) {
				return true; // The user's login is locked
			} else {
				// Remove the user from the locked user map
				lockedUsers.remove(username);
				// Update the persistent storage mechanism with the new map
				// ...
	
				return false; // The user's login is not locked
			}
		} else {
		return false;}
	}

	public Optional<Login> getLogin(Login login) {
		return null;
	}

	public boolean hasDisabled(String username) {
		return false;
	}
	
}
