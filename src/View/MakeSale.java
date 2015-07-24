package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import Model.Sales;
import Model.Stock;
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;
import Service.SalesServiceImplementation;
import Service.StockServiceImplementation;

public class MakeSale extends JFrame implements ActionListener{

	JTextField qtyTF, staffTF, statusTF, dateTF, itemTF;
	JTextArea recieptTF;
	JButton addB, printB, updateB;
	JComboBox<Item> itemList; 
	JComboBox<Price> priceList;
	
	private double totalAmount;
	
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd, HH-mm-ss").format (day);
	ItemServiceImplementation iServe;
	PriceServiceImplementation pServe;
	StockServiceImplementation sServe;
	SalesServiceImplementation saServe;
	Sales sa;
	Price p;
	Item it;
	Stock st;
	
/**
 * 
 */
public MakeSale() {
	iServe = new ItemServiceImplementation();
	pServe = new PriceServiceImplementation();
	sServe = new StockServiceImplementation();
	saServe = new SalesServiceImplementation();
	
	st = new Stock();
	sa = new Sales();
	it = new Item();
	p = new Price();
	
	this.setLayout(new FlowLayout());
	this.setTitle("Make Sale");
	
	setBounds(100, 60, 700, 500);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel content = new JPanel();
	BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
	content.setLayout(layout);

	JLabel banner = new JLabel("Sell Products");
	banner.setFont(new Font("Algerian", Font.PLAIN, 14));
	banner.setForeground(Color.BLUE);
	
	JPanel display = new JPanel();
	display.setLayout(new BorderLayout());
	recieptTF = new JTextArea();
	JScrollPane scrollP = new JScrollPane(recieptTF);
	recieptTF.setEditable(false);
	display.add(scrollP);
	
	JPanel filedPan = new JPanel();
	BoxLayout layout2 = new BoxLayout(filedPan, BoxLayout.Y_AXIS);
	filedPan.setLayout(layout2);
	itemList = new JComboBox<Item>();
	itemList.setPreferredSize(new Dimension(100, 40));
	itemList.setBorder(new TitledBorder("choose product"));
	
	priceList = new JComboBox<Price>();
	priceList.setPreferredSize(new Dimension(100, 40));
	priceList.setBorder(new TitledBorder("Select price"));
	
	JLabel qtylab = new JLabel("Enter quantity");
	JLabel staffLab = new JLabel("Staff stamp");//
	JLabel statusLab = new JLabel("status");
	JLabel dateLab = new JLabel("today's date");//
	
	addB = new JButton("Add");
	addB.addActionListener(this);
	printB = new JButton("Print");
	printB.addActionListener(this);
	
	qtyTF = new JTextField(5);
	staffTF = new JTextField(5);//
	statusTF = new JTextField(5);
	dateTF = new JTextField(""+ date);//
	
	JPanel staffPan = new JPanel();
	staffPan.setLayout(new GridLayout(2, 2));
	staffPan.setBorder(new TitledBorder(""));
	
	staffPan.add(staffLab); staffPan.add(staffTF);
	staffPan.add(dateLab); staffPan.add(dateTF);
	filedPan.add(itemList); filedPan.add(priceList);
	filedPan.add(qtylab); filedPan.add(qtyTF);
	filedPan.add(statusLab); filedPan.add(statusTF);
	content.add(banner);
	content.add(staffPan);
	content.add(filedPan, "West");
	content.add(addB);
	content.add(printB);
	
	this.add(content);//, FlowLayout.LEFT);
	this.add(display); //FlowLayout.RIGHT);
	this.setVisible(true);
}

private void getProductFromDatabase() throws Exception {
	List<Item> listItem = iServe.findAllItem();
	for(Item i : listItem){
		//itemList.insertItemAt(i.getName(), 0);
		//addPro.getItemList()
		itemList.addItem(i);
	}
	itemList.setSelectedIndex(0);
	
	List<Price> listPrice = pServe.findAllPrice();
	for(Price p : listPrice){
		//priceList.insertItemAt(p.getSellPrice(), 0);
		priceList.addItem(p);
	}
	priceList.setSelectedIndex(0);
}

public void addDetail(){
	
	try {
		
	sa.setItem((String) itemList.getSelectedItem());
	p.setSellPrice(priceList.getSelectedIndex());
	
	sa.setQuantity(Integer.parseInt(qtyTF.getText()));
	qtyTF.setText("");
	
	sa.setStaffStamp(staffTF.getText());
	
	sa.setStatus(statusTF.getText());
	statusTF.setText("");
	
	sa.setDate(dateTF.getText());
	sa.getTotalprice();
	
		saServe.saveSales(sa);
	} catch (Exception e) {
		e.printStackTrace();
	}
}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addB)){
			
			if(qtyTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter quantity!"); 
			
			else if(staffTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter your name!");
			
			else if(statusTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter status!");
			else{
		try {
			st = sServe.findItemByName("");
			if(st.getQuantity() < 1){
				JOptionPane.showMessageDialog(null, "less qty left");
				return;
			}
			st.setSoldNo(Integer.parseInt(st.getSoldNo()+ qtyTF.getText()));
			sServe.updateStock(st);
			addDetail();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
		}
		else if(e.getSource().equals(printB)){
			
		}
	}
}
