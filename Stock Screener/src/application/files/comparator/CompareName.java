package application.files.comparator;

import java.util.Comparator;

import application.files.Stock;

public class CompareName implements Comparator<Stock> 
{
	/**
	 * Compares the names of two Stock objects.
	 */
	@Override
	public int compare(Stock item1, Stock item2) 
	{
		// Since the name is a string, we can invoke 
		// String's compare method
		return item1.getName().compareTo(item2.getName());
	}
	
}
