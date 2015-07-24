package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Model.Stock;
import Service.StockServiceImplementation;
/**
 * Class that contain a user interface for serach for item in stock
 * @author Eucheria innocent
 *
 */
public class SearchProduct extends JFrame implements ActionListener {
	
	JTextField nameField;
	 TextArea information; 
	 JButton checkB;
	 JLabel checkL;
	 
	 Locale lc = new Locale("NGA", "NG");
	 NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	 StockServiceImplementation sServe;
	 Stock st;
	 
	public SearchProduct() {
		sServe = new StockServiceImplementation();
		st = new Stock();
		//this.setLayout(new FlowLayout());

		setBackground(new Color(240, 240, 240));
		setBounds(100, 60, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel checkStockPanel = new JPanel();
		leftPanel.add(checkStockPanel);
		checkStockPanel.setLayout(new BorderLayout());
		
		JPanel checkBPanel = new JPanel();
		checkBPanel.setBorder(new TitledBorder("Serach for a product in stock"));
		checkStockPanel.add(checkBPanel, BorderLayout.NORTH);
		checkBPanel.setLayout(new FlowLayout());
		
		checkB = new JButton("Search");
		checkB.addActionListener(this);
		nameField = new JTextField(7);
		checkL = new JLabel("Enter Product name");
		
		checkBPanel.add(checkL);
		checkBPanel.add(nameField);
		checkBPanel.add(checkB, "South");
		
		information = new TextArea(3, 5);
		information.setPreferredSize(new Dimension(50, 100));
		//information.
		JScrollPane scrollPane_1 = new JScrollPane(information);
		checkStockPanel.add(scrollPane_1, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String key = nameField.getText();
		 try {
			st = sServe.findItemByName(key);
		 if (st == null) {
			 information.setText("Item with name: "+ key+ " does not exist in stock.");
		 }
		 else if(st.getQuantity() < 1){
			 information.setText(key+", is finished!");
		 }
		 else {
			 information.setText("Item number: "+ st.getId());
			 information.setText("\nItem name: "+ st.getItem());
			 information.setText("\nItem quantity instock: "+ st.getQuantity());
			 information.setText("\nItem quantity sold: "+ st.getSoldNo());
			 information.setText("\nItem quantity damaged: "+ st.getDamagedNo());
			 information.setText("\nItem date: "+ st.getDate());
		 }
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

}
