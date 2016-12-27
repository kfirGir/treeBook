package SearchTree;

public interface CompareTree {
	public static final int LL = -2;//first key and second key are lower
	public static final int LR = -1; //second key are larger and first key lower equal
	public static final int LRE =-3; //second key are larger and first key equal
	public static final int RL =1; //first key are larger and second key equal
	public static final int RR =2; //first key and second key are larger
	public static final int RLE =3; //first key are larger and second key equal
	public static final int EQUAL = 0;//Equal list
	public static final int SAME =100 ; // the same Node
}
