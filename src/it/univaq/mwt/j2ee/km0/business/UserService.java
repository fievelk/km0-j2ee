package it.univaq.mwt.j2ee.km0.business;

import java.util.List;

import it.univaq.mwt.j2ee.km0.business.model.Seller;
import it.univaq.mwt.j2ee.km0.business.model.User;

public interface UserService {
	
	void createUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(long oid_user);
	
	List<User> viewAllUsers();
	
	List<User> getUsersFromPaidCarts();
	
	void createSeller(Seller seller);
	
	void updateSeller(Seller seller);
	
	void deleteSeller(long oid_seller);
	
	List<Seller> viewAllSellers();
	
	List<Seller> getSellersFromPaidCarts();	

}
