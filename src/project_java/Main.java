package project_java;

import java.sql.*;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Main {
	
	Login log =new Login();
	Register reg = new Register();
	Staffmenu staff = new Staffmenu();
	Usermenu user = new Usermenu();
	Koneksi konek = new Koneksi();
	BuyProduct b = new BuyProduct();
	Manageuser m = new Manageuser();
	ManageProduct p = new ManageProduct();
	ViewTransaction vt = new ViewTransaction();
	
	String email;
	String pass;
	String confrimPass;
	String address;
	String phone;
	
	String year;
	String Month;
	String date;
	
	String UserID;
	String UserID_login;
	String TransactionID;
	
	public Main(){
		main();
	}
	
	public void main(){
		log.login();
		log.submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = log.EmailInput.getText();
				String password = log.PassInput.getText();
				
				if(username.isEmpty()){
					JOptionPane.showMessageDialog(null, "You must fill the email field");
				}else if (password.isEmpty()){
					JOptionPane.showMessageDialog(null, "You must fill the password field");
				}else{
					
				konek.koneksi();
				String sql = "Select UserEmail, UserPassword,Role from user where UserEmail='"+log.EmailInput.getText()+"' "
						+ "and UserPassword='"+log.PassInput.getText().toString()+"' and Role = 'user'";
				
				
				String getID = "Select UserID from user where UserEmail = '"+username+"'";
				
				try {
					ResultSet rs=konek.stmt.executeQuery(sql);
					if(rs.next()){
						ResultSet resultID = konek.stmt.executeQuery(getID);
					
						resultID.next();
					
						UserID_login = resultID.getString("UserID");
					
					
					

						log.loginFrame.dispose();
						
						user.Usermenu();
						
			//---------------------user menu system---------------------
						
						user.logoff_item.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								user.Userframe.dispose();
								main();
							}
						});
						
						user.exit_item.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								user.Userframe.dispose();
							}
						});
						
						
						user.buy_item.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								b.buy();
								//
								b.checkout.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e){
							
								if(!(b.transaction.isEmpty())){
									konek.koneksi();
									try{
										String getID = "Select TransactionID from headertransaction ORDER BY TransactionID DESC";
										ResultSet resultID = konek.stmt.executeQuery(getID);
										
												
													if(resultID.next()){
														String lastID = resultID.getString("TransactionID");
														Integer idNumber = Integer.parseInt(lastID.substring(2,5));
														idNumber++;
														TransactionID = "TR" + String.format("%03d", idNumber);
													}else{
														TransactionID = "TR001";
													}
													
													String sql = "INSERT INTO headertransaction (TransactionID, UserID, TransactionDate) "
															+ "VALUES (?,?,CURRENT_TIME());";

														PreparedStatement pst = konek.con.prepareStatement(sql);
														pst.setString(1, TransactionID);
														pst.setString(2, UserID_login);										
											
														
														pst.executeUpdate();
														
													String sql1 = "INSERT INTO detailtransaction (TransactionID, ProductID, Qty) "
															+ "VALUES (?,?,?);";

														PreparedStatement pst1 = konek.con.prepareStatement(sql1);
														pst1.setString(1, TransactionID);
														pst1.setString(2, b.prID.getText());
														pst1.setString(3, Integer.toString(b.valQty));
												
															
														pst1.executeUpdate();

													String sql2 = "UPDATE product set ProductStock = ? where ProductID = ?";
															
													PreparedStatement pst2 = konek.con.prepareStatement(sql2);
													pst2.setString(1, Integer.toString(b.stockupdte));
													pst2.setString(2, b.productid);
											
														
													pst2.executeUpdate();
														
	
														
														JOptionPane.showMessageDialog(null, "Transaction Success");


									}catch(SQLException e1){
										e1.printStackTrace();
										
									}
								}else{
									JOptionPane.showMessageDialog(null, "your cart is empty");
									}
								}
							});
								//
							}
						});
			//-------------------------end user menu-------------------			
			
					}else{
	
						konek.koneksi();
						String sql1 = "Select UserEmail, UserPassword, Role from user where UserEmail='"+log.EmailInput.getText()+"' "
								+ "and UserPassword='"+log.PassInput.getText().toString()+"' and Role = 'staff'";
						try {
							ResultSet rs1=konek.stmt.executeQuery(sql1);
							if(rs1.next()){
								log.loginFrame.dispose();
								staff.Staffmenu();
								
				//---------------------staff menu system----------------------------
								
								staff.logoff_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										staff.Staffframe.dispose();
										main();
									}
								});
								
								staff.exit_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										staff.Staffframe.dispose();
									}
								});
								
								staff.user_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										m.Manage();
									}
								});
								
								staff.viewtransaction_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										vt.view();
									}
								});
								
								staff.product_item.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										p.Manage();
									}
								});
								
				//---------------------end staff menu system----------------------------
							}else{
								JOptionPane.showMessageDialog(null, "Invalid Email and Password");
								
							}
						}catch (SQLException e1){
						e1.printStackTrace();
						}

					}
					
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
}}
		});

		log.register_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.loginFrame.dispose();
				reg.Register();
				
				
				reg.submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						email = reg.EmailInput.getText();
						pass = reg.PassInput.getText();
						confrimPass = reg.ConfrimInput.getText();
						address = reg.AddressInput.getText();
						phone = reg.Phone.getText();

						year = (String) reg.yearList.getSelectedItem();
						Month = (String) reg.MonthList.getSelectedItem();
						date = (String) reg.DateList.getSelectedItem();
						
						
	//---------------------validating regist form----------------------------			
					if(email.isEmpty()){
						JOptionPane.showMessageDialog(null, "Please fill your Email");
						
					}else if(pass.isEmpty()){
						JOptionPane.showMessageDialog(null, "Please fill your Password");
						
					}else if(confrimPass.isEmpty()){
						JOptionPane.showMessageDialog(null, "Please fill your Confrim Password");
					
					}else if(Month.contains("Month") || year.contains("Year") || date.contains("Date")){
						JOptionPane.showMessageDialog(null, "Please choose your date of birth correctly");
						
					}else if(phone.isEmpty()){
						JOptionPane.showMessageDialog(null, "Please fill your Phone");
						
					}else if(address.isEmpty()){
						JOptionPane.showMessageDialog(null, "Please fill your Address");
						
					}else if(reg.genderGroup.isSelected(null)){
						JOptionPane.showMessageDialog(null, "Please choose your gender");
						
					}else{
						
						if(email.endsWith(".com") 
								&& (!email.startsWith("@") && !email.startsWith("."))
									&& (!email.contains("@."))
										&& (email.contains("@")&& email.contains(".")) &&(validasiEmail() == true)
									){
							if(passwordAlphanumeric() == true){	
								if(confrimPass.equals(pass)){
									if(address.endsWith(" Street")){
										int valYear = 2018 - Integer.parseInt(year);
										if(valYear >= 17){
											if(phoneNumeric() == true){//validasi phone number numeric
												if(phone.length() > 11){
										 
										//------------insert data to database-----------------
										//-----------generate user id------------
										konek.koneksi();
									try{
										String getID = "Select UserID from user ORDER BY UserID DESC";
										ResultSet resultID = konek.stmt.executeQuery(getID);
										
												
													if(resultID.next()){
														String lastID = resultID.getString("UserID");
														Integer idNumber = Integer.parseInt(lastID.substring(2,5));
														idNumber++;
														UserID = "CU" + String.format("%03d", idNumber);
													}else{
														UserID = "CU001";
													}
											
												
										//-----------end generate user id----------		
										String dob = year+"-"+reg.month()+"-"+date;
										
										String sql = "INSERT INTO user (UserID, UserEmail, UserPassword, UserGender, UserDOB, UserPhone, UserAddress, Role) "
												+ "VALUES (?,?,?,?,?,?,?,?);";

											PreparedStatement pst = konek.con.prepareStatement(sql);
											pst.setString(1, UserID);
											pst.setString(2, reg.EmailInput.getText());
											pst.setString(3, reg.PassInput.getText().toString());
											pst.setString(4, reg.genderGet);
											pst.setString(5, dob);
											pst.setString(6, reg.Phone.getText());
											pst.setString(7, reg.AddressInput.getText());
											pst.setString(8, "user");
											
											pst.executeUpdate();
											
											JOptionPane.showMessageDialog(null, "Registration Success");
											
											reg.registerFrame.dispose();
											main();
										}catch(SQLException e1){
											e1.printStackTrace();
											
										}
												}else{
													JOptionPane.showMessageDialog(null, "your phone number must be more than 11 digits");
												}
											}else{
												JOptionPane.showMessageDialog(null, "your phone number must be numeric only");
											}
										}else{
											JOptionPane.showMessageDialog(null, "you must 17years old or more ");
										}
										 
										//-----------------------------------------------------
									}else{
										JOptionPane.showMessageDialog(null, "Your address must end with Street");
									}
								}else{
									JOptionPane.showMessageDialog(null, "Your confrim password must same as you password");
								}
							}else{
								JOptionPane.showMessageDialog(null, "Your password must be alphanumeric");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Incorrect email format");
						
						}
					}
						
					}

		
				});
				
	//---------------------end validating regist form----------------------------
				
				reg.cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reg.registerFrame.dispose();
						main();
					}
				});
			}		
		});
			
	}
		
	public static void main(String[] args) {
		new Main();

	}
	public boolean validasiEmail(){
		int container1 = 0;
		int container2 = 0;
		for(int i = 0; i<email.length();i++){
			if(email.charAt(i)=='@'){
				container1 = container1 + 1;
			}else if(email.charAt(i)=='.'){
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
		if(pass.matches("[A-Za-z0-9]+")){
			return true;
		}else{
			return false;
		}
	}
	//validasi phone number (numeric only)
	public boolean phoneNumeric(){
		if(phone.matches("[0-9]+")){
			return true;
		}else{
			return false;
		}
	}
}
	/* cara kedua validasi phone number (numeric only)
	public boolean numeric() {
		for (int i = 0; i < phone.length(); i++) {
		      if (!Character.isDigit(phone.charAt(i)))
		        return false;
		    }
		    return true;
		  }
}*/