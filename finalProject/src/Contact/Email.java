package Contact;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Email implements Comparable<Object> {
private String mail;

	public Email(String st) {
	setMail(st);
}

	public String getMail() {
	return mail;
}

public void setMail(String st) {
	EmailValidator em=new EmailValidator();
	if(em.validate(st)==false)
		throw new RuntimeException("incorrect email input."); 
	else
		this.mail = new String(st);
		
}

	@Override
	public int compareTo(Object arg0) {
		if(arg0==null)
			return -100;
		return mail.compareTo(((Email) arg0).getMail());
	}

	@Override
	public String toString() {
		return this.mail;
	}

private class EmailValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
}
}
