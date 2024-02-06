package my.portal;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import my.portal.component.panels.MainPanel;

public class Main {

	private static void createAndShowGUI() {

		// Create and set up the window.
		JFrame frame = new JFrame("Portal Platformu");
//		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		PortalMainPanel portalMainPanel = new PortalMainPanel();
//		portalMainPanel.getTopDockPanel().add(new TopPanel());
//		portalMainPanel.getLeftDockPanel().add(new LinksPanel(new LinkController().getLinks()));
//		portalMainPanel.getCenterDockPanel().add(new SliderPanel());
//		portalMainPanel.getBottomDockPanel().add(new StatusPanel());
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(new MainPanel());
		
//		Insets insets = frame.getContentPane().getInsets();

		Dimension preferedSize = new Dimension(1310,728);
		MainPanel mainPanel = new MainPanel();
		mainPanel.setPreferredSize(preferedSize);
		mainPanel.setBounds(0, 0, (int)preferedSize.getWidth(), (int)preferedSize.getHeight());
		frame.getContentPane().add(mainPanel);
//		
		// Display the window.
		frame.setPreferredSize(preferedSize); //acilista gozukmesi icin
		frame.setSize(preferedSize); //setLocationRelativeTo ortali gostermesi icin 
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
		setNimbus();
		CommInitilizer.initializeCommLibrary();
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
	}
	
	private static void setNimbus() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
	}
}
