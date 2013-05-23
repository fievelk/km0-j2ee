package it.univaq.mwt.j2ee.kmZero.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.model.Category;
import it.univaq.mwt.j2ee.kmZero.business.model.Image;
import it.univaq.mwt.j2ee.kmZero.business.model.Product;
import it.univaq.mwt.j2ee.kmZero.business.service.ProductService;
import it.univaq.mwt.j2ee.kmZero.common.DateUtility;

public class JDBCProductService implements ProductService{

	private DataSource dataSource;
	
	public JDBCProductService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	
	@Override
	public void createProduct(Product product) throws BusinessException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		/* Date conversion from java.util.Date to java.sql.date format
		java.sql.Date sqldate_in = new java.sql.Date(product.getDate_in().getTime());
		java.sql.Date sqldate_out = new java.sql.Date(product.getDate_in().getTime());
		*/
		
		/* Data conversion with common.DateUtility class created on purpose */
		java.sql.Date sqlDate_in = DateUtility.convertUtilToSql(product.getDate_in());
		java.sql.Date sqlDate_out = DateUtility.convertUtilToSql(product.getDate_out());
		
		try {
			connection = dataSource.getConnection();
			
			String sql = "INSERT INTO products (id, name, description, " +
												"price, date_in, date_out, " +
												"categories_id, sellers_users_id)" +
												"VALUES (PRODUCTS_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setFloat(3, product.getPrice());
			preparedStatement.setDate(4, sqlDate_in);
			preparedStatement.setDate(5, sqlDate_out);
			preparedStatement.setLong(6, 1); /*Da sostituire con l'id della categoria. Va inserita la categoria tra i parametri di Product */
			preparedStatement.setLong(7, 2); /*Da sostituire con l'id del Seller. Va inserito il Seller tra i parametri di Product */
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
		
	}

	@Override
	public void updateProduct(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(long oid_product) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> viewProducts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCategory(Category category) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCategory(Category category) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategory(long oid_category) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> findAllCategories() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	private java.sql.Date convertDateToSqlFormat(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
}
