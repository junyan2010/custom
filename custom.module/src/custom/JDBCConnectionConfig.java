package custom;

public class JDBCConnectionConfig {
	private String _url;
	private String _driver;
	private String _user;
	private String _password;
	public JDBCConnectionConfig() {
	}
	public String get_url() {
		return _url;
	}
	public void set_url(String _url) {
		this._url = _url;
	}
	public String get_driver() {
		return _driver;
	}
	public void set_driver(String _driver) {
		this._driver = _driver;
		try {
		   Class.forName(this._driver);
		}
		catch(ClassNotFoundException ex) {
		   System.out.println("Error: unable to load driver class!" + _driver);
		}
	}
	public String get_user() {
		return _user;
	}
	public void set_user(String _user) {
		this._user = _user;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String _password) {
		this._password = _password;
	}

}
