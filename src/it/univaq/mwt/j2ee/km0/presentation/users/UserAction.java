package it.univaq.mwt.j2ee.km0.presentation.users;

import it.univaq.mwt.j2ee.km0.business.BusinessException;
import it.univaq.mwt.j2ee.km0.business.impl.JDBCUserService;
import it.univaq.mwt.j2ee.km0.business.model.User;
import it.univaq.mwt.j2ee.km0.business.service.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

public class UserAction extends MappingDispatchAction{
	
	public ActionForward insert (ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse response){
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		DateFormat df = new SimpleDateFormat("dd/mm/aaaa"); /* Questo dovrei metterlo dentro una classe utility, in modo che possa parsare le date anche in altri metodi*/
		Date created = null, date_of_birth = null, last_access = null;
		try {
			created = df.parse(req.getParameter("created"));
			date_of_birth = df.parse(req.getParameter("date_of_birth"));
			last_access = df.parse(req.getParameter("last_access"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String address = req.getParameter("adress");
		User user = new User (name, surname, email, password, created, date_of_birth, last_access, address);
		/*UserService service = new JDBCUserService();
		try {
			service.createUser(user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}*/
		
		
		return mapping.findForward("null");
	}

}
