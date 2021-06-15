package custom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCConnection {
	private Connection _connection;
	public JDBCConnection()
	{
	}
	public boolean open(JDBCConnectionConfig conf)
	{
		boolean bretval = true;
		try {
			_connection = DriverManager.getConnection(  
					conf.get_url(), conf.get_user(), conf.get_password());
		} catch (SQLException e) {
			_connection = null;
			bretval = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return bretval;
	}
	public boolean close()
	{
		boolean bretval = true;
		if(_connection != null) {
			try {
				_connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_connection = null;
		}
		return bretval;
	}
	public boolean prepare(Statement statement)
	{
		PreparedStatement pstmt = null;
		try {
			String sql = statement.get_sql();
			pstmt = _connection.prepareStatement(sql);
			statement.set_preparedstatement(pstmt);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
