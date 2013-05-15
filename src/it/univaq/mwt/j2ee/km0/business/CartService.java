package it.univaq.mwt.j2ee.km0.business;

import java.util.Date;
import java.util.List;

import it.univaq.mwt.j2ee.km0.business.model.Cart;

public interface CartService {
	
	void createCart (Cart cart);
	
	void editCart (long oid_cart);
	
	void deleteCart (long oid_cart);
	
	List<Cart> viewOrders ();
	
	Cart payment (Date paid);
	
	void leaveFeedback (long oid_cart);
	
	List<Cart> viewOrdersSeller (String p_iva);
	
	List<Cart> viewOrdersAdmin ();

}
