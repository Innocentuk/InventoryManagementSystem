package Model;

import java.io.Serializable;
import java.util.Date;

public class Price implements Serializable
{
	
	
	private int id;
	private String item;
	private String date;
	private double costPrice;
	private double sellPrice;
	
	public Price() {
		// TODO Auto-generated constructor stub
	}
	
	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public double getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public Price(int id, String date, double sprice) {
		super();
		this.id = id;
		//Name = name;
		this.date = date;
		this.sellPrice = sprice;
	}

	public double getSellPrice() {
		return sellPrice;
	}


	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}*/
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
	    return "Price{" +
	            "id=" + id +
	            ", sprice=" + sellPrice +
	            ", date=" + date +
	            '}';
	}
}
