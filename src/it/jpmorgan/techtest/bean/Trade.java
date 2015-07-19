package it.jpmorgan.techtest.bean;

import java.sql.Timestamp;
import java.util.Date;

public class Trade {
	
	private Date date;
	private Integer quantity;
	private double price;
	private boolean buy;
	
	
	public Trade(Date date, Integer quantity, double price, boolean buy) {
		super();
		this.date = date;
		this.quantity = quantity;
		this.price = price;
		this.buy = buy;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isBuy() {
		return buy;
	}
	public void setBuy(boolean buy) {
		this.buy = buy;
	}
	
	

}
