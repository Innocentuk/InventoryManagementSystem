package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class MasterWindow extends JFrame {
	
	/*private RegisterProduct addProduct;
	private EditStockView edditItem;
	private FindStockView checkProduct;
	private StockDetailView viewStock;*/
	
	 JTabbedPane tabbedPane;
	 JPanel contentPane = new JPanel();
	 JMenuBar menuBar = new JMenuBar();
     
	 public MasterWindow(){
		 
		 setBounds(100, 60, 1500, 800);
		 setBackground(new Color(53, 56, 64));
		 setTitle("Inventory Management System: HENZ NIG LTD");
		 setFont(new Font("Algerian", Font.PLAIN, 16));
		 // close application only by clicking the quit button
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setVisible(true);
		// Add the menubar to the frame
	     setJMenuBar(menuBar);
		 contentPane.setBorder(new EmptyBorder (5, 5, 5, 5));
		 contentPane = new JPanel(new BorderLayout());
		 setContentPane(contentPane);
		 
		 JPanel layer2 = new JPanel();
		 JMenu fileMenu = new JMenu("File");
		 JMenu editMenu = new JMenu("Update");
		 JMenu optionMenu = new JMenu("Options");
		 JMenu viewMenu = new JMenu("View");
		 menuBar.add(fileMenu);
		 menuBar.add(editMenu);
		 menuBar.add(viewMenu);
		 menuBar.add(optionMenu);
		 
		// Create and add simple menu item to one of the drop down menu
	        JMenuItem regProduct = new JMenuItem("Register New Product");
	        JMenuItem addStockAction = new JMenuItem("Add Product");
	        JMenuItem sellAction = new JMenuItem("Make New Sell");
	        JMenuItem damageAction = new JMenuItem("Record Damaged Product");
	        
	        JMenuItem signInAction = new JMenuItem("Sign in");
	        JMenuItem exitAction = new JMenuItem("Exit");
	        
	        JMenuItem restockAction = new JMenuItem("Restock");
	        JMenuItem updateSale = new JMenuItem("Update sale");
	        JMenuItem updatePrice = new JMenuItem("Update price");
	        
	        JMenuItem findAction = new JMenuItem("Search Product");
	        JMenuItem viewStockAction = new JMenuItem("View Product instock");
	        JMenuItem viewSoldAction = new JMenuItem("View Sold Products");
	        JMenuItem viewDamageAction = new JMenuItem("View Damage Products");
	        JMenuItem viewProPrice = new JMenuItem("view product price");

	        fileMenu.add(signInAction);
	        fileMenu.add(exitAction);
	        
	        editMenu.add(restockAction);
	        editMenu.add(updateSale);
	        editMenu.add(updatePrice);
	        
	        optionMenu.add(regProduct);
	        optionMenu.add(addStockAction);
	        optionMenu.add(sellAction);
	        optionMenu.add(damageAction);
	        optionMenu.add(findAction);
	        
	        viewMenu.add(viewStockAction);
	        viewMenu.add(viewSoldAction);
	        viewMenu.add(viewDamageAction);
	        viewMenu.add(viewProPrice);
	        
	        regProduct.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new RegisterProduct().setVisible(true);
						System.out.println("You clicked: " + e.getActionCommand());		
				}
			});
	        
	        addStockAction.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent ae) {
					new AddProduct().setVisible(true);
					System.out.println("You clicked: " + ae.getActionCommand());
				}
			});
	        
	        sellAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new MakeSale().setVisible(true);
				}
			});
	        
	        damageAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new RecordDamage().setVisible(true);
				}
			});
	        
	        findAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new SearchProduct().setVisible(true);
				}
			});
	        
	        restockAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new Restock().setVisible(true);
				}
			});
	        
	        updateSale.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new updateSale().setVisible(true);
				}
			});
	        viewStockAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					try {
						new ViewStockList().setVisible(true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
	        viewProPrice.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ViewProductsPrices().setVisible(true);
				}
			});
	        
	        viewSoldAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ViewSoldProducts().setVisible(true);
				}
			});
	        viewDamageAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ViewDamagedProducts().setVisible(true);
				}
			});
	        exitAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
					
				}
			});
		 
		 
		 }
	 
	}
