package application.files;

public class Tester {

	public static void main(String[] args) throws StockException {
		//Object
		Parser parser = new Parser();
		//Print all stock
		for(int i =0;i<10;i++)
		{
			parser.printStock("RAND"+i);
			System.out.println();
		}
		//Test for all get method excluding expansion 1
		System.out.println("**********************");
		System.out.println("MaxHigh:" + parser.getMaxHigh().getHigh());
		System.out.println("MinHigh:" + parser.getMinHigh().getHigh());
		System.out.println("MaxLow:" + parser.getMaxLow().getLow());
		System.out.println("MinLow:" + parser.getMinLow().getLow());
		System.out.println("MaxOpen:" + parser.getMaxOpen().getOpen());
		System.out.println("MinOpen:" + parser.getMinOpen().getOpen());
		System.out.println("MaxClose:" + parser.getMaxClose().getClose());
		System.out.println("MinClose:" + parser.getMinClose().getClose());
		System.out.println("MaxMove200:" + parser.getMaxMove200().getMoveAvg200());
		System.out.println("MinMove200:" + parser.getMinMove200().getMoveAvg200());
		System.out.println("Maxmove50:" + parser.getMaxMove50().getMoveAvg50());
		System.out.println("MinMove50:" + parser.getMinMove50().getMoveAvg50());
		System.out.println("MaxAvg:" + parser.getMaxAvgVolume().getAvgVolume());
		System.out.println("MinAvg:" + parser.getMinAvgVolume().getAvgVolume());
		System.out.println("**********************");
		for(Stock s: parser.getTop10MaxHigh())
		{
			System.out.print(s.getHigh() + " ");
		}
		System.out.println();
		
		parser.getTop10MaxHigh();
		
		
	}

}
