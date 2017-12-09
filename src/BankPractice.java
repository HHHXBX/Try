import java.util.*;
import java.io.*;

public class BankPractice implements Cloneable, Serializable{
	private String name, account;
	double amount;
	public BankPractice(String name, double amount, String account) { //only clone depositor
		this.name = name;
		this.amount = amount;
		this.account = account;
	}
	public BankPractice clone() {
		return new BankPractice(this.name, this.amount, this.account);
	}
	public boolean equal(BankPractice e) {
		return this.name.equals(e.name) && this.amount==e.amount && this.account.equals(e.account);
	}
	public static void main(String[] args) throws FileNotFoundException, IOException{
		List<Bank> ls = new ArrayList<>();
		ls.add(new Depositor("Tome", 1000, "1234"));
		ls.add(new Borrower("Tome", 2000, 2017, 12, 9));
		for (int i = 0; i < ls.size(); i++) {
			Bank b = ls.get(i);
			System.out.println(b.print());
		}
		BankPractice b1 = new BankPractice("Tome", 1000, "1234");
		BankPractice b2 = b1.clone();
		System.out.println(b2.equals(b1));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Bank.ser"));
		oos.writeObject(ls);
		oos.close();
	}
}

abstract class Bank{
	private String name;
	private double amount;
	public Bank(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public String getName() { return name; }
	public double getAmount() { return amount; }
	public abstract String print();
}

class Depositor extends Bank{
	private String account;
	public Depositor(String name, double amount, String account) {
		super(name, amount);
		this.account = account;
	}
	public String print() {
		return "I'm a depositor, my name is " + super.getName() + ", my account is " + account + ", and my money amount is " + super.getAmount(); 
	}
}

class Borrower extends Bank{
	private Date d;
	public Borrower(String name, double amount, int year, int month, int day) {
		super(name, amount);
		d = new Date(year, month, day);
	}
	
	class Date{
		private int year, month, day;
		public Date(int year, int month, int day) {
			this.year = year;
			this.month = month;
			this.day = day;
		}
		public String toString(){ return year + "/" + month + "/" + day; }
	}
	public String print() {
		return "I'm a borrower, my name is " + super.getName() + ", my money amount is " + super.getAmount() + "and the borrow date is " + d;
		//date 里面写tostring函数, 这一步不会写
	}
}
