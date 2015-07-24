package View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DAO.DamageDAO;
import DAO.ItemDAO;
import DAO.PriceDAO;
import DAO.SalesDAO;
import DAO.StockDAO;
import Model.DamageItem;
import Model.Item;
import Model.Price;
import Model.Sales;
import Model.Stock;
import Service.DamageServiceImplementation;
import Service.ItemService;
import Service.ItemServiceImplementation;
import Service.PriceServiceImplementation;
import Service.SalesServiceImplementation;
import Service.StockService;
import Service.StockServiceImplementation;

public class View
{
	ItemServiceImplementation service;
	PriceServiceImplementation priceServe;
	StockServiceImplementation stockServe;
	SalesServiceImplementation saleService;
	DamageServiceImplementation damServe;
	
	Price p = new Price();
	Stock st = new Stock();
	Sales sa = new Sales();
	Item t = new Item();
	DamageItem d = new DamageItem();
	//Date date = new Date();
	
	public View() throws Exception {
		// TODO Auto<-generated constructor stub
		service = new ItemServiceImplementation();
		priceServe = new PriceServiceImplementation();
		stockServe = new StockServiceImplementation();
		saleService = new SalesServiceImplementation();
		damServe = new DamageServiceImplementation();
		
		 //createPrice();
		 //createItem();
		 //CreateStock();
		 //recordDamage();
		//restock();
		//inStock();
		makeNewSale();
	}
	
	public void recordDamage() throws Exception {

		Stock st = stockServe.findItemByName("Gold2");
		if(st.getQuantity() < 1){
			System.out.println("no item in stock ");
			return;
		}else{
			System.out.println(st+" is in stock");
		}
		st.setDamagedNo(st.getDamagedNo()+ 2);
		stockServe.updateStock(st);
		
		d.setItem("Gold1");//+ s.getItem());
		d.setReason("fell from top, broken");
		d.setQty(2);
		d.setStaffstamp("Chike");
		d.setDate("" + new Date());
		damServe.saveItem(d);
		try{
			//DamageDAO ddao = new DamageDAO();
			//damServe.saveItem(d);
			
			List<DamageItem> dl = damServe.findAlldamaged();// to view
			
			System.out.println(dl.size());
			for (int i = 0; i < dl.size()-1; i++) {
				DamageItem d = dl.get(i);
				System.out.println(d.getItem()+": "+ d.getQty()+", "+ d.getReason()+", "+ d.getDate()+", "+d.getStaffstamp());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void createItem()
	{
		//t.setId(1);
		t.setDescription("ok");
		t.setName("Gold2");
		
		//t.setPrice()
		try {
			ItemDAO s = new ItemDAO();
			service.saveItem(t);
			List<Item> li = service.findAllItem();
			
			System.out.println(li.size());
			for (int i = 0; i < li.size()-1; i++) {
				Item it = li.get(i);
				System.out.println(it.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createPrice(){
		//p.setId(1);
		p.setItem("Gold2");
		p.setSellPrice(65.00);
		p.setCostPrice(47.00);
		p.setDate("" + new Date());
		
		try {
			PriceDAO pDAO = new PriceDAO();
			priceServe.savePrice(p);
			List<Price> pl = priceServe.findAllPrice();
			
			System.out.println(pl.size());
			for (int i = 0; i < pl.size()-1; i++) {
				Price p = pl.get(i);
				System.out.println(p.getItem()+ ":"+ p.getCostPrice()+","+ p.getSellPrice()+ ","+ p.getDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CreateStock(){
		StockDAO sdao = new StockDAO();
		
		st.setDamagedNo(0);
		st.setDate(""+new Date());
		st.setItem("Gold2");
		st.setQuantity(12);
		st.setSoldNo(0);
	//imp.updateStock(s.)
		try {
			stockServe.saveStock(st);
			//sdao.delete(s);
			List<Stock> sss = stockServe.findAllStock(); //to View
			
			System.out.println(sss.size());
			for (int i = 0; i < sss.size()-1; i++) {
				Stock ok = sss.get(i);
				System.out.println(ok.getItem());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * http://ideone.com/ROPNhb
     * Restocks an item.
     *
     * Adds a particular amount of stock of the item.
     *
     * @param newStock  the new stock to add to the current stock
     */
	
	public void restock() throws Exception{
		int newStock = 12;
		st = stockServe.findItemByName("Gold2");
		st.setQuantity(st.getQuantity()+ newStock);
		stockServe.updateStock(st);
		//stockServe.saveStock(st);
		System.out.println(newStock+":"+" is added stock"+ st.getQuantity()+" on "+ new Date());
		//return newStock;
}
	/**
     * Determines if the item is in stock.
-     * If there is at least one of an item available, it is in stock.
     * 
     * @return  true if the item is in stock, false otherwise
	 * @throws Exception 
     */
    public boolean inStock() throws Exception{
    	st = stockServe.findItemByName("Gold2");
		if(st.getQuantity() > 0){
			System.out.println(st.getItem()+", "+ t.getDescription()+", instock "+ st.getQuantity()+ ", sell price"+ p.getSellPrice()+", date added "+ st.getDate());
			return true;
}		else
		System.out.println("Not in Stock !");
			return false;
}
	
	public void makeNewSale() throws Exception{
		st = stockServe.findItemByName("Gold2");
		if(st.getQuantity() < 1){
			System.out.println("less qty left");
			return;
		}
		st.setSoldNo(st.getSoldNo()+ 2);
		//st.setQuantity(st.getQuantity()- 2);
		stockServe.updateStock(st);
		sa.setItem("Gold2");
		sa.setQuantity(2);
		//sa.setTotalprice();//p.getSellPrice());
		sa.getTotalprice();
		sa.setStatus("paid");
		sa.setDate(""+ new Date());
		sa.setStaffStamp("Henry");
		saleService.saveSales(sa);
	}
	
	/*public void makeSale(){
		//sa.setId(1);
		
		
		try {
			SalesDAO sDAO = new SalesDAO();
			saleService.saveSales(sa);
			List<Sales> ls = saleService.findAllSale();
			
			System.out.println(ls.size());
			
			for (int i = 0; i < ls.size()-1; i++) {
				sa = ls.get(i);
				System.out.println(sa.getItem());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
