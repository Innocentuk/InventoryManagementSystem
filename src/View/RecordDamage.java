package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Model.DamageItem;
import Model.Item;
import Model.Price;
import Model.Sales;
import Model.Stock;
import Service.DamageServiceImplementation;
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;
import Service.SalesServiceImplementation;
import Service.StockServiceImplementation;

public class RecordDamage extends JFrame implements ActionListener{

	JTextField itemTF, qtyTF, staffTF, reasonTF, dateTF;
	JButton addB;
	StockServiceImplementation sServe;
	DamageServiceImplementation dServe;
	DamageItem di;
	Stock st;
	
	Date day = new Date();
	String date = new SimpleDateFormat ("yyyy-MM-dd, HH:mm:ss").format (day);
	
	public RecordDamage() {
		sServe = new StockServiceImplementation();
		dServe = new DamageServiceImplementation();
		di = new DamageItem();
		st = new Stock();
		
		this.setLayout(new FlowLayout());
		this.setTitle("Record Damage");
		
		setBounds(100, 60, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = new JPanel();
		BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(layout);
		
		JLabel banner = new JLabel("Record Damage");
		banner.setFont(new Font("Algerian", Font.PLAIN, 14));
		banner.setForeground(Color.BLUE);
		
		JPanel filedPan = new JPanel();
		BoxLayout layout2 = new BoxLayout(filedPan, BoxLayout.Y_AXIS);
		filedPan.setLayout(layout2);
				
		JLabel qtylab = new JLabel("Enter quantity");
		JLabel staffLab = new JLabel("Staff stamp");//
		JLabel itemLab = new JLabel("Enter item");
		JLabel reasonLab = new JLabel("Reason");//
		JLabel datLab = new JLabel("today's date");
		
		addB = new JButton("Add");
		addB.addActionListener(this);
		
		itemTF = new JTextField(5);
		qtyTF = new JTextField(5);
		staffTF = new JTextField(5);//
		reasonTF = new JTextField(5);
		dateTF = new JTextField(""+ date);//
		
		filedPan.add(itemLab); filedPan.add(itemTF);
		filedPan.add(qtylab); filedPan.add(qtyTF);
		filedPan.add(reasonLab); filedPan.add(reasonTF);
		filedPan.add(datLab); filedPan.add(dateTF);
		filedPan.add(staffLab); filedPan.add(staffTF);
		content.add(banner);
		content.add(filedPan, "West");
		content.add(addB);
		
		this.add(content);//, FlowLayout.LEFT);
		this.setVisible(true);
	}
	public void addDetail(){
		
		try {
			//st = sServe.findItemByName(itemTF.getText());
		di.setItem(itemTF.getText());
		itemTF.setText("");
		
		di.setQty(Integer.parseInt(qtyTF.getText()));
		qtyTF.setText("");
		
		di.setStaffstamp(staffTF.getText());
		staffTF.setText("");
		
		di.setReason(reasonTF.getText());
		reasonTF.setText("");
		
		di.setDate(dateTF.getText());
		dateTF.setText("");
		
		dServe.findAlldamaged();
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
			
			else if(itemTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter product!");
			
			else if(reasonTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter reason!");
			
			else{
				try {
					//st = sServe.findItemByName(itemTF.getText());
					String key = itemTF.getText();
					st = sServe.findItemByName(key);
					
				if(st.getQuantity() < 1){
					System.out.println("no item in stock ");
					return;
				}
				st.setDamagedNo(Integer.parseInt(st.getDamagedNo()+ qtyTF.getText()));
				sServe.updateStock(st);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				addDetail();
			}
		}
	}

}
