package project_java;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Register{
	
	JFrame registerFrame;
	JPanel regisSec,title,EmailSec,PassSec,ConfrimSec,dobSec,NumberSec,AddressSec,GenderSec,SubmitSec;
	JLabel registxt,Email,Pass,Confrim,dob,Number,Address,Gender;
	JTextField EmailInput,Phone,AddressInput;
	JPasswordField PassInput,ConfrimInput;
	JComboBox<String> yearList,MonthList,DateList;
	JRadioButton Male,Female;
	JButton submit,cancel;
	ButtonGroup genderGroup;
	String genderGet;

		public void Register(){
			registerFrame = new JFrame();
			//---------------------register panel------------------------------------------------
			regisSec = new JPanel(new GridLayout(9,1));
			
					//---------------------judul register panel----------------------------------
					title = new JPanel(new FlowLayout());
					
					registxt = new JLabel();
					registxt.setText("Register");
					registxt.setFont(new Font("Arial", Font.PLAIN ,20));
					
					title.add(registxt);
				
					registerFrame.add(title, "North");
					//-----------------------------end judul----------------------------------
					
					//---------------------panel email----------------------------------------
					EmailSec = new JPanel(new FlowLayout());
					
					Email = new JLabel();
					Email.setText("Email :                         ");
				
					EmailInput = new JTextField();
					EmailInput.setPreferredSize(new Dimension(200,20));
					
					EmailSec.add(Email);
					EmailSec.add(EmailInput);
					
					//------------------------end email----------------------------------------
					
					//----------------------panel password-------------------------------------
					PassSec = new JPanel(new FlowLayout());
					
					Pass = new JLabel();
					Pass.setText("Password :                ");
				
					PassInput = new JPasswordField();
					PassInput.setPreferredSize(new Dimension(200,20));
					PassInput.setEchoChar('*');
					
					PassSec.add(Pass);
					PassSec.add(PassInput);
					
					//------------------------end password--------------------------------------
					
					//---------------------panel confrim password----------------------------------------
					ConfrimSec = new JPanel(new FlowLayout());
					
					Confrim = new JLabel();
					Confrim.setText("Confrim Password :");
				
					ConfrimInput = new JPasswordField();
					ConfrimInput.setPreferredSize(new Dimension(200,20));
					ConfrimInput.setEchoChar('*');
					
					ConfrimSec.add(Confrim);
					ConfrimSec.add(ConfrimInput);
			
					//------------------------end confrim password----------------------------------------
					
					//-------------------------day of birth-------------------------------------
					dobSec = new JPanel(new FlowLayout());
					
					dob = new JLabel();
					dob.setText("Day of Birth :           ");
				
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

					//-------------------------end day of birth---------------------------------
					
					//-------------------------Phone Number-------------------------------------
					NumberSec = new JPanel(new FlowLayout());
					
					
					Number = new JLabel();
					Number.setText("Phone Number :       ");
					
					Phone = new JTextField();
					Phone.setPreferredSize(new Dimension(200,20));
					
					NumberSec.add(Number);
					NumberSec.add(Phone);

					//-------------------------end phone number---------------------------------
					
					//-------------------------Phone Address-------------------------------------
					AddressSec = new JPanel(new FlowLayout());
					
					Address = new JLabel();
					Address.setText("Address :                   ");
					
					AddressInput = new JTextField();
					AddressInput.setPreferredSize(new Dimension(200,20));
					
					AddressSec.add(Address);
					AddressSec.add(AddressInput);
										
					//-------------------------end Address---------------------------------
					
					//---------------------------Gender Panel-----------------------------------
					GenderSec = new JPanel(new FlowLayout());
					
					
					Gender = new JLabel();
					Gender.setText("Gender :                   ");
									
					Male = new JRadioButton("Male                    ");
					Male.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							genderGet = "Male";
							
						}
					});
					
										
					Female = new JRadioButton("Female        ");
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
					
					//----------------------panel Submit and cancel---------------------------
					SubmitSec = new JPanel(new FlowLayout()); 
						
					submit = new JButton("Submit");
					cancel = new JButton("Cancel");
					
					SubmitSec.add(submit);
					SubmitSec.add(cancel);
						
					//------------------------end submit and cancel---------------------------
					
			regisSec.add(title);
			regisSec.add(EmailSec);
			regisSec.add(PassSec);
			regisSec.add(ConfrimSec);
			regisSec.add(dobSec);
			regisSec.add(NumberSec);
			regisSec.add(AddressSec);
			regisSec.add(GenderSec);
			
			regisSec.add(SubmitSec);
				
			registerFrame.add(regisSec, "Center");
						
			//--------------------------end register Panel-----------------------------------------

			registerFrame.setSize(600,500);
			
			registerFrame.setLocationRelativeTo(null);
				
			registerFrame.setTitle("Register");
			
			registerFrame.setVisible(true);
				
		}
		
		//method mengubah month ke angka agar mudah dimasukan ke dalam database mysql
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
}