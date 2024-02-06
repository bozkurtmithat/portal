package my.portal.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import my.portal.model.ImageLink;

public class IOUtil {

	private static Color uyapBGColor = Color.WHITE;

	public static void setSystemBackgroudColor(Color c) {
		uyapBGColor = c;
	}

	public static Color getSystemBackgroudColor() {
		return uyapBGColor;
	}

	public static Icon loadIcon32(String resource) {
		return loadIcon(resource, 32, 32);
	}

	public static Icon loadIcon24(String resource) {
		return loadIcon(resource, 24, 24);
	}

	public static Icon loadIcon(String resource) {
		return loadIcon(resource, 0, 0);
	}

	public static Icon loadIcon(String resource, int width, int height) {
		try {
			URL url = getURL(resource);
			BufferedImage bufferedImage = ImageIO.read(url);
//			System.out.println("width:" + bufferedImage.getWidth() + " height:" + bufferedImage.getHeight());
			return new ImageIcon(
					needResize(bufferedImage, width, height) ? resize(bufferedImage, width, height) : bufferedImage);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean needResize(BufferedImage bufferedImage, int w, int h) {
		return w > 0 && h > 0 && (bufferedImage.getHeight() > h || bufferedImage.getWidth() > w);
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
	
	public static Image getImageFromUrl(String urlString) {
		try {
			URL url = new URL(urlString);
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Image getImage(String imageName) {

		Image image=null;
		URL url = getURL(imageName);
		if (url != null) {
			image = Toolkit.getDefaultToolkit().getImage(url);
		}
		return image;

	}
	
	public static Image getImage(ImageLink imageLink) {
		if(imageLink.getImage() == null && imageLink.getImageUrl() != null) {
			imageLink.setImage(IOUtil.getImageFromUrl(imageLink.getImageUrl()));
		}
		return imageLink.getImage();
	}

	private static URL getURL(String resource) {
		ClassLoader cl = IOUtil.class.getClassLoader();
		URL url = cl.getResource(resource);
		if (url == null) {
			url = cl.getResource("/" + resource);
		}

		if (url == null) {
			if (resource.indexOf("/") > -1) {
				int i = resource.lastIndexOf('/');
				resource = resource.substring(i + 1);
				url = cl.getResource("/" + resource);
			}

		}

		if (url == null) {
			url = cl.getResource("/" + resource);
		}

		return url;
	}
	
	public static void openBrowser(Component comp, String url){
		 try {
             Desktop.getDesktop().browse(new URI(url));
         } catch (IOException | URISyntaxException e1) {
             JOptionPane.showMessageDialog(comp,
                     "Could not open the hyperlink. Error: " + e1.getMessage(),
                     "Error",
                     JOptionPane.ERROR_MESSAGE);
         }      
	}
}
