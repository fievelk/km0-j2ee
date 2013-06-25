package it.univaq.mwt.j2ee.kmZero.presentation.users;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.kmZero.business.RequestGrid;
import it.univaq.mwt.j2ee.kmZero.business.ResponseGrid;
import it.univaq.mwt.j2ee.kmZero.business.impl.JDBCUserService;
import it.univaq.mwt.j2ee.kmZero.business.model.Seller;
import it.univaq.mwt.j2ee.kmZero.business.model.User;
import it.univaq.mwt.j2ee.kmZero.business.service.UserService;
import it.univaq.mwt.j2ee.kmZero.common.DateConversionUtility;
import it.univaq.mwt.j2ee.kmZero.common.JsonUtility;
import it.univaq.mwt.j2ee.kmZero.common.ResponseUtility;
import it.univaq.mwt.j2ee.kmZero.presentation.common.RequestGridForm;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import sun.security.util.Password;

public class UserAction extends MappingDispatchAction{
	
	public ActionForward views(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	public ActionForward viewAllUsersPaginated(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		RequestGridForm form = (RequestGridForm) actionForm;
		RequestGrid requestGrid = new RequestGrid();
		BeanUtils.copyProperties(requestGrid, form);
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		ResponseGrid responseGrid = service.viewAllUsersPaginated(requestGrid);
		String json = JsonUtility.writeValueAsString(responseGrid);
		ResponseUtility.generateJsonResponse(response, json);
		
		return null;
	}
	
	public ActionForward insert (ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		UserCreateForm form = (UserCreateForm) actionForm;
		User user = new User();
		/* Il throws Exception ci serve per prendere l'eccezione generata dalla BeanUtils */
		BeanUtils.copyProperties(user, form);
		
		String dateBorn = req.getParameter("date_of_birth");
		user.setDate_of_birth(DateConversionUtility.stringToCalendar(dateBorn));
		user.setCreated(Calendar.getInstance());
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		
		service.createUser(user);
		
		return mapping.findForward("success");
	}
	
	public ActionForward updateStartUser(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		
		UserForm form = (UserForm) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance(); 
		UserService service = factory.getUserService();
		
		User user = service.findUserByPK(form.getOid());
		BeanUtils.copyProperties(form, user);
		// In questo modo faccio la conversione della data e la passo al form
		PropertyUtils.setProperty(form, "date_of_birth", DateConversionUtility.calendarDateToString(user.getDate_of_birth()));
		
		return mapping.findForward("form");
	}
	
	public ActionForward updateUser(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		UserForm form = (UserForm) actionForm;
		//User user = new User();
		//PropertyUtils.setProperty(user, "date_of_birth", DateConversionUtility.stringToCalendar(form.getDate_of_birth()));
		/*BeanUtils.copyProperty(user, "name", form.getName());
		BeanUtils.copyProperty(user, "surname", form.getSurname());
		BeanUtils.copyProperty(user, "email", form.getEmail());
		BeanUtils.copyProperty(user, "date_of_birth", DateConversionUtility.stringToCalendar(form.getDate_of_birth()));
		BeanUtils.copyProperty(user, "address", form.getAddress());*/
		//BeanUtils.copyProperties(user, form);
		User user = new User (form.getOid(), form.getName(), form.getSurname(), form.getEmail(), DateConversionUtility.stringToCalendar(form.getDate_of_birth()), form.getAddress());
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance(); 
		UserService service = factory.getUserService();
		
		service.updateUser(user);

		return mapping.findForward("success");
	}
	
	public ActionForward deleteUser(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		UserForm form = (UserForm) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		service.deleteUser(form.getOid());
		return mapping.findForward("success");
	}
	
	public ActionForward createSeller(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		SellerCreateForm form = (SellerCreateForm) actionForm;
		Seller seller = new Seller ();
		BeanUtils.copyProperties(seller, form);
		
		String dateBorn = req.getParameter("date_of_birth");
		seller.setDate_of_birth(DateConversionUtility.stringToCalendar(dateBorn));
		seller.setCreated(Calendar.getInstance());
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		service.createSeller(seller);
		
		return mapping.findForward("success");
	}
	
	public ActionForward viewAllSellersPaginated(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		RequestGridForm form = (RequestGridForm) actionForm;
		RequestGrid requestGrid = new RequestGrid();
		BeanUtils.copyProperties(requestGrid, form);
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		ResponseGrid responseGrid = service.viewAllSellersPaginated(requestGrid);
		String json = JsonUtility.writeValueAsString(responseGrid);
		ResponseUtility.generateJsonResponse(response, json);
		
		return null;
	}
	
	public ActionForward updateStartSeller(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		SellerForm form = (SellerForm) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		Seller seller = service.findSellerByPK(form.getOid());
		BeanUtils.copyProperties(form, seller);
		PropertyUtils.setProperty(form, "date_of_birth", DateConversionUtility.calendarDateToString(seller.getDate_of_birth()));
		return mapping.findForward("form");
	}
	
	public ActionForward updateStartSellerAdmin(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		SellerFormAdmin form = (SellerFormAdmin) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		Seller seller = service.findSellerByPK(form.getOid());
		BeanUtils.copyProperties(form, seller);
		PropertyUtils.setProperty(form, "date_of_birth", DateConversionUtility.calendarDateToString(seller.getDate_of_birth()));
		return mapping.findForward("form");
	}
	
	public ActionForward updateSeller(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		SellerForm form = (SellerForm) actionForm;
		Seller seller = new Seller (form.getOid(), form.getName(), form.getSurname(), form.getEmail(), DateConversionUtility.stringToCalendar(form.getDate_of_birth()),
				form.getAddress(), form.getUrl(), form.getPhone());
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		service.updateSeller(seller);
		return mapping.findForward("success");
	}
	
	public ActionForward updateSellerAdmin(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		SellerFormAdmin form = (SellerFormAdmin) actionForm;
		Seller seller = new Seller (form.getOid(), form.getName(), form.getSurname(), form.getEmail(), DateConversionUtility.stringToCalendar(form.getDate_of_birth()),
				form.getAddress(), form.getP_iva(), form.getCod_fisc(), form.getCompany(), form.getUrl(), form.getPhone());
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		service.updateSellerByAdmin(seller);
		return mapping.findForward("success");
	}
	
	public ActionForward deleteSeller(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		SellerFormAdmin form = (SellerFormAdmin) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		service.deleteSeller(form.getOid());
		return mapping.findForward("success");
	}
	
	public ActionForward updateStartPassword(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		return mapping.findForward("form");
	}
	
	public ActionForward updatePassword(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		PasswordForm form = (PasswordForm) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		User user = service.getPassword(form.getOid());
		String oldPass = user.getPassword();
		String newPass = form.getNewPass();
		System.out.println(oldPass + "1");
		System.out.println(form.getOldPass() + "2");
		if (oldPass.equals(DigestUtils.md5Hex(form.getOldPass()))){
			service.updatePassword(form.getOid(), newPass);
			return mapping.findForward("success");
		}
		
		return mapping.findForward("failure");
	}
	
}
