package it.univaq.mwt.j2ee.kmZero.presentation.products;

import it.univaq.mwt.j2ee.kmZero.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.kmZero.business.model.Category;
import it.univaq.mwt.j2ee.kmZero.business.service.ProductService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;

public class CategoriesTilesAction implements Controller {

	@Override
	public void execute(ComponentContext context, HttpServletRequest request,
			HttpServletResponse response, ServletContext servletContext) throws Exception {

		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		ProductService service = factory.getProductService();
		
		List<Category> categories = service.findAllCategories();
		request.setAttribute("categories", categories);
		
	}

	@Override
	public void perform(ComponentContext context, HttpServletRequest request, 
			HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

	}

}
