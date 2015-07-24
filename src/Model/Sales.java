package Model;

import java.io.Serializable;
import java.sql.Date;

import javax.management.loading.PrivateClassLoader;

public class Sales implements Serializable
{
	private int id;
	private String item;
	private double totalprice;
	private String date;
	private int quantity;
	private String status;
	private String staffStamp;
	private Price price;
	
	public Sales() {}

	public Sales( String cashier, int id, String item, double price, int quantity, String date, String status) {
		super();
		this.id = id;
		this.item = item;
		this.totalprice = price;
		this.date = date;
		this.quantity = quantity;
		this.status = status;
		this.staffStamp = cashier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Price getPrice() {
		return price;
	}

	public void setSellprice(Price price) {
		this.price = price;
	}

	public double getTotalprice() {
		//totalprice = price.getSellPrice() * quantity;
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getStaffStamp() {
		return staffStamp;
	}

	public void setStaffStamp(String staffStamp) {
		this.staffStamp = staffStamp;
	}

	@Override
	public String toString() {
	    return "Sale{" +
	            "sellId=" + id +
	            "cashier=" + staffStamp +
	            ", item=" + item +
	            ", total price=" + totalprice +
	            ", qty=" + quantity +
	            ", status=" + status +
	            ", date=" + date +
	            '}';
	}
}
