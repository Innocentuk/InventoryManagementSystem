package Model;

import java.io.Serializable;

public class DamageItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String item;
	private String reason;
	private String staffstamp;
	private int qty;
	private String date;
	
public DamageItem() {
	// TODO Auto-generated constructor stub
}

public DamageItem(int id, String item, String reason, int qty, String date, String staff) {
	super();
	this.id = id;
	this.item = item;
	//this.priceNo = price;
	this.reason = reason;
	this.qty = qty;
	this.date = date;
	this.staffstamp = staff;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getStaffstamp() {
	return staffstamp;
}

public void setStaffstamp(String staffstamp) {
	this.staffstamp = staffstamp;
}

public String getItemNo() {
	return item;
}

public void setItemNo(String item) {
	this.item = item;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
}

public int getQty() {
	return qty;
}

public void setQty(int qty) {
	this.qty = qty;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}
public String getItem() {
	return item;
}

public void setItem(String item) {
	this.item = item;
}

@Override
public String toString() {
    return "Damage{" +
            "id=" + id +
            ", Item=" + item +
            ", Reason=" + reason +
            ", staff=" + staffstamp +
            ", qty=" + qty +
            ", date=" + date +
            '}';
}

}
