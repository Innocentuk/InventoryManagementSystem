package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.DamageItem;
import Model.Stock;
import Service.StockServiceImplementation;

public class ViewStockList extends JFrame{//JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTable stockTable;
	private JLabel banner;
	private DefaultTableModel model;
	private final String[] headers = {"Product id", "Product name", "quantity", "amount sold", "amount damaged", "Date entered"};
	//Stock st;
	StockServiceImplementation stockserve;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
	public ViewStockList() throws Exception {
		int row = 0;
		stockserve = new StockServiceImplementation();
		
		JPanel content = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); 
		setSize(500, 400);
		setVisible(true);
		
		banner = new JLabel("All products in stock.");
		banner.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(layout);
		content.add(banner);
		this.add(content);
		
		stockTable = new JTable();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		stockTable.setFillsViewportHeight(true);
		JScrollPane pane = new JScrollPane(stockTable);
		pane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		content.add(pane);
		
		try{
			List<Stock> list = stockserve.findAllStock();
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				Stock st = list.get(i);
				System.out.println(st.getId()+", "+st.getItem()+", "+ st.getQuantity()+", "+ st.getSoldNo()+", "+ st.getDamagedNo()+", "+ st.getDate());
			}
			for(Stock stk :list){
				model.setValueAt(stk.getId(), row, 0);
				model.setValueAt(stk.getItem(), row, 1);
				model.setValueAt(stk.getQuantity(), row, 2);
				model.setValueAt(stk.getSoldNo(), row, 3);
				model.setValueAt(stk.getDamagedNo(), row, 4);
				model.setValueAt(stk.getDate(), row, 5);
				row++;
			}
			stockTable.setModel(model);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
}
