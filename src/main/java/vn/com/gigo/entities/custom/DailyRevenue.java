package vn.com.gigo.entities.custom;

import java.util.Date;

public class DailyRevenue {
	private Date date;
	private Double revenue;
	private Integer countOrders;
	
	public DailyRevenue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DailyRevenue(Date date, Double revenue, Integer countOrders) {
		super();
		this.date = date;
		this.revenue = revenue;
		this.countOrders = countOrders;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date row) {
		this.date = row;
	}

	public Integer getCountOrders() {
		return countOrders;
	}

	public void setCountOrders(Integer countOrders) {
		this.countOrders = countOrders;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	
	

}
