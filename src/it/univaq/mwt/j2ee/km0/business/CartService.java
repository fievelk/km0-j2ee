package it.univaq.mwt.j2ee.km0.business;

import java.util.List;

import it.univaq.mwt.j2ee.km0.business.model.Cart;
import it.univaq.mwt.j2ee.km0.business.model.CartLine;

public interface CartService {
	
	void createCart(Cart cart);
	
	void updateCart(Cart cart);
	
	void deleteCart(long oid_cart);
	
	Cart viewCart(long oid_user, long oid_cart);
	
	List<Cart> viewUserOrders(long oid_user);
	
	List<Cart> viewSellerOrders(long oid_seller);
	
	List<Cart> viewAdminOrders();
	
	// CRUD per le CartLine
	
	void createCartLine(CartLine cartline);

	void updateCartLine(CartLine cartline);

	void deleteCartLine(long oid_cartLine);
	
	List<CartLine> viewCartLines(long oid_cart);

}
