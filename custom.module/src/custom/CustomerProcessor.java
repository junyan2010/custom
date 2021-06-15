package custom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerProcessor implements java.io.Serializable {
	private static final long serialVersionUID = 5532700362193344596L;
	private JDBCConnectionConfig _jdbcconnectionconfig;
	private JDBCConnection _jdbcconnection;
	private Customer1List _customer1list;
	public CustomerProcessor()
	{
		_jdbcconnectionconfig = new JDBCConnectionConfig();
		_jdbcconnection = new JDBCConnection(); 
	}
	public JDBCConnectionConfig get_jdbcconnectionconfig() {
		return _jdbcconnectionconfig;
	}
	public Customer1List get_customer1list() {
		return _customer1list;
	}
	public boolean load(CustomerList custlist)
	{
		boolean bretval = true;
		_customer1list = new Customer1List();
		bretval = _jdbcconnection.open(_jdbcconnectionconfig);
		if(bretval) {
			Statement cust1stmt = new Statement("select customer_id from customer1 where customer_no = ?");
			_jdbcconnection.prepare(cust1stmt);
			Statement cust1newstmt = new Statement("select customer1seq.nextval from dual");
			_jdbcconnection.prepare(cust1newstmt);

			for(Customer cust : custlist.get_customerlist()) {
				Customer1 cust1 = loadCustomer1(cust1stmt, cust1newstmt, cust);
				if(cust1 != null) {
					_customer1list.get_customer1list().add(cust1);

					loadCustomer1Phone(cust1.get_phonelist(), cust.get_customer_no(), (List<String>)cust.get_phonetypelist().clone());
					loadCustomer1Address(cust1.get_addresslist(), cust.get_customer_no(), (List<String>)cust.get_addresstypelist().clone());
				}
			}
			cust1stmt.close();
			cust1newstmt.close();
		}
		_jdbcconnection.close();
		return bretval;
	}
	public Customer1 loadCustomer1(Statement cust1stmt, Statement cust1newstmt, Customer cust)
	{
		Customer1 cust1 = null;
		int opcode = cust.get_opcode();
		String customer_no = cust.get_customer_no(); 

		try {
			ArrayList<Object> pl = new ArrayList<Object>();
			pl.add(customer_no);
			cust1stmt.execute(pl);

			ResultSet resset = cust1stmt.get_resultset();
			boolean hasrecord = resset.next();
			if(hasrecord) {
				cust1 = new Customer1();
				if(opcode == 1) { //insert
					opcode = 2;
				}
				cust1.set_opcode(opcode);
				cust1.set_customer_id(resset.getLong("customer_id"));
				cust1.set_customer_no(customer_no);
			}
			resset.close();
			if(!hasrecord) {
				if(opcode != 3) { //delete
					cust1newstmt.execute();
					ResultSet newresset = cust1newstmt.get_resultset();
					hasrecord = newresset.next();
					if(hasrecord) {
						cust1 = new Customer1();
						cust1.set_opcode(1); //insert
						cust1.set_customer_id(newresset.getLong(1));
						cust1.set_customer_no(customer_no);
					}
					newresset.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cust1;
	}
	public boolean loadCustomer1Phone(List<CustPhone1> phonelist, String customer_no, List<String> phonetypelist) {
		try {
			Statement custphone1idstmt = new Statement("select phone_type, phone_id from customer1, custphone1 " +
					"where customer1.customer_id = custphone1.customer_id and customer_no = ?");
			_jdbcconnection.prepare(custphone1idstmt);
			
			ArrayList<Object> pl = new ArrayList<Object>();
			pl.add(customer_no);
			custphone1idstmt.execute(pl);
			
			ResultSet rs = custphone1idstmt.get_resultset();
			while(rs.next()) {
				CustPhone1 custphone1 = new CustPhone1();
				phonelist.add(custphone1);

				String phonetype = rs.getString("phone_type");
				int ind = phonetypelist.indexOf(phonetype);
				if(ind >= 0) {
					custphone1.set_opcode(2); //update
					phonetypelist.remove(ind);
				} else {
					custphone1.set_opcode(3); //delete
				}
				custphone1.set_phone_id(rs.getLong("phone_id"));
				custphone1.set_phonetype(phonetype);
			}
			rs.close();
			custphone1idstmt.close();

			Statement custphone1newidstmt = new Statement("select custphone1seq.nextval from dual");
			_jdbcconnection.prepare(custphone1newidstmt);
			
			for(String phonetype : phonetypelist) {
				custphone1newidstmt.execute();
				
				ResultSet rsn = custphone1newidstmt.get_resultset(); 
				if(rsn.next()) {
					CustPhone1 custphone1 = new CustPhone1();
					phonelist.add(custphone1);

					custphone1.set_opcode(1); //insert
					custphone1.set_phone_id(rsn.getLong(1));
					custphone1.set_phonetype(phonetype);
				}
				rsn.close();
			}
			custphone1newidstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean loadCustomer1Address(List<CustAddress1> addresslist, String customer_no, List<String> addresstypelist) {
		try {
			Statement custaddress1idstmt = new Statement("select address_type, address_id from customer1, custaddress1 " +
					"where customer1.customer_id = custaddress1.customer_id and customer_no = ?");
			_jdbcconnection.prepare(custaddress1idstmt);

			ArrayList<Object> pl = new ArrayList<Object>();
			pl.add(customer_no);
			custaddress1idstmt.execute(pl);

			ResultSet rs = custaddress1idstmt.get_resultset();
			while(rs.next()) {
				CustAddress1 custaddress1 = new CustAddress1();
				addresslist.add(custaddress1);

				String addresstype = rs.getString("address_type");
				int ind = addresstypelist.indexOf(addresstype);
				if(ind >= 0) {
					custaddress1.set_opcode(2); //update
					addresstypelist.remove(ind);
				} else {
					custaddress1.set_opcode(3); //delete
				}
				custaddress1.set_address_id(rs.getLong("address_id"));
				custaddress1.set_addresstype(addresstype);
			}
			rs.close();
			custaddress1idstmt.close();
			
			Statement custaddress1newidstmt = new Statement("select custaddress1seq.nextval from dual");
			_jdbcconnection.prepare(custaddress1newidstmt);
			for(String addresstype : addresstypelist) {
				custaddress1newidstmt.execute();
				
				ResultSet rsn = custaddress1newidstmt.get_resultset(); 
				if(rsn.next()) {
					CustAddress1 custaddress1 = new CustAddress1();
					addresslist.add(custaddress1);

					custaddress1.set_opcode(1); //insert
					custaddress1.set_address_id(rsn.getLong(1));
					custaddress1.set_addresstype(addresstype);
				}
				rsn.close();
			}
			custaddress1newidstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
