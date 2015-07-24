package Model;

import java.io.Serializable;
import java.util.Date;

public class Stock  implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int soldNo;
	private int damagedNo;
	private String date;
	private int quantity;
	private String item;
	
	public int getSoldNo() {
		return soldNo;
	}

	public void setSoldNo(int soldNo) {
		this.soldNo = soldNo;
	}

	public int getDamagedNo() {
		return damagedNo;
	}

	public void setDamagedNo(int damagedNo) {
		this.damagedNo = damagedNo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Stock() {}

	public Stock(int id, String date, int soldNo, int damageNo, int quantity, String item) {
		super();
		this.id = id;
		this.soldNo = soldNo;
		this.damagedNo = damageNo;
		this.date = date;
		this.quantity = quantity;
		this.item = item;
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

	@Override
	public String toString() {
	    return "Stock{" +
	            "id=" + id +
	            ", product=" + item +
	            ", quantity=" + quantity +
	            ", item sold=" + soldNo +
	            ", item damaged=" + damagedNo +
	            ", date=" + date +
	            '}';
	}
}
