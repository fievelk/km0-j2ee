package it.univaq.mwt.j2ee.kmZero.presentation.products;

import java.util.Calendar;
import java.util.List;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.kmZero.business.model.Category;
import it.univaq.mwt.j2ee.kmZero.business.model.Product;
import it.univaq.mwt.j2ee.kmZero.business.service.ProductService;
import it.univaq.mwt.j2ee.kmZero.common.DateConversionUtility;
import it.univaq.mwt.j2ee.kmZero.common.DateUtility;
import it.univaq.mwt.j2ee.kmZero.presentation.products.ProductForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

public class ProductAction extends MappingDispatchAction {
	
/* DA DECOMMENTARE QUANDO SI USERà DATATABLES

	public ActionForward views(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("success");
	}
*/
	
	public ActionForward views(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		ProductService service = factory.getProductService();
		List<Product> products = service.viewProducts();
		request.setAttribute("products", products);
		
		} catch (BusinessException e){
		    e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	public ActionForward viewsForSellers(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		ProductService service = factory.getProductService();
		List<Product> products = service.viewActiveProducts();
		
/*		for(Product product: products) {
			String date_inToString = DateConversionUtility.calendarDateToString(product.getDate_in());
			String date_outToString = DateConversionUtility.calendarDateToString(product.getDate_out());
		}*/
		
		request.setAttribute("products", products);

		
		} catch (BusinessException e){
		    e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	public ActionForward insert(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		ProductForm form = (ProductForm) actionForm;
		Product product = new Product();
		BeanUtils.copyProperties(product, form); // Copia tutti gli attributi dal form
		
		// setta la Category nel Product (copyProperties non era in grado di farlo)
		Category category = new Category(form.getCategoryId()); // Costruisce la Category dal categoryId
		product.setCategory(category); 
		
		// setta date_in e out nel Product senza utilizzare BeanUtils.copyProperties, per evitare problemi di conversione da String a Calendar //
		Calendar date_in = DateConversionUtility.stringToCalendar(req.getParameter("date_in"));
		product.setDate_in(date_in);
		Calendar date_out = DateConversionUtility.stringToCalendar(req.getParameter("date_out"));
		product.setDate_out(date_out);
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		ProductService service = factory.getProductService();

		service.createProduct(product);
		return mapping.findForward("success");
	}
	
	public ActionForward updateStart(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		ProductForm form = (ProductForm) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		ProductService service = factory.getProductService();
		
//		Product product = service.findProductById(form.getOid());
		//System.out.println(req.getAttribute("id"));
		Product product = service.findProductById(form.getOid());
		BeanUtils.copyProperties(form, product); // Copia tutti gli attributi dal product nel form
		
		// setta la Category nel form (copyProperties non era in grado di farlo)
		form.setCategoryId(product.getCategory().getOid());
		
		// setta manualmente le date nel dizionario request convertendole in String
		req.setAttribute("date_in", DateConversionUtility.calendarDateToString(product.getDate_in()));
		req.setAttribute("date_out", DateConversionUtility.calendarDateToString(product.getDate_out()));
		
		return mapping.findForward("form");
	}	
	
}
