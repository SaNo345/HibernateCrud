package pac.crud.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFrame extends JFrame implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Մենյու");
    private JMenuItem usersItem = new JMenuItem("Օգտագործող");
    private JMenuItem companyItem = new JMenuItem("Կազմակաերպություն");
    private  UserFrame userFrame ;
    private  CompanyFrame companyFrame ;
	
	public  ViewFrame(String title) {
			setTitle(title);
			setLayout(new FlowLayout());
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(500,300);
			
			menu.add(usersItem);
			menu.add(companyItem);
			menuBar.add(menu);
			setJMenuBar(menuBar);
			usersItem.addActionListener(this);
			companyItem.addActionListener(this);

			setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		

		if(e.getSource()==usersItem ){
		    if(userFrame ==null){
                userFrame = new UserFrame();
            }

			    userFrame.setVisible(true);

		}
	
		if(e.getSource()==companyItem ){
		    if(companyFrame==null) {
                companyFrame = new CompanyFrame();
            }

			companyFrame.setVisible(true);

		}
	}
     

}
