package custom;

import java.util.ArrayList;

public class CustomerList implements java.io.Serializable {
	private static final long serialVersionUID = -2291070298996751378L;
	public ArrayList<Customer> _customerlist;
	
	public CustomerList()
	{
		_customerlist = new ArrayList<Customer>();
	}
	public ArrayList<Customer> get_customerlist() {
		return _customerlist;
	}
	public void set_customerlist(ArrayList<Customer> _customerlist) {
		this._customerlist = _customerlist;
	}
}
