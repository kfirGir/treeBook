package ContactBook;

import SearchTree.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Contact.*;

public class GraficMain  {
	private FouraryTree<Contact> phoneBook;
	ArrayList<Contact> resultShowList=null;
	private JFrame f, f2, f3;
	private JButton secSearchButton;
	private JButton fersearchButton;
	private JButton closeButton;
	private JButton addButton;
	private JButton searchButton;
	private JButton showAllButton;
	private JButton showByletterButton;
	private JTextField showByletterTxt;
	private JTextField fersearchTxt;
	private JTextField secsearchTxt;
	private JLabel searchLable;
	private JFrame curFrame; // hold the current Frame
	private Contact tempSt;
	private int current=0; 
	public GraficMain(FouraryTree<Contact> pB) {

		phoneBook = pB;
		
		f = new JFrame("Contact      Book");
		f.setSize(800, 600);
		f.getContentPane().setBackground(Color.YELLOW);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setLayout(null);

		searchLable = new JLabel("Fill the data and choose how to search: \n");
		searchLable.setLocation(300, 10);
		searchLable.setSize(searchLable.getPreferredSize());
		f.add(searchLable);
		/////

		searchLable = new JLabel("Enter first name: \n");
		searchLable.setLocation(10, 70);
		searchLable.setSize(searchLable.getPreferredSize());
		f.add(searchLable);
		////

		fersearchTxt = new JTextField(15);
		fersearchTxt.setColumns(15);
		fersearchTxt.setSize(fersearchTxt.getPreferredSize());
		fersearchTxt.setLocation(150, 70);
		fersearchTxt.setToolTipText("Enter first name");
		f.add(fersearchTxt);

		searchLable = new JLabel("Enter last name: \n");
		searchLable.setLocation(10, 140);
		searchLable.setSize(searchLable.getPreferredSize());
		f.add(searchLable);
		////

		secsearchTxt = new JTextField(20);
		secsearchTxt.setColumns(15);
		secsearchTxt.setSize(fersearchTxt.getPreferredSize());
		secsearchTxt.setLocation(150, 140);
		secsearchTxt.setToolTipText("Enter last name");
		f.add(secsearchTxt);

		//
		searchLable = new JLabel("to search by letter: \n");
		searchLable.setLocation(10, 210);
		searchLable.setSize(searchLable.getPreferredSize());
		f.add(searchLable);
		//
		showByletterTxt = new JTextField(1);
		showByletterTxt.setColumns(1);
		showByletterTxt.setSize(fersearchTxt.getPreferredSize());
		showByletterTxt.setLocation(150, 210);
		showByletterTxt.setToolTipText("Enter  a lettet");
		f.add(showByletterTxt);
		//
		///
		///
		fersearchButton = new JButton("Search By first name -->");
		fersearchButton.setSize(fersearchButton.getPreferredSize());
		fersearchButton.setLocation(400, 70);
		fersearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((fersearchTxt.getText().isEmpty()))
					JOptionPane.showMessageDialog(f, " please insert first name to search \n", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				else
					try {
						searchGrafic(new Contact(fersearchTxt.getText(), null, Contact.BYFIRST),Contact.BYFIRST);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		f.add(fersearchButton);

		////
		secSearchButton = new JButton("Search By last name -->");
		secSearchButton.setSize(secSearchButton.getPreferredSize());
		secSearchButton.setLocation(400, 140);
		secSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (secsearchTxt.getText().isEmpty())
					JOptionPane.showMessageDialog(f, " please insert last name to search \n", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				else
					try {
						searchGrafic(new Contact(null, secsearchTxt.getText(), Contact.BYSECOND),Contact.BYSECOND);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
					}

			}
		});
		f.add(secSearchButton);

		searchButton = new JButton("Search by Name");
		searchButton.setSize(searchButton.getPreferredSize());
		searchButton.setLocation(400, 500);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fersearchTxt.getText().isEmpty() && secsearchTxt.getText().isEmpty())
					JOptionPane.showMessageDialog(f, " please insert values to search \n", "Inane error",
							JOptionPane.ERROR_MESSAGE);
				else
					try {
						searchGrafic(new Contact(fersearchTxt.getText(), secsearchTxt.getText(), Contact.BOTH),Contact.BOTH);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
					}
			}
			
		});
		f.add(searchButton);
		
		
		closeButton = new JButton("Exit");
		closeButton.setSize(closeButton.getPreferredSize());
		closeButton.setLocation(700, 500);
		closeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int answer;
				answer = JOptionPane.showConfirmDialog(f, "Are you Sure, you want to exit?", "An Inane Question",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
					try {
						FileWriter fw = new FileWriter("ContactList.txt");
						PrintWriter pw = new PrintWriter(fw);
						phoneBook.printTree(pw);
						pw.close();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
					}
					System.exit(0);

				}
			}
		});

		f.add(closeButton);

		addButton = new JButton("New contact");
		addButton.setSize(addButton.getPreferredSize());
		addButton.setLocation(200, 500);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					addGrafic();
				}
				catch(Exception ex){
				JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		f.add(addButton);
		showAllButton = new JButton("Browes Contact");
		showAllButton.setSize(showAllButton.getPreferredSize());
		showAllButton.setLocation(20, 500);
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					current=0;
					resultShowList=phoneBook.Tolist();
			       showAllGrafic();
				}
				catch(Exception ex){
				JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		f.add(showAllButton);
		
		showByletterButton = new JButton("search By letter");
		showByletterButton.setSize(showByletterButton.getPreferredSize());
		showByletterButton.setLocation(400, 210);
		showByletterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showByletterTxt.getText().length()>1||showByletterTxt.getText().isEmpty())
		       throw new RuntimeException("you have a problem with the char to search.");
				try{
					current=0;
					resultShowList=phoneBook.searchByComparatorChar(new MyComp1(),(new Contact(showByletterTxt.getText(),null, Contact.BYFIRST)));
			       showAllGrafic();
				}
				catch(Exception ex){
				JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		f.add(showByletterButton);
		f.setVisible(true);
		curFrame = f;

	}

	private void addGrafic() {
		JTextField fnameTxt;
		JTextField lnameTxt;
		JTextField hPhoneTxt;
		JTextField mPhoneTxt;
		JTextField emailTxt;
		JTextField cityTxt;
		JTextField streetTxt;
		JTextField numberTxt;
		JButton addNewButton;
		JButton backButton;

		f2 = new JFrame("Add new contact");
		f2.setSize(800, 600);
		f2.getContentPane().setBackground(Color.yellow);
		f2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f2.setLayout(null);
		searchLable = new JLabel("Fill all the data of the new contact: \n");
		searchLable.setLocation(300, 10);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);

		//
		searchLable = new JLabel("Enter first name: \n");
		searchLable.setLocation(10, 70);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		fnameTxt = new JTextField(15);
		fnameTxt.setColumns(15);
		fnameTxt.setSize(fnameTxt.getPreferredSize());
		fnameTxt.setLocation(150, 70);
		fnameTxt.setToolTipText("Enter first name");
		f2.add(fnameTxt);

		///

		searchLable = new JLabel("Enter last name: \n");
		searchLable.setLocation(10, 100);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		lnameTxt = new JTextField(20);
		lnameTxt.setColumns(20);
		lnameTxt.setSize(lnameTxt.getPreferredSize());
		lnameTxt.setLocation(150, 100);
		lnameTxt.setToolTipText("Enter last name");
		f2.add(lnameTxt);
		//
		searchLable = new JLabel("Enter home phone number: \n");
		searchLable.setLocation(10, 140);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		hPhoneTxt = new JTextField(10);
		hPhoneTxt.setColumns(10);
		hPhoneTxt.setSize(hPhoneTxt.getPreferredSize());
		hPhoneTxt.setLocation(180, 140);
		hPhoneTxt.setToolTipText("Enter home phone number");
		f2.add(hPhoneTxt);
		//
		searchLable = new JLabel("Enter moblie phone number: \n");
		searchLable.setLocation(10, 180);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		mPhoneTxt = new JTextField(10);
		mPhoneTxt.setColumns(10);
		mPhoneTxt.setSize(mPhoneTxt.getPreferredSize());
		mPhoneTxt.setLocation(180, 180);
		mPhoneTxt.setToolTipText("Enter moblie phone number");
		f2.add(mPhoneTxt);
		//
		searchLable = new JLabel("Enter your address (city,street,number): \n");
		searchLable.setLocation(10, 220);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////
		cityTxt = new JTextField(15);
		cityTxt.setColumns(15);
		cityTxt.setSize(cityTxt.getPreferredSize());
		cityTxt.setLocation(10, 250);
		streetTxt = new JTextField(25);
		streetTxt.setColumns(25);
		streetTxt.setSize(streetTxt.getPreferredSize());
		streetTxt.setLocation(200, 250);
		numberTxt = new JTextField(4);
		numberTxt.setColumns(4);
		numberTxt.setSize(numberTxt.getPreferredSize());
		numberTxt.setLocation(500, 250);
		f2.add(cityTxt);
		f2.add(streetTxt);
		f2.add(numberTxt);
		//
		searchLable = new JLabel("Enter your email: \n");
		searchLable.setLocation(10, 320);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		emailTxt = new JTextField(50);
		emailTxt.setColumns(50);
		emailTxt.setSize(emailTxt.getPreferredSize());
		emailTxt.setLocation(120, 320);
		emailTxt.setToolTipText("Enter your email");
		f2.add(emailTxt);
		//
		addNewButton = new JButton("Add New contact");
		addNewButton.setSize(addNewButton.getPreferredSize());
		addNewButton.setLocation(180, 500);
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				String st = " ";
				if (lnameTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your Last name please.\n";
				}
				if (hPhoneTxt.getText().isEmpty() && mPhoneTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter At least one phone number please.\n";
				}
				if (fnameTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your First name please.\n";
				}
				if (cityTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your city please.\n";
				}
				if (streetTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your street please.\n";
				}
				if (numberTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your home number please.\n";
				}
				if (flag)
					JOptionPane.showMessageDialog(f2, st, "Inane error", JOptionPane.ERROR_MESSAGE);
				else {
					int answer;
					Contact temp=new Contact(fnameTxt.getText(), lnameTxt.getText(), mPhoneTxt.getText(),
							hPhoneTxt.getText(),
							new Address(cityTxt.getText(), streetTxt.getText(), numberTxt.getText()),
							emailTxt.getText());
					answer = JOptionPane.showConfirmDialog(f, "Are you Sure you want to add?\n"+temp, "An Inane Question",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
						try {
							phoneBook.insert(temp);
							JOptionPane.showMessageDialog(f2, "This conact is added");
							JOptionPane.showMessageDialog(f2, "we are back to main");
							f2.dispose();
							f.add(closeButton);
							f.setVisible(true);
						} catch (RuntimeException ex) {
							JOptionPane.showMessageDialog(f2, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
							JOptionPane.showMessageDialog(f2, "Enter again please");
						}
					}
				}
			}

		});

		f2.add(addNewButton);

		backButton = new JButton("Back to main");
		backButton.setSize(backButton.getPreferredSize());
		backButton.setLocation(380, 500);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer;
				answer = JOptionPane.showConfirmDialog(f, "Are you Sure, you want back to main?", "An Inane Question",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
				f2.dispose();
				f.add(closeButton);
				f.setVisible(true);
				curFrame = f;
				}

			}
		});

		f2.add(backButton);

		f2.add(closeButton);

		f.setVisible(false);
		f2.setVisible(true);
		curFrame = f2;

	}

	private void searchGrafic(Contact toSearch,int howToSearch) {
		ArrayList<Contact> resultList;
		JButton backButton;
		JButton updateButtons[];
		JButton deleteButtons[];

		f3 = new JFrame("search contact");
		try {
			f3.setContentPane(new JLabel(new ImageIcon("book3.gif")));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
		}
		f3.setSize(1000, 700);
		f3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f3.setLayout(null);
		
		////
		searchLable = new JLabel(String.format("%15s %15s   %20s      %20s   %30s   %35s", "First Name","Last Name","Phone1","Phone2","Address","Email"));
		searchLable.setLocation(40, 20);
		searchLable.setSize(searchLable.getPreferredSize());
		searchLable.setForeground(Color.WHITE);
		f3.add(searchLable);
		///
		switch(howToSearch)
		{
		case Contact.BYFIRST:
			resultList=phoneBook.searchForFirst(toSearch);
			break;
		case Contact.BYSECOND:
			resultList=phoneBook.searchForSecond(toSearch);
			break; 
		case Contact.BOTH:
			resultList=phoneBook.searchForEqual(toSearch);
			break;
		default:
			throw new RuntimeException("incorrent how to search input");
		}
		if(resultList==null||resultList.isEmpty())
			throw new RuntimeException("There is no one that matches your request");
		int len=resultList.size();
		int addSize=30,place=60;
		int i;
		updateButtons=new JButton[len];
		deleteButtons=new JButton[len];
		for(i=0;i<len;i++,place+=addSize)
		{
			tempSt=resultList.get(i);
			//
			updateButtons[i]= new JButton("Update");
			updateButtons[i].setSize(updateButtons[i].getPreferredSize());
			updateButtons[i].setLocation(800, place);
			updateButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int answer;
					answer = JOptionPane.showConfirmDialog(f, "Are you Sure, you want to update?\n"+tempSt, "An Inane Question",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
						f3.dispose();
						updateGrafic(tempSt,howToSearch);
					}

				}
			});
			f3.add(updateButtons[i]);
			//
			//
			deleteButtons[i]= new JButton("Delete");
			deleteButtons[i].setSize(deleteButtons[i].getPreferredSize());
			deleteButtons[i].setLocation(900, place);
			deleteButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int answer;
					answer = JOptionPane.showConfirmDialog(f, "Are you Sure, you want to delete?\n"+tempSt, "An Inane Question",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
						try{
						phoneBook.delete(tempSt);
						JOptionPane.showMessageDialog(f3, "This contact was deleted");
						f3.dispose();
						if(resultList.size()>1)
							searchGrafic(toSearch, howToSearch);
						else
						{
						JOptionPane.showMessageDialog(f3, "this contact was the only result..\n we are back to main!");
						f.add(closeButton);
						f.setVisible(true);
						curFrame = f;
						}
					}
						catch(Exception ex){
							JOptionPane.showMessageDialog(f, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
						}
					}

				}
			});
			f3.add(deleteButtons[i]);
			//
			searchLable = new JLabel((resultList.get(i)).myToString());
			searchLable.setLocation(40, place);
			searchLable.setSize(searchLable.getPreferredSize());
			searchLable.setForeground(Color.YELLOW);
			f3.add(searchLable);
		}

		///

		//
		backButton = new JButton("Back to main");
		backButton.setSize(backButton.getPreferredSize());
		backButton.setLocation(380, 600);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer;
				answer = JOptionPane.showConfirmDialog(f, "Are you Sure, you want back to main?", "An Inane Question",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
				f3.dispose();
				closeButton.setLocation(700, 500);
				f.add(closeButton);
				f.setVisible(true);
				curFrame = f;
				}

			}
		});

		f3.add(backButton);
		closeButton.setLocation(700, 600);
		f3.add(closeButton);

		f.setVisible(false);
		f3.setVisible(true);
		curFrame = f3;
	}
	private void updateGrafic(Contact toUpdate,int howToSearch){
		JTextField fnameTxt;
		JTextField lnameTxt;
		JTextField hPhoneTxt;
		JTextField mPhoneTxt;
		JTextField emailTxt;
		JTextField cityTxt;
		JTextField streetTxt;
		JTextField numberTxt;
		JButton addNewButton;
		JButton backButton;

		f2 = new JFrame("Update contact");
		f2.setSize(800, 600);
		f2.getContentPane().setBackground(Color.yellow);
		f2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f2.setLayout(null);
		searchLable = new JLabel("Fill all the new information to update: \n");
		searchLable.setLocation(300, 10);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);

		//
		searchLable = new JLabel("First name: \n");
		searchLable.setLocation(10, 70);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		fnameTxt = new JTextField(15);
		fnameTxt.setColumns(15);
		fnameTxt.setSize(fnameTxt.getPreferredSize());
		fnameTxt.setLocation(150, 70);
		fnameTxt.setText(toUpdate.getFname().toString());
		fnameTxt.setEditable(false);
		fnameTxt.setToolTipText("Enter first name");
		f2.add(fnameTxt);

		///

		searchLable = new JLabel("Last name: \n");
		searchLable.setLocation(10, 100);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		lnameTxt = new JTextField(20);
		lnameTxt.setColumns(20);
		lnameTxt.setSize(lnameTxt.getPreferredSize());
		lnameTxt.setLocation(150, 100);
		lnameTxt.setText(toUpdate.getLname().toString());
		lnameTxt.setEditable(false);
		lnameTxt.setToolTipText("Enter last name");
		f2.add(lnameTxt);
		//
		searchLable = new JLabel("Home phone number: \n");
		searchLable.setLocation(10, 140);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		hPhoneTxt = new JTextField(10);
		hPhoneTxt.setColumns(10);
		hPhoneTxt.setSize(hPhoneTxt.getPreferredSize());
		hPhoneTxt.setLocation(180, 140);
		if(toUpdate.getHomeNumber()!=null)
			hPhoneTxt.setText(toUpdate.getHomeNumber().toString());
		hPhoneTxt.setToolTipText("Enter home phone number");
		f2.add(hPhoneTxt);
		//
		searchLable = new JLabel("Moblie phone number: \n");
		searchLable.setLocation(10, 180);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		mPhoneTxt = new JTextField(10);
		mPhoneTxt.setColumns(10);
		mPhoneTxt.setSize(mPhoneTxt.getPreferredSize());
		mPhoneTxt.setLocation(180, 180);
		mPhoneTxt.setToolTipText("Enter moblie phone number");
		if(toUpdate.getMobileNumber()!=null)
			mPhoneTxt.setText(toUpdate.getMobileNumber().toString());
		f2.add(mPhoneTxt);
		//
		searchLable = new JLabel("Your address (city,street,number): \n");
		searchLable.setLocation(10, 220);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////
		cityTxt = new JTextField(15);
		cityTxt.setColumns(15);
		cityTxt.setSize(cityTxt.getPreferredSize());
		cityTxt.setLocation(10, 250);
		streetTxt = new JTextField(25);
		streetTxt.setColumns(25);
		streetTxt.setSize(streetTxt.getPreferredSize());
		streetTxt.setLocation(200, 250);
		numberTxt = new JTextField(4);
		numberTxt.setColumns(4);
		numberTxt.setSize(numberTxt.getPreferredSize());
		numberTxt.setLocation(500, 250);
		cityTxt.setText(toUpdate.getAddress().getCity().toString());
		streetTxt.setText(toUpdate.getAddress().getStreet().toString());
		numberTxt.setText(toUpdate.getAddress().getNum().toString());
		f2.add(cityTxt);
		f2.add(streetTxt);
		f2.add(numberTxt);
		//
		searchLable = new JLabel("Your email: \n");
		searchLable.setLocation(10, 320);
		searchLable.setSize(searchLable.getPreferredSize());
		f2.add(searchLable);
		////

		emailTxt = new JTextField(50);
		emailTxt.setColumns(50);
		emailTxt.setSize(emailTxt.getPreferredSize());
		emailTxt.setLocation(120, 320);
		if(toUpdate.getEmail()!=null)
			emailTxt.setText(toUpdate.getEmail().toString());
		emailTxt.setToolTipText("Enter your email");
		f2.add(emailTxt);
		//
		addNewButton = new JButton("Update");
		addNewButton.setSize(addNewButton.getPreferredSize());
		addNewButton.setLocation(180, 500);
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				String st = " ";
				if (lnameTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your Last name please.\n";
				}
				if (hPhoneTxt.getText().isEmpty() && mPhoneTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter At least one phone number please.\n";
				}
				if (fnameTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your First name please.\n";
				}
				if (cityTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your city please.\n";
				}
				if (streetTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your street please.\n";
				}
				if (numberTxt.getText().isEmpty()) {
					flag = true;
					st += "Enter your home number please.\n";
				}
				if (flag)
					JOptionPane.showMessageDialog(f2, st, "Inane error", JOptionPane.ERROR_MESSAGE);
				else {
					int answer;
					Contact newData=new Contact(fnameTxt.getText(), lnameTxt.getText(), mPhoneTxt.getText(),
							hPhoneTxt.getText(),
							new Address(cityTxt.getText(), streetTxt.getText(), numberTxt.getText()),
							emailTxt.getText());
					answer = JOptionPane.showConfirmDialog(f, "Are you Sure you want to update?\n"+toUpdate, "An Inane Question",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
						try {
							toUpdate.update(newData);
							JOptionPane.showMessageDialog(f2, "This conact is Updated");
							f2.dispose();
							switch(howToSearch)
							{
							case 1:
								searchGrafic(new Contact(toUpdate.getFname().getFname(),null, 1),1);
								break;
							case 2:
								searchGrafic(new Contact(null,toUpdate.getLname().getlname(), 2),2);
								break;
							case 0:
								searchGrafic(toUpdate, 0);
								break;
								default:
									throw new RuntimeException("Error input");
							
							}
						} catch (RuntimeException ex) {
							JOptionPane.showMessageDialog(f2, ex, "Inane error", JOptionPane.ERROR_MESSAGE);
							JOptionPane.showMessageDialog(f2, "Enter again please");
						}
					}
				}
			}

		});

		f2.add(addNewButton);

		backButton = new JButton("Back to main");
		backButton.setSize(backButton.getPreferredSize());
		backButton.setLocation(380, 500);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer;
				answer = JOptionPane.showConfirmDialog(f, "Are you Sure, you want back to main?", "An Inane Question",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
				f2.dispose();
				f.add(closeButton);
				f.setVisible(true);
				curFrame = f;
				}

			}
		});

		f2.add(backButton);

		f2.add(closeButton);
		f2.setVisible(true);
		curFrame = f2;


	}
	public JFrame getCurFrame() {
		return curFrame;
	}
	private void showAllGrafic() {
		JButton backButton;
		JButton nextButton;
		JButton prevButton;
		f3 = new JFrame("browes contact");
		f3.setSize(1000, 250);
		f3.getContentPane().setBackground(Color.white);
		f3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f3.setLayout(null);
		
		////
		searchLable = new JLabel(String.format("%15s %15s   %20s      %20s   %30s   %35s", "First Name","Last Name","Phone1","Phone2","Address","Email"));
		searchLable.setLocation(40, 20);
		searchLable.setSize(searchLable.getPreferredSize());
		f3.add(searchLable);
		//
		searchLable = new JLabel(resultShowList.get(current).myToString());
		searchLable.setLocation(40, 80);
		searchLable.setSize(searchLable.getPreferredSize());
		searchLable.setForeground(Color.red);
		f3.add(searchLable);
		///
		nextButton = new JButton("next");
		nextButton.setSize(nextButton.getPreferredSize());
		nextButton.setLocation(20, 180);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current<resultShowList.size()-1)
				{
					current++;
					f3.dispose();
					showAllGrafic();
				}
				else
				{
					JOptionPane.showMessageDialog(f3, "you finish all contact", "Inane error", JOptionPane.ERROR_MESSAGE);
				    JOptionPane.showMessageDialog(f3, "please press anther Button");
				}
				
			}
		});
		f3.add(nextButton);
		prevButton = new JButton("prev");
		prevButton.setSize(prevButton.getPreferredSize());
		prevButton.setLocation(200, 180);
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current>0)
				{
					current--;
					f3.dispose();
					showAllGrafic();
				}
				else
				{
					JOptionPane.showMessageDialog(f3, "you finish all contact", "Inane error", JOptionPane.ERROR_MESSAGE);
				    JOptionPane.showMessageDialog(f3, "please press anther Button");
				}
				
			}
		});
		f3.add(prevButton);
		//
		backButton = new JButton("Back to main");
		backButton.setSize(backButton.getPreferredSize());
		backButton.setLocation(720, 180);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer;
				answer = JOptionPane.showConfirmDialog(f, "Are you Sure, you want back to main?", "An Inane Question",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) { // The ISSUE is here
			    current=0;
			    resultShowList=null;
				f3.dispose();
				closeButton.setLocation(700, 500);
				f.add(closeButton);
				f.setVisible(true);
				curFrame = f;
				}

			}
		});

		f3.add(backButton);
		closeButton.setLocation(900, 180);
		f3.add(closeButton);

		f.setVisible(false);
		f3.setVisible(true);
		curFrame = f3;
	}
}

