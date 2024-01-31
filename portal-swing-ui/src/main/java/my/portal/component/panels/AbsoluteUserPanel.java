package my.portal.component.panels;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import my.portal.bean.AuthorizedUser;
import my.portal.component.JPanelBase;
import my.portal.util.IOUtil;

public class AbsoluteUserPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;
	private AuthorizedUser user;

	public AbsoluteUserPanel() {
		initComponents();
	}

	private void initComponents() {
		setPreferredSize(new Dimension(250, 50));
		setLayout(null);
		removeAll();
		add( createButton() );
	}

	private JComponent createButton() {
		JButton iconButton = hasPicture() ? new JButton(user.getResim())
				:createGiris();
		iconButton.setVerticalTextPosition(AbstractButton.CENTER);
		iconButton.setHorizontalTextPosition(AbstractButton.LEADING);
		return iconButton;
	}
	
	private JButton createGiris() {
		JButton iconButton = new JButton();
		iconButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		iconButton.setHorizontalTextPosition(JButton.TRAILING);
		iconButton.setVerticalTextPosition(JLabel.CENTER);
		iconButton.setText("Giriş");
		iconButton.setIcon(IOUtil.loadIcon32("enter.png"));
		return iconButton;
	}
	
	boolean hasPicture(){
		return user != null && user.getResim() != null;
	}

	public void setUser(AuthorizedUser user) {
		this.user = user;
		initComponents();
	}

	public AuthorizedUser getUser() {
		return user;
	}

}
