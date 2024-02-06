package my.portal.model;

import javax.swing.Icon;

import lombok.Data;

@Data
public class AuthorizedUser {

	private String name;
	private String surname;
	private String title;
	private String sicil; //Usercredentialdaki personelId;
	private String kullaniciId;
	private String birim;
	private String eposta;
	private Icon resim;
	
}
