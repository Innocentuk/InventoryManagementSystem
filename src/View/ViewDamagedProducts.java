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

import Model.DamageItem;
import Model.Stock;
import Service.DamageServiceImplementation;
import Service.StockServiceImplementation;

public class ViewDamagedProducts extends JFrame{
	
	private JTable damageTable;
	private JLabel banner;
	private DefaultTableModel model;
	private final String[] headers = {"Product id", "Product name", "quantity", "Reason", "Staff", "Date entered"};
	//Stock st;
	DamageServiceImplementation damageserve;
	
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);
	
	public ViewDamagedProducts(){
		
		int row = 0;
		damageserve = new DamageServiceImplementation();
		
		JPanel content = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); 
		setSize(500, 400);
		setVisible(true);
		
		banner = new JLabel("All damaged products.");
		banner.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(layout);
		content.add(banner);
		this.add(content);
		
		damageTable = new JTable();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		damageTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		damageTable.setFillsViewportHeight(true);
		JScrollPane pane = new JScrollPane(damageTable);
		pane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		content.add(pane);
		/*JScrollPane pane = new JScrollPane(stockTable);
		pane.setPreferredSize(new Dimension(200, 200));*/
		try{
			List<DamageItem> list = damageserve.findAlldamaged();
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				DamageItem di = list.get(i);
				System.out.println(di.getItem()+": "+ di.getQty()+", "+ di.getReason()+", "+ di.getDate()+", "+di.getStaffstamp());
			}
			for(DamageItem dam :list){
				model.setValueAt(dam.getId(), row, 0);
				model.setValueAt(dam.getItem(), row, 1);
				model.setValueAt(dam.getQty(), row, 2);
				model.setValueAt(dam.getReason(), row, 3);
				model.setValueAt(dam.getStaffstamp(), row, 4);
				model.setValueAt(dam.getDate(), row, 5);
				row++;
			}
			damageTable.setModel(model);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
