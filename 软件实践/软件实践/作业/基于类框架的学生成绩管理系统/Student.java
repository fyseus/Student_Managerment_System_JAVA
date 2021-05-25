package student_Class;

public class Student{
	private double score;
	private String name;
	public Student(double s,String n) throws ScoreException{
		score=s;
		name=n;
		if(s>100||s<0)throw new ScoreException(s);
	}
	public double getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
	public void setScore(double s) throws ScoreException {
		score=s;
		if(s>100||s<0)throw new ScoreException(s);
	}
	public void setName(String n) {
		name=n;
	}
}
