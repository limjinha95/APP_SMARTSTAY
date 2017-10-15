package smartstay.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import smartstay.model.dto.Office;
import smartstay.model.dto.User;

public class OfficeDao {
	private FactoryDao factory = FactoryDao.getInstance();

	public Connection getConnection() {
		return factory.getConnection();
	}
	

}
