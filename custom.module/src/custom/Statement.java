package custom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Statement {
	private String _sql;

	private PreparedStatement _preparedstatement;
	private ResultSet _resultset;

	public Statement(String sql) {
		_sql = sql;
	}
	public String get_sql() {
		return _sql;
	}
	public void set_preparedstatement(PreparedStatement _preparedstatement) {
		this._preparedstatement = _preparedstatement;
	}
	public ResultSet get_resultset() {
		return _resultset;
	}
	public boolean execute(){
		return execute(null);
	}
	public boolean execute(List<Object> parameters){
		try {
			if(parameters != null) {
				for(int i = 0; i < parameters.size(); i++) {
					Object param = parameters.get(i);
					if(param instanceof String) {
						_preparedstatement.setString(i + 1, (String)param);
					} else if(param instanceof Long) {
						_preparedstatement.setLong(i + 1, (Long)param);
					}
				}
			}
			_resultset = _preparedstatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean close() {
		try {
			_preparedstatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
