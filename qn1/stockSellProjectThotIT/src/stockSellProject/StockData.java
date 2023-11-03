package stockSellProject;

public class StockData {
	private String date;
	private String time;
	private double price;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public StockData(String date, String time, double price) {
		super();
		this.date = date;
		this.time = time;
		this.price = price;
	}
	@Override
	public String toString() {
		return "date=" + date + ", time=" + time + ", price=" + price;
	}
	
	
	
	

}
