package it.univaq.mwt.j2ee.km0.business.impl;

import java.util.List;

import it.univaq.mwt.j2ee.km0.business.BusinessException;
import it.univaq.mwt.j2ee.km0.business.UserService;
import it.univaq.mwt.j2ee.km0.business.model.Seller;
import it.univaq.mwt.j2ee.km0.business.model.User;

public class JDBCUserService implements UserService{

	@Override
	public void createUser(User user) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(long oid_user) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> viewAllUsers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersFromPaidCarts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createSeller(Seller seller) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSeller(Seller seller) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSeller(long oid_seller) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Seller> viewAllSellers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> getSellersFromPaidCarts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}