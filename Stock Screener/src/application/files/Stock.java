package application.files;

import java.util.Random;
import java.util.Scanner;
import yahoofinance.YahooFinance;

@SuppressWarnings({ "rawtypes", "unused" })
public class Stock implements Comparable
{
	//Objects:
	Random rand = new Random();
	Scanner keyboard = new Scanner(System.in);
	
	//INSTANCE VARIABLES:
	private String name; //Company Name.
	private String ticker; //Ticker symbol.
	private double high; //52 week high.
	private double low; //52 week low.
	private double open; //Days opening price.
	private double close; //Days closing price.
	private double move200; //200 day moving average.
	private double move50; //50 day moving average.
	private int avgVolume; //Days average trade volume.
	
	//INSTANCE VARIABLES (EXPANSION 1):
	private double marketCap; //Company market valuation.
	private double revenue; //Company's revenue for the last quarter.
	private double epsCurrentYear; //Current year earnings per share.
	private double epsEstimateNextQ; //Next quarters estimate for earnings per share. 
	private double epsEstimateNextY; //Next years estimate for earnings per share.
	private double oneYearPriceTarget; //One year price target estimate.
	private double bookValue; //Company's book value.
	private double priceToBook; //Company's price to book ratio.
	
	//Constructors:
	/**
	 * Default constructor, sets all data to good random values.
	 * @throws StockException, wont actually be thrown, but keeps compiler happy.
	 */
	public Stock()
	{
		try
		{
			randomFill();
		}
		catch(StockException e)
		{
			System.out.println("Bad data, shutting down program...");
			System.exit(0);
		}
	}
	/**
	 * Full constructor, sets all the data to the given values.
	 * @param name, the name of the company.
	 * @param ticker, stocks symbol.
	 * @param high, 52 week high.
	 * @param low, 52 week low.
	 * @param open, opening price.
	 * @param close, closing price.
	 * @param move200, 200 day moving average.
	 * @param move50, 50 day moving average.
	 * @param volume, daily trade volume.
	 * @param marketCap, the value of the company on the stock market.
	 * @param epsCurrentY, the earnings per share for the current year.
	 * @param epsEstimateNextQ, the earnings per share estimate for next quarter.
	 * @param epsEstimateNextY, the earnings per share estimate for the next year.
	 * @param oneYearPT, Stock prices one year price target.
	 * @param bookValue, Company book valuation.
	 * @param priceToBook, Company's current book value to their current price.
	 * @throws StockException, all data must be valid.
	 */
	public Stock(String name, String ticker, double high, double low, 
					double open, double close, double move200, double move50,
					int volume, double marketCap, double revenue, double epsCurrentY,
					double epsEstimateNextQ, double epsEstimateNextY, double oneYearPT,
					double bookValue, double priceToBook)
	{
		try
		{
			setName(name);
			setTicker(ticker);
			setHigh(high);
			setLow(low);
			setOpen(open);
			setClose(close);
			setMoveAvg200(move200);
			setMoveAvg50(move50);
			setAvgVolume(volume);
			setMarketCap(marketCap);
			setRevenue(revenue);
			setEpsCurrentY(epsCurrentY);
			setEpsEstimateNextQ(epsEstimateNextQ);
			setEpsEstimateNextY(epsEstimateNextY);
			setOneYearPriceTarget(oneYearPT);
			setBookValue(bookValue);
			setPriceToBookValue(priceToBook);
		}
		catch(StockException e)
		{
			System.out.println("Bad data, shutting down program...");
			System.exit(0);
		}
	}
	
	/**
	 * Sets the price to book ratio for a stock object.
	 * @param priceToBook, the price to book ratio.
	 */
	public void setPriceToBookValue(double priceToBook)
	{
		this.priceToBook = priceToBook;
	}
	/**
	 * Sets the company's book value for a stock object.
	 * @param bookValue, the companies estimated value based on certain assets.
	 * @throws StockExcpetion, company value must be >= 0.
	 */
	public void setBookValue(double bookValue) throws StockException
	{
		if(bookValue >= 0)
		{
			this.bookValue = bookValue;
		}
		else
		{
			throw new StockException("Value must be greater than or equal to zero!");
		}
	}
	/**
	 * Sets the one year price target for a stock object.
	 * @param target, the price goal for one years time.
	 * @throws StockExcpetion, company price target must be >= 0.
	 */
	public void setOneYearPriceTarget(double target) throws StockException
	{
		if(target >= 0)
		{
			this.oneYearPriceTarget = target;
		}
		else
		{
			throw new StockException("Price target must be greater than or equal to zero!");
		}
	}
	/**
	 * Sets next years earnings per share for a stock object.
	 * @param estimate, earnings per share.
	 */
	public void setEpsEstimateNextY(double estimate) 
	{
		this.epsEstimateNextY = estimate;
	}
	/**
	 * Sets next quarters earnings per share for a stock object.
	 * @param estimate, earnings per share.
	 */
	public void setEpsEstimateNextQ(double estimate) 
	{
		this.epsEstimateNextQ = estimate;
	}
	/**
	 * Sets the current earnings per share for the last reported year.
	 * @param current, earnings per share.
	 */
	public void setEpsCurrentY(double current) 
	{
		this.epsCurrentYear = current;
	}
	/**
	 * Records the name of the company, not to be confused with the ticker symbol.
	 * @param name, the name of the company.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * Records the ticker symbol for the stock as a string.
	 * @param symbol, the stocks ticker symbol.
	 * @throws StockException, symbol can not be more than 5 chars. 
	 */
	public void setTicker(String symbol) throws StockException
	{
		if(symbol.length() < 6)
		{
			this.ticker = symbol;
		}
		else
		{
			throw new StockException("Ticker Symbol must be less than 6 characters!");
		}
	}
	/**
	 * Records the 52 week high for the stock.
	 * @param value, the stocks 52 week high.
	 * @throws StockException, value must be >= zero. 
	 */
	public void setHigh(double value) throws StockException
	{
		if(value >= 0)
		{
			this.high = value;
		}
		else
		{
			throw new StockException("Value must be greater than or equal to zero!");
		}
	}
	/**
	 * Records the 52 week low for the stock.
	 * @param value, the stocks 52 week low.
	 * @throws StockException, value must be >= zero. 
	 */
	public void setLow(double value) throws StockException
	{
		if(value >= 0)
		{
			this.low = value;
		}
		else
		{
			throw new StockException("Value must be greater than or equal to zero!");
		}
	}
	/**
	 * Records the days opening price for the stock.
	 * @param value, todays opening price.
	 * @throws StockException, value must be >= zero. 
	 */
	public void setOpen(double value) throws StockException
	{
		if(value >= 0)
		{
			this.open = value;
		}
		else
		{
			throw new StockException("Value must be greater than or equal to zero!");
		}
	}
	/**
	 * Records the days closing price for the stock.
	 * @param value, todays closing price.
	 * @throws StockException, value must be >= zero. 
	 */
	public void setClose(double value) throws StockException
	{
		if(value >= 0)
		{
			this.close = value;
		}
		else
		{
			throw new StockException("Value must be greater than or equal to zero!");
		}
	}
	/**
	 * Records the stocks 200 day moving average.
	 * @param value, the stocks current 200 day moving average.
	 * @throws StockException, must be greater than zero.
	 */
	public void setMoveAvg200(double value) throws StockException
	{
		if(value > 0)
		{
			this.move200 = value;
		}
		else
		{
			System.out.println("If a company in the watchlist as recently been"
					+ "\ndisolved or consumed by another corperation, it could result"
					+ "\nin this error being thrown...");
			throw new StockException("Value must be greater than zero!");
		}
	}
	/**
	 * Records the stocks 50 day moving average.
	 * @param value, the stocks current 50 day moving average.
	 * @throws StockException, must be greater than zero. 
	 */
	public void setMoveAvg50(double value) throws StockException
	{
		if(value > 0)
		{
			this.move50 = value;
		}
		else
		{
			System.out.println("If a company in the watchlist as recently been"
					+ "\ndisolved or consumed by another corperation, it could result"
					+ "\nin this error being thrown...");
			throw new StockException("Value must be greater than zero!");
		}
	}
	/**
	 * Records the days trading volume.
	 * @param num, the days trading volume.
	 * @throws StockException, value must be >= zero.
	 */
	public void setAvgVolume(int num) throws StockException
	{
		if(num >= 0)
		{
			this.avgVolume = num;
		}
		else
		{
			throw new StockException("Number must be greater or equal to zero!");
		}
	}
	/**
	 * Records the company's market capital.
	 * @param value, the market capital or money the company is worth.
	 * @throws StockException, value must be >= zero.
	 */
	public void setMarketCap(double value) throws StockException
	{
		if(value >= 0)
		{
			this.marketCap = value;
		}
		else
		{
			throw new StockException("Number must be greater or equal to zero!");
		}
	}
	/**
	 * Records the company's revenue for the quarter.
	 * This is not the company's profits.
	 * @param value, the total revenue for the company for the quarter.
	 */
	public void setRevenue(double value)
	{
		this.revenue = value;
	}
	
	//GETTERS:
	/**
	 * Returns the price to book ratio for a stock object.
	 * @return priceToBook instance variable.
	 */
	public double getPriceToBookValue()
	{
		return this.priceToBook;
	}
	/**
	 * Returns the book value for a stock object.
	 * @return bookValue instance variable.
	 */
	public double getBookValue()
	{
		return this.bookValue;
	}
	/**
	 * Returns the one year price target for a stock object.
	 * @return oneYearPriceTarget instance variable.
	 */
	public double getOneYearPriceTarget()
	{
		return this.oneYearPriceTarget;
	}
	/**
	 * Returns the earnings per share estimate for next year.
	 * @return epsEstimateNextY instance variable.
	 */
	public double getEpsEstimateNextY()
	{
		return this.epsEstimateNextY;
	}
	/**
	 * Returns the earnings per share estimate for the next quarter.
	 * @return epsEstimateNextQ instance variable.
	 */
	public double getEpsEstimateNextQ()
	{
		return this.epsEstimateNextQ;
	}
	/**
	 * Returns the current earnings per share for the this year.
	 * @return epsCurrentYear instance variable.
	 */
	public double getEpsCurrentY()
	{
		return this.epsCurrentYear;
	}
	/**
	 * Returns the company's name.
	 * @return name instance variable.
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 * Returns the stocks ticker symbol.
	 * @return ticker instance variable.
	 */
	public String getTicker()
	{
		return this.ticker;
	}
	/**
	 * Returns the stocks 52 week high.
	 * @return high instance variable.
	 */
	public double getHigh()
	{
		return this.high;
	}
	/**
	 * Returns the stocks 52 week low.
	 * @return low instance variable.
	 */
	public double getLow()
	{
		return this.low;
	}
	/**
	 * Returns todays opening price for the stock.
	 * @return open instance variable.
	 */
	public double getOpen()
	{
		return this.open;
	}
	/**
	 * Returns todays closing price for the stock.
	 * @return close instance variable.
	 */
	public double getClose()
	{
		return this.close;
	}
	/**
	 * Returns the stocks current 200 day moving average.
	 * @return move200 instance variable.
	 */
	public double getMoveAvg200()
	{
		return this.move200;
	}
	/**
	 * Returns the stocks current 50 day moving average.
	 * @return move50 instance variable.
	 */
	public double getMoveAvg50()
	{
		return this.move50;
	}
	/**
	 * Returns todays current trading volume.
	 * @return avgVolume instance variable.
	 */
	public int getAvgVolume()
	{
		return this.avgVolume;
	}
	/**
	 * Returns the stocks current market capital.
	 * This is generally a good estimate of how much money the company is worth.
	 * @return marketCap instance variable.
	 */
	public double getMarketCap()
	{
		return this.marketCap;
	}
	/**
	 * Returns the stocks current revenue for the quarter.
	 * @return revenue instance variable.
	 */
	public double getRevenue()
	{
		return this.revenue;
	}
	
	//OTHER METHODS:
	/**
	 * Prints a String representation of all the instance variables.
	 * @return all instance variables.
	 */
	public String toString()
	{
		return "Company: " + this.getName() + "\nTicker Symbol: " + this.getTicker() + "\n52 Week High: " + this.getHigh() +
				"\n52 Week Low: " + this.getLow() + "\nToday's Open: " + this.getOpen() +
				"\nToday's Close: " + this.getClose() + "\n200 Day Moving Average: " + this.getMoveAvg200() +
				"\n50 Day Moving Average: " + this.getMoveAvg50() + "\nAverage Volume: " + this.getAvgVolume() + 
				"\nMarket Cap: " + this.getMarketCap() + "\nQuarter Revenue: " + this.getRevenue() + 
				"\nEPS Current Year: " + this.getEpsCurrentY() + "\nEPS Estimate for next Quarter: " + this.getEpsEstimateNextQ() + 
				"\nEPS Estimate for next Year: " + this.getEpsEstimateNextY() + "\nOne year price target: " + this.getOneYearPriceTarget() +
				"\nBook Value: " + this.getBookValue() + "\nPrice to Book Ratio: " + this.getPriceToBookValue();
	}
	/**
	 * Equals method that checks for deep copies of stock objects.
	 * @return true if the object is a deep copy, false otherwise.
	 */
	public boolean equals(Stock other)
	{
		if(this.name.equalsIgnoreCase(other.getName()) &&
				this.ticker.equalsIgnoreCase(other.getTicker()) &&
				this.high == other.getHigh() &&
				this.low == other.getLow() &&
				this.open == other.getOpen() &&
				this.close == other.getClose() &&
				this.move200 == other.getMoveAvg200() &&
				this.move50 == other.getMoveAvg50() &&
				this.avgVolume == other.getAvgVolume() &&
				this.marketCap == other.getMarketCap() &&
				this.revenue == other.getRevenue() &&
				this.epsCurrentYear == other.getEpsCurrentY() &&
				this.epsEstimateNextQ == other.getEpsEstimateNextQ() &&
				this.epsEstimateNextY == other.getEpsEstimateNextY() &&
				this.oneYearPriceTarget == other.getOneYearPriceTarget() &&
				this.bookValue == other.getBookValue() &&
				this.priceToBook == other.getPriceToBookValue())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * Creates random values for each instance variable except the
	 * ticker symbol, good for testing.
	 * @throws StockException 
	 */
	public void randomFill() throws StockException
	{
		setName("RAND Company Inc.");
		setTicker("RAND");
		setLow((rand.nextInt(697) + 1));
		setHigh((rand.nextInt(50) + 1) + this.getLow());
		setOpen((rand.nextInt(697) + 1));
		setClose((rand.nextInt(697) + 1));
		setMoveAvg200((rand.nextInt(697) + 1));
		setMoveAvg50((rand.nextInt(697) + 1));
		setAvgVolume((rand.nextInt(6934567) + 1));
		setMarketCap((rand.nextInt(6934567) + 1));
		setRevenue((rand.nextInt(6934567) + 1));
		setEpsCurrentY((rand.nextInt(50) + 1));
		setEpsEstimateNextQ((rand.nextInt(50) + 1));
		setEpsEstimateNextY((rand.nextInt(50) + 1));
		setOneYearPriceTarget((rand.nextInt(697) + 1) + this.getClose());
		setBookValue((rand.nextInt(50) + 1));
		setPriceToBookValue(this.getBookValue() / this.getClose());
	}
	/**
	 * Default compareTo() that calls Strings compareTo method.
	 * All other number comparisons should be done with a comparator.
	 * @param otherStock, the objected being compared to.
	 * @return -1 if the other stock is larger.
	 * 			0 if the other stock is equal.
	 * 			1 if the other stock is smaller.
	 */
	@Override
	public int compareTo(Object otherStock) 
	{
		return this.getTicker().compareTo(((Stock)otherStock).getTicker());
	}
}