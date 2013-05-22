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
	
	public ActionForward insertStart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("form");
	}
	
	public ActionForward insert (ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception{
		UserForm form = (UserForm) actionForm;
		User user = new User();
		/* Il throws Exception ci serve per prendere l'eccezione generata dalla BeanUtils */
		BeanUtils.copyProperties(user, form);
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance();
		UserService service = factory.getUserService();
		
		try {
			service.createUser(user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward updateStart(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm form = (UserForm) actionForm;
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance(); 
		UserService service = factory.getUserService();

		try {
			User user = service.findUserByPK(form.getOid());
			BeanUtils.copyProperties(form, user);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("form");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		UserForm form = (UserForm) actionForm;
		User user = new User();
		BeanUtils.copyProperties(user, form);
		
		KmZeroBusinessFactory factory = KmZeroBusinessFactory.getInstance(); 
		UserService service = factory.getUserService();

		try {
			service.updateUser(user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	

}
