
package ContactBook;
import java.io.*;

import Contact.Address;
import Contact.Contact;
import SearchTree.FouraryTree;

public class mainRunTree {

	public static void main(String[] args) {
		FouraryTree<Contact> phoneBook= new FouraryTree<Contact>();
		File contactFile=new File("ContactList.txt");
		int countLine=0;
		try{
			 //Create object of FileReader
	          FileReader inputFile = new FileReader(contactFile);

	          //Instantiate the BufferedReader Class
	          BufferedReader bufferReader = new BufferedReader(inputFile);
	          
	         //Variable to hold the one line data
	          String line;

	          // Read file line by line and print on the console
	          while ((line = bufferReader.readLine()) != null)   {
	            String[] st=line.split(", ");
	            countLine++;
	            if(!(line.isEmpty()||st.length==0))
	            {
	            switch(st.length)
	            {
	            case 8:
	            	phoneBook.insert(new Contact(st[0], st[1], st[2],st[3],new Address(st[4],st[5],st[6]), st[7]));
	           break;
	            case 7:
	            	if(st[3].contains("0")==true)
	            		phoneBook.insert(new Contact(st[0], st[1], st[2],st[3],new Address(st[4],st[5],st[6])));
	            	else
	            		phoneBook.insert(new Contact(st[0], st[1], st[2],(new Address(st[3],st[4],st[5])),st[6]));
	            	break;
	            case 6:
	            	phoneBook.insert(new Contact(st[0], st[1], st[2],new Address(st[3],st[4],st[5])));
	            	break;
	           default:
	        	   throw new RuntimeException("incurrect line at your file\n");
	            }
	            }
	           
	            
		}
	          //Close the buffer reader
	          bufferReader.close();
		}
		catch(IOException ex)
		{
			System.out.println("Error while reading file line by line:"+countLine+" " + ex.getMessage());
			System.exit(0);
		}
		catch(RuntimeException Rex)
		{
			System.out.println("Error in your contact information by line "+countLine+", in your file.\n " + Rex.getMessage());
			System.exit(0);
		}
		try{
		new GraficMain(phoneBook);
		}
		catch(Exception Rex)
		{
			System.out.println("Error in your contact information:" + Rex.getMessage());
			
		}
		
	}

}
