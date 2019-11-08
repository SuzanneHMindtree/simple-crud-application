package project_java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Manageuser {
	Koneksi konek = new Koneksi();
	
	Vector<String> header = new Vector<>();
	Vector<Vector<Object>> data = new Vector<>();
	Vector<Object> User;
	JFrame user;
	JTextField usrID,usrEmail, usrPhone, usrAddress;
	JButton insert, delete, update, submit, cancel;
	JPanel Content, ID, email, psswrd, dobSec, isi, phone, address, GenderSec, Role, bttn, insrt, dlt, updt, cncl, sbmt;
	JScrollPane scroll;
	JTable table;
	JLabel userID, userEmail, userPass, dob, userPhone, userAddress, Gender, userRole;
	JComboBox<String> yearList,MonthList,DateList,role;
	JPasswordField usrPass;
	JRadioButton Male, Female;
	String genderGet;
	ButtonGroup genderGroup;
	
	String UserID;
	//
	String getEmail;
	String getPass;
	String getAddress;
	String getPhone;

	String getRole;
	String year;
	String Month;
	String date;
	
	DefaultTableModel dtm;
	
	ResultSetMetaData rsm;
	
	/*public Manageuser(){
		Manage();
	}*/
	public void Manage(){
			
			user = new JFrame();
			user.setSize(1000, 600);
			user.setLocationRelativeTo(null);
			user.setLayout(new BorderLayout());
			user.setTitle("Manage User");
			
			//-------------start table atas-----------------
			header = new Vector<>();
			header.add("User ID");
			header.add("Email");
			header.add("Password");
			header.add("Gender");
			header.add("DOB");
			header.add("Phone");
			header.add("Address");
			header.add("Role");
			
			dtm = new DefaultTableModel(header, 0);
			
			
		try{
			
			konek.koneksi();
			String sql = "Select UserID, UserEmail, UserPassword, UserGender, UserDOB, UserPhone, UserAddress, Role from user";
			ResultSet rs=konek.stmt.executeQuery(sql);
			rsm = rs.getMetaData();
			
			
			while(rs.next()){
			

				User = new Vector();
				for(int i = 1; i<= rsm.getColumnCount(); i++){
					User.add(rs.getObject(i));
					
					}
					dtm.addRow(User);
			}
		}catch(SQLException e){
			e.printStackTrace(); 
		}
			
			
			table = new JTable(dtm);
			table.setAutoCreateRowSorter(true); //auto sorting
			
			//TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
			//table.setRowSorter(sorter);  cara sorting ke-2
			
			scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(0, 300));
			
			
			
			//-------------end table atas-----------------
			
			//------center container-------------
				
			Content = new JPanel(new GridLayout(1,2));
				//---------------start input layout---------
				isi = new JPanel(new GridLayout(8,1));
							
						//---------start id----------
						ID = new JPanel(new FlowLayout());
			
						userID = new JLabel();
						userID.setText("User ID :                ");
			
						usrID = new JTextField();
						usrID.setPreferredSize(new Dimension(200, 20));
						usrID.setEnabled(false);
				
						ID.add(userID);
						ID.add(usrID);
						//----end id-------------
						//----start email---------
				
						email = new JPanel(new FlowLayout());
			
						userEmail = new JLabel();
						userEmail.setText("User Email :          ");
			
						usrEmail = new JTextField();
						usrEmail.setPreferredSize(new Dimension(200, 20));
						usrEmail.setEnabled(false);
				
						email.add(userEmail);
						email.add(usrEmail);
						//----end email-----------
						//----start price----------
						psswrd = new JPanel(new FlowLayout());
			
						userPass = new JLabel();
						userPass.setText("User Password : ");
			
						usrPass = new JPasswordField();
						usrPass.setPreferredSize(new Dimension(200, 20));
						usrPass.setEnabled(false);
				
						psswrd.add(userPass);
						psswrd.add(usrPass);
						//----end price-------
						//----start quantity-------
						dobSec = new JPanel(new FlowLayout());
						
						dob = new JLabel();
						dob.setText("Day of Birth :     ");
					
						yearList = new JComboBox<String>();
						yearList.addItem("Year");
						for(int i=0;i<44;i++){
							yearList.addItem(Integer.toString(1975+i));
						}
						
						MonthList = new JComboBox<String>();
						MonthList.addItem("Month");
						MonthList.addItem("January");
						MonthList.addItem("February");
						MonthList.addItem("March");
						MonthList.addItem("April");
						MonthList.addItem("May");
						MonthList.addItem("June");
						MonthList.addItem("July");
						MonthList.addItem("August");
						MonthList.addItem("September");
						MonthList.addItem("October");
						MonthList.addItem("November");
						MonthList.addItem("December");
						
						DateList = new JComboBox<String>();
						DateList.addItem("Date");
						for(int i=1;i<32;i++){
							DateList.addItem(Integer.toString(i));
						}
						
						dobSec.add(dob);
						dobSec.add(yearList);
						dobSec.add(MonthList);
						dobSec.add(DateList);
						//------end quantity-----------
						//-------add, remove, checkout---------
						phone = new JPanel(new FlowLayout());
						
						userPhone = new JLabel();
						userPhone.setText("User Phone :        ");
			
						usrPhone = new JTextField();
						usrPhone.setPreferredSize(new Dimension(200, 20));
						usrPhone.setEnabled(false);
				
						phone.add(userPhone);
						phone.add(usrPhone);
						//-------------------------------------
						//-------add, remove, checkout---------
						address = new JPanel(new FlowLayout());
						
						userAddress = new JLabel();
						userAddress.setText("User Address :    ");
			
						usrAddress = new JTextField();
						usrAddress.setPreferredSize(new Dimension(200, 20));
						usrAddress.setEnabled(false);
				
						address.add(userAddress);
						address.add(usrAddress);
						//-------------------------------------
						//---------------------------Gender Panel-----------------------------------
						GenderSec = new JPanel(new FlowLayout());
						
						
						Gender = new JLabel();
						Gender.setText("User Gender :              ");
										
						Male = new JRadioButton("Male                  ");
						Male.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								genderGet = "Male";
								
							}
						});
						
											
						Female = new JRadioButton("Female");
						Female.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								genderGet = "Female";
								
							}
						});
						
						genderGroup = new ButtonGroup();
						genderGroup.add(Male);
						genderGroup.add(Female);
						
						GenderSec.add(Gender);
						
						GenderSec.add(Male);
						
						GenderSec.add(Female);
						//---------------------------End Gender ------------------------------------
						//-----------------------------------------
						Role = new JPanel(new FlowLayout());
						
						userRole = new JLabel();
						userRole.setText("User Role :            ");
			
						role = new JComboBox<String>();
						role.addItem("Choose");
						role.addItem("staff");
						role.addItem("user");
						role.setPreferredSize(new Dimension(200, 20));
				
						Role.add(userRole);
						Role.add(role);
						//----------------------------------------------
				isi.add(ID);
				isi.add(email);
				isi.add(psswrd);
				isi.add(dobSec);	
				isi.add(phone);
				isi.add(address);
				isi.add(GenderSec);
				isi.add(Role);
				//---end input layout--------
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						int i = table.getSelectedRow();
						
						usrID.setText(table.getValueAt(i, 0).toString());
						
						//
						update.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								submit.setEnabled(true);
								cancel.setEnabled(true);
								delete.setEnabled(false);
								insert.setEnabled(false);
								
								usrEmail.setEnabled(true);
								usrPass.setEnabled(true);
								usrPhone.setEnabled(true);
								usrAddress.setEnabled(true);
								
								int i = table.getSelectedRow();
								
								usrID.setText(table.getValueAt(i, 0).toString());
								usrEmail.setText(table.getValueAt(i, 1).toString());
								usrPass.setText(table.getValueAt(i, 2).toString());
								usrPhone.setText(table.getValueAt(i, 5).toString());
								usrAddress.setText(table.getValueAt(i, 6).toString());
								role.setSelectedItem(table.getValueAt(i, 7).toString());
								
								
							
							}
						});
						//
						delete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							try{
								usrID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
							try {
								
								
								
								String sql = "DELETE FROM user WHERE UserID = ?";
								
								PreparedStatement pst = konek.con.prepareStatement(sql);
								pst.setString(1, usrID.getText());

								
								pst.executeUpdate();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Delete Success");
								
								dtm.setRowCount(0); //untuk fungsi refresh
								try {
									String sql = "Select * from user";
									ResultSet rs=konek.stmt.executeQuery(sql);
									rsm = rs.getMetaData();
									while(rs.next()){
										User = new Vector<>();
										for(int i = 1; i<= rsm.getColumnCount(); i++){
											User.add(rs.getObject(i));
											
										}
											dtm.addRow(User);
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}catch(Exception e1){
								
							}
							}
						});
					}
				});
				
				
				//--------------------------
				bttn = new JPanel(new GridLayout(5,1)); 
				
				insrt = new JPanel(new FlowLayout());
				dlt = new JPanel(new FlowLayout());
				updt = new JPanel(new FlowLayout());
				sbmt = new JPanel(new FlowLayout());
				cncl = new JPanel(new FlowLayout());
				
				insert = new JButton("Insert");
				delete = new JButton("Delete");
				
				update = new JButton("Update");
				
				submit = new JButton("Submit");
				submit.setEnabled(false);
				
				cancel = new JButton("Cancel");
				cancel.setEnabled(false);
				
				insrt.add(insert);
				dlt.add(delete);
				updt.add(update);
				sbmt.add(submit);
				cncl.add(cancel);
				
				bttn.add(insrt);
				bttn.add(dlt);
				bttn.add(updt);
				bttn.add(sbmt);
				bttn.add(cncl);
				//-----------------
			Content.add(isi);
			Content.add(bttn);
			//------------end container------
			
			//---------------------------------------INSERT BUTTON------------------------------
			insert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					usrID.setText(null);
					usrPass.setEnabled(true);
					usrEmail.setEnabled(true);
					usrPhone.setEnabled(true);
					usrAddress.setEnabled(true);
					
					submit.setEnabled(true);
					cancel.setEnabled(true);
					delete.setEnabled(false);
					update.setEnabled(false);
					
					konek.koneksi();
					try{
						String getID = "Select UserID from user ORDER BY UserID DESC";
						ResultSet resultID = konek.stmt.executeQuery(getID);
						
								
						//---------------------------------------GENERATE USER ID--------------------------------------
									if(resultID.next()){
										String lastID = resultID.getString("UserID");
										Integer idNumber = Integer.parseInt(lastID.substring(2,5));
										idNumber++;
										UserID = "CU" + String.format("%03d", idNumber);
									}else{
										UserID = "CU001";
									}
						
									
					}catch(SQLException e1){
						e1.printStackTrace();	
					}
				}});
	
			//-----------------submit------------------------
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					getEmail = usrEmail.getText();
					getPass = usrPass.getText();
					getAddress = usrAddress.getText();
					getPhone = usrPhone.getText();
					
					getRole = (String) role.getSelectedItem();

					year = (String) yearList.getSelectedItem();
					Month = (String) MonthList.getSelectedItem();
					date = (String) DateList.getSelectedItem();
					
					
					
//---------------------validating insert dan update form----------------------------			
				if(getEmail.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill the Email");
					
				}else if(getPass.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill the Password");
				
				}else if(Month.contains("Month") || year.contains("Year") || date.contains("Date")){
					JOptionPane.showMessageDialog(null, "Please choose the date of birth correctly");
					
				}else if(getPhone.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill the Phone");
					
				}else if(getAddress.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill the Address");
					
				}else if(genderGroup.isSelected(null)){
					JOptionPane.showMessageDialog(null, "Please choose the gender");
				
				}else if(getRole.contains("Choose")){
					JOptionPane.showMessageDialog(null, "Please choose the Role");
				
				}else{
					
					if(getEmail.endsWith(".com") 
							&& (!getEmail.startsWith("@") && !getEmail.startsWith("."))
								&& (!getEmail.contains("@."))
									&& (getEmail.contains("@")&& getEmail.contains(".")) &&(validasiEmail() == true)
								){
						if(passwordAlphanumeric() == true){	
								if(getAddress.endsWith(" Street")){
									int valYear = 2018 - Integer.parseInt(year);
									if(valYear >= 17){
										if(phoneNumeric() == true){//validasi phone number numeric
											if(getPhone.length() > 11){
									 
							//------------insert data to database-----------------

							if(usrID.getText().isEmpty()){
									konek.koneksi();
								try{
										
									String dob = year+"-"+ month()+"-"+date;
									
									String sql = "INSERT INTO user (UserID, UserEmail, UserPassword, UserGender, UserDOB, UserPhone, UserAddress, Role) "
											+ "VALUES (?,?,?,?,?,?,?,?);";

										PreparedStatement pst = konek.con.prepareStatement(sql);
										pst.setString(1, UserID);
										pst.setString(2, usrEmail.getText());
										pst.setString(3, usrPass.getText().toString());
										pst.setString(4, genderGet);
										pst.setString(5, dob);
										pst.setString(6, usrPhone.getText());
										pst.setString(7, usrAddress.getText());
										pst.setString(8, getRole);
										
										pst.executeUpdate();
										
										JOptionPane.showMessageDialog(null, "Insert Success");
										
										
										
										
									}catch(SQLException e1){
										e1.printStackTrace();
										
									}
								//---------------update data to database---------------
							}else{

								String dob = year+"-"+ month()+"-"+date;
								
								try {
									
								
									String sql = "UPDATE user set UserEmail = ?, UserPassword = ?, UserGender = ?, UserDOB = ?, UserPhone = ?, UserAddress = ?, Role = ? where UserID = ?";
								
								
								
								PreparedStatement pst = konek.con.prepareStatement(sql);
								pst.setString(1, usrEmail.getText());
								pst.setString(2, usrPass.getText().toString());
								pst.setString(3, genderGet);
								pst.setString(4, dob);
								pst.setString(5, usrPhone.getText());
								pst.setString(6, usrAddress.getText());
								pst.setString(7, getRole);
								pst.setString(8, usrID.getText());

						
									
								pst.executeUpdate();
								
								JOptionPane.showMessageDialog(null, "Update Success");
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
											}else{
												JOptionPane.showMessageDialog(null, "the phone number must be more than 11 digits");
											}
										}else{
											JOptionPane.showMessageDialog(null, "the phone number must be numeric only");
										}
									}else{
										JOptionPane.showMessageDialog(null, "Date of Birth must be 17years old or more ");
									}
									 
									//-----------------------------------------------------
								}else{
									JOptionPane.showMessageDialog(null, "the address must end with Street");
								}
						}else{
							JOptionPane.showMessageDialog(null, "the password must be alphanumeric");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Incorrect email format");
					
					}
				}
				
				submit.setEnabled(false);
				cancel.setEnabled(false);
				delete.setEnabled(true);
				update.setEnabled(true);
				insert.setEnabled(true);

				usrID.setText(null);
				usrPass.setText(null);
				usrEmail.setText(null);
				usrPhone.setText(null);
				usrAddress.setText(null);
				yearList.setSelectedItem("Year");
				MonthList.setSelectedItem("Month");
				DateList.setSelectedItem("Date");
				role.setSelectedItem("Choose");
				
				
				
				dtm.setRowCount(0); //untuk fungsi refresh
				try {
					String sql = "Select * from user";
					ResultSet rs=konek.stmt.executeQuery(sql);
					rsm = rs.getMetaData();
					while(rs.next()){
						User = new Vector<>();
						for(int i = 1; i<= rsm.getColumnCount(); i++){
							User.add(rs.getObject(i));
							
						}
							dtm.addRow(User);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
			});
			
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					submit.setEnabled(false);
					cancel.setEnabled(false);
					delete.setEnabled(true);
					update.setEnabled(true);
					insert.setEnabled(true);
					
					usrID.setText(null);
					usrPass.setText(null);
					usrEmail.setText(null);
					usrPhone.setText(null);
					usrAddress.setText(null);
					
					usrPass.setEnabled(false);
					usrEmail.setEnabled(false);
					usrPhone.setEnabled(false);
					usrAddress.setEnabled(false);
					
					
				}
			});
			//
				
		user.add(scroll, "North");
		user.add(Content, "Center");
			
		user.setVisible(true);
	}
	
	public int month(){
		String Month = (String) MonthList.getSelectedItem();
		if(Month.contains("January")){
			return 1;
		}else if(Month.contains("February")){
			return 2;
		}else if(Month.contains("March")){
			return 3;
		}else if(Month.contains("April")){
			return 4;
		}else if(Month.contains("May")){
			return 5;
		}else if(Month.contains("June")){
			return 6;
		}else if(Month.contains("July")){
			return 7;
		}else if(Month.contains("August")){
			return 8;
		}else if(Month.contains("September")){
			return 9;
		}else if(Month.contains("October")){
			return 10;
		}else if(Month.contains("November")){
			return 11;
		}else if(Month.contains("December")){
			return 12;
		}
		return month();
		
	}
	public boolean validasiEmail(){
		int container1 = 0;
		int container2 = 0;
		for(int i = 0; i<getEmail.length();i++){
			if(getEmail.charAt(i)=='@'){
				container1 = container1 + 1;
			}else if(getEmail.charAt(i)=='.'){
				container2 = container2 + 1;			
			}
		}
		if(container1 > 1 || container2 > 1){
			return false;
		}else{
			return true;
		}
	}
	
	//validasi password (alphanumeric only)
	public boolean passwordAlphanumeric(){
		if(getPass.matches("[A-Za-z0-9]+")){
			return true;
		}else{
			return false;
		}
	}
	//validasi phone number (numeric only)
	public boolean phoneNumeric(){
		if(getPhone.matches("[0-9]+")){
			return true;
		}else{
			return false;
		}
	}
}


