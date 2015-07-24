package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Sales;
import Service.SalesServiceImplementation;

public class updateSale extends JFrame implements ActionListener{

	SalesServiceImplementation saServe;
	Sales sa;
	
	JTextField statusTF,itemTF;
	JButton updateB;
	
	public updateSale(){
		sa = new Sales();
		saServe = new SalesServiceImplementation();
		
		setBounds(100, 60, 700, 500);
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		JPanel updatePan = new JPanel();
		updatePan.setLayout(new FlowLayout());
		updatePan.setBorder(new TitledBorder(""));
		
		JLabel itemLab = new JLabel("enter item");
		JLabel statusLab = new JLabel("enter status");
		JLabel bannerLab = new JLabel("Status Update");
		itemTF = new JTextField(10);
		statusTF = new JTextField(10);
		
		updateB = new JButton("update");
		updateB.addActionListener(this);
		
		updatePan.add(itemLab);
		updatePan.add(itemTF);
		updatePan.add(statusLab);
		updatePan.add(statusTF);
		add(bannerLab, BorderLayout.NORTH);
		add(updatePan, BorderLayout.CENTER);
		updatePan.add(updateB, BorderLayout.SOUTH);
	}
	public void updateDetail() {
		try {
			String key = itemTF.getText();
			sa = saServe.findItemByName(key);
			itemTF.setText("");
			
			if(sa == null){
				JOptionPane.showMessageDialog(null, "Product with name "+key+ " does not in exist");
			}
			sa.setStatus(statusTF.getText());//
			statusTF.setText("");
			
			saServe.updateSale(sa);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(updateB)){
				
			if(itemTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "you forgot to enter product!");
			
			else if(statusTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter status!");
		else{
			Boolean pass = new Boolean(true);
				updateDetail();
		}
		}
	}
}
