package it.univaq.mwt.j2ee.km0.business;

import java.util.Date;
import java.util.List;

import it.univaq.mwt.j2ee.km0.business.model.Cart;

public interface CartService {
	
	void createCart(Cart cart);
	
	void updateCart(long oid_cart);
	
	void deleteCart(long oid_cart);
	
	Cart viewCart(long oid_user, long oid_cart);
	
	List<Cart> viewOrders(long oid_user);
	
	Cart payment(Date paid);
	
	List<Cart> viewOrdersSeller(long oid_seller);
	
	List<Cart> viewOrdersAdmin();

}
