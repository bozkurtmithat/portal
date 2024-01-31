package my.portal.component.miglayout;

import java.awt.Dimension;

import javax.swing.JPanel;

import lombok.Getter;
import my.portal.component.JPanelBase;
import net.miginfocom.swing.MigLayout;

@Getter
public class PortalMainPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;
	private JPanel topDockPanel;
	private JPanel leftDockPanel;
	private JPanel rightDockPanel;
	private JPanel bottomDockPanel;
	private JPanel centerDockPanel;
	
	public PortalMainPanel() {
		setLayout(new MigLayout("insets 0 0 0 0, wrap 3", 
				"[fill,20%!][fill,60%!][fill,20%!]", 
				"[fill,10%:10%:70px][fill,80%!][fill,10%!]"));
		setPreferredSize(new Dimension(1024, 768));
		
		topDockPanel = new JPanel(new MigLayout("","[fill,grow]","[fill,grow]"));
		leftDockPanel = new JPanel(new MigLayout("wrap 1"));
		centerDockPanel = new JPanel(new MigLayout("","[fill,grow]","[fill,grow]"));
		rightDockPanel = new JPanel(new MigLayout("wrap 1","",""));
		bottomDockPanel = new JPanel(new MigLayout("","[fill,grow]","[fill,grow]"));

		add(topDockPanel, "span");
		add(leftDockPanel);
		add(centerDockPanel);
		add(rightDockPanel);
		add(bottomDockPanel, "span");
	}
	
}
