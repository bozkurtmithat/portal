package my.portal.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.FontUIResource;

public class UIUtils {

	public static void setUIFont(int size) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				FontUIResource orig = (FontUIResource) value;
				Font font = new Font(orig.getFontName(), orig.getStyle(), size);
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}

	public static void setNimbus() {
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

	public static GraphicsConfiguration getGraphicsConfiguration() {

		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

	}

	public static BufferedImage createCompatibleImage(int width, int height) {

		return createCompatibleImage(width, height, Transparency.TRANSLUCENT);

	}

	public static void applyQualityProperties(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	}

	public static BufferedImage createCompatibleImage(int width, int height, int transparency) {

		BufferedImage image = getGraphicsConfiguration().createCompatibleImage(width, height, transparency);
		image.coerceData(true);
		return image;

	}

	public static void roundedCorner(Component c, Graphics g, Image image) {
		// Border corners arcs {width,height}, change this to whatever you want
		Dimension arcs = new Dimension(15, 15); 
		int width = image == null ? c.getWidth() : image.getWidth(c);
		int height = image == null ? c.getHeight() : image.getHeight(c) ;
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draws the rounded panel with borders.
		graphics.setColor(c.getBackground());
		graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint background
		if(image != null)
			graphics.drawImage(image, 0, 0, c);
		graphics.setColor(c.getForeground());
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint border
	}
	
	public static Image imageToRoundedCornerImage(Image image) {
		 // Get the width,height of the image
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        // Create a new BufferedImage object with the width,height
        // equal to that of the image file
        BufferedImage bim = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object by using
        // createGraphics() method. This object is 
        // used to perform the operation!
        Graphics2D g2 = bim.createGraphics();

        // You can also use rendering hints
        // to smooth the edges or the rounded rec
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);
        
     // This method does it all!. You can clip the
        // image into the shape you wish, play it as you like!
        g2.setClip(new RoundRectangle2D.Double(0, 0, width, height, 15, 15));

        // Now, draw the image. The image is now
        // in the 'clipped' shape, the shape in the setClip()
        g2.drawImage(image, 0, 0, null);

        // Dispose it, we no longer need it.
        g2.dispose();
        
        return bim;
	}
}
