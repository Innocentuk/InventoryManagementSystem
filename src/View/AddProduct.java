package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Item;
import Model.Price;
import Model.Stock;
import Service.StockServiceImplementation;

/**
 *  Class that contains user interface for adding a product in to stock list.
 * @author Eucheria innocent
 *
 */
public class AddProduct extends JPanel implements ActionListener{//JPanel{

	JTextField qtyTF, noSoldTF, noDamageTF, dateTF;
	JLabel dateLab;
	JButton addB;
	//JComboBox itemList;
	JComboBox<Item> itemList; 
	
	StockServiceImplementation sServe;
	Stock st;
	
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd, HH-mm-ss").format (day);
	
	public AddProduct() {
		
		sServe = new StockServiceImplementation();
		st = new Stock();
		this.setLayout(new FlowLayout());
		
		setBounds(200, 80, 500, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = new JPanel();
		BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(layout);

		JLabel banner = new JLabel("Enter stock detail");
		banner.setFont(new Font("Algerian", Font.PLAIN, 14));
		banner.setForeground(Color.BLUE);
		
		JPanel filedPan = new JPanel();
		BoxLayout layout2 = new BoxLayout(filedPan, BoxLayout.Y_AXIS);
		filedPan.setLayout(layout2);
		
		itemList = new JComboBox<Item>();
		itemList.setPreferredSize(new Dimension(100, 40));
		itemList.setBorder(new TitledBorder("Select product"));
		
		JLabel qtylab = new JLabel("Enter quantity");
		JLabel noSoldlab = new JLabel("Enter number sold");
		JLabel noDamagedLab = new JLabel("Enter number damaged");
		JLabel dateLab = new JLabel("today's date");
		
		addB = new JButton("Add");
		addB.addActionListener(this);
		
		qtyTF = new JTextField(5);
		noSoldTF = new JTextField(5);
		noDamageTF = new JTextField(5);
		dateTF = new JTextField(""+ date);
		
		content.add(banner);
		filedPan.add(itemList);
		filedPan.add(qtylab); filedPan.add(qtyTF);
		filedPan.add(noSoldlab); filedPan.add(noSoldTF);
		filedPan.add(noDamagedLab); filedPan.add(noDamageTF);
		filedPan.add(dateLab); filedPan.add(dateTF);
		content.add(filedPan);
		content.add(addB);
		
		this.add(content);
		this.setVisible(true);
		
	}
	public void addDetails(){
		try {
		st.setItem((String) itemList.getSelectedItem());
		st.setQuantity(Integer.parseInt(qtyTF.getText()));
		qtyTF.setText("");
		st.setSoldNo(Integer.parseInt(noSoldTF.getText()));
		noSoldTF.setText("");
		st.setDamagedNo(Integer.parseInt(noDamageTF.getText()));
		noDamageTF.setText("");
		st.setDate(dateTF.getText());
		dateTF.setText("");
			sServe.saveStock(st);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addB)){
			
			if(qtyTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter quantity!"); 
			
			else if(noSoldTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter number sold!");
			
			else if(noDamageTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter number damaged!");
				
			else{
				Boolean pass = new Boolean(true);
				try{
					Integer.parseInt(qtyTF.getText());
					Integer.parseInt(noSoldTF.getText());
					Integer.parseInt(noDamageTF.getText());
				
				}	catch(NumberFormatException nfe){
					qtyTF.setText("");
					noSoldTF.setText("");
					noDamageTF.setText("");
					JOptionPane.showMessageDialog(null, "You must enter number!");
				}
					addDetails();
			}
		}
	}

	public JTextField getDateTF() {
		return dateTF;
	}

	public JTextField getQtyTF() {
		return qtyTF;
	}

	public JTextField getNoSoldTF() {
		return noSoldTF;
	}

	public JTextField getNoDamageTF() {
		return noDamageTF;
	}

	public JButton getAdd() {
		return addB;
	}

	public JComboBox getItemList() {
		return itemList;
	}
	
}
