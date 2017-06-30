package application.files;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import application.files.comparator.CompareAvgVolume;
import application.files.comparator.CompareBookValue;
import application.files.comparator.CompareClose;
import application.files.comparator.CompareCurrentEPS;
import application.files.comparator.CompareEpsNextQ;
import application.files.comparator.CompareEpsNextY;
import application.files.comparator.CompareHigh;
import application.files.comparator.CompareLow;
import application.files.comparator.CompareMarketCap;
import application.files.comparator.CompareMoveAvg200;
import application.files.comparator.CompareMoveAvg50;
import application.files.comparator.CompareOneYearPriceTarget;
import application.files.comparator.CompareOpen;
import application.files.comparator.ComparePriceToBook;
import application.files.comparator.CompareRevenue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import yahoofinance.YahooFinance; // author: Stijn Strickx

/**
 * Parser.java
 * 
 * This Parser class provide the functionality to read a .txt file which stored
 * all the information of stocks. or if you use the default constructor, this
 * class will randomly fill 10 Stock for test.
 * 
 * Once you created the object(Parser). It will fill the stockHeapAry.
 * 
 * You can use all the get method to get the max/min Stocks for each value.
 * 
 * @author Jack Rollinson
 * 			Yunan Zhang
 *
 */
public class Parser 
{
	//Note: does not have heaps for the company name or ticker symbol.
	//Heaps:
	public static final int MAX_HIGH = 0;
	public static final int MIN_HIGH = 1;
	public static final int MAX_LOW = 2;
	public static final int MIN_LOW = 3;
	public static final int MAX_OPEN = 4;
	public static final int MIN_OPEN = 5;
	public static final int MAX_CLOSE = 6;
	public static final int MIN_CLOSE = 7;
	public static final int MAX_MOVE_200 = 8;
	public static final int MIN_MOVE_200 = 9;
	public static final int MAX_MOVE_50 = 10;
	public static final int MIN_MOVE_50 = 11;
	public static final int MAX_AVG_VOLUME = 12;
	public static final int MIN_AVG_VOLUME = 13;
	public static final int MAX_MARKET_CAP = 14;
	public static final int MIN_MARKET_CAP = 15;
	public static final int MAX_REVENUE = 16;
	public static final int MIN_REVENUE = 17;
	
	public static final int MAX_EPS_CURRENT_YEAR = 18;
	public static final int MIN_EPS_CURRENT_YEAR = 19;
	public static final int MAX_EPS_ESTIMATE_NEXT_Q = 20;
	public static final int MIN_EPS_ESTIMATE_NEXT_Q = 21;
	public static final int MAX_EPS_ESTIMATE_NEXT_Y = 22;
	public static final int MIN_EPS_ESTIMATE_NEXT_Y = 23;
	public static final int MAX_ONE_YEAR_PRICE_TARGET = 24;
	public static final int MIN_ONE_YEAR_PRICE_TARGET = 25;
	public static final int MAX_BOOK_VALUE = 26;
	public static final int MIN_BOOK_VALUE = 27;
	public static final int MAX_PRICE_TO_BOOK_RATIO = 28;
	public static final int MIN_PRICE_TO_BOOK_RATIO = 29;
	
	//Number of variables above.
	public static final int NUM_HEAPS = 30;
	
	// Objects:
	YahooFinance web = new YahooFinance();
	
	// instance variables:
	private ArrayList<Stock> stockList;// store the stocks
	@SuppressWarnings("unchecked")
	private Heap<Stock> stockHeapAry[] = new Heap[NUM_HEAPS]; // Array store all the
																// compareHeap;

	// Constructors
	/**
	 * Testing the webscraper with this constructor.
	 * Reads all data from a text file that was created by the Webscraper class.
	 */
	public Parser() 
	{ 
		stockList = new ArrayList<Stock>();
		readFromFile("StockData.txt");
		fillHeapArray();
	}
	/**
	 * Constructor with the fileName, read all the information from the file and
	 * add all the stock into the ArrayList(call readFromFile method)
	 * 
	 * @param fileName
	 * @throws StockException
	 */
	/*
	public Parser(String fileName) 
	{ 
		stockList = new ArrayList<Stock>();
		readFromFile(fileName);
		fillHeapArray();
	}
	*/

	/**
	 * Default constructor, call randomFillList method,put random stock into
	 * ArrayList
	 * 
	 * @throws StockException
	 */
	/*
	public Parser() {
            
            System.out.println("in parser constructor");
            
		stockList = new ArrayList<Stock>();
		randomFillList();
		fillHeapArray();
	}
	*/
	
	/**
	 * getStockList method
	 * 
	 * @return stockList
	 */
	public ArrayList<Stock> getStockList() 
	{
		return this.stockList;
	}

	/**
	 * printStock method take the ticker of the stock as parameter and print it
	 * by using toString method in the Stock class
	 * 
	 * @param stock
	 */
	public void printStock(String stock) {
		for (Stock s : stockList) 
		{
			if (s.getTicker().equals(stock)) 
			{
				System.out.println(s.toString());
				return;
			}
		}
		System.out.println(stock + " is not in the list.");
	}

	/**
	 * getStock method
	 * 
	 * @param stock
	 * @return stock object
	 */
	public Stock getStock(String stock) 
	{
		for (Stock s : stockList) 
		{
			if (s.getTicker().equals(stock)) 
			{
				return s;
			}
		}
		System.out.println(stock + " is not in the list.");
		return null;
	}

	/**
	 * readFromFile method Pre-requirement for web scraping read a .txt file and
	 * add all stocks into the ArrayList
	 * 
	 * @param fileName
	 * @throws StockException
	 */
	public void readFromFile(String fileName) 
	{
		try 
		{
			Scanner input = new Scanner(new FileInputStream(fileName));
			while (input.hasNextLine()) 
			{
				Stock temp = new Stock();
				
				try
				{
					temp.setName(input.nextLine());
					temp.setTicker(input.nextLine());
					temp.setHigh(Double.parseDouble(input.nextLine()));
					temp.setLow(Double.parseDouble(input.nextLine()));
					temp.setOpen(Double.parseDouble(input.nextLine()));
					temp.setClose(Double.parseDouble(input.nextLine()));
					temp.setMoveAvg200(Double.parseDouble(input.nextLine()));
					temp.setMoveAvg50(Double.parseDouble(input.nextLine()));
					temp.setAvgVolume(Integer.parseInt(input.nextLine()));
					temp.setMarketCap(Double.parseDouble(input.nextLine()));
					temp.setRevenue(Double.parseDouble(input.nextLine()));
					temp.setEpsCurrentY(Double.parseDouble(input.nextLine()));
					temp.setEpsEstimateNextQ(Double.parseDouble(input.nextLine()));
					temp.setEpsEstimateNextY(Double.parseDouble(input.nextLine()));
					temp.setOneYearPriceTarget(Double.parseDouble(input.nextLine()));
					temp.setBookValue(Double.parseDouble(input.nextLine()));
					temp.setPriceToBookValue(Double.parseDouble(input.nextLine()));
				}
				catch (StockException e)
				{
					System.out.println("Bad data, shutting down program...");
					System.exit(0);
				}
				stockList.add(temp);
			}
			input.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * randomFillList method use the RandomFill method in the Stock class to
	 * create random Stocks and add them into the ArrayList
	 * 
	 * @throws StockException
	 */
	public void randomFillList() 
	{
		for (int i = 0; i < 10; i++) 
		{
			Stock randomNew = new Stock();
			
			try
			{
				randomNew.setTicker(randomNew.getTicker() + i);
			}
			catch(StockException e)
			{
				System.out.println("Bad data, shutting down program...");
				System.exit(0);
			}
			stockList.add(randomNew);
		}
	}  
       

	/**
	 * fillHeapArray method Create 14 heap(max and min) with different
	 * comparator, add all stock into. And add them into the stockHeapAry.
	 */
	public void fillHeapArray() 
	{
		MaxHeap<Stock> maxHigh = new MaxHeap<Stock>(new CompareHigh());
		fillHeap(maxHigh);
		stockHeapAry[MAX_HIGH] = maxHigh;

		MinHeap<Stock> minHigh = new MinHeap<Stock>(new CompareHigh());
		fillHeap(minHigh);
		stockHeapAry[MIN_HIGH] = minHigh;

		MaxHeap<Stock> maxLow = new MaxHeap<Stock>(new CompareLow());
		fillHeap(maxLow);
		stockHeapAry[MAX_LOW] = maxLow;

		MinHeap<Stock> minLow = new MinHeap<Stock>(new CompareLow());
		fillHeap(minLow);
		stockHeapAry[MIN_LOW] = minLow;

		MaxHeap<Stock> maxOpen = new MaxHeap<Stock>(new CompareOpen());
		fillHeap(maxOpen);
		stockHeapAry[MAX_OPEN] = maxOpen;

		MinHeap<Stock> minOpen = new MinHeap<Stock>(new CompareOpen());
		fillHeap(minOpen);
		stockHeapAry[MIN_OPEN] = minOpen;

		MaxHeap<Stock> maxClose = new MaxHeap<Stock>(new CompareClose());
		fillHeap(maxClose);
		stockHeapAry[MAX_CLOSE] = maxClose;

		MinHeap<Stock> minClose = new MinHeap<Stock>(new CompareClose());
		fillHeap(minClose);
		stockHeapAry[MIN_CLOSE] = minClose;

		MaxHeap<Stock> maxMove200 = new MaxHeap<Stock>(new CompareMoveAvg200());
		fillHeap(maxMove200);
		stockHeapAry[MAX_MOVE_200] = maxMove200;

		MinHeap<Stock> minMove200 = new MinHeap<Stock>(new CompareMoveAvg200());
		fillHeap(minMove200);
		stockHeapAry[MIN_MOVE_200] = minMove200;

		MaxHeap<Stock> maxMove50 = new MaxHeap<Stock>(new CompareMoveAvg50());
		fillHeap(maxMove50);
		stockHeapAry[MAX_MOVE_50] = maxMove50;

		MinHeap<Stock> minMove50 = new MinHeap<Stock>(new CompareMoveAvg50());
		fillHeap(minMove50);
		stockHeapAry[MIN_MOVE_50] = minMove50;

		MaxHeap<Stock> maxAvgVolume = new MaxHeap<Stock>(new CompareAvgVolume());
		fillHeap(maxAvgVolume);
		stockHeapAry[MAX_AVG_VOLUME] = maxAvgVolume;

		MinHeap<Stock> minAvgVolume = new MinHeap<Stock>(new CompareAvgVolume());
		fillHeap(minAvgVolume);
		stockHeapAry[MIN_AVG_VOLUME] = minAvgVolume;
		
		MaxHeap<Stock> maxMarketCap = new MaxHeap<Stock>(new CompareMarketCap());
		fillHeap(maxMarketCap);
		stockHeapAry[MAX_MARKET_CAP] = maxMarketCap;

		MinHeap<Stock> minMarketCap = new MinHeap<Stock>(new CompareMarketCap());
		fillHeap(minMarketCap);
		stockHeapAry[MIN_MARKET_CAP] = minMarketCap;
		
		MaxHeap<Stock> maxRevenue = new MaxHeap<Stock>(new CompareRevenue());
		fillHeap(maxRevenue);
		stockHeapAry[MAX_REVENUE] = maxRevenue;

		MinHeap<Stock> minRevenue = new MinHeap<Stock>(new CompareRevenue());
		fillHeap(minRevenue);
		stockHeapAry[MIN_REVENUE] = minRevenue;
		
		MaxHeap<Stock> maxEpsCurrent = new MaxHeap<Stock>(new CompareCurrentEPS());
		fillHeap(maxEpsCurrent);
		stockHeapAry[MAX_EPS_CURRENT_YEAR] = maxEpsCurrent;

		MinHeap<Stock> minEpsCurrent = new MinHeap<Stock>(new CompareCurrentEPS());
		fillHeap(minEpsCurrent);
		stockHeapAry[MIN_EPS_CURRENT_YEAR] = minEpsCurrent;
		
		MaxHeap<Stock> maxEpsNextQ = new MaxHeap<Stock>(new CompareEpsNextQ());
		fillHeap(maxEpsNextQ);
		stockHeapAry[MAX_EPS_ESTIMATE_NEXT_Q] = maxEpsNextQ;

		MinHeap<Stock> minEpsNextQ = new MinHeap<Stock>(new CompareEpsNextQ());
		fillHeap(minEpsNextQ);
		stockHeapAry[MIN_EPS_ESTIMATE_NEXT_Q] = minEpsNextQ;
		
		MaxHeap<Stock> maxEpsNextY = new MaxHeap<Stock>(new CompareEpsNextY());
		fillHeap(maxEpsNextY);
		stockHeapAry[MAX_EPS_ESTIMATE_NEXT_Y] = maxEpsNextY;

		MinHeap<Stock> minEpsNextY = new MinHeap<Stock>(new CompareEpsNextY());
		fillHeap(minEpsNextY);
		stockHeapAry[MIN_EPS_ESTIMATE_NEXT_Y] = minEpsNextY;
		
		MaxHeap<Stock> maxOneYearPriceTarget = new MaxHeap<Stock>(new CompareOneYearPriceTarget());
		fillHeap(maxOneYearPriceTarget);
		stockHeapAry[MAX_ONE_YEAR_PRICE_TARGET] = maxOneYearPriceTarget;

		MinHeap<Stock> minOneYearPriceTarget = new MinHeap<Stock>(new CompareOneYearPriceTarget());
		fillHeap(minOneYearPriceTarget);
		stockHeapAry[MIN_ONE_YEAR_PRICE_TARGET] = minOneYearPriceTarget;
		
		MaxHeap<Stock> maxBookValue = new MaxHeap<Stock>(new CompareBookValue());
		fillHeap(maxBookValue);
		stockHeapAry[MAX_BOOK_VALUE] = maxBookValue;

		MinHeap<Stock> minBookValue = new MinHeap<Stock>(new CompareBookValue());
		fillHeap(minBookValue);
		stockHeapAry[MIN_BOOK_VALUE] = minBookValue;
		
		MaxHeap<Stock> maxPriceToBook = new MaxHeap<Stock>(new ComparePriceToBook());
		fillHeap(maxPriceToBook);
		stockHeapAry[MAX_PRICE_TO_BOOK_RATIO] = maxPriceToBook;

		MinHeap<Stock> minPriceToBook = new MinHeap<Stock>(new ComparePriceToBook());
		fillHeap(minPriceToBook);
		stockHeapAry[MAX_PRICE_TO_BOOK_RATIO] = minPriceToBook;
	}

	/**
	 * fillHeap method take a heap<Stock> as parameter, add all the stock from
	 * stockList(ArrayList) into the heap;
	 * 
	 * @param stockHeap
	 */
	public void fillHeap(Heap<Stock> stockHeap) 
	{
		for (int i = 0; i < this.stockList.size(); i++) 
		{
			stockHeap.insert(this.stockList.get(i));
		}
	}
        
    /**
     * Returns the root of the heap that is stored in each of the 
     * stockHeapAry elements. Use the class constants for the argument. 
     * @param heapName int The array element that the heap exists in. 
     * @return Returns the root of the heap that is stored in the stockHeapAry
     * element that is called in the argument
     */
    protected Stock getWinner(int heapName) 
    {
        return stockHeapAry[heapName].peek();
    }
    
    //---------------------------------------------------
	/**
	 * This will serve as documentation for all the get methods in this section.
	 * All the methods perform the same task, but on different heaps.
	 * @return the root of each heap, which is either the smallest/largest value.
	 */
	public Stock getMaxHigh() 
	{
		return stockHeapAry[MAX_HIGH].peek();
	}

	public Stock getMinHigh() 
	{
		return stockHeapAry[MIN_HIGH].peek();
	}

	public Stock getMaxLow() 
	{
		return stockHeapAry[MAX_LOW].peek();
	}

	public Stock getMinLow() 
	{
		return stockHeapAry[MIN_LOW].peek();
	}

	public Stock getMaxOpen() 
	{
		return stockHeapAry[MAX_OPEN].peek();
	}

	public Stock getMinOpen() 
	{
		return stockHeapAry[MIN_OPEN].peek();
	}

	public Stock getMaxClose() 
	{
		return stockHeapAry[MAX_CLOSE].peek();
	}

	public Stock getMinClose() 
	{
		return stockHeapAry[MIN_CLOSE].peek();
	}

	public Stock getMaxMove200() 
	{
		return stockHeapAry[MAX_MOVE_200].peek();
	}

	public Stock getMinMove200() 
	{
		return stockHeapAry[MIN_MOVE_200].peek();
	}

	public Stock getMaxMove50() 
	{
		return stockHeapAry[MAX_MOVE_50].peek();
	}

	public Stock getMinMove50() 
	{
		return stockHeapAry[MIN_MOVE_50].peek();
	}

	public Stock getMaxAvgVolume() 
	{
		return stockHeapAry[MAX_AVG_VOLUME].peek();
	}

	public Stock getMinAvgVolume() 
	{
		return stockHeapAry[MIN_AVG_VOLUME].peek();
	}
	public Stock getMaxMarketCap()
	{
		return stockHeapAry[MAX_MARKET_CAP].peek();
	}
	public Stock getMinMarketCap()
	{
		return stockHeapAry[MIN_MARKET_CAP].peek();
	}
	public Stock getMaxRevenue()
	{
		return stockHeapAry[MAX_REVENUE].peek();
	}
	public Stock getMinRevenue()
	{
		return stockHeapAry[MIN_REVENUE].peek();
	}
	public Stock getMaxEpsCurrentY()
	{
		return stockHeapAry[MAX_EPS_CURRENT_YEAR].peek();
	}
	public Stock getMinEpsCurrentY()
	{
		return stockHeapAry[MIN_EPS_CURRENT_YEAR].peek();
	}
	public Stock getMaxEpsEstimateNextQ()
	{
		return stockHeapAry[MAX_EPS_ESTIMATE_NEXT_Q].peek();
	}
	public Stock getMinEpsEstimateNextQ()
	{
		return stockHeapAry[MIN_EPS_ESTIMATE_NEXT_Q].peek();
	}
	public Stock getMaxEpsEstimateNextY()
	{
		return stockHeapAry[MAX_EPS_ESTIMATE_NEXT_Y].peek();
	}
	public Stock getMinEpsEstimateNextY()
	{
		return stockHeapAry[MIN_EPS_ESTIMATE_NEXT_Y].peek();
	}
	public Stock getMaxOneYearPriceTarget()
	{
		return stockHeapAry[MAX_ONE_YEAR_PRICE_TARGET].peek();
	}
	public Stock getMinOneYearPriceTarget()
	{
		return stockHeapAry[MIN_ONE_YEAR_PRICE_TARGET].peek();
	}
	public Stock getMaxBookValue()
	{
		return stockHeapAry[MAX_BOOK_VALUE].peek();
	}
	public Stock getMinBookValue()
	{
		return stockHeapAry[MIN_BOOK_VALUE].peek();
	}
	public Stock getMaxPriceToBookValue()
	{
		return stockHeapAry[MAX_PRICE_TO_BOOK_RATIO].peek();
	}
	public Stock getMinPriceToBookValue()
	{
		return stockHeapAry[MIN_PRICE_TO_BOOK_RATIO].peek();
	}

	// End of get method.
	//---------------------------------------------------
	
	// getTop10 methods: return the sorted array of the first ten stock in
	// order.
	
	@SuppressWarnings("unchecked")
	/**
	 * helper method getTop10 Use HeapSort class to sort the array from the
	 * given heap. take the first 10 and output it
	 * 
	 * @param stockHeap
	 * @return array
	 */
	public Stock[] getTop10(Heap<Stock> stockHeap, @SuppressWarnings("rawtypes") Comparator comp) 
	{
		Stock[] top10Ary = new Stock[10];
		
		for (int i = 0; i < 10; i++) 
		{
			top10Ary[i] = stockHeap.getData().get(i);
		}
		HeapSort.sort(top10Ary, true, comp);
		return top10Ary;
	}

	/**
	 * getTop10Max & getTop10Min Helper method for getTop10 Flip the array
	 * 
	 * @param stockHeap
	 * @param comp
	 * @return
	 */
	public Stock[] getTop10Max(Heap<Stock> stockHeap, @SuppressWarnings("rawtypes") Comparator comp) 
	{
		Stock top10[] = getTop10(stockHeap, comp);
		Stock Max10[] = new Stock[10];
		for (int i = 0; i < 10; i++) 
		{
			Max10[i] = top10[9 - i];
		}
		return Max10;
	}
	
	/**
	 * getTop10Max & getTop10Min Helper method for getTop10 Flip the array
	 * @param stockHeap
	 * @param comp
	 * @return
	 */
	public Stock[] getTop10Min(Heap<Stock> stockHeap, @SuppressWarnings("rawtypes") Comparator comp)
	{
		Stock top10[] = getTop10(stockHeap, comp);
		Stock Min10[] = new Stock[10];
		for (int i = 0; i < 10; i++) 
		{
			Min10[i] = top10[9 - i];
		}
		return Min10;
	}

	//---------------------------------------------------
	/**
	 * This will server as a the documentation for all the get methods below, 
	 * as they all perform the same task but on different heaps.
	 * Sorts each heap to show the best 10 in order for that category.
	 * @return a stock[] containing the 10 best stocks in that category.
	 */
	public Stock[] getTop10MaxHigh() 
	{
		return getTop10Max(stockHeapAry[MAX_HIGH], new CompareHigh());
	}

	public Stock[] getTop10MinHigh() 
	{
		return getTop10Min(stockHeapAry[MIN_HIGH], new CompareHigh());
	}

	public Stock[] getTop10MaxLow() 
	{
		return getTop10Max(stockHeapAry[MAX_LOW], new CompareLow());
	}

	public Stock[] getTop10MinLow() {
		return getTop10Min(stockHeapAry[MIN_LOW], new CompareLow());
	}

	public Stock[] getTop10MaxOpen() {
		return getTop10Max(stockHeapAry[MAX_OPEN], new CompareOpen());
	}

	public Stock[] getTop10MinOpen() {
		return getTop10Min(stockHeapAry[MIN_OPEN], new CompareOpen());
	}

	public Stock[] getTop10MaxClose() {
		return getTop10Max(stockHeapAry[MAX_CLOSE], new CompareClose());
	}

	public Stock[] getTop10MinClose() {
		return getTop10Min(stockHeapAry[MIN_CLOSE], new CompareClose());
	}

	public Stock[] getTop10MaxMove200() {
		return getTop10Max(stockHeapAry[MAX_MOVE_200], new CompareMoveAvg200());
	}

	public Stock[] getTop10MinMove200() {
		return getTop10Min(stockHeapAry[MIN_MOVE_200], new CompareMoveAvg200());
	}

	public Stock[] getTop10MaxMove50() {
		return getTop10Max(stockHeapAry[MAX_MOVE_50], new CompareMoveAvg50());
	}

	public Stock[] getTop10MinMove50() {
		return getTop10Min(stockHeapAry[MIN_MOVE_50], new CompareMoveAvg50());
	}

	public Stock[] getTop10MaxAvgVolume() {
		return getTop10Max(stockHeapAry[MAX_AVG_VOLUME], new CompareAvgVolume());
	}

	public Stock[] getTop10MinAvgVolume() {
		return getTop10Min(stockHeapAry[MIN_AVG_VOLUME], new CompareAvgVolume());
	}
	
	public Stock[] getTop10MaxMarketCap() {
		return getTop10Max(stockHeapAry[MAX_MARKET_CAP], new CompareMarketCap());
	}

	public Stock[] getTop10MinMarketCap() {
		return getTop10Min(stockHeapAry[MIN_MARKET_CAP], new CompareMarketCap());
	}
	
	public Stock[] getTop10MaxRevenue() {
		return getTop10Max(stockHeapAry[MAX_REVENUE], new CompareRevenue());
	}

	public Stock[] getTop10MinRevenue() {
		return getTop10Min(stockHeapAry[MIN_REVENUE], new CompareRevenue());
	}
	
	public Stock[] getTop10MaxEpsCurrentY() {
		return getTop10Max(stockHeapAry[MAX_EPS_CURRENT_YEAR], new CompareCurrentEPS());
	}

	public Stock[] getTop10MinEpsCurrentY() {
		return getTop10Min(stockHeapAry[MIN_EPS_CURRENT_YEAR], new CompareCurrentEPS());
	}
	
	public Stock[] getTop10MaxEpsEstimateNextQ() {
		return getTop10Max(stockHeapAry[MAX_EPS_ESTIMATE_NEXT_Q], new CompareEpsNextQ());
	}

	public Stock[] getTop10MinEpsEstimateNextQ() {
		return getTop10Min(stockHeapAry[MIN_EPS_ESTIMATE_NEXT_Q], new CompareEpsNextQ());
	}
	
	public Stock[] getTop10MaxEpsEstimateNextY() {
		return getTop10Max(stockHeapAry[MAX_EPS_ESTIMATE_NEXT_Y], new CompareEpsNextY());
	}

	public Stock[] getTop10MinEpsEstimateNextY() {
		return getTop10Min(stockHeapAry[MIN_EPS_ESTIMATE_NEXT_Y], new CompareEpsNextY());
	}
	
	public Stock[] getTop10MaxOneYearPriceTarget() {
		return getTop10Max(stockHeapAry[MAX_ONE_YEAR_PRICE_TARGET], new CompareOneYearPriceTarget());
	}

	public Stock[] getTop10MinOneYearPriceTarget() {
		return getTop10Min(stockHeapAry[MIN_ONE_YEAR_PRICE_TARGET], new CompareOneYearPriceTarget());
	}
	
	public Stock[] getTop10MaxBookValue() {
		return getTop10Max(stockHeapAry[MAX_BOOK_VALUE], new CompareBookValue());
	}

	public Stock[] getTop10MinBookValue() {
		return getTop10Min(stockHeapAry[MIN_BOOK_VALUE], new CompareBookValue());
	}
	
	public Stock[] getTop10MaxPriceToBookValue() {
		return getTop10Max(stockHeapAry[MAX_PRICE_TO_BOOK_RATIO], new ComparePriceToBook());
	}

	public Stock[] getTop10MinPriceToBookValue() {
		return getTop10Min(stockHeapAry[MIN_PRICE_TO_BOOK_RATIO], new ComparePriceToBook());
	}
	
	//---------------------------------------------------
	// End of getTop10 methods
}
