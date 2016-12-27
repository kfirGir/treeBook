package ContactBook;

import java.util.Comparator;

import Contact.Contact;
import SearchTree.CompareTree;

public class MyComp1 implements Comparator<Contact> {

	@Override
	public int compare(Contact x, Contact cy) {
		 {
			   int ans1=-11,ans2=-11;
			   char cf,cl;
			   char  y=cy.getFname().getFname().charAt(0);
		       cf=x.getFname().getFname().charAt(0);
		       cl=x.getLname().getlname().charAt(0);
		       if(cf>y)ans2=1;
		       else if(cf==y)ans2=0;
		       else ans2=-1;
		       if(cl>y)ans1=1;
		       else if(cl==y)ans1=0;
		       else ans1=-1;
		       
		       if(ans1==0||ans2==0)
		    	   return CompareTree.EQUAL;
		       if(ans1>0)
				{
					if(ans2>0)
						return CompareTree.RR;
					if(ans2==0)
						return CompareTree.RLE;
					else
						return CompareTree.RL;
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
}
