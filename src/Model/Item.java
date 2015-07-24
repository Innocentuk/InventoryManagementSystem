package Model;

import java.io.Serializable;

public class Item implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String Name;
	//private double cPrice;
	private String Description;
	//private int priceNo;
	
	public Item()
	{
		// TODO Auto-generated constructor stub
	}
		public Item(int id, String name, String description, int price, double cPrice) 
	{
		super();
		this.id = id;
		Name = name;
		Description = description;
		//this.priceNo = price;
		//this.cPrice = cPrice;
	}

	/*public int getPriceNo() {
			return priceNo;
		}
	
	public void setPriceNo(int price) {
			this.priceNo = price;
		}
	
	public double getcPrice() {
		return cPrice;
	}
	public void setcPrice(double cPrice) {
		this.cPrice = cPrice;
	}*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	@Override
	public String toString() {
	    return "Item{" +
	            "id=" + id +
	            ", name=" + Name +
	            ", description=" + Description +
	            //", cprice=" + cPrice +
	            //", sprice=" + priceNo +
	            '}';
	}

}
