package custom;

import java.util.ArrayList;

public class Customer implements java.io.Serializable {
	private static final long serialVersionUID = -4296396870050189426L;
	private String _customer_no;
	private int _opcode;
	private ArrayList<String> _phonetypelist;
	private ArrayList<String> _addresstypelist;
	
	public Customer()
	{
		_phonetypelist = new ArrayList<String>();
		_addresstypelist = new ArrayList<String>();
	}
	public String get_customer_no() {
		return _customer_no;
	}
	public void set_customer_no(String _customer_no) {
		this._customer_no = _customer_no;
	}
	public int get_opcode() {
		return _opcode;
	}
	public void set_opcode(int _opcode) {
		this._opcode = _opcode;
	}
	public ArrayList<String> get_phonetypelist() {
		return _phonetypelist;
	}
	public void set_phonetypelist(ArrayList<String> _phonetypelist) {
		this._phonetypelist = _phonetypelist;
	}
	public ArrayList<String> get_addresstypelist() {
		return _addresstypelist;
	}
	public void set_addresstypelist(ArrayList<String> _addresstypelist) {
		this._addresstypelist = _addresstypelist;
	}
}
