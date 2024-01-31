package my.portal.component.miglayout;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.Timer;

import my.portal.component.JPanelBase;

public class StatusPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private JLabel clock;
	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss");
	
	public StatusPanel() {
		clock = new JLabel();
		add(clock);
		timer = new Timer(1000, this::updateTime);
		timer.start(); 
	}
	
	private void updateTime(ActionEvent event) {
		clock.setText(fmt.format(LocalDateTime.now()));
	}
	
}
