package View;
import java.awt.BorderLayout;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Sales;
import Model.Stock;
import Service.SalesServiceImplementation;
import Service.StockServiceImplementation;


public class ViewSoldProducts extends JFrame {
	
	private JTable soldTable;
	private JLabel banner;
	private DefaultTableModel model;
	private final String[] headers = {"Product id", "Product name", "quantity", "total price", "payment status", "staff", "Date"};
	SalesServiceImplementation saleserve;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
	public ViewSoldProducts() {
		int row = 0;
		saleserve = new SalesServiceImplementation();
		
		JPanel content = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); 
		setSize(500, 400);
		setVisible(true);
		
		banner = new JLabel("Sold out products.");
		banner.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(layout);
		content.add(banner);
		this.add(content);
		
		soldTable = new JTable();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		soldTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		soldTable.setFillsViewportHeight(true);
		JScrollPane pane = new JScrollPane(soldTable);
		pane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		content.add(pane);
		
		try{
			List<Sales> list = saleserve.findAllSale();
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				Sales sa = list.get(i);
				System.out.println(sa.getId()+", "+sa.getItem()+", "+ sa.getQuantity()+", "+ sa.getTotalprice() +", "+sa.getStatus()+", "+ sa.getStaffStamp()+", "+ sa.getDate());
			}
			for(Sales sl :list){
				model.setValueAt(sl.getId(), row, 0);
				model.setValueAt(sl.getItem(), row, 1);
				model.setValueAt(sl.getTotalprice(), row, 2);
				model.setValueAt(sl.getQuantity(), row, 3);
				model.setValueAt(sl.getStatus(), row, 4);
				model.setValueAt(sl.getStaffStamp(), row, 5);
				model.setValueAt(sl.getDate(), row, 6);

				row++;
			}
			soldTable.setModel(model);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
