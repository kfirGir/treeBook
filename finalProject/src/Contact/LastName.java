package Contact;

public class LastName  implements Comparable<Object>{
	private String lname;

	
	public LastName(String lname) {
		setlname(lname);
	}
	

	public String getlname() {
		return lname;
	}

	public void setlname(String lname) {
		for(int i=0;i<lname.length();i++)
			if(!((lname.charAt(i)<='Z'&&lname.charAt(i)>='A')||(lname.charAt(i)<='z'&&lname.charAt(i)>='a')||((lname.charAt(i)==39||lname.charAt(i)==' ')&&i>1)))
			throw new  RuntimeException("incorrect last name input.");
		this.lname = new String(lname);
	}
	
	
	@Override
	public String toString() {
		return  lname;
	}


	@Override
	public int compareTo(Object arg0) {
		return lname.compareTo(((LastName)arg0).getlname());
	}
	
}
