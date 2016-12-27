package SearchTree;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class FouraryTree<T extends Comparable<T>> implements CompareTree {

	private Node<T> root;
	private Comparator<T> comparator;
	private int totalNode;

	public FouraryTree() {
		totalNode = 0;
		root = null;
		comparator = null;
	}

	public FouraryTree(Comparator<T> comp) {
		root = null;
		comparator = comp;
		totalNode = 0;
	}

	private int compare(T x, T y) {
		if (comparator == null)
			return x.compareTo(y);
		else
			return comparator.compare(x, y);
	}

	/// ***********************************************/////
	/// ******************* INSERT *********************/////
	/// ***********************************************/////
	public void insert(T data) {
		totalNode++;
		root = insert(root, data);
	}

	private Node<T> insert(Node<T> root, T toInsert) {
		int ans;
		if (root == null) {
			return new Node<T>(toInsert);
		}

		ans = compare(toInsert, root.getKey());
		switch (ans) {
		case SAME:
			totalNode--;
			throw new RuntimeException("this Node alreay in the Tree.");
		case EQUAL:
			root.insetTolist(toInsert);
			break;
		case LL:
			root.setLeftLeft(insert(root.getLeftLeft(), toInsert));
			break;
		case LR: case LRE:
			root.setLeftRight(insert(root.getLeftRight(), toInsert));
			break;
		case RR:
			root.setRightRight(insert(root.getRightRight(), toInsert));
			break;
		case RL: case RLE:
			root.setRightLeft(insert(root.getRightLeft(), toInsert));
			break;
		}

		return root;
	}

	/// ***********************************************/////
	/// ******************* SEARCH *********************/////
	/// ***********************************************/////

	public ArrayList<T> searchForEqual(T toSearch) {
		ArrayList<T> res = new ArrayList<T>();
		searchForEqual(root, toSearch, res);
		return res;
	}

	private void searchForEqual(Node<T> root, T toSearch, ArrayList<T> list) {
		int ans;
		if (root == null) {
			throw new RuntimeException("There is no one that matches your request.");
		} else
			ans = compare(toSearch, root.getKey());
		switch (ans) {
		case EQUAL:
			for (T values : root.getContacts())
				list.add(values);
			return;
		case LL:
			searchForEqual(root.getLeftLeft(), toSearch, list);
			break;
		case LR: case LRE:
			searchForEqual(root.getLeftRight(), toSearch, list);
			break;
		case RR:
			searchForEqual(root.getRightRight(), toSearch, list);
			break;
		case RL: case RLE:
			searchForEqual(root.getRightLeft(), toSearch, list);
			break;
		}
	}

	///////////////////////////////////////////////////
	//////////////////////////////////////////////////

	public ArrayList<T> searchForFirst(T toSearch) {
		ArrayList<T> res = new ArrayList<T>();
		searchForFirst(root, toSearch, res);
		if (res.isEmpty())
			return null;
		return res;
	}

	private void searchForFirst(Node<T> root, T toSearch, ArrayList<T> list) {
		int ans;
		if (root == null) {
			throw new RuntimeException("There is no one that matches your request.");
		} else
			ans = compare(toSearch, root.getKey());
		switch (ans) {
		case EQUAL:
			Node<T> temp = root;
			while (temp != null) {
				if(temp.getKey().compareTo(root.getKey())==RLE||temp.getKey().compareTo(root.getKey())==EQUAL||temp.getKey().compareTo(root.getKey())==SAME)
				for (T values : temp.getContacts())
					list.add(values);
				temp = temp.getRightLeft();
			}
			return;
		case LL:
			if(root.getLeftLeft()!=null)
			searchForFirst(root.getLeftLeft(), toSearch, list);
			if(list.isEmpty()&&root.getRightLeft()!=null)
			searchForFirst(root.getRightLeft(), toSearch, list);
			break;
		case LR: case LRE:
			if(root.getLeftRight()!=null)
			searchForFirst(root.getLeftRight(), toSearch, list);
			if(list.isEmpty()&&root.getRightRight()!=null)
				searchForFirst(root.getRightRight(), toSearch, list);
			break;
		case RR:
			searchForFirst(root.getRightRight(), toSearch, list);
			break;
		case RL: case RLE:
			searchForFirst(root.getRightLeft(), toSearch, list);
			break;
		}
	}

	///////////////////////////////////////////////////
	//////////////////////////////////////////////////

	public ArrayList<T> searchForSecond(T toSearch) {
		ArrayList<T> res = new ArrayList<T>();
		searchForSecond(root, toSearch, res);
		if (res.isEmpty())
			return null;
		return res;
	}

	private void searchForSecond(Node<T> root, T toSearch, ArrayList<T> list) {
		int ans;
		if (root == null) {
			throw new RuntimeException("There is no one that matches your request");
		} else
			ans = compare(toSearch, root.getKey());
		switch (ans) {
		case EQUAL:
			Node<T> temp = root;
			while (temp != null) {
				if(temp.getKey().compareTo(temp.getKey())==LRE||temp.getKey().compareTo(temp.getKey())==EQUAL||temp.getKey().compareTo(temp.getKey())==SAME)
				for (T values : temp.getContacts())
					list.add(values);
				temp = temp.getLeftRight();
			}
			return;
		case LL:
			searchForSecond(root.getLeftLeft(), toSearch, list);
			break;
		case LR: case LRE:
			if(root.getLeftRight()!=null)
			searchForSecond(root.getLeftRight(), toSearch, list);
			if(list.isEmpty()&&root.getLeftLeft()!=null)
				searchForSecond(root.getLeftLeft(), toSearch, list);
			break;
		case RR:
			if(root.getRightRight()!=null)
			searchForSecond(root.getRightRight(), toSearch, list);
			if(list.isEmpty()&&root.getRightLeft()!=null)
				searchForSecond(root.getRightLeft(), toSearch, list);
			break;
		case RL: case RLE:
			searchForSecond(root.getRightLeft(), toSearch, list);
			break;
		}
	}

	/// ***********************************************/////
	/// ******************* DELETE *********************/////
	/// ***********************************************/////

	public void delete(T toDelete) {
		root = delete(root, toDelete);
	}

	private Node<T> delete(Node<T> root, T toDelete) {
		int ans;
		if (root == null)
			throw new RuntimeException("cannot delete.");
		else
			ans = compare(toDelete, root.getKey());
		switch (ans) {
		case EQUAL: case SAME:
			root.deleteFromlist(toDelete);
			totalNode--;
			break;
		case LL:
			root.setLeftLeft(delete(root.getLeftLeft(), toDelete));
			break;
		case LR: case LRE:
			root.setLeftRight(delete(root.getLeftRight(), toDelete));
			break;
		case RR:
			root.setRightRight(delete(root.getRightRight(), toDelete));
			break;
		case RL: case RLE:
			root.setRightLeft(delete(root.getRightLeft(), toDelete));
			break;
		}
		return root;
	}

	/// ***********************************************/////
	/// ******************* UPDATE *********************/////
	/// ***********************************************/////

	public void update(T toUpdate, T newData) {
		Node<T> temp = root;
		boolean flag = false;
		int ans;
		while (temp != null && !flag) {
			ans = compare(toUpdate, temp.getKey());
			switch (ans) {
			case SAME:
				toUpdate=newData;
				return;
			case EQUAL:
				temp.changeInList(toUpdate, newData);
				break;
			case LL:
				temp = temp.getLeftLeft();
				break;
			case LR: case LRE:
				temp = temp.getLeftRight();
				break;
			case RR:
				temp = temp.getRightRight();
				break;
			case RL: case RLE:
				temp = temp.getRightLeft();
				break;
			}
		}
	}

	/// ***********************************************/////
	/// ******************* ToList *********************/////
	/// ***********************************************/////

	
	public ArrayList<T> Tolist() {
		ArrayList<T> res=new ArrayList<T>();
		Tolist(root,res);
		if(res.size()!=totalNode)
			throw new RuntimeException("you have a problem with you contact");
		if(res.isEmpty())
			throw new RuntimeException("your contact is empty");
		return res;
	}

	private void Tolist(Node<T> root,ArrayList<T> list) {
		if (root == null)
			return;
		Tolist(root.getLeftLeft(),list);
		if(!root.getContacts().isEmpty())
			for (T values : root.getContacts())
				list.add(values);
		Tolist(root.getLeftRight(),list);
		Tolist(root.getRightLeft(),list);
		Tolist(root.getRightRight(),list);
		
	}
	/// ***********************************************/////
	/// ******************* Print *********************/////
	/// ***********************************************/////

	public void printTree() {
		Node<T> temp = root;
		printTree(temp);
	}

	private void printTree(Node<T> root) {
		if (root == null)
			return;
		printTree(root.getLeftLeft());
		if(!root.getContacts().isEmpty())
		printList(root);
		printTree(root.getRightLeft());
		printTree(root.getRightRight());
	}

	private void printList(Node<T> root) {
		Node<T> temp = root;
		while (temp != null) {
			for (T values : root.getContacts())
				System.out.println(values);
			temp = temp.getLeftRight();
		}
	}

	/// ***********************************************/////
	/// ******************* Print To File *********************/////
	/// ***********************************************/////

	public void printTree(PrintWriter pw) {
		Node<T> temp = root;
		printTree(temp, pw);
	}

	private void printTree(Node<T> root, PrintWriter pw) {
		if (root == null)
			return;
		printTree(root.getLeftLeft(), pw);
		if(root.getContacts().size()>0)
		if(!root.getContacts().isEmpty())
		printList(root, pw);
		printTree(root.getLeftRight(), pw);
		printTree(root.getRightLeft(), pw);
		printTree(root.getRightRight(), pw);
	}

	private void printList(Node<T> root, PrintWriter pw) {
		Node<T> temp = root;
			for (T values : temp.getContacts())
				pw.println(values);
	}

	public int getTotalNode() {
		return totalNode;
	}
	
	
	/////////////////////
	
	/////////////////
	public ArrayList<T> searchByComparatorChar(Comparator<T> cmp,T toSearch) {
		ArrayList<T> res=new ArrayList<T>();
		this.comparator=cmp;
		searchByComparatorChar(root,res,toSearch);
		if(res.isEmpty())
			throw new RuntimeException("There is no one that matches your request.");
		this.comparator=null;
		return res;
	}

	private void searchByComparatorChar(Node<T> root,ArrayList<T> list,T toSearch) {
		if (root == null)
			return;
		searchByComparatorChar(root.getLeftLeft(),list,toSearch);
		if(!root.getContacts().isEmpty()&&(compare(root.getKey(), toSearch)==EQUAL||compare(root.getKey(), toSearch)==LRE||compare(root.getKey(), toSearch)==RLE))
			for (T values : root.getContacts())
				list.add(values);
		searchByComparatorChar(root.getLeftRight(),list,toSearch);
		searchByComparatorChar(root.getRightLeft(),list,toSearch);
		searchByComparatorChar(root.getRightRight(),list,toSearch);
	}
		

}
