package fr.etu.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Chart {
	
	private JFreeChart chart;
	private XYSeries series;
	private XYSeriesCollection data;
	private ApplicationFrame plotFrame;
	private XYSeries seriesMoyenne;
	private XYSeries seriesMin;
	
	private XYSeries seriesKFlip1;
	private XYSeries seriesKFlip3;
	private XYSeries seriesKFlip5;
	private XYSeries seriesBitFlips;
	private XYSeries seriesNoM;
	
	public Chart(String title) {
		this.series = new XYSeries("Best Fitness");
		this.data = new XYSeriesCollection(this.series);
		this.plotFrame = new ApplicationFrame("");
	    this.plotFrame.setVisible(true);
	    this.chart = ChartFactory.createXYLineChart(
	        "",
	        "Générations", 
	        "Fitness", 
	        this.data,
	        PlotOrientation.VERTICAL,
	        true,
	        false,
	        false
	    );

		ChartPanel chartPanel = new ChartPanel(this.chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(600, 1000));
	    this.plotFrame.add(chartPanel);
		this.plotFrame.pack();
	    RefineryUtilities.centerFrameOnScreen(this.plotFrame);
		
		this.chart.setTitle(title);
		this.seriesMoyenne = new XYSeries("Moyenne Fitness");
		this.seriesMin = new XYSeries("Minimum Fitness");
		this.data.addSeries(this.seriesMoyenne);
		this.data.addSeries(this.seriesMin);
	}
	
	public Chart() {
	    this.seriesKFlip1 = new XYSeries("KFlips 1");
		this.seriesKFlip3 = new XYSeries("KFlips 3");
		this.seriesKFlip5 = new XYSeries("KFlips 5");
		this.seriesBitFlips = new XYSeries("BitFlips");
		this.seriesNoM = new XYSeries("No Mutation");
		
		XYSeriesCollection dataMutation = new XYSeriesCollection(this.seriesKFlip1);
	
		dataMutation.addSeries(this.seriesKFlip3);
		dataMutation.addSeries(this.seriesKFlip5);
		dataMutation.addSeries(this.seriesBitFlips);
		dataMutation.addSeries(this.seriesNoM);
		this.chart = ChartFactory.createXYLineChart(
	    		"Opérateurs mutation",
	    		"Generations", 
			"Utilité", 
	        dataMutation,
	        PlotOrientation.VERTICAL,
	        true,
	        false,
	        false
	    );
		
		ChartPanel chartPanel = new ChartPanel(this.chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(600, 1000));

	   
	    ApplicationFrame plotFrame = new ApplicationFrame("");
	    plotFrame.add(chartPanel);
	    
		plotFrame.pack();
		plotFrame.setVisible(true);
	}
	
	public void addBestXY(int x, double y) {
		this.series.add(x, y);
	}
	
	public void addMoyenneXY(int x, double y) {
		this.seriesMoyenne.add(x, y);
	}
	
	public void addMinXY(int x, double y) {
		this.seriesMin.add(x, y);
	}

	public String getTitleGraph() {
		return this.chart.getTitle().getText();
	}
	
	public void setTitleGraph(String title) {
		this.chart.setTitle(title);
	}
	
	public void addKFlip1(int x, double y) {
		this.seriesKFlip1.add(x, y);
	}
	
	public void addKFlip3(int x, double y) {
		this.seriesKFlip3.add(x, y);
	}
	
	public void addKFlip5(int x, double y) {
		this.seriesKFlip5.add(x, y);
	}
	
	public void addBitFlips(int x, double y) {
		this.seriesBitFlips.add(x, y);
	}
	
	public void addNoM(int x, double y) {
		this.seriesNoM.add(x, y);
	}
	
	/**
	 * Wait during "time" seconds
	 * @param time in seconds
	 */
	public void busyWait(double time) {
		double nanoTime = System.nanoTime() + time*1000000000;
		while(nanoTime > System.nanoTime())
			;
	}
}
