package my.portal.controllers;

import java.net.URL;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import tr.gov.uyap.system.login.esign.misc.Config;

public class ESignController {
	
	public void prepare(String arg) {
			try {
				Config.initialize(readConfiguration(arg));
			} catch (java.io.FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Konfigürasyon dosyası okunamadı, dosya bulunamadı...",
						"Hata oluştu", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (java.net.ConnectException e) {
				JOptionPane.showMessageDialog(null,
						"Konfigürasyon dosyası okunamadı, Sunucu ile bağlantı kurulamadı, İnternet bağlantınızı kontrol ediniz...",
						"Hata oluştu", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Konfigürasyon dosyası okunamadı, bilinmeyen hata...",
						"Hata oluştu", JOptionPane.ERROR_MESSAGE);
				return;
			}
	}
	
	private static Document readConfiguration(String url) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setCoalescing(true);
		return factory.newDocumentBuilder().parse(new URL(url).openStream());
	}

}
