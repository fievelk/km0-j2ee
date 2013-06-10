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
		//java.sql.Date sqlDate_in = DateUtility.convertUtilToSql(product.getDate_in());
		//java.sql.Date sqlDate_out = DateUtility.convertUtilToSql(product.getDate_out());
		
		try {
			connection = dataSource.getConnection();
			
			String sql = "INSERT INTO products (id, name, description, price, categories_id, sellers_users_id)" +
					"VALUES (PRODUCTS_SEQ.NEXTVAL,?,?,?,?,?)";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setFloat(3, product.getPrice());
			preparedStatement.setLong(4, product.getCategory().getOid());
			preparedStatement.setLong(5, 200); // Ho impostato l'id del seller a 200 per prova, poi andrà cambiato in maniera dinamica
//			preparedStatement.setLong(5, product.getSeller().getOid());
			
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
	public List<Product> viewProductsBySellerId(long id) throws BusinessException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Product> result = new ArrayList<Product>();
		
		try {
			connection = dataSource.getConnection();
			String sql = "SELECT p.*, cat.name as category_name, s.company seller_company " +
						"FROM products p, categories cat, users u, sellers s " +
						"WHERE p.categories_id=cat.id AND p.sellers_users_id=? p.sellers_users_id=u.id";
					
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id); // Passo alla query l'id inserito come parametro del metodo
			rs = preparedStatement.executeQuery();
		
			while (rs.next()) {
				Long oid = rs.getLong("id");
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
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Category> result = new ArrayList<Category>();
		
		try {
			connection = dataSource.getConnection();
			
			String sql = "SELECT * FROM Categories ORDER BY name";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				Long parent_id = rs.getLong("parent_id");
				
				Category category = new Category(id, name, parent_id);
				result.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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

	private java.sql.Date convertDateToSqlFormat(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
}
