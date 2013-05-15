package it.univaq.mwt.j2ee.km0.business;

import java.util.List;

import it.univaq.mwt.j2ee.km0.business.model.User;

public interface UserService {
	
	void createUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(long oid_user);
	
	void userMapView(long oid_user, String address);
	
	void sellerMapView(long oid_seller, String address);
	
	List<User> viewAllUsers();

}
