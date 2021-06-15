package custom;

import java.util.ArrayList;

public class Customer1 implements java.io.Serializable {
	private static final long serialVersionUID = -912797946153979673L;
	private String _customer_no;
	private long _customer_id;
	private int _opcode;
	private ArrayList<CustPhone1> _phonelist;
	private ArrayList<CustAddress1> _addresslist;
	
	public Customer1()
	{
		_phonelist = new ArrayList<CustPhone1>();
		_addresslist = new ArrayList<CustAddress1>();
	}

	public String get_customer_no() {
		return _customer_no;
	}

	public void set_customer_no(String _customer_no) {
		this._customer_no = _customer_no;
	}

	public long get_customer_id() {
		return _customer_id;
	}

	public void set_customer_id(long _customer_id) {
		this._customer_id = _customer_id;
	}

	public int get_opcode() {
		return _opcode;
	}

	public void set_opcode(int _opcode) {
		this._opcode = _opcode;
	}

	public ArrayList<CustPhone1> get_phonelist() {
		return _phonelist;
	}

	public void set_phonelist(ArrayList<CustPhone1> _phonelist) {
		this._phonelist = _phonelist;
	}

	public ArrayList<CustAddress1> get_addresslist() {
		return _addresslist;
	}

	public void set_addresslist(ArrayList<CustAddress1> _addresslist) {
		this._addresslist = _addresslist;
	}
}
