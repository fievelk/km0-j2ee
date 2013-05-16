package it.univaq.mwt.j2ee.km0.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

public class UserAction extends MappingDispatchAction{
	
	public ActionForward insert (ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse response) throws Exception{
		String name = req.getParameter("name");
		String surnanme = req.getParameter("surname");
		String password = req.getParameter("password");
		
		
		return mapping.findForward("null");
	}

}
