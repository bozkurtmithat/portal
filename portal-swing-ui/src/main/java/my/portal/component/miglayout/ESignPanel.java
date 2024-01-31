package my.portal.component.miglayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import my.portal.component.JPanelBase;
import net.miginfocom.swing.MigLayout;
import tr.gov.uyap.system.login.esign.LoginTask;
import tr.gov.uyap.system.login.esign.SignActionListener;
import tr.gov.uyap.system.login.esign.SignTask;
import tr.gov.uyap.system.login.esign.bean.ApplicationBean;
import tr.gov.uyap.system.login.esign.bean.CertificateWrapper;
import tr.gov.uyap.system.login.esign.bean.SmartCardManager;
import tr.gov.uyap.system.login.esign.exception.ExceptionHandler;
import tr.gov.uyap.system.login.esign.misc.BrowserUtil;
import tr.gov.uyap.system.login.esign.misc.Config;
import tr.gov.uyap.system.login.esign.misc.ImageUtil;
import tr.gov.uyap.system.login.esign.misc.TextResources;
import tr.gov.uyap.system.login.esign.swing.LockingLayerUI;
import tr.gov.uyap.system.login.esign.swing.OptionPane;
import tr.gov.uyap.system.login.esign.swing.Status;
import tr.gov.uyap.system.login.esign.swing.StatusBar;
import tr.gov.uyap.system.login.esign.swing.StatusIndicator;
import tr.gov.uyap.system.login.esign.swing.TextPane;
import tr.gov.uyap.system.login.esign.ui.ApplicationChoosePanel;
import tr.gov.uyap.system.login.esign.ui.CertDetailsPanel;
import tr.gov.uyap.system.login.esign.ui.IconButton;
import tr.gov.uyap.system.login.esign.ui.NumPad;
import tr.gov.uyap.system.login.esign.ui.SettingsPanel;

public class ESignPanel extends JPanelBase implements SignActionListener {
	
	private static final long serialVersionUID = 1L;
	private final LockingLayerUI<JPanel> lockingLayer = new LockingLayerUI<>();
	private java.awt.Image backGrounImage  = ImageUtil.getImage("uyap-logo-only.png");//("background.png");
	private NumPad numPad;
	private JButton settingsButton;
	private JTextPane dataToSignTextPane;
	private CertDetailsPanel certDetailsPanel;
	private JScrollPane dataToSignScrollPane;
	private ApplicationChoosePanel applicationChoosePanel;
	
	private JFrame window;

	public ESignPanel(JFrame w) {
		this.window=w;
		SmartCardManager.INSTANCE.initialize(window);
		initializeUI();
	}

	private void initializeUI() {
		JPanel backgroundPanel = new JPanel() {
			
			private static final long serialVersionUID = 1L;

			{
				setBackground(Color.WHITE);
			}
			
			public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    Graphics2D g2d = (Graphics2D) g;
			    g2d.drawImage(backGrounImage, this.getWidth() - backGrounImage.getWidth(this) - 30, this.getHeight() - backGrounImage.getHeight(this) - 30, this);
			}
			
		};
		
//		setTitle(TextResources.applicationFrameTitle + " - " + VersionUtil.getCurrentVersion());
		
		setBackground(Color.WHITE);
		backgroundPanel.setLayout(new MigLayout("wrap", "[160!][fill,grow][]", "[10!]10[pref:pref:pref,fill]10[grow]"));
		// 4,0
		backgroundPanel.add(getSettingsButton(), "alignx right, spanx");
		
		// 0,1
		applicationChoosePanel = new ApplicationChoosePanel();
		backgroundPanel.add(applicationChoosePanel, "grow");
		
		// 2,1
		certDetailsPanel = new CertDetailsPanel(window);
		backgroundPanel.add(certDetailsPanel, "grow, w 450");
		
		numPad = new NumPad(window);
		numPad.addSignActionListener(this);
		backgroundPanel.add(numPad, "growy, w 100");

		backgroundPanel.add(getDataToSignPanel(), "grow, spanx");
		JPanel contentPane = new JPanel(new BorderLayout());
		JLayer<JPanel> layer = new JLayer<>(backgroundPanel, lockingLayer);
		contentPane.add(layer, BorderLayout.CENTER);
		StatusBar statusBar = new StatusBar();
	    contentPane.add(statusBar, BorderLayout.SOUTH);
	    add(contentPane);
	}
	
	

	public JButton getSettingsButton() {
		if(settingsButton == null) {
			settingsButton = new IconButton("settings.png");
			settingsButton.addActionListener( this::settingButtonActionPerformed );
		}
		return settingsButton;
	}

	private void settingButtonActionPerformed(ActionEvent e) {
		JDialog dialog = new SettingsPanel(window);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setSize(450, 250);
		dialog.setLocationRelativeTo(ESignPanel.this);
		dialog.setVisible(true);
	}
	
	
	
	public JTextPane getDataToSignTextPane() {
		if(dataToSignTextPane == null){
			dataToSignTextPane = new TextPane();
			dataToSignTextPane.setBorder(null);
			dataToSignTextPane.setBackground(new Color(0,0,0,0));
			dataToSignTextPane.setEditable(false);
			dataToSignTextPane.setOpaque(false);
			dataToSignTextPane.setText(Config.getDataToSign());
		}
		return dataToSignTextPane;
	}
	
	
	public JScrollPane getDataToSignPanel() {
		if(dataToSignScrollPane == null){
			dataToSignScrollPane = new JScrollPane();
			dataToSignScrollPane.setViewportBorder(null);
			dataToSignScrollPane.setOpaque(false);
			dataToSignScrollPane.getViewport().setOpaque(false);
			dataToSignScrollPane.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.gray), TextResources.dataToSign));
			
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.add(getDataToSignTextPane(), BorderLayout.CENTER);
			panel.setOpaque(false);
			dataToSignScrollPane.setViewportView(panel);
			
			javax.swing.SwingUtilities.invokeLater( () -> getDataToSignPanel().getVerticalScrollBar().setValue(0) );
			
		}
		return dataToSignScrollPane;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!applicationChoosePanel.getInputVerifier().verify(applicationChoosePanel)){
			return;
		}
		
		if(!certDetailsPanel.getInputVerifier().verify(certDetailsPanel)){
			return;
		}
		
		if(!numPad.getInputVerifier().verify(numPad)){
			return;
		}
		
		final ApplicationBean application = applicationChoosePanel.getSelectedApplication();
		CertificateWrapper certificateWrapper = certDetailsPanel.getSelectedCertificate();
		String pin = numPad.getTypedPassword();
		
		lockingLayer.lock();
		
		final SignTask task = new SignTask(Config.getDataToSign(), application.getSecurityTokenEndpoint(), certificateWrapper, pin);
		task.addPropertyChangeListener( (PropertyChangeEvent evt) -> {
			
				if ("state".equals(evt.getPropertyName()) && SwingWorker.StateValue.DONE == evt.getNewValue()) {
					try {
						byte[] signedData = task.get();
						StatusIndicator.setStatus("İmzalı veri hazırlandı...");
						executeLoginTask(signedData, application);
					} catch (Exception exc) {
						String message = ExceptionHandler.handleAndGetExceptionMessage(exc);
						lockingLayer.unlock();
						StatusIndicator.setStatus(message, Status.ERROR);
						OptionPane.showMessageDialog(ESignPanel.this, message);
					}
				}
		});

		task.execute();
	}
	
	private void executeLoginTask(byte[] signedData, final ApplicationBean application) {
		
		final LoginTask task = new LoginTask(signedData, application);
		task.addPropertyChangeListener( evt -> {
				if ("state".equals(evt.getPropertyName()) && SwingWorker.StateValue.DONE == evt.getNewValue()) {
					lockingLayer.unlock();
					String token = null;
					try {
						token = task.get();
					} catch (Exception e) {
						String message = ExceptionHandler.handleAndGetExceptionMessage(e);
						StatusIndicator.setStatus(message, Status.ERROR);
						OptionPane.showMessageDialog(ESignPanel.this, message);
						return;
					}
					
					String encodedToken = null;
					try {
						
						//token = URLEncoder.encode(new BASE64Encoder().encode(jsessionId.getBytes(Charset.forName("utf-8"))), "utf-8");
						encodedToken = URLEncoder.encode(javax.xml.bind.DatatypeConverter.printBase64Binary(token.getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1.name());
					} catch (UnsupportedEncodingException e1) {
						// omit
					}
					
					String url = application.getLoginEndpoint() + "&token=" + encodedToken;
					
					try {
						BrowserUtil.openWebpage(url);
//						dispose();
						// ??
//						System.exit(1);
					} catch (Exception e) {
						e.printStackTrace();
						
						StringSelection stringSelection = new StringSelection(url);
						java.awt.datatransfer.Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
						clpbrd.setContents(stringSelection, null);
						
						OptionPane.showMessageDialog(ESignPanel.this, "Sayfa adresi panoya koyalandı. İstediğiniz tarayıcıyı açıp adres satırına yapıştırarak sayfaya erişebilirsiniz.");
						
					}
				}
			});

		task.execute();
	}

}

