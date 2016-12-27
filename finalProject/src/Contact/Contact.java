package Contact;

import SearchTree.CompareTree;

public class Contact implements Comparable<Contact>,CompareTree {
private FirstName Fname;
private LastName Lname;
private Phone mobileNumber;
private Phone HomeNumber;

private Address address;
private Email email;

public static final int BYFIRST=1; //by last name
public static final int BYSECOND=2;// by first name
public static final int BOTH=0;// by name (first name and last name)

// FULL contact constractor while reading form text
public Contact(String fname, String lname, String homeNumber, String mobileNumber, String address, String email) {

	this.Fname =new FirstName(fname);
	this.Lname =new LastName(lname);
	this.mobileNumber = new Phone(mobileNumber);
	if(homeNumber!=null)
	this.HomeNumber =  new Phone(homeNumber);
	else
		this.HomeNumber=null;
	this.address = new Address (address);
	if(email!=null)
	this.email = new Email(email);
	else
		this.email=null;

}
//FULL contact constractor while insert by the app
@SuppressWarnings("null")
public Contact(String fname, String lname,String mobileNumber,String homeNumber, Address address, String email) {

	this.Fname =new FirstName(fname);
	this.Lname =new LastName(lname);
	if((mobileNumber==null||mobileNumber.isEmpty())&&(homeNumber!=null||!homeNumber.isEmpty()))
	this.mobileNumber=new Phone(homeNumber);
	else
	{
	this.mobileNumber = new Phone(mobileNumber);
	if((homeNumber==null)||(homeNumber.isEmpty()))
		this.HomeNumber=null;
	else
	   this.HomeNumber =  new Phone(homeNumber);
	}

	this.address =address;
	if((email!=null)&&(!email.isEmpty()))
	this.email = new Email(email);
	else
		this.email=null;

}
// contact with one number constractor
public Contact(String fname, String lname, String mobileNumber, String address, String email) {

	this(fname,lname,mobileNumber,null,address,email);

}
//contact with one number constractor  while insert by the app
public Contact(String fname, String lname, String mobileNumber, Address address, String email) {

	this(fname,lname,mobileNumber,null,address,email);

}

//contact with  out mail  while insert by the app
public Contact(String fname, String lname,String mobileNumber,String homeNumber , Address address) {

	this(fname,lname,homeNumber,mobileNumber,address,null);

}

//contact with  out mail and homeNumber or moblieNumber while insert by the app
public Contact(String fname, String lname,String mobileNumber, Address address) {

	this(fname,lname,mobileNumber,null,address,null);

}

// to search Constractor
public Contact(String fname,String lname,int howToSearch)
{
	if((lname==null||lname.isEmpty())&&(fname==null||fname.isEmpty()))
		throw new RuntimeException("Enter please a values to search for \n");
	
	switch(howToSearch)
	{
	case BYFIRST:
		this.Fname=new FirstName(fname);
		this.Lname=null;
		this.mobileNumber=null;
		this.HomeNumber=null;
		this.address=null;
		this.email=null;;
		break;
	case BYSECOND:
		this.Fname=null;
		this.Lname=new LastName(lname);
		this.mobileNumber=null;
		this.HomeNumber=null;
		this.address=null;
		this.email=null;;
		break;
	case BOTH:
		this.Fname=new FirstName(fname);
		this.Lname=new LastName(lname);
		this.mobileNumber=null;
		this.HomeNumber=null;
		this.address=null;
		this.email=null;;
		break;
		default:
			throw new RuntimeException("incurrect how to search input");
	}
	
}
public FirstName getFname() {
	return Fname;
}
public void setFname(String fname) {
	Fname.setFname(fname);
}
public LastName getLname() {
	return Lname;
}
public void setLname(String lname) {
	Lname.setlname(lname);
}
public Phone getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber.setnumber(mobileNumber);
}
public Phone getHomeNumber() {
	return HomeNumber;
}
public void setHomeNumber(String homeNumber) {
	HomeNumber.setnumber(homeNumber);
}
public Address getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address.setAddress(address);
}
public Email getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email.setMail(email);
}
@Override
public String toString() {
	String st="";
	
	if(this.Fname!=null)st+=this.Fname.toString();
	if(this.Lname!=null)st=st+", "+this.Lname.toString();
	if(this.mobileNumber!=null)st=st+", "+this.mobileNumber.toString();
	if(this.HomeNumber!=null)st=st+", "+this.HomeNumber.toString();
	if(this.address!=null)st=st+", "+this.address.toString();
	if(this.email!=null)st=st+", "+this.email.toString();
	return st;
}
public String myToString(){
	String st[]=new String[7];
	for(int i=0;i<7;i++)
		st[i]="                         ";
	if(this.Fname!=null)st[0]=this.Fname.toString();
	if(this.Lname!=null)st[1]=this.Lname.toString();
	if(this.mobileNumber!=null)st[2]=this.mobileNumber.toString();
	if(this.HomeNumber!=null)st[3]=this.HomeNumber.toString();
	if(this.address!=null)st[4]=this.address.toString();
	if(this.email!=null)st[5]=this.email.toString();
	return String.format("%15s   %20s   %20s   %15s   %30s   %30s", st[0],st[1],st[2],st[3],st[4],st[5],st[6]);
}
@Override
public int compareTo(Contact o) {
	Contact temp=(Contact)o;
	int ans;
	boolean totalAnswer=true,firstAns=false;
	Name n1=null,n2=null;
   n1=Name.createName(temp.getFname(),  temp.getLname());
   n2=Name.createName(this.Fname,  this.Lname);
   ans=n1.compareTo(n2);
   if(address!=null)
	   firstAns=totalAnswer=totalAnswer&&(address.compareTo(temp.getAddress())==0);
   if(HomeNumber!=null)
	   totalAnswer=totalAnswer&&(HomeNumber.compareTo(temp.getHomeNumber())==0);
   if(mobileNumber!=null)
	   totalAnswer=totalAnswer&&(mobileNumber.compareTo(temp.getMobileNumber())==0);
   if(email!=null)
	   totalAnswer=totalAnswer&&(email.compareTo(temp.getEmail())==0);

   if(ans==CompareTree.EQUAL&&(totalAnswer&&firstAns))
	return CompareTree.SAME;
   return ans;
}

public static int myCompareTo(Contact o1,Contact o2)
{
	return o1.compareTo(o2);
}
public void update(Contact newData)
{
	this.Fname=null;this.Fname=newData.getFname();
	this.Lname=null;this.Lname=newData.getLname();
	this.address=null;this.address=newData.getAddress();
	this.email=null;this.email=newData.getEmail();
	this.HomeNumber=null;this.HomeNumber=newData.getHomeNumber();
	this.mobileNumber=null;this.mobileNumber=newData.getMobileNumber();
}

}
