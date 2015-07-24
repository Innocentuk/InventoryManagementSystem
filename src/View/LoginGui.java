package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.Login;
import Service.LoginServiceImplementation;

public class LoginGui extends JFrame implements ActionListener{

	JLabel passLab, nameLab, roleLab, banner;
    JTextField nameTF, roleTF;
    JButton logB;
    JPasswordField passField;
    
    LoginServiceImplementation lServe;
    Login lg;
    
    public LoginGui() {
    	lServe = new LoginServiceImplementation();
    	lg = new Login();
    	
    	 setTitle("Login Form");
         setVisible(true);
         setSize(800, 800);
         setLayout(null);
         setResizable(false);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
         banner = new JLabel("Login Form:");
         banner.setForeground(Color.blue);
         banner.setFont(new Font("Serif", Font.BOLD, 15));
  
         nameLab = new JLabel("Enter UserName:");
         passLab = new JLabel("Enter Password:");
         roleLab = new JLabel("Enter UserRole:");
         roleTF = new JTextField();
         nameTF = new JTextField();
         passField = new JPasswordField();
         logB = new JButton("Submit");
  
         banner.setBounds(100, 30, 400, 30);
         roleLab.setBounds(200, 70, 200, 30);
         nameLab.setBounds(200, 110, 200, 30);
         passLab.setBounds(200, 150, 200, 30);
         roleTF.setBounds(300, 70, 200, 30);
         nameTF.setBounds(300, 110, 200, 30);
         passField.setBounds(300, 150, 200, 30);
         logB.setBounds(300, 190, 100, 30);
  
         add(banner);
         add(roleLab);
         add(roleTF);
         add(nameLab);
         add(nameTF);
         add(passLab);
         add(passField);
         add(logB);
         logB.addActionListener(this);
	}
    public void addDetail(){
    	lg.setRole(roleTF.getText());
    	roleTF.setText("");
    	
    	lg.setUsername(nameTF.getText());
    	nameTF.setText("");
    	
    	lg.setPassword2(passField.getPassword());
    	passField.setText("");
    	
    	try {
			lServe.findAllUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(logB)){
			
			if(roleTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "you forgot to enter role!");
			
			else if(nameTF.getText().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter userName!");
			
			else if(passField.getPassword().equals(""))
				JOptionPane.showMessageDialog(null, "You forgot to enter password!");
		else{
			Boolean pass = new Boolean(true);
				addDetail();
		}
		}
	}

}
