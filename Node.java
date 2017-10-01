/**
 * class that creates a node which stores data and has a pointer for the dictionary 
 */

package assignment2;

public class Node{
	// attribute declarations
	private ConfigData element; 
	private Node next; 
	
	/**
	 * Constructor creates a node with a pointer and an element
	 * @param elem
	 */
	public Node (ConfigData elem){
		next = null; 
		element = elem; 
	}
	
	/**
	 * method sets the pointer to the next node
	 * @param node
	 */
	public void setNext(Node node){
		next = node;
	}
	
	/**
	 * method that returns the next node that is being pointed to 
	 * @return node that is being pointed to 
	 */
	public Node getNext(){
		return next; 
	}
	
	/**
	 * method that returns the element stored within the node
	 * @return element
	 */
	public ConfigData getElement(){
		return element; 
	}
	
	/**
	 * method that returns the score of the node (configuration)
	 * @return score of node
	 */
	public int getScore(){
		return element.getScore();
	}
	
}