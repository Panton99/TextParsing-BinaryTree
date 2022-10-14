//Jisoo Kim 08/04/2022
//---------------------------------------------------------------------------
//
// Text Parser - with Linked Tree
// This program reads the text and inserts the text into a Linked Tree List
//
// Author: Jisoo Kim
// Date: 08/04/2022
// Class: MET CS342
// Issues: None known
//
// Description:
// Bram Stokers Dracula as text file is used for this project.
// It is from http://www.gutenberg.org/ebooks/345.txt.utf-8.
// This program counts the specific words, total words, root, deepest word, the most frequent word,
// pre-order, in-order, and post-order of the tree

//
//
// Assumptions:
// The program will count and show the words in the linked tree list as follows.
//____________________________________________________________________________

//
// Class: DriverTree
//
// Description:
// This is the main class that runs the program. 
// It runs the methods in the LinkedTree.class.
//
public class DriverTree {

	public static void main(String[] args) {
		DriverTree me = new DriverTree();
		me.textParseTree();
	}
	
	/* textParseTree() (shows the output)
	 * input : none
	 * output : methods from the LinkedTree class
	 * return : none
	 * */
	private void textParseTree() {
		LinkedTree l = new LinkedTree();
		//text in added into the linked tree
		l.fillTxt();
		//show the occurrence of each word in the linked tree
		System.out.println("'transylvania' is occurred : " + l.searchWord("transylvania") + " counts");
		System.out.println("'harker' is occurred       : " + l.searchWord("harker") + " counts");
		System.out.println("'renfield' is occurred 	   : " + l.searchWord("renfield") + " counts");
		System.out.println("'vampire' is occurred      : " + l.searchWord("vampire") + " counts");
		System.out.println("'expostulate' is occurred  : " + l.searchWord("expostulate") + " counts");
		//show the depth of the tree
		System.out.println("\nThe depth of the tree     	     : " + l.depth() + " nodes");
		//distinct word count is the size of the tree
		System.out.println("Count of different words in the book : " + l.size() + " distinct word counts");
		//show the root of the tree
		System.out.println("The root of the tree                  : " + l.showRoot());
		//show the deepest leaf in the tree
		System.out.println("\nThe deepest leaves of the tree       : " + l.deepestLeaf());
		//show the total count of each nodes
		System.out.println("Total word count of the book         : " + l.totalWordCnt());
		//show the most biggest count node and the word
		System.out.println("The most frequent word               : " + "'" + l.freqWord() + "' occurred " + l.freqWordCnt() + " times");
		//show the first word of pre-order
		System.out.println("First word in pre-order              : " + l.preOrder());
		//show the first word of post-order
		System.out.println("First word in post-order             : " + l.postOrder());
		//show the first word of in-order
		System.out.println("First word in in-order               : " + l.inOrder());
	}
}
