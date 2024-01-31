package my.portal.component;

import java.awt.Cursor;

import javax.swing.JButton;

import my.portal.bean.ImageLink;
import my.portal.util.IOUtil;

public class JButtonHyperlink extends JButton{

	private final ImageLink link;
	private static final long serialVersionUID = 1L;

	public JButtonHyperlink() {
		this(new ImageLink());
	}

	public JButtonHyperlink(ImageLink link) {
		this.link = link;
		initComponents();

	}

	private void initComponents() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addActionListener(e -> IOUtil.openBrowser(this, link.getTargetUrl()));
	}

}
