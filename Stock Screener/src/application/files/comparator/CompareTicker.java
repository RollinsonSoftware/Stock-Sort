package application.files.comparator;

import java.util.Comparator;

import application.files.Stock;

public class CompareTicker implements Comparator<Stock> 
{
	/**
	 * Does the same thing as the Stock class's default compare method.
	 * Check the method in the stock class for more documentation.
	 */
	@Override
	public int compare(Stock item1, Stock item2) 
	{
		// Since the ticker is a string, we can invoke 
		// String's compare method
		return item1.getTicker().compareTo(item2.getTicker());
	}
	
}
