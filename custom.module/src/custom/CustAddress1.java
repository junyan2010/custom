package custom;

public class CustAddress1 implements java.io.Serializable {
	private static final long serialVersionUID = 9014718154595221022L;
	private String _addresstype;
	private long _address_id;
	private int _opcode;
	public CustAddress1()
	{
	}
	public String get_addresstype() {
		return _addresstype;
	}
	public void set_addresstype(String _addresstype) {
		this._addresstype = _addresstype;
	}
	public long get_address_id() {
		return _address_id;
	}
	public void set_address_id(long _address_id) {
		this._address_id = _address_id;
	}
	public int get_opcode() {
		return _opcode;
	}
	public void set_opcode(int _opcode) {
		this._opcode = _opcode;
	}
}
