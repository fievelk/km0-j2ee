package it.univaq.mwt.j2ee.kmZero.business.service;

import java.util.List;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.RequestGrid;
import it.univaq.mwt.j2ee.kmZero.business.ResponseGrid;
import it.univaq.mwt.j2ee.kmZero.business.model.Seller;
import it.univaq.mwt.j2ee.kmZero.business.model.User;

public interface UserService {
	
	/* Per modificare la Password, facciamo un form dedicato? Al momento non � cos�. */
	
	void createUser(User user) throws BusinessException;
	
	void updateUser(User user) throws BusinessException;
	
	void deleteUser(long oid) throws BusinessException;
	
	/* Metodo vecchio per la visualizzazione della lista. Questo metodo potrebbe non servirci*/
	List<User> viewAllUsers() throws BusinessException;
	
	/* Metodo nuovo per la visualizzazione della lista tramite dataTables */
	ResponseGrid viewAllUsersPaginated(RequestGrid requestGrid) throws BusinessException;
	
	User findUserByPK (Long oid) throws BusinessException;
	
	String getPassword (Long oid) throws BusinessException;
	
	List<User> getUsersFromPaidCarts() throws BusinessException;
	
	void createSeller(Seller seller) throws BusinessException;
	
	/* Metodo che permette all'amministratore di poter modificare i dati sensibili del venditore */
	void updateSellerByAdmin(Seller seller) throws BusinessException;
	
	/* Il venditore non pu� modificare alcuni dei suoi dati manualmente e con questo metodo tutto questo viene garantito */
	void updateSeller(Seller seller) throws BusinessException;
	
	void deleteSeller(long oid) throws BusinessException;
	
	Seller findSellerByPK (Long oid) throws BusinessException;
	
	// Questo metodo viene usato quando lo user vuol vedere la lista dei venditori
	List<Seller> viewAllSellers() throws BusinessException;
	
	ResponseGrid viewAllSellersPaginated(RequestGrid requestGrid) throws BusinessException;
	
	List<Seller> getSellersFromPaidCarts() throws BusinessException;
	
	void updatePassword(long oid, String password) throws BusinessException;

}
