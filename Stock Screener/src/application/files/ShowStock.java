/*
 * AUTHOR: All
 */
package application.files;

/*******************************************************************************
 * CLASS DESCRIPTION: This is the ShowStock class. It provides the necessary 
 * methods required for the GUI to functionally operate with the parser. 
 * extends Parser class
 ******************************************************************************/

// *** IMPORTS ***
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class ShowStock extends Parser  
{
    // *** OBJECTS ***
    
    //protected static Parser parser = new Parser();

    // *** VARIABLES ***
    Stock high;
    Stock low;
    Stock chartHigh;
    Stock chartLow;
    String topTen;
    
    // *** ARRAY ***
    String[] options = {"52 Week High", "52 Week Low", "Highest Openings", "Highest Closings", "200 Day Price Average", 
        "50 Day Price Average", "Days Trade Volume"};

    /**
     * Full constructor
     * @param heapName 
     */
    public ShowStock(int heapName) {       
        //parser = new Parser();
        
        setHighStock(heapName);
        setLowStock(++heapName);
        setTopTen(heapName);      
    }
    
    // *** SETTERS ***
    /**
     * Sets the high Stock variable
     * @param heapName 
     */
    protected void setHighStock(int heapName) {
        this.high = getWinner(heapName);
    }
    
    /**
     * Sets the low Stock variable
     * @param heapName 
     */
    protected void setLowStock(int heapName) {
        this.low = getWinner(heapName);
    }
    
    /**
     * Sets the top 10 stocks of the index provided in the arguement to the 
     * topTen Stock[] object.
     * void method
     * @pre Preconditions expects the index of the heap needed. 
     * @return void method  
     */
    protected void setTopTen(int heapName) {
        StringBuilder sb = new StringBuilder(); 
        switch(heapName) {
        
            case 0: 
            case 1:{
                sb.append("Max high: ");
                for(Stock s: getTop10MaxHigh()) {
                    sb.append(" " + s.getTicker() + ": " + s.getHigh() + " ");
                }                
                 break;
            }
            case 2: 
            case 3: {
                sb.append("Max low: ");
                for(Stock s: getTop10MaxLow()) {
                    sb.append(" " + s.getTicker() +  ": " + s.getLow() + "   ");
                }
                break;
            }
            case 4: 
            case 5: {
                sb.append("Max open ");
                for(Stock s: getTop10MaxOpen()) {
                    sb.append(s.getTicker() +  ": " + s.getOpen() + "   ");
                }
                break;
            }
            case 6: 
            case 7: {
                sb.append("Max close: ");
                for(Stock s: getTop10MaxClose()) {
                    sb.append( s.getTicker() +  ": " + s.getClose() + "   ");
                } 
                break;
            }
            case 8: 
            case 9: {
                sb.append("Max avg 200: ");
                for(Stock s: getTop10MaxMove200()) {
                    sb.append( s.getTicker() +  ": " + s.getMoveAvg200() + "   ");
                } 
                break;
            }
            case 10: 
            case 11: {
                sb.append("Max avg 50: ");
                for(Stock s: getTop10MaxMove50()) {
                    sb.append( s.getTicker() +  ": " + s.getMoveAvg50() + "   ");
                }
                break;
            }
            case 12: 
            default: {
                sb.append("Max avg Vol: ");
                for(Stock s: getTop10MaxAvgVolume()) {
                    sb.append(s.getTicker() +  ": " + String.format("%,d", s.getAvgVolume()) + "   ");
                } 
                break;
            }                   
        }
        this.topTen = sb.toString();
    }
    
    // *** GETTERS ***
    
    /**
     * Returns the high Stock object
     * @param heapName
     * @return Returns a Stock object
     */
    protected Stock getHighStock() { 
        return this.high;        
    }
    
    
    /**
     * Returns the low Stock object
     * @param heapName
     * @return Returns a stock object
     */
    protected Stock getLowStock() {      
        return this.low;        
    }
    
    /**
     * Returns a string representation of the top 10 stocks for the desired
     * heap provided in the argument. 
     * @param heapName: int The heap element  
     * @return 
     */
    protected String getTopTen() { 
         return topTen;       
    }
    
    /**
     * Returns the High XYChart.Data for the selected chart provided in the 
     * argument
     * @param heapName: int
     * @return XYChartData
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected Data getHighChart(int heapName) {
        Data data = new Data();
        switch(heapName) {
            
            case 0: {
                data =  new XYChart.Data(high.getTicker(), high.getHigh());
                break;
            }
            case 2: {
                data =  new XYChart.Data(low.getTicker(), low.getHigh());
                break;
            }
            case 4: {
                data =  new XYChart.Data(high.getTicker(), high.getOpen());
                break;
            }
            case 6: {
                data =  new XYChart.Data(high.getTicker(), high.getClose());
                break;
            }
            case 8: {
                data =  new XYChart.Data(high.getTicker(), high.getMoveAvg200());
                break;
            }
            case 10: {
                data =  new XYChart.Data(high.getTicker(), high.getMoveAvg50());
                break;
            }
            default:
            case 12: {
                data =  new XYChart.Data(high.getTicker(), high.getAvgVolume());
                break;
            } 
        }
        return data;
    }
    
    /**
     * Returns the Low XYChart.Data for the selected chart provided in the 
     * argument
     * @param heapName: int
     * @return XYChartData
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected Data getLowChart(int heapName) {
        Data data = new Data();
        switch(heapName) {

            case 0: {
                data = new XYChart.Data(low.getTicker(), low.getLow());
                break;
            }
            case 2: {
                data = new XYChart.Data(high.getTicker(), high.getHigh());
                break;
            }
            case 4: {
                data = new XYChart.Data(low.getTicker(), low.getOpen());
                break;
            }
            case 6: {
                data = new XYChart.Data(low.getTicker(), low.getClose());
                break;
            }
            case 8: {
                data = new XYChart.Data(low.getTicker(), low.getMoveAvg200());
                break;
            }
            case 10: {
                data = new XYChart.Data(low.getTicker(), low.getMoveAvg50());
                break;
            }
            default:
            case 12: {
                data = new XYChart.Data(low.getTicker(), low.getAvgVolume());
                break;
            }
        }
        return data;
    }
}
