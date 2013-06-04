package it.univaq.mwt.j2ee.kmZero.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.RequestGrid;
import it.univaq.mwt.j2ee.kmZero.business.ResponseGrid;
import it.univaq.mwt.j2ee.kmZero.business.model.Seller;
import it.univaq.mwt.j2ee.kmZero.business.model.User;
import it.univaq.mwt.j2ee.kmZero.business.service.UserService;

public class JDBCUserService implements UserService{
	
	private DataSource dataSource;

	public JDBCUserService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void createUser(User user) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "insert into users (id, name, surname, email, password, created, date_of_birth, last_access, address)" +
					"values (USERS_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setDate(5, new Date(user.getCreated().getTime())); // Conversione da util.Data a sql.Data
			preparedStatement.setDate(5, user.getCreated());
			preparedStatement.setDate(6, new Date(user.getDate_of_birth().getTime()));
			preparedStatement.setDate(7, new Date(user.getLast_access().getTime()));
			preparedStatement.setString(8, user.getAddress());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	@Override
	public void updateUser(User user) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "update users set name=?, surname=?, email=?, password=?, created=?, date_of_birth=?, last_access=?, address=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setDate(5, new Date(user.getCreated().getTime())); // Conversione da util.Data a sql.Data
			preparedStatement.setDate(6, new Date(user.getDate_of_birth().getTime()));
			preparedStatement.setDate(7, new Date(user.getLast_access().getTime()));
			preparedStatement.setString(8, user.getAddress());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	@Override
	public void deleteUser(User user) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "delete from users where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, user.getOid());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	// Metodo per la visualizzazione degli utenti
	@Override
	public List<User> viewAllUsers() throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			connection = dataSource.getConnection();
			String sql = "select id, name, surname, email, created, date_of_birth, last_access, address from users";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				Date created = rs.getDate("created");
				Date date_of_birth = rs.getDate("date_of_birth");
				Date last_access = rs.getDate("last_access");
				String address = rs.getString("address");
				User user = new User (id, name, surname, email, created, date_of_birth, last_access, address);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return users;
	}
	
	// Metodo per la visualizzazione degli utenti tramite datatables
	@Override
	public ResponseGrid viewAllUsersPaginated(RequestGrid requestGrid) throws BusinessException {
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("id");
		} else {
			if ("name".equals(requestGrid.getSortCol())) {
				requestGrid.setSortCol("name");
			} else {
				requestGrid.setSortCol(requestGrid.getSortCol());
			}
		}
		String orderBy = (!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) ? "order by " + requestGrid.getSortCol() + " " + requestGrid.getSortDir() : "";
		String baseSearch = "select id, name, surname, email, created, date_of_birth, last_access, address " +
			 	"from users " + 
			 	((!"".equals(requestGrid.getsSearch())) ? " and name like '" + ConversionUtility.addPercentSuffix(requestGrid.getsSearch()) + "'":"");
		
		String sql = "select * from (" +
					 "select rownum as rn, id, name, surname, email, created, date_of_birth, last_access, address from (" +
					 	baseSearch +
					 	orderBy + 
					 ")" +
					 ")" + 
					 "where rn >= " + (requestGrid.getiDisplayStart() + 1)+ 
					 " and rownum<=" + requestGrid.getiDisplayLength();
		String countSql = "select count(*) from (" + baseSearch + ")";
		long records = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			//CONTA GLI ELEMENTI
			rs = statement.executeQuery(countSql);
			if (rs.next()) {
				records = rs.getLong(1);
			}
			//ESEGUI LA QUERY "LIMITED"
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				Date created = rs.getDate("created");
				Date date_of_birth = rs.getDate("date_of_birth");
				Date last_access = rs.getDate("last_access");
				String address = rs.getString("address");
				User user = new User (id, name, surname, email, created, date_of_birth, last_access, address);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return new ResponseGrid(requestGrid.getsEcho(), records, records, users);
	}
	
	// Questo metodo serve per poter prendere un utente, tramite id, e riempire il form con i dati di questo.
	@Override
	public User findUserByPK(Long oid) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		User user = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select name, surname, email, created, date_of_birth, last_access, address from users where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, oid);
			rs = preparedStatement.executeQuery();
			if (rs.next()){
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				Date created = rs.getDate("created");
				Date date_of_birth = rs.getDate("date_of_birth");
				Date last_access = rs.getDate("last_access");
				String address = rs.getString("address");
				user = new User (oid, name, surname, email, created, date_of_birth, last_access, address);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return user;
	}
	
	@Override
	public String getPassword(Long oid) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String password = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select password from users where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, oid);
			rs = preparedStatement.executeQuery();
			if (rs.next()){
				password = rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return password;
	}

	@Override
	public List<User> getUsersFromPaidCarts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	// Metodo per la crezione del Seller da zero (avviene al momento dell'iscrizione)
	@Override
	public void createSeller(Seller seller) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null, preparedStatement2 = null, preparedStatement3 = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sqlUser = "insert into users (id, name, surname, email, password, created, date_of_birth, last_access, address)" +
					"values (USERS_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?)";
			String sqlSeller = "insert into sellers (users_id, p_iva, cod_fisc, company, url, phone)" +
					"values (?,?,?,?,?,?)";
			
			preparedStatement = connection.prepareStatement(sqlUser);
			preparedStatement2 = connection.prepareStatement(sqlSeller);
			
			preparedStatement.setString(1, seller.getName());
			preparedStatement.setString(2, seller.getSurname());
			preparedStatement.setString(3, seller.getEmail());
			preparedStatement.setString(4, seller.getPassword());
			preparedStatement.setDate(5, new Date(seller.getCreated().getTime()));
			preparedStatement.setDate(6, new Date(seller.getDate_of_birth().getTime()));
			preparedStatement.setDate(7, new Date(seller.getLast_access().getTime()));
			preparedStatement.setString(8, seller.getAddress());
			/* Mi prendo l'id appena creato e lo inserisco come id del seller */
			
			
			/* ------------------------------------ */
			String sqlIdUser = "select id from users where name=? and surname=?";
			if (rs.next()){
				Long getId = rs.getLong("id");
			}
			/* ------------------------------------ */
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
	}

	@Override
	public void updateSeller(Seller seller) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSeller(Seller seller) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Seller> viewAllSellers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> getSellersFromPaidCarts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
