package it.univaq.mwt.j2ee.kmZero.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.model.Category;
import it.univaq.mwt.j2ee.kmZero.business.model.Image;
import it.univaq.mwt.j2ee.kmZero.business.model.Product;
import it.univaq.mwt.j2ee.kmZero.business.model.Seller;
import it.univaq.mwt.j2ee.kmZero.business.model.User;
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
			preparedStatement.setLong(7, 1); /*Da sostituire con l'id del Seller. Va inserito il Seller tra i parametri di Product */
			
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

	/*
	public List<Product> viewProducts() throws BusinessException {
		List<Product> result = new ArrayList<Product>();

				Long id = 2L;
				String name = "productname";
				String description = "description";
				float price = 7.55f;
				Long categoryId= 1L;
				String categoryName = "category_name";
				Long sellerId= 2L;
				//String sellerName = rs.getString("seller_name");
				
				Category category = new Category(categoryId, categoryName);
				Seller seller = new Seller(sellerId);
				
				Product product = new Product(id, name, description, price, category, seller);
				
				result.add(product);
		
				return result;
	}
	*/

	@Override
	public List<Product> viewProducts() throws BusinessException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Product> result = new ArrayList<Product>();
		
		try {
			connection = dataSource.getConnection();
			String sql = "SELECT p.*, cat.name as category_name, s.company seller_company " +
						"FROM products p, categories cat, users u, sellers s " +
						"WHERE p.categories_id=cat.id AND p.sellers_users_id=u.id";
			
/*			
 			String sql = "SELECT p.*, cat.name as category_name, u.name as user_name, s.company seller_company " +
					"FROM products p, categories cat, users u, sellers s " +
					"WHERE p.categories_id=cat.id AND p.sellers_users_id=u.id";
*/		
					
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
		
			while (rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				Long categoryId= rs.getLong("categories_id");
				String categoryName = rs.getString("category_name");
				String company = rs.getString("seller_company");
				Long userId= rs.getLong("sellers_users_id");
				//String userName = rs.getString("user_name");
				
				Category category = new Category(categoryId, categoryName);
				
				Seller seller = new Seller(userId, company); // Instantiate a Seller object, using its User oid and company)
				//System.out.println("Seller company = " + seller.getCompany()); // Checks if it works
				
				Product product = new Product(id, name, description, price, category, seller);
				
				result.add(product);
				
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
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
		
		return result;
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

	public static void main(String[] args) {
		System.out.println("prova");
		
	}
	
}
