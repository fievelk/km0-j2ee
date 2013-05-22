package it.univaq.mwt.j2ee.kmZero.business.service;

import java.util.List;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.model.Seller;
import it.univaq.mwt.j2ee.kmZero.business.model.User;

public interface UserService {
	
	void createUser(User user) throws BusinessException;
	
	void updateUser(User user) throws BusinessException;
	
	void deleteUser(long oid_user) throws BusinessException;
	
	List<User> viewAllUsers() throws BusinessException;
	
	List<User> getUsersFromPaidCarts() throws BusinessException;
	
	void createSeller(Seller seller) throws BusinessException;
	
	void updateSeller(Seller seller) throws BusinessException;
	
	void deleteSeller(long oid_seller) throws BusinessException;
	
	List<Seller> viewAllSellers() throws BusinessException;
	
	List<Seller> getSellersFromPaidCarts() throws BusinessException;

	User findUserByPK(long oid) throws BusinessException;
	

}
