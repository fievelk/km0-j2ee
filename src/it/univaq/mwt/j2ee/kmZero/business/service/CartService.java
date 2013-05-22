package it.univaq.mwt.j2ee.km0.business.service;

import java.util.List;

import it.univaq.mwt.j2ee.km0.business.BusinessException;
import it.univaq.mwt.j2ee.km0.business.model.Cart;
import it.univaq.mwt.j2ee.km0.business.model.CartLine;

public interface CartService {
	
	void createCart(Cart cart) throws BusinessException;
	
	void updateCart(Cart cart) throws BusinessException;
	
	void deleteCart(long oid_cart) throws BusinessException;
	
	Cart viewCart(long oid_user, long oid_cart) throws BusinessException;
	
	List<Cart> viewUserOrders(long oid_user) throws BusinessException;
	
	List<Cart> viewSellerOrders(long oid_seller) throws BusinessException;
	
	List<Cart> viewAdminOrders() throws BusinessException;
	
	// CRUD per le CartLine
	
	void createCartLine(CartLine cartline) throws BusinessException;

	void updateCartLine(CartLine cartline) throws BusinessException;

	void deleteCartLine(long oid_cartLine) throws BusinessException;
	
	List<CartLine> viewCartLines(long oid_cart) throws BusinessException;

}
