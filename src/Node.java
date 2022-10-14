//Jisoo Kim 08/04/2022

//Class: Node
//
//Description:
//Node class have methods used in LinkedTree.class.
//This class contains the rChild, lChild node, word, and count, which also has accessors and mutators.
//
public class Node {
	private Node rChild;
	private Node lChild;
	private String word;
	private int count;
	
	public Node() { //default constructor
		count = 0;
		word = "";
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Node getrChild() {
		return rChild;
	}
	public void setrChild(Node rChild) {
		this.rChild = rChild;
	}
	public Node getlChild() {
		return lChild;
	}
	public void setlChild(Node lChild) {
		this.lChild = lChild;
	}
	

}
