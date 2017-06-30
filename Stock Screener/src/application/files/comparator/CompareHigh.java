package application.files.comparator;

import java.util.Comparator;

import application.files.Stock;

public class CompareHigh implements Comparator<Stock> 
{
	/**
	 * Compares the 52 week high's on file for two Stock objects.
	 */
	@Override
	public int compare(Stock item1, Stock item2) 
	{
		return (int) ( (100 * item1.getHigh()) - (100 * item2.getHigh()) );
	}

}
