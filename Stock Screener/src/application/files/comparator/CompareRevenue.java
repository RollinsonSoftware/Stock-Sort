package application.files.comparator;

import java.util.Comparator;

import application.files.Stock;

public class CompareRevenue implements Comparator<Stock> 
{	
	/**
	 * Compares the revenues on file for two stock objects.
	 */
	@Override
	public int compare(Stock item1, Stock item2) 
	{
		return (int) ( (100 * item1.getOpen()) - (100 * item2.getOpen()) );
	}

}