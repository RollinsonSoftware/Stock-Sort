package application.files.comparator;

import java.util.Comparator;

import application.files.Stock;

public class CompareOneYearPriceTarget implements Comparator<Stock> 
{
	/**
	 * Compares the one year price targets on file for the two Stock objects.
	 */
	@Override
	public int compare(Stock item1, Stock item2) 
	{
		return (int) ( (100 * item1.getClose()) - (100 * item2.getClose()) );
	}

}