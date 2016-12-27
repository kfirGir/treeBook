package SearchTree;

import java.util.ArrayList;
import java.util.Comparator;

public class Node<T extends Comparable<T>> implements CompareTree {

	private T key;
	private Node<T> LeftLeft;
	private Node<T> LeftRight;
	private Node<T> RightLeft;
	private Node<T> RightRight;
	private ArrayList<T> contacts;
	
	private Comparator<T> comparator;

	public Node(T key) {
		this.key = key;
		this.LeftLeft = this.LeftRight = this.RightLeft = this.RightRight = null;
		this.contacts = new ArrayList<T>();
		this.contacts.add(key);

	}
	public Node(T key,Comparator<T> comp)
	{
		this(key);
		comparator=comp;	
	}

	  private int compare(T x, T y)
	   {
	      if(comparator == null) return x.compareTo(y);
	      else
	      return comparator.compare(x,y);
	   }
	   
	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public Node<T> getLeftLeft() {
		return LeftLeft;
	}

	public void setLeftLeft(Node<T> leftLeft) {
		LeftLeft = leftLeft;
	}

	public Node<T> getLeftRight() {
		return LeftRight;
	}

	public void setLeftRight(Node<T> leftRight) {
		LeftRight = leftRight;
	}

	public Node<T> getRightLeft() {
		return RightLeft;
	}

	public void setRightLeft(Node<T> rightLeft) {
		RightLeft = rightLeft;
	}

	public Node<T> getRightRight() {
		return RightRight;
	}

	public void setRightRight(Node<T> rightRight) {
		RightRight = rightRight;
	}

	public ArrayList<T> getContacts() {
		return contacts;
	}

	public void insetTolist(T key) {
		for(T data:this.contacts)
			if(compare(key, data)==SAME)
				throw new RuntimeException("this Node alreay in the Tree.");
		this.contacts.add(key);
	}

	public void deleteFromlist(T toDelete) {
		for(T data:this.contacts)
			if(compare(key, data)==SAME)
			{
				this.contacts.remove(toDelete);
				return;
			}
		throw new RuntimeException("this node is not at the tree.");
	}
	public void changeInList(T toUpdate,T newData) {
		for(T data:this.contacts)
			if(compare(key, data)==SAME)
				data=newData;
		throw new RuntimeException("this node is not at the tree.");
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (T data : this.contacts)
			sb.append(data.toString());

		return sb.toString();
	}

}
