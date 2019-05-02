import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class HammingDist{
	
protected ArrayList <String> stationIDS;
protected ArrayList<String> distZeroStations;
protected ArrayList<String> distOneStations;
protected ArrayList<String> distTwoStations;
protected ArrayList<String> distThreeStations;
protected ArrayList<String> distFourStations;
protected int distZero;
protected int distOne;
protected int distTwo;
protected int distThree;
protected int distFour;

	/**
	 * 	
	 * @param entry
	 * @throws IOException
	 */
	public HammingDist(String entry) throws IOException
	{
		stationIDS = new ArrayList<String>();
		distZeroStations = new ArrayList<String>();
		distOneStations = new ArrayList<String>();
		distTwoStations = new ArrayList<String>();
		distThreeStations = new ArrayList<String>();
		distFourStations = new ArrayList<String>();
		readFile();
		totalHammingDist(entry);
		
	}
	
	/**
	 * reads Mesonet.txt file into an ArrayList
	 * @throws IOException
	 */
	public void readFile() throws IOException
	{
		// Creates Scanner that will read Mesonnet.txt file
		Scanner scnr = new Scanner(new File("Mesonet.txt"));
		
		
		String station = "";
		// Fills ArrayList with indivdual stations
		while(scnr.hasNextLine())
		{
			station = scnr.nextLine();
			stationIDS.add(station);
			
		}
		
	}
	/**
	 * calculates the hamming distance of the string entered in the param
	 * @param entry
	 */
	public void totalHammingDist(String entry)
	{
		distZero = 0;
		distOne= 0;
		distTwo = 0;
		distThree = 0;
		distFour = 0;
		char[] stationChar = new char[4];
		entry.getChars(0, 4, stationChar, 0);
		
		for(int index = 0; index<stationIDS.size(); ++index)
		{
			String id = stationIDS.get(index);
			// breaks string from stationIDS ArrayList into individual chars and places them in a char array 
			char[] ids = id.toCharArray();
			int totalDist = 0;
			for(int index2 = 0; index2<ids.length; ++index2)
			{
			
				if(ids[index2] != stationChar[index2])
				{
					++totalDist;
					
				}
				
			}
			// Determines what count to add by 1 based on the totalDist value
				if(totalDist == 0)
				{
					++distZero;
					distZeroStations.add(id);
					
				}
				if(totalDist == 1)
				{
					++distOne;
					distOneStations.add(id);
				}
				
				if(totalDist == 2)
				{
					++distTwo;
					distTwoStations.add(id);
				}
				
				if(totalDist == 3)
				{
					++distThree;
					distThreeStations.add(id);
				}
				
				if(totalDist == 4)
				{
					++distFour;
					distFourStations.add(id);
				}
				
		}
		
	}
	
	/**
	 * 
	 * @return distZero
	 */
	public int getDistZero() {
		return distZero;
	}

	/**
	 * 
	 * @return distOne
	 */
	public int getDistOne() {
		return distOne;
	}

	/**
	 * 
	 * @return distTwo
	 */
	public int getDistTwo() {
		return distTwo;
	}

	/**
	 * 
	 * @return distThree
	 */
	public int getDistThree() {
		return distThree;
	}

	/**
	 * 
	 * @return distFour
	 */
	public int getDistFour() {
		return distFour;
	}

	/**
	 * 
	 * @return distZeroStations ArrayList
	 */
	public ArrayList<String> getDistZeroStations() {
		return distZeroStations;
	}

	/**
	 * 
	 * @return distOneStations
	 */
	public ArrayList<String> getDistOneStations() {
		return distOneStations;
	}

	/**
	 * 
	 * @return distTwoStations
	 */
	public ArrayList<String> getDistTwoStations() {
		return distTwoStations;
	}

	/**
	 * 
	 * @return distThreeStations
	 */
	public ArrayList<String> getDistThreeStations() {
		return distThreeStations;
	}

	/**
	 * 
	 * @return distFourStations
	 */
	public ArrayList<String> getDistFourStations() {
		return distFourStations;
	}
	
	
	
}