package it.univaq.mwt.j2ee.km0.presentation.common;

import it.univaq.mwt.j2ee.km0.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.km0.business.impl.JDBCKmZeroBusinessFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class KmZeroServletContextListner implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = ( Context ) initCtx.lookup( "java:comp/env" );
			DataSource dataSource = ( DataSource ) envCtx.lookup( "jdbc/librarydb" );
			KmZeroBusinessFactory.setInstance(new JDBCKmZeroBusinessFactory(dataSource));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
