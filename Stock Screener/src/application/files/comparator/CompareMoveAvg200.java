package application.files.comparator;

import java.util.Comparator;

import application.files.Stock;

public class CompareMoveAvg200 implements Comparator<Stock> 
{
	/**
	 * Compares the 200 day moving average's on file for two Stock objects.
	 */
	@Override
	public int compare(Stock item1, Stock item2) 
	{
		return (int) ( (100 * item1.getMoveAvg200()) - (100 * item2.getMoveAvg200()) );
	}
	
}
