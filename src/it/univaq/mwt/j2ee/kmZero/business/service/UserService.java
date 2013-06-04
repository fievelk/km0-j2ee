package it.univaq.mwt.j2ee.kmZero.business.service;

import java.util.List;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.RequestGrid;
import it.univaq.mwt.j2ee.kmZero.business.ResponseGrid;
import it.univaq.mwt.j2ee.kmZero.business.model.Seller;
import it.univaq.mwt.j2ee.kmZero.business.model.User;

public interface UserService {
	
	void createUser(User user) throws BusinessException;
	
	void updateUser(User user) throws BusinessException;
	
	void deleteUser(User user) throws BusinessException;
	
	/* Metodo vecchio per la visualizzazione della lista*/
	List<User> viewAllUsers() throws BusinessException;
	
	/* Metodo nuovo per la visualizzazione della lista tramite dataTables */
	ResponseGrid viewAllUsersPaginated(RequestGrid requestGrid) throws BusinessException;
	
	User findUserByPK (Long oid) throws BusinessException;
	
	String getPassword (Long oid) throws BusinessException;
	
	List<User> getUsersFromPaidCarts() throws BusinessException;
	
	void createSeller(Seller seller) throws BusinessException;
	
	void updateSeller(Seller seller) throws BusinessException;
	
	void deleteSeller(Seller seller) throws BusinessException;
	
	List<Seller> viewAllSellers() throws BusinessException;
	
	List<Seller> getSellersFromPaidCarts() throws BusinessException;

}
