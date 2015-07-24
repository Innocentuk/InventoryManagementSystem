package Model;

import java.util.ArrayList;

public class SellAnItem {
	
	public static ArrayList<Sales> sell =new ArrayList<Sales>(); 
	//public static ArrayList<Item> items =new ArrayList<Item>();
	//public static ArrayList<Stock> stock=new ArrayList<Stock>();
	
	Item item;
	Stock stock;
	Price price;
	Sales sale;
	int qtyOrdered;
	String cashier;
	
	public SellAnItem() {
	}

	public SellAnItem(Item item, Stock stock, Price price, Sales sale, int qtyOrdered) {
		int itemId = item.getId();
		int sellId = sale.getId();
		String cashier = sale.getStaffStamp();
		String date = sale.getDate();
		String status = sale.getStatus();
		double totalPrice = sale.getTotalprice();
		double sellprice = price.getSellPrice();
		String itemName = sale.getItem();
		//int saleId = sale.getId();
		int qty = stock.getQuantity();
		int qtyInStock = qty - qtyOrdered;
		stock.setQuantity(qtyInStock);
		
		if (qtyOrdered>0 && itemId>0 && itemId>0){
			sale = new Sales(cashier, sellId, itemName, totalPrice, qtyOrdered, status, date);
			sell.add(sale);
		}
		
	}
	
}
