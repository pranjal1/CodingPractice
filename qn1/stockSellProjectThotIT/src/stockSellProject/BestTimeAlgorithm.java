package stockSellProject;

import java.util.ArrayList;

public class BestTimeAlgorithm {
	public static ArrayList<Integer> findBestTime(ArrayList<StockData> allStockData){
		
		int buyIdx = 0;
		int sellIdx = 0;
		double bestProfit = 0;
		
	
		for (int counter = 0; counter < allStockData.size(); counter++) { 		      
	          if (allStockData.get(counter).getPrice() < allStockData.get(buyIdx).getPrice()) {
	        	  buyIdx = counter;
	          }		
	          
	          double currProfit = allStockData.get(counter).getPrice() - allStockData.get(buyIdx).getPrice();
	          if (currProfit > bestProfit) {
	        	  bestProfit = currProfit;
	        	  sellIdx = counter;
	          }
	      } 
		
		ArrayList<Integer> res = new ArrayList<>();
		res.add(buyIdx);
		res.add(sellIdx);
		return res;
	}
	
	public static void main(String[] args) {
		ArrayList<StockData> allData = new ArrayList<StockData>();
		StockData d1 = new StockData("Oct 01 2018", "9:30:00 AM " , 227.95);
		StockData d2 = new StockData("Oct 01 2018", "9:40:00 AM " , 224.79);
		StockData d3 = new StockData("Oct 01 2018", "9:50:00 AM " , 223.82);
		StockData d4 = new StockData("Oct 01 2018", "10:00:00 AM" ,  221);
		StockData d5 = new StockData("Oct 01 2018", "10:10:00 AM" ,  259.75);
		
		allData.add(d1);
		allData.add(d2);
		allData.add(d3);
		allData.add(d4);
		allData.add(d5);
		
		ArrayList<Integer> res = findBestTime(allData);
		if(res.get(0) >= res.get(1)) {
			System.out.println("No good solution found");
		}
		else {
			System.out.println("best time to buy ->"+allData.get(res.get(0)));
			System.out.println("best time to sell ->"+allData.get(res.get(1)));
		}
	}
	
	
}
