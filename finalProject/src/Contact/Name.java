package Contact;

import SearchTree.*;;
public class Name implements Comparable<Object>{
	private FirstName  fname;
	private LastName   lname;
	
	
	public Name(String name1,String name2) {
		this.fname = new FirstName (name1);
		this.lname = new LastName (name2);
	}
	public Name(FirstName name1,LastName name2) {
		this.fname = name1;
		this.lname =name2;
	}
	private Name(FirstName name1)
	{
		this.fname=name1;
		this.lname=null;
	}
	private Name(LastName name1)
	{
		this.fname=null;
		this.lname=name1;
	}
	
	
	public FirstName getFname() {
		return fname;
	}


	public void setFname(FirstName fname) {
		this.fname = fname;
	}


	public LastName getLname() {
		return lname;
	}


	public void setLname(LastName lname) {
		this.lname = lname;
	}


	@Override
	public String toString() {
		return "firstName"+fname.toString() +"LastName=" + lname.toString();
	}

	public static Name createName(FirstName name1,LastName name2) {
		if(name1==null&&name2==null)
			throw new RuntimeException("incurrect first name and last name to create 'Name' \n");
		if(name1==null)
			return new Name(name2);
		if(name2==null)
			return new Name(name1);
		return new Name(name1,name2);
	}
		
	@Override
	public int compareTo(Object arg0) {
		Name temp=(Name)arg0;
		int ans1,ans2;
		if(fname==null||temp.getFname()==null)ans2=0;
		else
		ans2=temp.getFname().compareTo(fname);
		if(temp==null||temp.getLname()==null)ans1=0;
		else
		ans1=temp.getLname().compareTo(lname);
			if(ans1>0)
			{
				if(ans2>0)
					return CompareTree.RR;
				if(ans2==0)
					return CompareTree.RLE;
				else
					return CompareTree.RL;
			}
			if(ans1==0)
				if(ans2==0)
					return CompareTree.EQUAL;
				else{
					if(ans2>0)
					return CompareTree.LR;
					else
						return CompareTree.LL;
				}
			if(ans1<0)
			{
				if(ans2<0)
					return CompareTree.LL;
				if(ans2==0)
					return CompareTree.LRE; 
				else
					return CompareTree.LR;
			}
		
		return -100;
		}

}
