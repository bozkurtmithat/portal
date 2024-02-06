package my.portal.component;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import my.portal.model.ImageLink;
import my.portal.util.IOUtil;

public class JLabelHyperlink extends JLabel {
	
    private static final long serialVersionUID = 1L;
    private ImageLink link;
    
    public JLabelHyperlink(ImageLink link) {
    	this.link = link;
    	init();
    }
    
     
    public void init() {
        if(link.getImage() != null)
        	setIcon(new ImageIcon(link.getImage()));
        setText(link.getHtmlPlainText());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setHorizontalTextPosition(JLabel.TRAILING);
        setVerticalTextPosition(JLabel.CENTER);
        addMouseListener(new MouseAdapter() {
             
            @Override
            public void mouseEntered(MouseEvent e) {
                setText(link.getHtmlAnchorText());
            }
             
            @Override
            public void mouseExited(MouseEvent e) {
                setText(link.getHtmlPlainText());
            }
             
            @Override
            public void mouseClicked(MouseEvent e) {
               IOUtil.openBrowser(JLabelHyperlink.this, link.getTargetUrl());        
            }
             
        });
         
    }
}