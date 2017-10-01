/**
 * class implementing the class of exceptions thrown by the insert and remove methods
 */
package assignment2;

public class DictionaryException extends Exception{
	public DictionaryException(String message){
		// print message specified within insert/remove methods
		super(message);
	}
}
