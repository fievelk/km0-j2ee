package it.univaq.mwt.j2ee.kmZero.presentation.users;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.kmZero.business.impl.JDBCUserService;
import it.univaq.mwt.j2ee.kmZero.business.model.User;
import it.univaq.mwt.j2ee.kmZero.business.service.UserService;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public ActionForward views(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	/*public ActionForward insertStart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("form");
	}*/
	
	public ActionForward insert (ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		UserCreateForm form = (UserCreateForm) actionForm;
		User user = new User();
		/* Il throws Exception ci serve per prendere l'eccezione generata dalla BeanUtils */
		BeanUtils.copyProperties(user, form);
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		
		/* Vedere se il confronto delle password può essere fatto nella validazione invece che nella servlet */
		if (form.getPassword().equals(form.getConfirm_password())){
			service.createUser(user);
		}
		
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
	
	public ActionForward findAllPaginatedUsers(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		// TODO: finire di implementare il metodo e gli altri metodi.
		return mapping.findForward("success");
	}
	
}
