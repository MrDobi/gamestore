package edu.pdobrosz.gamestore.sdkuser;

import edu.pdobrosz.gamestore.common.WebService;

public class UserService extends WebService<User> {

	public UserService() {
		super("http://gamestore-user:9002/api");
	}

}