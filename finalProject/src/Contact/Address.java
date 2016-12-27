package Contact;

public class Address  implements Comparable<Object> {

	private String street;
	private String city;
	private String num;
	
	public Address(String add)
	{
		String spAdd[]=add.split(" ");
		setStreet(spAdd[1]);
		setCity(spAdd[0]);
		setNum(spAdd[2]);
		
	}
	public Address(String city, String street,String num) {
		setStreet(street);
		setCity(city);
		setNum(num);
	}
	public void setAddress(String s)
	{
		String spAdd[]=s.split(" ");
		setStreet(spAdd[0]);
		setCity(spAdd[1]);
		setNum(spAdd[2]);
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		boolean flag=false;//check if there is a letter written
		for(int i=0;i<street.length();i++)
		{
			if(!(((street.charAt(i)==' '||street.charAt(i)==39)&&flag)||(street.charAt(i)<='Z'&&street.charAt(i)>='A')||(street.charAt(i)<='z'&&street.charAt(i)>='a')))
				throw new  RuntimeException("incorrect street input.");
			flag=true;
		}
		this.street = new String(street);
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		boolean flag=false;
		for(int i=0;i<city.length();i++)
		{
			if(!(((city.charAt(i)==' '||city.charAt(i)==39)&&flag)||(city.charAt(i)<='Z'&&city.charAt(i)>='A')||(city.charAt(i)<='z'&&city.charAt(i)>='a')))
				throw new  RuntimeException("incorrect City input.");
			flag=true;
		}
		this.city=new String(city);
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		boolean flag=false;
		for(int i=0;i<num.length();i++)
		{
			if(!((num.charAt(i)>='0'&&num.charAt(i)<='9')||((city.charAt(i)<='Z'&&city.charAt(i)>='A')||(city.charAt(i)<='z'&&city.charAt(i)>='a'))&&flag))
				throw new  RuntimeException("incorrect home number input.");
			flag=true;
		}
		this.num = new String(num);
	}
	@Override
	public String toString() {
		return  city + ", "  + street + ", "+num;
	}
	
	public static void spliteAdd(Address n,String name1,String name2,String name3) {
		name1=new String(n.getStreet());
		name2=new String(n.getCity());
		name3=new String(n.getNum());
	}
	@Override
	public int compareTo(Object arg0) {
		Address temp=(Address)arg0;
		if(arg0==null)
			return -100;
		return (temp.getStreet()).compareTo(street)+(temp.getCity()).compareTo(city)+(temp.getNum()).compareTo(this.num);
	}

}
