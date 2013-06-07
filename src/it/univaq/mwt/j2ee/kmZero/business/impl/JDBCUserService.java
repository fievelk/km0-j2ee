package it.univaq.mwt.j2ee.kmZero.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import it.univaq.mwt.j2ee.kmZero.business.BusinessException;
import it.univaq.mwt.j2ee.kmZero.business.RequestGrid;
import it.univaq.mwt.j2ee.kmZero.business.ResponseGrid;
import it.univaq.mwt.j2ee.kmZero.business.model.Seller;
import it.univaq.mwt.j2ee.kmZero.business.model.User;
import it.univaq.mwt.j2ee.kmZero.business.service.UserService;
import it.univaq.mwt.j2ee.kmZero.common.DateConversionUtility;

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
			preparedStatement.setTimestamp(5, new Timestamp(user.getCreated().getTimeInMillis())); // Conversione da util.Data a sql.Data
			preparedStatement.setTimestamp(6, new Timestamp(user.getDate_of_birth().getTimeInMillis()));
			preparedStatement.setTimestamp(7, new Timestamp(user.getLast_access().getTimeInMillis()));
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
			String sql = "update users set name=?, surname=?, email=?, created=?, date_of_birth=?, last_access=?, address=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getSurname());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setTimestamp(4, new Timestamp(user.getCreated().getTimeInMillis()));
			preparedStatement.setTimestamp(5, new Timestamp(user.getDate_of_birth().getTimeInMillis()));
			preparedStatement.setTimestamp(6, new Timestamp(user.getLast_access().getTimeInMillis()));
			preparedStatement.setString(7, user.getAddress());
			preparedStatement.setLong(8, user.getOid());
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

	// Metodo per la visualizzazione degli utenti. Questo metodo potrebbe non servirci
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
				Calendar created = DateConversionUtility.timestampToCalendar(rs.getTimestamp("created"));
				Calendar date_of_birth = DateConversionUtility.timestampToCalendar(rs.getTimestamp("date_of_birth"));
				Calendar last_access = DateConversionUtility.timestampToCalendar(rs.getTimestamp("last_access"));
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
				Calendar created = DateConversionUtility.timestampToCalendar(rs.getTimestamp("created"));
				Calendar date_of_birth = DateConversionUtility.timestampToCalendar(rs.getTimestamp("date_of_birth"));
				Calendar last_access = DateConversionUtility.timestampToCalendar(rs.getTimestamp("last_access"));
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
				Calendar created = DateConversionUtility.timestampToCalendar(rs.getTimestamp("created"));
				Calendar date_of_birth = DateConversionUtility.timestampToCalendar(rs.getTimestamp("date_of_birth"));
				Calendar last_access = DateConversionUtility.timestampToCalendar(rs.getTimestamp("last_access"));
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
		PreparedStatement preparedStatement = null, preparedStatement2 = null;
		ResultSet rs = null;
		long getId = 0;
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
			preparedStatement.setTimestamp(5, new Timestamp(seller.getCreated().getTimeInMillis()));
			preparedStatement.setTimestamp(6, new Timestamp(seller.getDate_of_birth().getTimeInMillis()));
			preparedStatement.setTimestamp(7, new Timestamp(seller.getLast_access().getTimeInMillis()));
			preparedStatement.setString(8, seller.getAddress());
			preparedStatement.executeUpdate();
			
			/* Mi prendo l'id appena creato e lo inserisco come id del seller */
			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()){
				getId = rs.getLong(1);
			}
			rs.close();
			preparedStatement.close();
			
			preparedStatement2.setLong(1, getId);
			preparedStatement2.setString(2, seller.getP_iva());
			preparedStatement2.setString(3, seller.getCod_fisc());
			preparedStatement2.setString(4, seller.getCompany());
			preparedStatement2.setString(5, seller.getUrl());
			preparedStatement2.setString(6, seller.getPhone());
			preparedStatement2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement2 != null){
				try {
					preparedStatement2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateSellerByAdmin(Seller seller) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null, preparedStatement2 = null;
		try {
			connection = dataSource.getConnection();
			String sql = "update users set name=?, surname=?, email=?, created=?, date_of_birth=?, last_access=?, address=? where id=?";
			String sql2 = "update sellers set p_iva=?, cod_fisc=?, company=?, url=?, phone=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement.setString(1, seller.getName());
			preparedStatement.setString(2, seller.getSurname());
			preparedStatement.setString(3, seller.getEmail());
			preparedStatement.setTimestamp(4, new Timestamp(seller.getCreated().getTimeInMillis()));
			preparedStatement.setTimestamp(5, new Timestamp(seller.getDate_of_birth().getTimeInMillis()));
			preparedStatement.setTimestamp(6, new Timestamp(seller.getLast_access().getTimeInMillis()));
			preparedStatement.setString(7, seller.getAddress());
			preparedStatement.setLong(8, seller.getOid());
			preparedStatement.executeUpdate();
			
			preparedStatement2.setString(1, seller.getP_iva());
			preparedStatement2.setString(2, seller.getCod_fisc());
			preparedStatement2.setString(3, seller.getCompany());
			preparedStatement2.setString(4, seller.getUrl());
			preparedStatement2.setString(5, seller.getPhone());
			preparedStatement2.setLong(6, seller.getOid());
			preparedStatement2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement2 != null){
				try {
					preparedStatement2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public void updateSeller(Seller seller) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null, preparedStatement2 = null;
		try {
			connection = dataSource.getConnection();
			String sql = "update users set name=?, surname=?, email=?, created=?, date_of_birth=?, last_access=?, address=? where id=?";
			String sql2 = "update sellers set url=?, phone=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement.setString(1, seller.getName());
			preparedStatement.setString(2, seller.getSurname());
			preparedStatement.setString(3, seller.getEmail());
			preparedStatement.setTimestamp(4, new Timestamp(seller.getCreated().getTimeInMillis()));
			preparedStatement.setTimestamp(5, new Timestamp(seller.getDate_of_birth().getTimeInMillis()));
			preparedStatement.setTimestamp(6, new Timestamp(seller.getLast_access().getTimeInMillis()));
			preparedStatement.setString(7, seller.getAddress());
			preparedStatement.setLong(8, seller.getOid());
			preparedStatement.executeUpdate();
			
			preparedStatement2.setString(1, seller.getUrl());
			preparedStatement2.setString(2, seller.getPhone());
			preparedStatement2.setLong(3, seller.getOid());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement2 != null){
				try {
					preparedStatement2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void deleteSeller(Seller seller) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "delete from sellers where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, seller.getOid());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public Seller findSellerByPK(Long oid) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null, preparedStatement2 = null;
		ResultSet rs = null, rs2 = null;
		Seller seller = new Seller();
		try {
			connection = dataSource.getConnection();
			String sqlUser = "select name, surname, email, created, date_of_birth, last_access, address from users where id=?";
			String sqlSeller = "select * from sellers where id=?";
			preparedStatement = connection.prepareStatement(sqlUser);
			preparedStatement2 = connection.prepareStatement(sqlSeller);
			preparedStatement.setLong(1, oid);
			preparedStatement2.setLong(1, oid);
			rs = preparedStatement.executeQuery();
			rs2 = preparedStatement2.executeQuery();
			if (rs.next()){
				seller.setName(rs.getString("name"));
				seller.setSurname(rs.getString("surname"));
				seller.setAddress(rs.getString("email"));
				seller.setCreated(DateConversionUtility.timestampToCalendar(rs.getTimestamp("created")));
				seller.setDate_of_birth(DateConversionUtility.timestampToCalendar(rs.getTimestamp("date_of_birth")));
				seller.setLast_access(DateConversionUtility.timestampToCalendar(rs.getTimestamp("last_access")));
				seller.setAddress(rs.getString("address"));
			}
			if (rs2.next()){
				seller.setP_iva(rs.getString("p_iva"));
				seller.setCod_fisc(rs.getString("cod_fisc"));
				seller.setCompany(rs.getString("company"));
				seller.setUrl(rs.getString("url"));
				seller.setPhone(rs.getString("phone"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement2 != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs2 != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return seller;
	}

	// Questo metodo potrebbe non servirci
	@Override
	public List<Seller> viewAllSellers() throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Seller> sellers = new ArrayList<Seller>();
		try {
			connection = dataSource.getConnection();
			String sql = "select id, company from sellers";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while(rs.next()){
				Long id = rs.getLong("id");
				String company = rs.getString("company");
				Seller seller = new Seller (id, company);
				sellers.add(seller);
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
		return sellers;
	}

	@Override
	public ResponseGrid viewAllSellersPaginated(RequestGrid requestGrid)
			throws BusinessException {
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
		String baseSearch = "select u.id, u.name, u.surname, s.p_iva, s.company, s.phone " +
			 	"from users u, sellers s " + 
			 	((!"".equals(requestGrid.getsSearch())) ? " and u.name like '" + ConversionUtility.addPercentSuffix(requestGrid.getsSearch()) + "'":"");
		
		String sql = "select * from (" +
					 "select rownum as rn, u.id, u.name, u.surname, s.p_iva, s.company, s.phone from (" +
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
		List<Seller> sellers = new ArrayList<Seller>();
		
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
				String p_iva = rs.getString("p_iva");
				String company = rs.getString("company");
				String phone = rs.getString("phone");
				Seller seller = new Seller (id, name, surname,p_iva, company, phone);
				sellers.add(seller);
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
		return new ResponseGrid(requestGrid.getsEcho(), records, records, sellers);
	}
	
	@Override
	public List<Seller> getSellersFromPaidCarts() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePassword(long oid, String password) throws BusinessException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String sql = "update users set password=? from users where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setLong(2, oid);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
