package my.portal.component.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import my.portal.common.controllers.CDI;
import my.portal.component.JPanelBase;
import my.portal.controllers.LinkController;
import my.portal.util.IOUtil;

public class MainPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;

	private Image backGroundImage;

	public MainPanel() {
		setLayout(null);
		backGroundImage = IOUtil.getImage("background-1310x728.png");
		initComponents();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backGroundImage, 0, 0, getWidth(getPreferredSize()), getHeight(getPreferredSize()),
				Color.WHITE, this);
	}

	private void initComponents() {

		LinkController linker = CDI.getImpl(LinkController.class);
		
		// add top
		AbsoluteTopPanel topPanel = new AbsoluteTopPanel();
		topPanel.setPreferredSize(new Dimension(600, 50));
		topPanel.setBounds(180, 10, (int) topPanel.getPreferredSize().getWidth(),
				(int) topPanel.getPreferredSize().getHeight());
		linker.getTopLinks().forEach(topPanel.getModel()::addImageLink);
		add(topPanel);

		// add left
		AbsoluteLinksPanel leftPanel = new AbsoluteLinksPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setEnableRoundedBorder(true);
		leftPanel.setPreferredSize(new Dimension(400, 500));
		leftPanel.setBounds(20, 80, (int) leftPanel.getPreferredSize().getWidth(),
				(int) leftPanel.getPreferredSize().getHeight());
		linker.getLinks().forEach(leftPanel.getModel()::addImageLink);
		add(leftPanel);
		
		// add center
		AbsoluteCenterPanel centerPanel = new AbsoluteCenterPanel(new Dimension(851, 422)); //455
//		centerPanel.setBackground(Color.WHITE);
		centerPanel.setEnableRoundedBorder(true);
		centerPanel.setBounds(20+leftPanel.getWidth()+20, 80, (int) centerPanel.getPreferredSize().getWidth(),
				(int) centerPanel.getPreferredSize().getHeight());
		add(centerPanel);
	}
	
}
