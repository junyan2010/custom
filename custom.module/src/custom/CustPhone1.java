package custom;

public class CustPhone1 implements java.io.Serializable {
	private static final long serialVersionUID = -7037533558906692941L;
	private String _phonetype;
	private long _phone_id;
	private int _opcode;
	public CustPhone1()
	{
	}
	public String get_phonetype() {
		return _phonetype;
	}
	public void set_phonetype(String _phonetype) {
		this._phonetype = _phonetype;
	}
	public long get_phone_id() {
		return _phone_id;
	}
	public void set_phone_id(long _phone_id) {
		this._phone_id = _phone_id;
	}
	public int get_opcode() {
		return _opcode;
	}
	public void set_opcode(int _opcode) {
		this._opcode = _opcode;
	}
}
