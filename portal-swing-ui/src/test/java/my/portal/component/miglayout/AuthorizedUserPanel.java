package my.portal.component.miglayout;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import my.portal.bean.AuthorizedUser;
import my.portal.component.JPanelBase;
import my.portal.util.IOUtil;
import net.miginfocom.swing.MigLayout;

public class AuthorizedUserPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;
	private AuthorizedUser user;

	public AuthorizedUserPanel() {
		initComponents();
	}

	public AuthorizedUserPanel(AuthorizedUser user) {
		this.user = user;
		initComponents();
	}

	private void initComponents() {
		setPreferredSize(new Dimension(250, 50));
		setLayout(new MigLayout("insets 0 0 0 0","[fill,grow]", "[fill,grow]"));
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
