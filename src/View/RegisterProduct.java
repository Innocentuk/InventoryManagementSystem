package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;

/**
 * Class containing the user interface for registering new Product.
 * @author Eucheria Innocent
 *
 */
public class RegisterProduct extends JPanel implements ActionListener{//JPanel{

	private JButton addB;
	private JTextField itemNameTF, costPriceTF, sellPriceTF, priceDateTF;
	private JTextArea descriptionTA, displayArea;
	public static Boolean productCreated = new Boolean(false); 
	
	ItemServiceImplementation iServe;
	PriceServiceImplementation pServe;
	Item t = new Item();
	Price p = new Price();
	
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss").format(day);
	Locale lc = new Locale("NGA", "NG");
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(lc);

	/**
	 * Constructor which instantiate every component of the user interface.
	 */
	public RegisterProduct() {
		this.setLayout(new FlowLayout());

		//setBounds(100, 60, 500, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel content = new JPanel();
		BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(layout);

		JLabel banner = new JLabel("Register product detail:");
		banner.setFont(new Font("Algerian", Font.PLAIN, 14));
		banner.setForeground(Color.BLUE);
		JLabel nameLab = new JLabel("Enter product name");
		JLabel descriptionLab = new JLabel("Enter product description");
		
		addB = new JButton("Add");
		addB.addActionListener(this);
		itemNameTF = new JTextField(5);
		descriptionTA = new JTextArea(7, 5);
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionTA);
		
		JPanel display = new JPanel();
		displayArea = new JTextArea();
		JScrollPane scrollP = new JScrollPane(displayArea);
		displayArea.setEditable(false);
		display.add(scrollP);
		
		JPanel priceInfo = new JPanel();
		BoxLayout layout2 = new BoxLayout(priceInfo, BoxLayout.Y_AXIS);
		priceInfo.setLayout(layout2);
		priceInfo.setBorder(new TitledBorder("Product Prices"));
		JLabel costLab = new JLabel("Enter cost price");
		JLabel sellLab = new JLabel("Enter selling price");
		JLabel dateLab = new JLabel("price date");
		costPriceTF = new JTextField(5);
		sellPriceTF = new JTextField(5);
		priceDateTF = new JTextField(""+ date);
		
		content.add(banner);
		content.add(nameLab); content.add(itemNameTF);
		content.add(descriptionLab); content.add(descriptionScrollPane);
		priceInfo.add(costLab);
		priceInfo.add(costPriceTF);
		priceInfo.add(sellLab);
		priceInfo.add(sellPriceTF);
		priceInfo.add(dateLab);
		priceInfo.add(priceDateTF);
		
		content.add(priceInfo);
		add(display, "East");
		content.add(addB);
		
		this.add(content);
		this.setVisible(true);
	}
	
	public void addDetails(){
		
		try {
		t.setName(itemNameTF.getText());
		p.setItem(itemNameTF.getText());
		itemNameTF.setText("");
		
		t.setDescription(descriptionTA.getText());
		descriptionTA.setText("");
		
		p.setCostPrice(Double.parseDouble(costPriceTF.getText()));
		costPriceTF.setText("");
		
		p.setSellPrice(Double.parseDouble(sellPriceTF.getText()));
		sellPriceTF.setText("");
		
		p.setDate(priceDateTF.getText()+ date);
		priceDateTF.setText("");
		
			pServe.savePrice(p);
			iServe.saveItem(t);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		displayAddedProduct();
	}
	
	public void displayAddedProduct(){
		//addItem();
		List<Item> li;
		List<Price> pl;
		String txt = new String();
		try {
			li = iServe.findAllItem();
			txt = "Product arrived at: " + new Date() + "\r\n";
			for(Item i : li){
				txt += "Product Name: " + i.getName()+ "\r\n";
				txt += "\tProduct description: " + i.getDescription() + "\r\n";
			}
			pl = pServe.findAllPrice();
			for(Price pz : pl){
				txt += "\t cost price: " + currencyFormat.format(pz.getCostPrice()) + "\r\n";
				txt += "\t selling price: " + currencyFormat.format(pz.getSellPrice()) + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton getAddButton() {
		return addB;
	}
	public JTextField getItemNameTF() {
		return itemNameTF;
	}
	public JTextArea getDescriptionTA() {
		return descriptionTA;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(addB)){
			
			if(itemNameTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter product name!");
			
			else if(costPriceTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You must enter product cost price!");
			
			else if(sellPriceTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter the selling price!");
			
			else{
				Boolean pass = new Boolean(true);
				try{
					Double.parseDouble(costPriceTF.getText());
					Double.parseDouble(sellPriceTF.getText());
				
				}	catch(NumberFormatException nfe){
					pass = false;
					costPriceTF.setText("");
					sellPriceTF.setText("");
					JOptionPane.showMessageDialog(null, "You must enter a figure, prices fields!");
				}
			
					addDetails();
				
			}
		}
	}	
}
