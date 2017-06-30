package application.files.comparator;

import java.util.Comparator;

import application.files.Stock;

public class CompareMarketCap implements Comparator<Stock> 
{
	/**
	 * Compares the company's market capital on file for two Stock objects.
	 */
	public int compare(Stock item1, Stock item2)
	{
		return (int) ( (100 * item1.getLow()) - (100 * item2.getLow()) );
	}

}
