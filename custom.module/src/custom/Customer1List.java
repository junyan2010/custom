package custom;

import java.util.ArrayList;

public class Customer1List  implements java.io.Serializable {
	private static final long serialVersionUID = 4585102150870888998L;
	private ArrayList<Customer1> _customer1list;
	
	public Customer1List()
	{
		_customer1list = new ArrayList<Customer1>();
	}
	public ArrayList<Customer1> get_customer1list() {
		return _customer1list;
	}

	public void set_customer1list(ArrayList<Customer1> _customer1list) {
		this._customer1list = _customer1list;
	}
}
