package database;

import java.io.Serializable;

public class StudentItem implements Serializable{
	private static final long serialVersionUID = 1L;
	String card_number;
    public String name;
    String student_number;
    
    double balance;
    String bank_card_number;
    
    public StudentItem(String cd_number,String nm,String st_number,double bl,String bcn) {
    	card_number=cd_number;
    	name=nm;
    	student_number=st_number;
    	balance=bl;
    	bank_card_number=bcn;
    }
    
	
	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudent_number() {
		return student_number;
	}

	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getBank_card_number() {
		return bank_card_number;
	}

	public void setBank_card_number(String bank_card_number) {
		this.bank_card_number = bank_card_number;
	}

	
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:" + name + " student number:" + student_number + " card number:" + card_number;
	}
}
