package Contact;

public class FirstName implements Comparable<Object>{
	private String fname;

	
	public FirstName(String fname) {
		setFname(fname);
	}
	

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		for(int i=0;i<fname.length();i++)
			if(!((fname.charAt(i)<='Z'&&fname.charAt(i)>='A')||(fname.charAt(i)<='z'&&fname.charAt(i)>='a')||((fname.charAt(i)==39||fname.charAt(i)==' ')&&i>1)))
				throw new  RuntimeException("incorrect first name input.");
		this.fname = new String(fname);
	}
	
	
	@Override
	public String toString() {
		return fname;
	}


	@Override
	public int compareTo(Object arg0) {
		return fname.compareTo(((FirstName)arg0).getFname());
	}
	
}
