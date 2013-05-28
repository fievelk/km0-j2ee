package it.univaq.mwt.j2ee.kmZero.presentation.products;

import java.util.List;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.kmZero.business.model.Product;
import it.univaq.mwt.j2ee.kmZero.business.service.ProductService;
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
	
	public ActionForward insert(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		ProductForm form = (ProductForm) actionForm;
		Product product = new Product();
		BeanUtils.copyProperties(product, form);
		//ProductKind ProductKind = new ProductKind(form.getProductKindId());
		//product.setProductKind(ProductKind);

		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		ProductService service = factory.getProductService();

		service.createProduct(product);
		return mapping.findForward("success");
	}
	
}
