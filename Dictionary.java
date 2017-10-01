/*
 * Class that implements a dictionary using a hash table with separate chaining
 */

package assignment2;

public class Dictionary implements DictionaryADT{
	// Attribute declarations
	private Node[] table;
	private int arraySize = 6000;
	
	/**
	 * Constructor creates a dictionary of specified size
	 * @param size
	 */
	public Dictionary(int size){
		this.arraySize = size; 
		this.table = new Node[size];
		// loop through to create the array, mark each spot as null
		for (int i=0; i<arraySize; i++){
			table[i]=null;
		}
	}
	
	/**
	 * Constructor creates a dictionary of default size
	 */
	public Dictionary(){
		this.table = new Node[arraySize]; 
		// loop through to create the array, mark each spot as null
		for (int i=0; i<arraySize; i++){
			table[i]=null; 
		}
	}
	
	/**
	 * Method that determines the hashcode given a configuration
	 * @param element - a configuration 
	 * @return hashcode - to be used to insert element into function 
	 */
	private int hashCode(String element){
		int code = 1; 
		// apply hash function to each element in configuration
		for (int i=0; i < element.length(); i++){
			code = element.charAt(i) % 13;
		}
		// handle codes that are negative 
		if (code<0){
			code = code * -1; 
		}
		return code;
	}

	/**
	 * Method to insert data into the dictionary
	 * @param pair - ConfigData (configuration and score) to be inserted into dictionary
	 * @return 0 if no collisions occurred, 1 if a collision occurred
	 */
	public int insert(ConfigData pair) throws DictionaryException{
		Node node = new Node(pair); 
		String configString = pair.getConfig();
		int code = hashCode(configString); 
		// no collisions occurred
		if (table[code]==null){
			table[code]= node;
			return 0; 
		}
		// collision occurred 
		else{
			Node nodeCurr=table[code];
			// find the last node in the linked list 
			while (nodeCurr.getNext() != null){
				// check if configuration is already in dictionary 
				if (nodeCurr.getElement().getConfig().equals(configString)){
					throw new DictionaryException("This value is already in the dictionary"); 
				}
				nodeCurr = nodeCurr.getNext(); 
			}
			// check if configuration is already in dictionary
			if (nodeCurr.getElement().getConfig().equals(configString)          ){
				throw new DictionaryException("This value is already in dictionary");
			}
			// insert the node behind the last node in the list 
			nodeCurr.setNext(node);
			return 1; 
		}
}
	
	/**
	 * class that removes an element from the dictionary 
	 * @param config - configuration that needs to be removed from dictionary
	 */
	public void remove (String config) throws DictionaryException{
		int code = hashCode(config); // calculate hash code for element that needs to be removed
		// there is nothing at the hash code value in the dictionary for specified configuration
		if (table[code]==null){
			throw new DictionaryException("The specified configuration is not in dictionary");
		}
		// if there is a node at the hash code value in the dictionary for specified configuration
		else{
			Node node=table[code]; 
			// if there is only one node in that position
			if (node.getNext()==null){
				table[code]=null; // remove node
			}
			// if there is more than one node in that position
			else{
				Node nodeCurr = node.getNext();
				Node nodePrev = node; 
				// check the configuration of every node in the list 
				while (nodeCurr.getNext()!=null){
					if (nodeCurr.getElement().getConfig().equals(config)){
						nodePrev.setNext(nodeCurr.getNext());
					}
				// if none of the configurations matches with the one user wnated to remove
				if (nodeCurr.getNext()==null){
					throw new DictionaryException("The specified configuration is not in dictionary"); 
				}
				}
			}
			
		}
	}
	
	/**
	 * class to find score given a configuration 
	 * @param config - configuration that needs a score
	 * @return score if the configuration is in the dictionary, -1 if configuration isn't in dictionary
	 */
	public int find(String config){
		int code = hashCode(config); // find hash code for configuration that needs to be removed
		// if there is no node in the position of specified configuration's hash code
		if (table[code]==null){
			return -1; 
		}
		else{
			Node nodeCurr = table[code];
			// check every element in the linked list to find matching configuration and score
			while (nodeCurr.getNext()!=null){
				if (nodeCurr.getElement().getConfig().equals(config)){
					return nodeCurr.getScore();
				}
				nodeCurr = nodeCurr.getNext(); 
			}
			// if there is only one node in the position
			if (nodeCurr.getElement().getConfig().equals(config)){
				return nodeCurr.getScore();
			}
			// if nothing matches 
			return -1; 
		}
	}
	
}
	
	
	