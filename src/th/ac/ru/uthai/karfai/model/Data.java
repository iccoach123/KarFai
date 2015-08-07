package th.ac.ru.uthai.karfai.model;

import java.util.List;

public class Data {
	private int day=30;
	private int amount=1;
	private double wat;
	private int id;
	private int icon=0;
	private boolean statusExpand;
	private int hour=1;
	
	private int minute=0;
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public void changeStatusExpand(boolean status){
		statusExpand = status;
	}
	public boolean isStatusExpand() {
		return statusExpand;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	private String detail;

	public Data(){
		
	}

	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getWat() {
		return wat;
	}
	public void setWat(double wat) {
		this.wat = wat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

}
