package application.files;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

//Output all stock data to a text file, data must be stored as primitives or strings.
// Version 0.1

@SuppressWarnings("unused")
public class Webscraper 
{
    protected static ArrayList<Stock> watchList = new ArrayList<>(10);
    protected static PrintWriter outputStream = null;
    protected static Scanner keyboard = new Scanner(System.in);
    
	public static void main(String[] args) throws IOException
	{
		//Reading all the ticker symbols from the watch list text file.
		try 
		{
			Scanner input = new Scanner(new FileInputStream("watchList.txt"));
			
			while (input.hasNextLine()) 
			{
				String temp;
				
				try
				{
					temp = input.nextLine();
					watchList.add(YahooFinance.get(temp.toUpperCase()));
				}
				catch (IOException e)
				{
					System.out.println("Ticker Symbol could not be found...");
					System.exit(0);
				}
			}
			input.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			System.out.println("\nLoading Defualt values...");
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
	        watchList.add(YahooFinance.get("BRK-B"));
	        watchList.add(YahooFinance.get("F"));
	        watchList.add(YahooFinance.get("FCAU"));
	        watchList.add(YahooFinance.get("T"));
	        watchList.add(YahooFinance.get("TXN"));
	        watchList.add(YahooFinance.get("CLX"));
	        watchList.add(YahooFinance.get("DUK"));
		}
		
		//Printing the watchList to the console.
		for(Stock company : watchList)
		{
			company.print();
			System.out.println(" ");
		}
         
		//Updating the stockData.txt with the current watchList.
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
                 if(current.getYearHigh() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(current.getYearHigh().doubleValue());
                 }
                 if(current.getYearLow() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(current.getYearLow().doubleValue());
                 }
                 if(current.getOpen() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(current.getOpen().doubleValue());
                 }
                 if(current.getPreviousClose() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(current.getPreviousClose().doubleValue());
                 }
                 if(current.getPriceAvg200() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(current.getPriceAvg200().doubleValue());
                 }
                 if(current.getPriceAvg50() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(current.getPriceAvg50().doubleValue());
                 }
                 if(current.getVolume() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(current.getVolume().intValue());
                 }
                 if(stat.getMarketCap() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(stat.getMarketCap().doubleValue()); // Market Cap
                 }
                 if(stat.getRevenue() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(stat.getRevenue().doubleValue()); //Last quarters revenue.
                 }
                 if(stat.getEps() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(stat.getEps().doubleValue());
                 }
                 if(stat.getEpsEstimateNextQuarter() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(stat.getEpsEstimateNextQuarter().doubleValue());
                 }
                 if(stat.getEpsEstimateNextYear() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(stat.getEpsEstimateNextYear().doubleValue());
                 }
                 if(stat.getOneYearTargetPrice() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(stat.getOneYearTargetPrice().doubleValue());
                 }
                 if(stat.getBookValuePerShare() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(stat.getBookValuePerShare().doubleValue());
                 }
                 if(stat.getPriceBook() == null)
                 {
                	 outputStream.println(0);
                 }
                 else
                 {
                	 outputStream.println(stat.getPriceBook().doubleValue()); 
                 }
         } 
		
		System.out.println("\nAll stock data has been written to the file.\n\n");
		outputStream.close();
		keyboard.close();
		
		/*
		//how to get individual stock information.
		StockStats amdStat = amd.getStats();
		double shortRatio = amdStat.getShortRatio().doubleValue(); //Converting a BigDecimal to a primitive.
		
		System.out.println("------------------------------");
		System.out.println(amd.getName());
		System.out.println("Ticker Symbol: " + amdStat.getSymbol());
		System.out.println("Market Cap: " + amdStat.getMarketCap());
		System.out.println("------------------------------");
		//Most of these values are stored as BigDecimals, but can be converted with .doubleValue()
		System.out.println("One Year Price Target: " + amdStat.getOneYearTargetPrice());
		System.out.println("EPS: " + amdStat.getEps());
		System.out.println("EPS Current Year: " + amdStat.getEpsEstimateCurrentYear());
		System.out.println("EPS Next Quarters Estimate: " + amdStat.getEpsEstimateNextQuarter());
		System.out.println("EPS Next Years Estimate: " + amdStat.getEpsEstimateNextYear());
		System.out.println("Book Value: " + amdStat.getBookValuePerShare());
		System.out.println("Price to Book: " + amdStat.getPriceBook());
		System.out.println("Short Ratio: " + (shortRatio * 100) + "%"); //just testing the doubleValue() method.
		System.out.println("------------------------------");
		*/
	}
	
	public static void add(String stock)
	{
		//Doesn't check if the stock is already on the watch list. 
		try
        {
                outputStream = new PrintWriter(new FileOutputStream("watchList.txt", true));
        }
        catch(IOException e)
        {
                System.out.println("Output/Input Stream could not be established.");
                e.printStackTrace();
        }
		
		outputStream.println(stock);
		outputStream.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Lowell is using for the add button on the watch list tab.
	
        protected static void saveStockData()
        {
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
                if(current.getYearHigh() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(current.getYearHigh().doubleValue());
                }
                if(current.getYearLow() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(current.getYearLow().doubleValue());
                }
                if(current.getOpen() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(current.getOpen().doubleValue());
                }
                if(current.getPreviousClose() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(current.getPreviousClose().doubleValue());
                }
                if(current.getPriceAvg200() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(current.getPriceAvg200().doubleValue());
                }
                if(current.getPriceAvg50() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(current.getPriceAvg50().doubleValue());
                }
                if(current.getVolume() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(current.getVolume().intValue());
                }
                if(stat.getMarketCap() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(stat.getMarketCap().doubleValue()); // Market Cap
                }
                if(stat.getRevenue() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(stat.getRevenue().doubleValue()); //Last quarters revenue.
                }
                if(stat.getEps() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(stat.getEps().doubleValue());
                }
                if(stat.getEpsEstimateNextQuarter() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(stat.getEpsEstimateNextQuarter().doubleValue());
                }
                if(stat.getEpsEstimateNextYear() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(stat.getEpsEstimateNextYear().doubleValue());
                }
                if(stat.getOneYearTargetPrice() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(stat.getOneYearTargetPrice().doubleValue());
                }
                if(stat.getBookValuePerShare() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(stat.getBookValuePerShare().doubleValue());
                }
                if(stat.getPriceBook() == null)
                {
               	 outputStream.println(0);
                }
                else
                {
               	 outputStream.println(stat.getPriceBook().doubleValue()); 
                }
            } 
            
            System.out.println("\nAll stock data has been written to the file.\n\n");
            outputStream.close(); 
        } 
        
}