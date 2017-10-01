/*
 * Class that represents data stored in one entry of dictionary/node
 */
package assignment2;

public class ConfigData {
	// Attribute declaration
	String configData; 
	int scoreNum; 
	
	/**
	 * Constructor creates a configData object storing a configuration string and score
	 * @param config configuration of gameboard
	 * @param score current score of gameboard
	 */
	public ConfigData(String config, int score){
		this.configData = config; 
		this.scoreNum = score;
	}
	
	/**
	 * get config method returns the configuration of the date
	 * @return configuration of data
	 */
	public String getConfig(){
		return configData;
	}
	
	/**
	 * get score method returns the score of the configuration
	 * @return score
	 */
	public int getScore(){
		return scoreNum; 
	}
	
}