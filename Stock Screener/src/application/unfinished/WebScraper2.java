package application.unfinished;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

public class WebScraper2 
{
	static ArrayList<Stock> watchList = new ArrayList<>(10);
	
	public static void readFromUser(PrintWriter outputStream, String ticker)
	{
		Stock current;
		
		try 
		{
			current = YahooFinance.get(ticker);
			watchList.add(current);
		} 
		catch (IOException e) 
		{
			System.out.println("Error: Bad Internet Connection...");
			e.printStackTrace();
		}
	}
	
	public static void fill()
	{	
		try 
		{
			watchList.add(YahooFinance.get("INTC"));
			watchList.add(YahooFinance.get("NVDA"));
			watchList.add(YahooFinance.get("MU"));
			watchList.add(YahooFinance.get("QCOM"));
			watchList.add(YahooFinance.get("TXN"));
			watchList.add(YahooFinance.get("MSFT"));
			watchList.add(YahooFinance.get("AAPL"));
			watchList.add(YahooFinance.get("IBM"));
			watchList.add(YahooFinance.get("RTN"));
	        watchList.add(YahooFinance.get("AMD"));
	        watchList.add(YahooFinance.get("YHOO"));
	        watchList.add(YahooFinance.get("BRK-B"));
	        watchList.add(YahooFinance.get("F"));
	        watchList.add(YahooFinance.get("FCAU"));
	        watchList.add(YahooFinance.get("T"));
	        watchList.add(YahooFinance.get("TXN"));
	        watchList.add(YahooFinance.get("CLX"));
	        watchList.add(YahooFinance.get("DUK"));
		} 
		catch (IOException e) 
		{
			System.out.println("Error: Bad Internet Connection...");
			e.printStackTrace();
		}
	}
	public static void printWatchList()
	{
		for(Stock company : watchList)
		{
			company.print();
			System.out.println(" ");
		}
	}
	public static void flush()
	{
		PrintWriter outputStream = null;
		
		try
        {
                outputStream = new PrintWriter(new FileOutputStream("StockData.txt"));
        }
        catch(IOException e)
        {
                System.out.println("Output/Input Stream could not be established.");
                e.printStackTrace();
        }

        for(Stock company : watchList)
        {
            StockQuote current = company.getQuote();
            StockStats stat = company.getStats();

            outputStream.println(company.getName()); //Company name
            outputStream.println(current.getSymbol()); //Company ticker symbol.
            outputStream.println(current.getYearHigh().doubleValue());
            outputStream.println(current.getYearLow().doubleValue());
            outputStream.println(current.getOpen().doubleValue());
            outputStream.println(current.getPreviousClose().doubleValue());
            outputStream.println(current.getPriceAvg200().doubleValue());
            outputStream.println(current.getPriceAvg50().doubleValue());
            outputStream.println(current.getVolume().intValue());
            outputStream.println(stat.getMarketCap().doubleValue()); // Market Cap
            outputStream.println(stat.getRevenue().doubleValue()); //Last quarters revenue.
        } 
        
        System.out.println("\nAll stock data has been written to the file.\n\n");
        outputStream.close();
	}
}
