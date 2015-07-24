package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Item;
import Model.Price;
import Model.Stock;
import Service.StockServiceImplementation;

public class Restock extends JFrame implements ActionListener{

	JTextField qtyTF, itemTF, dateTF;
	JButton addB;
	
	StockServiceImplementation sServe;
	Stock st;
	
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss").format (day);
	
	public Restock() {
		
		sServe = new StockServiceImplementation();
		st = new Stock();
		this.setLayout(new FlowLayout());
		
		setBounds(100, 60, 600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel content = new JPanel();
		BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(new FlowLayout());
		content.setBorder(new TitledBorder("Increase existing item"));
		
		JPanel titleP = new JPanel();
		JLabel banner = new JLabel("Restock:");
		banner.setFont(new Font("Algerian", Font.PLAIN, 14));
		banner.setForeground(Color.BLUE);
		titleP.add(banner);
		this.add(titleP);//, new FlowLayout(FlowLayout.LEADING));
		
		JLabel itemLab = new JLabel("Enter product name");
		JLabel qtyLab = new JLabel("Enter quantity to add");
		
		addB = new JButton("Add");
		addB.addActionListener(this);
		
		qtyTF = new JTextField(5);
		itemTF = new JTextField(10);
		
		content.add(itemLab);
		content.add(itemTF);
		content.add(qtyLab);
		content.add(qtyTF);
		content.add(addB);
		this.add(content);
		this.setVisible(true);
	}
	
	public void addDetails(){
		try {
		String key = itemTF.getText();
		st = sServe.findItemByName(key);
		itemTF.setText("");
		if(st == null){
			JOptionPane.showMessageDialog(null, "Product with name "+key+ " is not instock");
		}
		//need to sum the qty in stock and qty to be added.
		st.setQuantity(Integer.parseInt(st.getQuantity() + qtyTF.getText()));
		qtyTF.setText("");
		
		//st.setDate(dateTF.getText());
		
			sServe.updateStock(st);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		display();
	}
	
	private void display() {
		List<Stock> li;
		String txt = new String();
		try {
			li = sServe.findAllStock();
			txt = "Product arrived at: " + new Date() + "\r\n";
			for(Stock s : li){
				txt += "Product Name: " +s.getItem()+ "\r\n";
				txt += "\tProduct quantity: " + s.getQuantity() + "\r\n";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addB)){
			
			if(qtyTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter quantity!"); 
			
			else if(itemTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please enter product name!");
						
			else{
				Boolean pass = new Boolean(true);
				try{
					Integer.parseInt(qtyTF.getText());
				
				}	catch(NumberFormatException nfe){
					qtyTF.setText("");
					JOptionPane.showMessageDialog(null, "You must enter number!");
				}
					addDetails();
			}
		}
	}

}
