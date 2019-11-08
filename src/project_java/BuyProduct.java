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
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


//import com.mysql.jdbc.ResultSetMetaData;

public class BuyProduct{
	String productNama;
	String productQty;
	
	Image img;
	
    Koneksi konek = new Koneksi();
    
	ResultSet rs;
	//result set metadata: informasi atau data 
	ResultSetMetaData rsm;
	
	//==============form input===========
	JTextField prID,prName,prPrice;
	JPanel Content, ID, name, price, quantity, isi, gambar, button;
	JScrollPane scroll,scroll2;
	JSpinner qty;
	JLabel productID,productName,productPrice,productqty,productImage, blank, blank2;
	JButton add, remove, checkout;
	//===============
	int valQty;
	int stock;
	
	
	int stockupdte;
	String productid;
	
	JFrame Frproduct;
	

	
	//table product
	Vector<Object> table_row;
	
	//table transaction
	Vector<Object> transaction;
	
	DefaultTableModel dtm;
	DefaultTableModel dtm2;
	
	public void buy(){
		Frproduct = new JFrame();
		Frproduct.setSize(1000, 600);
		Frproduct.setLocationRelativeTo(null);
		Frproduct.setLayout(new BorderLayout());
		Frproduct.setTitle("Buy Product");
		
		//----table header---
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Product Id");
		columnNames.add("Product Name");
		columnNames.add("Product Price");
		columnNames.add("Product Stock");
		columnNames.add("Product Image");
		dtm = new DefaultTableModel(columnNames, 0);
		
		//----------display table tentang product------------
		try {
			konek.koneksi();
			String sql = "Select ProductID, ProductName, ProductPrice, ProductStock, ProductImage from product";
			ResultSet rs=konek.stmt.executeQuery(sql);
			rsm = rs.getMetaData();
			while(rs.next()){
				table_row = new Vector<>();
				for(int i = 1; i<= rsm.getColumnCount(); i++){
					table_row.add(rs.getObject(i));
					
				}
					dtm.addRow(table_row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JTable table = new JTable(dtm);
		
		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(0, 350));
		
		
		//-----------end display-----------------
		
		Content = new JPanel(new GridLayout(1,3));

		//-------image layout-----------
		gambar = new JPanel(new FlowLayout());

		productImage = new JLabel();
		
		
		img = new ImageIcon(this.getClass().getResource("/img/noimage.png")).getImage();
		productImage.setIcon(new ImageIcon(img));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			try{
				img = new ImageIcon(this.getClass().getResource(table.getValueAt(table.getSelectedRow(), 4).toString())).getImage();
				productImage.setIcon(new ImageIcon(img));
			}catch(Exception e1){
				img = new ImageIcon(table.getValueAt(table.getSelectedRow(), 4).toString()).getImage();
				
				productImage.setIcon(new ImageIcon(img));
			}
			
			}
		});
		
		gambar.add(productImage);
		//----------------end image---------
		
		
		//---------------form input layout--------------
		isi = new JPanel(new GridLayout(5,1));
		
		//---------start id----------
		ID = new JPanel(new FlowLayout());

		productID = new JLabel();
		productID.setText("Product ID :");

		blank2 = new JLabel();
		blank2.setText("      ");
		
		prID = new JTextField();
		prID.setPreferredSize(new Dimension(200, 25));
		prID.setEnabled(false);

		ID.add(productID);
		ID.add(blank2);
		ID.add(prID);
		//----end id-------------
		//----start name---------

		name = new JPanel(new FlowLayout());

		productName = new JLabel();
		productName.setText("Product Name :");

		prName = new JTextField();
		prName.setPreferredSize(new Dimension(200, 25));
		prName.setEnabled(false);

		name.add(productName);
		name.add(prName);
		//----end name-----------
		//----start price----------
		price = new JPanel(new FlowLayout());

		productPrice = new JLabel();
		productPrice.setText("Product Price :");

		prPrice = new JTextField();
		prPrice.setPreferredSize(new Dimension(200, 25));
		prPrice.setEnabled(false);

		price.add(productPrice);
		price.add(prPrice);
		//----end price-------
		//----start quantity-------

		quantity = new JPanel(new FlowLayout());

		productqty = new JLabel();
		productqty.setText("Qty :");
		
		blank = new JLabel();
		blank.setText("                 ");

		qty = new JSpinner();
		qty.setPreferredSize(new Dimension(200, 25));

		quantity.add(productqty);
		quantity.add(blank);
		quantity.add(qty);
		//------end quantity-----------
		//-------add, remove, checkout---------
		button = new JPanel(new FlowLayout()); 
		
		add = new JButton("Add to Cart");
		
		remove = new JButton("Remove");
		remove.setEnabled(false);
		
		checkout = new JButton("Checkout");
		
		button.add(add);
		button.add(remove);
		button.add(checkout);
		//-------------------------------------
isi.add(ID);
isi.add(name);
isi.add(price);
isi.add(quantity);	
isi.add(button);
//---end input layout--------

//-------------------MOUSE EVENT---------------------
table.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
	
		int i = table.getSelectedRow();
		prID.setText(table.getValueAt(i, 0).toString());
		prName.setText(table.getValueAt(i, 1).toString());
		prPrice.setText(table.getValueAt(i, 2).toString());
	}
});


//----table header---
		Vector<String> headertable = new Vector<>();
		headertable.add("Product Name");
		headertable.add("Quantity");
		dtm2 = new DefaultTableModel(headertable, 0);
			
		

		
		
		//-----------------------add to cart-------------------------
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					transaction = new Vector<>();
					productid = table.getValueAt(table.getSelectedRow(), 0).toString();
					valQty = (int) qty.getValue();

					
					
					int i = table.getSelectedRow();
					stock = (int) table.getValueAt(table.getSelectedRow(), 3);
					
					stockupdte = stock - valQty;

						
						if(table.isRowSelected(i)){
							if(valQty >= 1){
								if(valQty <= stock){
									
									
										productNama = prName.getText();
										productQty = qty.getValue().toString();
								
										transaction.add(productNama);
										transaction.add(productQty);
								
								
							
										dtm2.addRow(transaction);
									
								}else{
									JOptionPane.showMessageDialog(null, "Quantity melebihi stock yang tersedia");
								}
							}else{
								JOptionPane.showMessageDialog(null, "minimum quantity is 1");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Pilih barang terlebih dahulu");
						}		
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Click the item first");
				}
			}
		});
		
		
		JTable tableproduct = new JTable(dtm2);
		JScrollPane scrollpane2 = new JScrollPane(tableproduct);
		scrollpane2.setPreferredSize(new Dimension(0, 350));
		
		tableproduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				remove.setEnabled(true);
			}
		});
		
		//-----------------------------------------------------remove cart-------------------------
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					//transaction.remove(tableproduct.getSelectedRow());//
					dtm2.removeRow(tableproduct.getSelectedRow());
					remove.setEnabled(false);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Click the item first");
				}
			}
		});
		
		
		
		//--------------------------------------------------------------check out------------------------------
		checkout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				konek.koneksi();
				dtm.setRowCount(0); //untuk fungsi refresh
				try {
					String sql = "Select ProductID, ProductName, ProductPrice, ProductStock, ProductImage from product";
					ResultSet rs=konek.stmt.executeQuery(sql);
					rsm = rs.getMetaData();
					while(rs.next()){
						table_row = new Vector<>();
						for(int i = 1; i<= rsm.getColumnCount(); i++){
							table_row.add(rs.getObject(i));
							
						}
							dtm.addRow(table_row);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		//-----------end display-----------------
//---
		

		
		Content.add(gambar);
		Content.add(isi);
		Content.add(scrollpane2);
		
		Frproduct.add(scrollpane, "North");
		Frproduct.add(Content, "Center");
			
		Frproduct.setVisible(true);
		
	}
	

}
	
