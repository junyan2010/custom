package custom;

public class customMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapKeys mapkeys = new MapKeys();
		mapkeys.set("jdbc:oracle:thin:@localhost:1521/emp", "oracle.jdbc.OracleDriver", "empuser", "empuser");
		CustomerList custlist = new CustomerList();
		Customer cust = new Customer();
		custlist.get_customerlist().add(cust);
		cust.set_customer_no("JY");
		cust.set_opcode(1);
		cust.get_phonetypelist().add("Work");
		cust.get_addresstypelist().add("Home");
		mapkeys.map(custlist);
	}

}
