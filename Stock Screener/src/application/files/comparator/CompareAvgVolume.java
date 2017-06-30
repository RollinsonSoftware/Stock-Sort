package application.files.comparator;

import java.util.Comparator;

import application.files.Stock;

public class CompareAvgVolume implements Comparator<Stock> 
{
	/**
	 * Compares the average Volume on file of two different stock objects.
	 */
	@Override
	public int compare(Stock item1, Stock item2) 
	{
		return item1.getAvgVolume() - item2.getAvgVolume();
	}
	
}
