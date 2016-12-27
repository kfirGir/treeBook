package Contact;

public class Phone  implements Comparable<Object>{
	private String number;
	
	public Phone(String n){
		setnumber(n);
	}
	public String getnumber(){
		return number;
	}
	
	public void setnumber(String s)
	{
		boolean flag=true;
		if(s.isEmpty())this.number=null;
		else
		{
		for(int i=0;i<s.length();i++)
			if(!((s.charAt(i)>='0'&&s.charAt(i)<='9')))
				flag=false;
		
		if(s.length()>10||s.length()<9||s.charAt(0)!='0')flag=false;
		if(!flag)
			throw new RuntimeException("incorrect phone input.");
		else
			this.number=new String(s);
		}
	}
	@Override
	public String toString() {
		return this.number;
	}
	@Override
	public int compareTo(Object arg0) {
		if(arg0==null)
			return -100;
		return number.compareTo(((Phone) arg0).getnumber());
	}
}
