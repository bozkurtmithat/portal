package my.portal.component.miglayout;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;
import my.portal.component.JButtonHyperlink;
import my.portal.component.JPanelBase;
import my.portal.component.SafeIcon;
import my.portal.model.ImageLink;
import my.portal.model.ImageLinkBuilder;
import my.portal.util.IOUtil;
import net.miginfocom.swing.MigLayout;

public class TopPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;
	@Getter @Setter
	AuthorizedUserPanel authorizedUserPanel;
	
	public TopPanel() {
		initComponents();
	}

	private void initComponents() {
		setBackground(new Color(55, 120, 173));
		setLayout(new MigLayout("insets 0 0 0 15,fill","[][fill,grow]20[align right, pref:pref:pref]","[fill]"));
		
		add(new JLabel(IOUtil.loadIcon("logo.png")));
		int width=150, height= 50;
		JPanelBase linkPanel = new JPanelBase();
		linkPanel.setBackground(getBackground());
		linkPanel.setLayout(new MigLayout("gapx 10", "[pref:pref:pref]"));
		linkPanel.add(createLinkButtonWithPreferedSize("My Bakanlık", "https://google.com", null, width,height));
		linkPanel.add(createLinkButtonWithPreferedSize("Sargı Birimleri", "https://google.com",null, width,height));
		linkPanel.add(createLinkButtonWithPreferedSize("Şifre İşlemleri", "https://google.com",null, width,height));
		add(linkPanel);
		authorizedUserPanel = new AuthorizedUserPanel();
		add(authorizedUserPanel);
		
	}

	private JButtonHyperlink createLinkButtonWithPreferedSize(String text, String url, Icon icon, int width, int height) {
		ImageLink imageLink = ImageLinkBuilder.builder().text(text).targetUrl(url).image(new SafeIcon(icon).iconToImage()).build();
		JButtonHyperlink hlink = new JButtonHyperlink(imageLink);
		hlink.setPreferredSize(new Dimension(width, height));
		return hlink;
	}
	
	
}
