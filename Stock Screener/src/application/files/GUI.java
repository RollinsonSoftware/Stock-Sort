/*
 * AUTHORS: Jack Rollinson
 */

package application.files;

/*** IMPORTS ***/
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import yahoofinance.YahooFinance;

/*******************************************************************************
 * CLASS DESCRIPTION: Displays the functionality of the stock program.
 *  
 ******************************************************************************/

/**
 * Start method: Starts the Stock GUI
 * @param <T>
 */
@SuppressWarnings("unused")
public class GUI<E> extends Application {

    // *** OBJECTS ***
    @SuppressWarnings("rawtypes")
	ComboBox menuDropDown = new ComboBox<>();
    
//    NumberFormat formatThis; 
    // initialize ShowStock to index 0
    ShowStock show = new ShowStock(0);
    
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    // *** START METHOD ***
    /**
     * Sets the Stage for the GUI 
     * @param primaryStage 
     */
    @SuppressWarnings("unchecked")
	@Override 
    public void start(Stage primaryStage) {
        
        
        BorderPane mainPane = new BorderPane();
        BorderPane southPane = new BorderPane();
        /** The Combo Box Pane and settings (aka Drop Down Menu Pane) */
        HBox comboBoxPane = new HBox();
        comboBoxPane.setPadding(new Insets(3,5,3,5));
        comboBoxPane.setSpacing(10);
        comboBoxPane.setStyle("-fx-background-color: #336699;");
        /** Label */
        Label cbpLabel = new Label("Choose an Option: ");
        cbpLabel.setFont(Font.font("Veranda", FontWeight.BOLD, 15));
        cbpLabel.setTextFill(Color.ALICEBLUE);
        /** Left Field Ticker Symbol entries */
        Label leftLabel = new Label("Left Label");
        VBox tickerBoxPane = new VBox();
        comboBoxPane.setPadding(new Insets(3,5,3,5));
        comboBoxPane.setSpacing(10);
        comboBoxPane.setStyle("-fx-background-color: #336699;");
        /** The top ten stocks for the item selected in drop down */
        TextField topTenField = new TextField(); 
        topTenField.setStyle("-fx-background-color: #336699; -fx-text-fill: #FFFFFF;");
        
        
            
        /** Add the comboBox/DropDown menu in the top pane */
        comboBoxPane.getChildren().addAll(cbpLabel, menuDropDown);
        
        /** Add the ticker entry fields to the left field */
        Label tickLbl = new Label("Enter Ticker Symbol");
        TextField tickEntry = new TextField();
        Button tickEntryButton = new Button("get data");
        tickerBoxPane.getChildren().addAll(tickLbl, tickEntry, tickEntryButton);
        //mainPane.setLeft(tickerEntryBox());
        mainPane.setTop(comboBoxPane);
        
        /** Key to the operation of the ComboBox(AKA menuDropDown) */
        ObservableList<String> items = FXCollections.observableArrayList(show.options);
        menuDropDown.getItems().addAll(items);

        // Using replacing one time use anonymous inner class with lambda 
        menuDropDown.setOnAction(e -> 
        {
                BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

                // Set drop down menu item to ShowStock Constructor
                show = new ShowStock(items.indexOf(menuDropDown.getValue()) * 2 );
                
                // Left and right bar charts
                @SuppressWarnings("rawtypes")
				XYChart.Series series1 = new XYChart.Series();
                @SuppressWarnings("rawtypes")
				XYChart.Series series2 = new XYChart.Series();
                
                // chart names
                series1.setName(show.getHighStock().getTicker());
                series2.setName(show.getLowStock().getTicker());
                
                // Chart data. Bar Chart Labels here
                series1.getData().add(
                        show.getHighChart(items.indexOf(menuDropDown.getValue()) * 2));
                series2.getData().add(
                        show.getLowChart(items.indexOf(menuDropDown.getValue()) * 2));
        
                // Set the data to the BarChart Object
                bc.getData().setAll(series1, series2);
                
                // Set bar chart width and gap
                bc.setBarGap(5);
                bc.setCategoryGap(10);
                 
                // Set bar chart data to center of mainPane
                mainPane.setCenter(bc);
                
                // Set data in left and right stock data boxes
                VBox leftBox = getStockBox(show.high, "Best: ");
                southPane.setLeft(leftBox);
                VBox rightBox = getStockBox(show.low, "Worst: ");
                southPane.setRight(rightBox);
                
                // Set Sorted Top Ten data in Bottom Pane 
                topTenField.setText(show.getTopTen());
                
        });   
        
        
        // These must be after the ComboOptionsAction() class
        // Sets the listener for the menuDropDown
        //menuDropDown.setOnAction(new ComboOptionsAction());
        menuDropDown.setValue("CATEGORIES");
        
        /** Add the bottom field to the southBottomPane */
        southPane.setBottom(topTenField);        
        mainPane.setBottom(southPane);
        
        
        // *** SET the scene to the stage and display ***
        Scene scene = new Scene(mainPane, 1200, 800);
        primaryStage.setTitle("RT-Screener Alpha 0.1"); 
        primaryStage.setScene(scene); 
        primaryStage.show();
    }
    
    /**
     * Returns a Vertical box of 10 Ticker Entry fields
     * @return a Vertical box of 10 Ticker Entry fields
     */
    /*
    private VBox tickerEntryBox() {
        
        VBox vbox = new VBox();
        for(int i = 0; i < 10; i++) {
            vbox.getChildren().add(tickerEntry(i));
        }
        return vbox;
    }
    */
    /**
     * Helper Method, Populates the ticker entry box with 
     * the ticker entry field and the getData button.
     * @return Horizontal box with the ticker entry field and
     * getData button as a row. 
     */
    private HBox tickerEntry(int index) {
        /** Add the ticker entry fields to the left field */
        Button tickEntryButton = new Button("get data");
        TextField tickEntry = new TextField();
        HBox hb = new HBox();
        Label l = new Label("Enter Ticker Symbol");
        /** set styles */
        l.setPrefWidth(120);
        tickEntry.setPrefWidth(100);
        // insets are top, left, bottom, right
        l.setPadding(new Insets(3,0,3,0));
        l.setFont(Font.font("Veranda", FontWeight.BOLD, 12));
        tickEntry.setPrefWidth(120);
        hb.setSpacing(10);
        // insets are top, left, bottom, right
        hb.setPadding(new Insets(8,4,0,4));
        
        hb.getChildren().addAll(l, tickEntry, tickEntryButton);
        tickEntryButton.setOnAction(e ->
        {
            try {
            tickEntry.getText();
            yahoofinance.Stock symbl = YahooFinance.get(tickEntry.getText());
            Webscraper.watchList.add(symbl);
            } catch (IOException g) {
                g.getStackTrace();
            }
            
            Webscraper.saveStockData();            
        });
        
        return hb;
    }
    
    

    /**
     * Returns a Vertical box populated with stock data
     * Dependencies: Relies on the line() method below
     * @param stock: Stock, the stocks data to display
     * @return Returns a Vertical Box populated with stock data.
     */
    private VBox getStockBox(Stock stock, String label) {
        VBox stockBox = new VBox();
        stockBox.setSpacing(3);
        Label lbl = new Label(label);
        lbl.setFont(Font.font("Veranda", FontWeight.BOLD, 20));
        // insets are top, left, bottom, right
        stockBox.setPadding(new Insets(20,100,20,100));
        stockBox.getChildren().addAll(lbl,
        new HBox(line("Ticker Symbol: ", stock.getTicker())),
        new HBox(line("52 Week High: ", stock.getHigh())),
        new HBox(line("52 Week Low: ", stock.getLow())),
        new HBox(line("Opening Price: ", stock.getOpen())),
        new HBox(line("Closing Price: ", stock.getClose())),
        new HBox(line("200 Day Avg: ", stock.getMoveAvg200())),
        new HBox(line("50 Day Avg: ", stock.getMoveAvg50())),
        new HBox(line("Volume: ", String.format("%,d", stock.getAvgVolume())))
        );
        return stockBox;
    }
    
    /**
     * Helper method for getStockBOx(). Facilitates consistent formating in 
     * getStockBox() method without the use of a CSS page.
     * @param label: String
     * @param info: Object
     * @return Returns a Horizontal box populated with the information in 
     * the arguments. 
     */
    private HBox line(String label, Object info ) {
        HBox hb = new HBox();
        Label l = new Label(label);
        TextField txFd = new TextField(info.toString());
        l.setPrefWidth(120);
        // insets are top, left, bottom, right
        l.setPadding(new Insets(3,0,0,0));
        l.setFont(Font.font("Veranda", FontWeight.BOLD, 15));
        txFd.setPrefWidth(100);
        hb.setSpacing(10);
        // insets are top, left, bottom, right
        hb.setPadding(new Insets(0,4,0,4));
        hb.getChildren().addAll(l, txFd);
        return hb;
    }
  
    
    // *** MAIN METHOD for IDE ***
    public static void main(String[] args) 
    {
        
    	//Runs the webscraper at launch so that the data is up to date when the application is launched.
        try 
        {
            Webscraper.main(args);
        } 
        catch (IOException f) 
        {
            System.out.println("Trouble connecting to the Internet...\nRunning application with data on file...");
            f.printStackTrace();
        }
    	
        launch(args);
    }

}
