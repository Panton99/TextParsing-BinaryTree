import edu.bu.met.cs342.TextParser;

//Jisoo Kim 08/04/2022
//
//Class: LinkedTree
//
//Description:
//LinkedTree class have methods about linked Tree list and text parsing.
//This class has methods like get the text from the text file, check the alphanumeric characters and change.
//Also, find the count of specific word, depth of the tree, node counts, the root of the tree, total word counts.
//Lastly, shows the first word of pre-, in-, and post- order traversal.
//
public class LinkedTree {
	TextParser t = new TextParser();
	private String word;
	private String []arr;
	private int count = 0;
	private Node root;
	private int dupCnt = 0;
	private String tmp = "";

	
	/* fillTxt() (to fill the text into the linked queue list)
	 * input : text file
	 * output : String array in to the linked queue list
	 * return : none
	 * */
	public void fillTxt() {
		t.openFile("pg345.txt");
			
		while ((word = t.getNextWord()) != null) {		
				arr = isAlphaNumeric(word).split(" "); //remove non-alphanumeric words and make it into String array
				addMany(arr); //add the String []arr into the linked list
		}
	}
	
	/* isAlphaNumeric() (find the non-alphanumeric char)
	 * input : word
	 * output : string word (lower case & non-alphanumeric char is removed)
	 * return : string word
	 * */
	public String isAlphaNumeric(String word) {
		String tmp = "";
		
		for(int i = 0; i < word.length(); i++) {
			 char x = word.charAt(i);
			 int ascii = (int)x;
			 
			 //ascii that are a-z, A-Z, 0-9, space (otherwise it is not put into the string)
			 if((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122) || (ascii >= 48 && ascii <= 57) || (ascii == 32) ) {
				 tmp += word.charAt(i); 
			 }
		 }
		return tmp.toLowerCase();
	}
	
	/* addMany() (is to add String []arr)
	 * input : Strings []arr
	 * output : String array in the queue
	 * */
	public void addMany(String... other) {
		for (String d : other) {
			add(d); //add the string []arr	
		}
	}
	
	/* add() (add the Node in the tree, check if it is null or duplicate)
	 * input : word
	 * output : none
	 * return : recAdd()
	 * */
	public boolean add(String word) {
		
		Node n = new Node();
		//add data to the root
		if (root == null) {
			n.setWord(word);
			n.setCount(dupCnt);
			count = 1;
			root = n;
			return true;
		}
		
		if (recSearch(root, word) != null) { //if the search of word is true
			return true;
		}
		//add the other word that is next in the arr[]
		return recAdd(root, word);
	}	
	
	/* recAdd() (add the value in right or left part of the tree)
	 * input : root, word
	 * output : none
	 * return : true if the word is set in the right or left child
	 * */
	private boolean recAdd(Node root, String word) {
		if(root == null) {
			return false;
		}
		
		if (word.compareTo(root.getWord()) < 0) {
			if (root.getlChild() == null) {
				Node n = new Node();
				n.setWord(word);
				root.setlChild(n);
				count++;
				return true;
			} else { //recursion
				return recAdd(root.getlChild(), word);
			}
		} else {
			if(root.getrChild() == null) { //if there is a right child
				Node n = new Node();
				n.setWord(word);
				root.setrChild(n);
				count++;
				return true;
			} else { //recursion
				return recAdd(root.getrChild(), word);
			}
		}
		
	}
	
	/* searchWord() (get the count of the searched word)
	 * input : root, word
	 * output : none
	 * return : the search word count
	 * */
	public int searchWord(String word) {
		return recSearch(root, word).getCount();
	}
	
	/* recSearch() (to check if the word is in the tree)
	 * input : root, word
	 * output : none
	 * return : node, set the counter incremented when there is a match 
	 * */
	private Node recSearch(Node root, String word) {
		
		if (root == null) {
			return null;
		}
		if (root.getWord().compareTo(word) == 0) {
			//increment the counter of each node when there is a same word
			dupCnt = root.getCount();
			dupCnt++;
			root.setCount(dupCnt);
			return root;
		}
		if (word.compareTo(root.getWord()) > 0) { //if there is no match, go to left or right child
			return recSearch(root.getrChild(), word);
		} else {
			return recSearch(root.getlChild(), word);
		}
	}
	
	/* size() (shows the size of the tree)
	 * input : none
	 * output : none
	 * return : count
	 * */
	public int size() {
		return count;
	}
	
	/* showRoot() (shows the root word)
	 * input : none
	 * output : none
	 * return : string word of the root
	 * */
	public String showRoot() {
		return root.getWord();
	}
	
	/* recDepth() (go to deepest node and get the level)
	 * input : root, level 
	 * output : none
	 * return : deepest level
	 * */
	private int recDepth(Node root, int level) {
		//Base case 
		if (root == null) {
			return level - 1;
		}
		int l = recDepth(root.getlChild(), level + 1);
		int r = recDepth(root.getrChild(), level + 1);
		int result = (l > r) ? l : r;
		return result;
	}
	
	/* depth() (return the recDepth())
	 * input : none
	 * output : none
	 * return : level of the deepest node
	 * */
	public int depth() {
		if(count == 0) { //nothing is in the tree
			return 0;
		}
		return recDepth(root, 0) + 1;
	}
	
	/* recDeepestLeaf() (return the max level of the tree)
	 * input : root, level
	 * output : none
	 * return : level of the deepest node
	 * */
	private int recDeepestLeaf(Node root, int level) {
		int max = 0;
		//Base case 
		if (root == null) {
			return level - 1;
		}
		
		//level of the root is at the deepest level
		if (level == (depth() - 1)) {
			tmp += root.getWord() + " ";
		}
		//recursively find right and left child
		recDeepestLeaf(root.getlChild(), level + 1);
		recDeepestLeaf(root.getrChild(), level + 1);
	
		//get the max level 
		return max;
	}
	
	/* deepestLeaf() (return the deepest node word)
	 * input : none
	 * output : none
	 * return : the word of the deepest node in the tree
	 * */
	public String deepestLeaf() {
		if(count == 0) { //nothing is in the tree
			return null;
		}
		//Recursion to find the deepest level
		recDeepestLeaf(root, 0);
		//return the deepest word
		return  tmp;
	}
	
	/* recTotalWordCnt() (recursively returns total count of words)
	 * input : count, word
	 * output : none
	 * return : sum of counts
	 * */
	private int recTotalWordCnt(Node root, String word) {
		int sum = 1;
		//Base case
		if (root == null) {
			return 0;
		}
		
		//Recursion of the right and left child
		sum += root.getCount();
		sum += recTotalWordCnt(root.getlChild(), word);
		sum += recTotalWordCnt(root.getrChild(), word);
	
		 return sum;
	}
	
	/* totalWordCnt() (returns total count of words)
	 * input : none
	 * output : none
	 * return : sum of counts
	 * */
	public int totalWordCnt() {
		return recTotalWordCnt(root, word);
	}
	
	/* recFreqWord() (recursively returns most frequent of words)
	 * input : count, word
	 * output : none
	 * return : most frequent node
	 * */
	private Node recFreqWord(Node root, String word) {
		int max = 0;
		int cnt = 0;
		Node tmp = null;
		//Base case
		if (root == null) {
			return null;
		}
		
		//find the maximum counter and the node in the tree
		if (root.getCount() > max) {
			max = root.getCount();
			tmp = root;
		} 
		//recursion of the right and left child
		recFreqWord(root.getlChild(), word);
		recFreqWord(root.getrChild(), word);
		
		return tmp;
	}
	
	/* freqWord() (returns most frequent of word)
	 * input : none
	 * output : none
	 * return : most frequent word
	 * */
	public String freqWord() { 
		//get the most frequent word
		return recFreqWord(root, word).getWord();
	}
	
	/* freqWordCnt() (returns most frequent of word count)
	 * input : none
	 * output : none
	 * return : most frequent word count
	 * */
	public int freqWordCnt() { 
		//get the most frequent word count
		return recFreqWord(root, word).getCount() + 1;
	}
	
	/* recInOrder() (returns the in-order traversal)
	 * input : root, word
	 * output : none
	 * return : string result
	 * */
	private String recInOrder(Node root, String word) {
		String result = "";
		//Base case
		if (root == null) {
			return "";
		}
		//result is the in-order traversal
		result += recInOrder(root.getlChild(), word);
		result += root.getWord() + " ";
		result += recInOrder(root.getrChild(), word);
	
		return result;
	}
	
	/* inOrder() (returns the first word of the in-order traversal string)
	 * input : none
	 * output : none
	 * return : first word of the in-order traversal string
	 * */
	public String inOrder() {
		//if nothing is in the tree
		if (count == 0) {
			return "<Empty>";
		}
		//To collect the first word from the in-order traversal
		String inOrder[] = recInOrder(root, word).split(" ");
		String firstWord = inOrder[0]; 
				
		return firstWord;
	}
	
	/* recPreOrder() (returns the pre-order traversal)
	 * input : root, word
	 * output : none
	 * return : string result
	 * */
	private String recPreOrder(Node root, String word) {
		String result = "";
		//Base case
		if (root == null) {
			return "";
		}
		//result is the pre-order traversal
		result += root.getWord() + " "; 
		result += recPreOrder(root.getlChild(), word);
		result += recPreOrder(root.getrChild(),word);
		 
		
		return result;
	}
	
	/* preOrder() (returns the first word of the pre-order traversal string)
	 * input : none
	 * output : none
	 * return : first word of the pre-order traversal string
	 * */
	public String preOrder() {
		//if nothing is in the tree
		if (count == 0) {
			return "<Empty>";
		}
		//To collect the first word from the pre-order traversal
		String preOrder[] = recPreOrder(root, word).split(" ");
		String firstWord = preOrder[0];
		return firstWord;
	}
	
	/* recPostOrder() (returns the post-order traversal)
	 * input : root, word
	 * output : none
	 * return : string result
	 * */
	private String recPostOrder(Node root, String word) {
		String result = "";
		//Base case
		if (root == null) {
			return "";
		}
		//result is the post-order traversal string 
		result += recPostOrder(root.getlChild(), word);
		result += recPostOrder(root.getrChild(), word);
		result += root.getWord() + " ";
		
		return result;
	}
	
	/* postOrder() (returns the first word of the post-order traversal string)
	 * input : none
	 * output : none
	 * return : first word of the post-order traversal string
	 * */
	public String postOrder() {
		//if nothing is in the tree
		if (count == 0) {
			return "<Empty>";
		}
		//To collect the first word from the post-order traversal
		String post[] = recPostOrder(root, word).split(" ");
		String firstWord = post[0]; 
		return firstWord;
	}	
}
