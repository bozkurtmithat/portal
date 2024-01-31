package my.portal.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import my.portal.util.UIUtils;

public class JPanelBase extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private boolean enableRoundedBorder = false;

	private Image backgroundImage;
 

	public JPanelBase() {
//		setBackground(IOUtil.getSystemBackgroudColor());
//		setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		setOpaque(false);
	}

	public void setEnableRoundedBorder(boolean enableRoundedBorder) {
		this.enableRoundedBorder = enableRoundedBorder;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public boolean isEnableRoundedBorder() {
		return enableRoundedBorder;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image roundedBackgroundImage=null;
		if (enableRoundedBorder) {
			if(backgroundImage != null) {
				roundedBackgroundImage = UIUtils.imageToRoundedCornerImage(backgroundImage);
			}
			UIUtils.roundedCorner(this, g, roundedBackgroundImage);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("should be override!");
	}

	public int getHeight(Dimension d) {
		return (int) d.getHeight();
	}

	public int getWidth(Dimension d) {
		return (int) d.getWidth();
	}

}
