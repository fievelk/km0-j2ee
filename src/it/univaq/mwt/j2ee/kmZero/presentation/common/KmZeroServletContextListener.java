package it.univaq.mwt.j2ee.kmZero.presentation.common;

import it.univaq.mwt.j2ee.kmZero.business.KmZeroBusinessFactory;
import it.univaq.mwt.j2ee.kmZero.business.impl.JDBCKmZeroBusinessFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class KmZeroServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = ( Context ) initCtx.lookup( "java:comp/env" ); 
			DataSource dataSource = ( DataSource ) envCtx.lookup( "jdbc/kmzerodb" );
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
