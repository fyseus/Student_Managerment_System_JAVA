package student_Class;
//import java.io.*;
public class ScoreException extends Exception{
	private static final long serialVersionUID = -6034035196828194874L;
	private double num;
	public ScoreException(double num) {
		this.num=num;
	}
	public double getNum() {
		return num;
	}
}
