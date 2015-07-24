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

import Model.Item;
import Model.Price;
import Model.Stock;
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;
import Service.StockServiceImplementation;

public class ViewProductsPrices extends JFrame{
	
	private JTable productTable;
	private JLabel banner;
	private DefaultTableModel model;
	private final String[] headers = {"Product id", "Product name", "quantity", "cost price", "sell price", "Price Date"};
	//Stock st;
	ItemServiceImplementation itemserve;
	PriceServiceImplementation priceserve;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
public ViewProductsPrices() {
	int row = 0;
	itemserve = new ItemServiceImplementation();
	priceserve = new PriceServiceImplementation();
	
	JPanel content = new JPanel();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new BorderLayout()); 
	setSize(500, 400);
	setVisible(true);
	
	banner = new JLabel("All products and Prices.");
	banner.setAlignmentX(Component.CENTER_ALIGNMENT);
	BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
	content.setLayout(layout);
	content.add(banner);
	this.add(content);
	
	productTable = new JTable();
	model = new DefaultTableModel();
	model.setColumnIdentifiers(headers);
	productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	productTable.setFillsViewportHeight(true);
	JScrollPane pane = new JScrollPane(productTable);
	pane.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	pane.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	content.add(pane);
	try{
		List<Item> list = itemserve.findAllItem();
		List<Price> pricelist = priceserve.findAllPrice();
		
		System.out.println(list.size()+ ", Products");
		for (int i = 0; i < list.size() && i<pricelist.size(); i++) {
			Item it = list.get(i);
			Price p = pricelist.get(i);
			System.out.println(it.getId()+", "+it.getName()+", "+ it.getDescription()+", "+ p.getCostPrice()+", "+p.getSellPrice()+", "+ p.getDate());
		}
		for(Item itm :list){
			model.setValueAt(itm.getId(), row, 0);
			model.setValueAt(itm.getName(), row, 1);
			model.setValueAt(itm.getDescription(), row, 2);
			row++;
		}
		for(Price pz : pricelist){
			model.setValueAt(pz.getItem(), row, 3);
			model.setValueAt(pz.getCostPrice(), row, 4);
			model.setValueAt(pz.getSellPrice(), row, 5);
			model.setValueAt(pz.getDate(), row, 6);
			row++;
		}
		productTable.setModel(model);
		
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
