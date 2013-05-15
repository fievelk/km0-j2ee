package it.univaq.mwt.j2ee.km0.business;

import java.util.List;

import it.univaq.mwt.j2ee.km0.business.model.User;

public interface UserService {
	
	void createUser(User user) throws BusinessException;
	
	void updateUser(User user) throws BusinessException;
	
	void deleteUser(long oid_user) throws BusinessException;
	
	void userMapView(long oid_user, String address) throws BusinessException;
	
	void sellerMapView(long oid_seller, String address) throws BusinessException;
	
	List<User> viewAllUsers() throws BusinessException;

}
