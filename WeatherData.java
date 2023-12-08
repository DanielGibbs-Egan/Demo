import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * <style>
 *  tab {
 * 		margin-left: 25px;
 *  }
	em {
		margin-left: 50px;
		border: 2px solid rgba(150, 150, 150, 0);
		background-opacity: .5;
		background-color: rgba(255, 255, 255, .75);
 * 		line-height: 2;
	}
	sp {
		margin-left: 4px;
	}
	</style>
 * 
 * A class representing weather data including temperatures, snowfall, and
 * precipitation over a period of time.<br>
 * 
 * <center>Daniel Gibbs-Egan<br><br>
 * 
 * 		__-[ INDEX ]-__<br>
 * </center>
 *<body>
 <b>variables:</b><br> <br>
 *	
 *	<tab><b>int</b> years :<br> <em><sp>amount of years covered by the data </em><br>
 *	<br>
 *	<tab><b>Set</b> highTemperatures :<br> <em><sp> of all recored high temperatures </em><br>
 *	<tab><b>Set</b> lowTemperatures :<br> <em><sp>set of all recored low temperatures </em><br>
 *	<br>
 *	<tab><b>Map</b> weatherData :<br> <em><sp>weather data indexed by date </em><br>
 *	<br>
 *	<tab><b>Map</b> highestHighForLow :<br> <em><sp>the highest temperatures indexed by corresponding low temperature </em><br>
 *	<tab><b>Map</b> lowestMostCommonHighByMonth :<br> <em><sp>the most common high indexed by month, prefers lower temperatures in the event of a tie </em><br>
 *	<br>
 *	<tab><b>Map</b> snowfallByYear :<br> <em><sp>amount of snowfall indexed by year </em><br>
 *	<tab><b>Map</b> precipitationByMonth :<br> <em><sp>amount of rainfall indexed by month </em><br>
 *	<br>
 *	<tab><b>Map</b> frequencyOfHighsByMonth :<br> <em><sp>high temperatures and their frequency indexed by month </em><br><br>
 *	
 *
 <b>methods</b> : <br> <br>
 
 *	<tab><b>double</b> doubleFromObject(Object value) : <br>
 *	<em><sp>returns a <b>double</b> representation of the given <b>Object</b> value </em><br>
 *	<tab><b>boolean</b> highTemp(int degrees) : <br>
 *	<em><sp>returns a <b>boolean</b> representing if the given <b>int</b> temperature was a recorded high </em><br>
 *	<tab><b>boolean</b> lowTemp(int degrees) : <br>
 *	<em><sp>returns a <b>boolean</b> representing if the given <b>int</b> temperature was a recorded low </em><br>
 *	<tab><b>double</b> totalSnowfallForYear(int year) : <br>
 *	<em><sp>returns a <b>double</b> value for the total snowfall in a given <b>int</b> year </em><br>
 *	<tab><b>double</b> averagePrecipitationForMonth(int month) : <br>
 *	<em><sp>returns a <b>double</b> value for the average precipitation in a given <b>int</b> month </em><br>
 *	<tab><b>int</b> lowestMostCommonHighForMonth(int month) : <br>
 *	<em><sp>returns an <b>int</b> value for the most common high temperature in a given <b>int</b> month </em><br>
 *	<tab><b>int</b> highestHighForLow(int degrees) : <br>
 *	<em><sp>returns an <b>int</b> value for the highest temperature seen alongside the given <b>int</b> low temperature </em><br>
 *</body>
 */

public class WeatherData {

	/* Variables */
	
	private int years = 0;

	// sets of all recorded high & low temperatures
	private Set<Integer> highTemperatures = new HashSet<>();
	private Set<Integer> lowTemperatures = new HashSet<>();
	
	// data collected from the file sorted by date
	private Map<String, String[]> weatherData = new HashMap<>();
	
	// the highest temperatures indexed by corresponding low temperature
	private Map<Integer, Integer> highestHighForLow = new HashMap<>();
	// the most common high temperature in a month, preffers lower temps in the event of a tie.
	private Map<Integer, Integer> lowestMostCommonHighByMonth = new HashMap<>();
	
	// the total snowfall in a given year
	private Map<Integer, Double> snowfallByYear = new HashMap<>();
	// the total precipitation/rainfall in a given month
	private Map<Integer, Double> precipitationByMonth = new HashMap<>();
	
	// the frequency of every high temperature in a given month
	private Map<Integer, Map<Integer, Integer>> frequencyOfHighsByMonth = new HashMap<>();

	/* Methods */
	
	/**
	 * returns a <b>double</b> representation of the <b>Object</b> value
	 * 
	 * @param value an instance that extends <b>Object</b>
	 * @return the value of the given object as a <b>double</b>, or 
	 * <br> <b>0</b> if the object is <b>null</b> or is not convertable 
	 */
	public double doubleFromObject(Object value) {
		// return 0: if (value) is null
		if (value == null) return 0d;
		// convert value to a String
		value = value.toString();
		// convert (value) into a double
		value = Double.parseDouble((String)value);
		// return (value) or 0 if (value) is null
		return (value != null)? (double)value : 0d;
	}
	
	/**
	 * Determine whether the given temperature was ever seen as a high temperature
	 * in the data provided to the constructor.
	 * 
	 * @param degrees Temperature (same units as data file)
	 * @return <b>true</b> if high temp, <b>false</b> otherwise
	 */
	public boolean highTemp(int degrees) {
		return highTemperatures.contains(degrees);
	}

	/**
	 * Determine whether the given temperature was ever seen as a low temperature in
	 * the data provided to the constructor. 
	 * 
	 * @param degrees Temperature (same units as data file)
	 * @return <b>true</b> if low temp, <b>false</b> otherwise
	 */
	public boolean lowTemp(int degrees) {
		return lowTemperatures.contains(degrees);
	}

	/**
	 * Determine the total amount of snowfall recorded in the given year. 
	 * 
	 * @param year : the year to obtain the total <b>snowfall</b> from.
	 * @return <b>snowfall</b> : the amount of snowfall in the given <b>year</b>.
	 */
	public double totalSnowfallForYear(int year) {
		return doubleFromObject(snowfallByYear.get(year));
	}

	/**
	 * Determine the average (mean) total precipitation recorded for the given
	 * month.
	 * 
	 * @param month : the month to obtain the total <b>precipitation</b> from.
	 * @return <b>precipitation</b> : the total precipitation in the given <b>month</b>
	 */
	public double averagePrecipitationForMonth(int month) {
		return doubleFromObject(precipitationByMonth.get(month)) / years; 
	}

	/**
	 * Return the most common high temperature seen in the
	 * given month. If two temperatures share frequencies it
	 * returns the lowest of the two temperature.
	 * 
	 * @param month : the month to obtain the most common <b>high temperature</b>
	 * @return <b>high temperature</b> : the most common high temperature 
	 * in the given <b>month</b>
	 */
	public int lowestMostCommonHighForMonth(int month) {
		return lowestMostCommonHighByMonth.get(month); 
	}

	/**
	 * For the given low temperature, find the highest high temperature seen with
	 * that low.
	 * 
	 * @param degrees : low temperature.
	 * @return <b>high temperature</b> : highest high ever seen alongside<br>
	 * the given low temperature.
	 */
	public int highestHighForLow(int degrees) {
		return highestHighForLow.get(degrees); 
	}
	
	public WeatherData(Scanner file) {
		
		file.nextLine(); // remove file header
		
		Integer currentYear = 0; // instantiate the current year variable
		
		while (file.hasNext()) { // repeat data analysis while the file contains more data
			
			String line = file.nextLine(); // 
			
			line = line.replaceAll("(\"| )", "");
			
			String[] lineArray = line.split(",");
			
			this.weatherData.put(lineArray[1], lineArray);
			
			String[] date = lineArray[1].split("-");
			
			Integer year = Integer.parseInt(date[0]);
			Integer month = Integer.parseInt(date[1]);
			//Integer day = Integer.parseInt(date[2]);
			
			if (year != null && !currentYear.equals(year)) {
				currentYear = year;
				years++;
			}
			
			{
				Double totalPrecipitation = doubleFromObject(precipitationByMonth.get(month));
				
				if (lineArray.length > 3 && !(lineArray[3].equals(""))){
					totalPrecipitation += Double.parseDouble(lineArray[3]);
				}
				
				if (lineArray.length > 4 && !(lineArray[4].equals(""))) {
					totalPrecipitation  +=  Double.parseDouble(lineArray[4]);
				}
				
				precipitationByMonth.put(month, totalPrecipitation);
			}
			
			if (lineArray.length > 5 && !(lineArray[5].equals(""))) {
				
				Double totalSnowfall = doubleFromObject(snowfallByYear.get(year));
				
				totalSnowfall += Double.parseDouble(lineArray[5]);

				snowfallByYear.put(year, totalSnowfall);
				
			}
			
			
			if (lineArray.length > 7 && !(lineArray[7].equals(""))) {
				
				Integer highTemp = Integer.parseInt(lineArray[7]);
				
				highTemperatures.add(highTemp);
				
				{
					Map<Integer, Integer> rarityOfHighs = frequencyOfHighsByMonth.get(month);
					
					if (rarityOfHighs == null) {
						rarityOfHighs = new HashMap<>();
						frequencyOfHighsByMonth.put(month, rarityOfHighs);
					}
					
					Integer amount = (int)(doubleFromObject(rarityOfHighs.get(highTemp)));
					
					rarityOfHighs.put(highTemp, amount+1);
				}
				
				if (lineArray.length > 8 && !(lineArray[8].equals(""))) {
					
					Integer lowTemp = Integer.parseInt(lineArray[8]);
					
					lowTemperatures.add(lowTemp);
					
					{
						Integer currentHighest = highestHighForLow.get(lowTemp);
						
						if (currentHighest == null || currentHighest < highTemp)
							this.highestHighForLow.put(lowTemp, highTemp);
						
					}
				}
			}
		}
		
		for (Integer month: frequencyOfHighsByMonth.keySet()) {
			
			Map<Integer, Integer> frequencyOfHighs = frequencyOfHighsByMonth.get(month);
			
			int highestFrequency = 0;
			int lowestTemperature = 0;
			
			for (int temperature: frequencyOfHighs.keySet()) {
				int frequency = frequencyOfHighs.get(temperature);
				
				boolean surpassedOldHighest = frequency > highestFrequency;
				boolean lowerTemperature = temperature < lowestTemperature;
				
				if (surpassedOldHighest || (lowerTemperature && (frequency == highestFrequency))) {
					lowestTemperature = temperature;
					highestFrequency = frequency;
				}
			}
			
			lowestMostCommonHighByMonth.put(month, lowestTemperature);
		}
		
	}

}
