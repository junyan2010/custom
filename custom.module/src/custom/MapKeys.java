package custom;

import com.tibco.security.ObfuscationEngine;

public class MapKeys implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private CustomerProcessor _customerprocessor;
	public MapKeys()
	{
		_customerprocessor = new CustomerProcessor();
	}
	public boolean set(String url, String driver, String user, String password) {
		JDBCConnectionConfig jdbccconf = _customerprocessor.get_jdbcconnectionconfig();
		jdbccconf.set_url(url);
		jdbccconf.set_driver(driver);
		jdbccconf.set_user(user);
		try {
			jdbccconf.set_password(new String(ObfuscationEngine.decrypt(password)));
		} catch (Exception e) {
			jdbccconf.set_password("empuser");
			System.out.println("Error in ObfuscationEngine.decrypt(password)");
		}

		return true;
	}
	public Customer1List map(CustomerList custlist)
	{
		_customerprocessor.load(custlist);
		Customer1List cust1list = _customerprocessor.get_customer1list();
		return cust1list;
	}
}
