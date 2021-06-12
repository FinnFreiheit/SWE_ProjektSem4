package model;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class ConfigurationWindow extends JFrame
{
	public ConfigurationWindow()
	{
		super("Satellites");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = this.initContent();
        this.getContentPane().add(content);
        
        this.setSize(400, 300);
        this.setVisible(true);
	}
	
	private JPanel initContent()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        JPanel oben = new JPanel();
        oben.setLayout(new GridLayout(5,3));
        JLabel lab1 = new JLabel("Filter model.satellite name ");
        JCheckBox cb1 = new JCheckBox();
        JTextField tf1 = new JTextField();
        
        oben.add(lab1);
        oben.add(cb1);
        oben.add(tf1);
        
        JPanel unten = new JPanel();
        		
        mainPanel.add(oben, BorderLayout.NORTH);
        mainPanel.add(unten, BorderLayout.CENTER);
        return mainPanel;
    }

}
