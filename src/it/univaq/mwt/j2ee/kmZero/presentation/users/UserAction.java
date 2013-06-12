package it.univaq.mwt.j2ee.kmZero.presentation.users;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.kmZero.business.RequestGrid;
import it.univaq.mwt.j2ee.kmZero.business.ResponseGrid;
import it.univaq.mwt.j2ee.kmZero.business.impl.JDBCUserService;
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

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
	
	/*public ActionForward insertStart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("form");
	}*/
	
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
	
	public ActionForward updateStartUser(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm form = (UserForm) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance(); 
		UserService service = factory.getUserService();
		
		User user = service.findUserByPK(form.getOid());
		BeanUtils.copyProperties(form, user);
		
		return mapping.findForward("form");
	}
	
	public ActionForward updateUser(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		UserForm form = (UserForm) actionForm;
		User user = new User();
		BeanUtils.copyProperties(user, form);
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance(); 
		UserService service = factory.getUserService();
		
		service.updateUser(user);

		/* La modifica della password la faccio tramite metodi e form separati!
		 * 
		 * try {
			 Gestione MD5 per il confronto delle password e questo lo faccio con MessageDigest di Java Security 
			String oldPass = service.getPassword(form.getOid());
			if (form.getNew_password().equals(form.getConfirm_password()) && (form.getOld_password().equals(oldPass))){
				service.createUser(user);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}*/
		return mapping.findForward("success");
	}
	
	public ActionForward deleteUser(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		UserForm form = (UserForm) actionForm;
		User user = new User();
		BeanUtils.copyProperties(user, form);
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		service.deleteUser(user);
		return mapping.findForward("success");
	}
	
	public ActionForward createSeller(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		// TODO: prima di fare i metodi per il seller è opportuno creare il suo form.
		return mapping.findForward("success");
	}
	
}
