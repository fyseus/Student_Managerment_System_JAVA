package student_Class;

public class StudentManagement {
	public static int good,soso,bad; 
	public static void sorts(Student[] student) {
		if(student==null||student.length<2) {
			return;
		}
		for(int i=0;i<student.length;i++) {
			for(int j=0;j<student.length-1;j++) {
				if(student[j].getScore()<student[j+1].getScore()) {
					Student temp=student[j];
					student[j]=student[j+1];
					student[j+1]=temp;
				}
			}
		}
	}
	public static void out_S(Student[] student) {
		if(student==null) {
			return;
		}
		for(Student s:student) {
			System.out.println("Name: "+s.getName());
			System.out.println("Score: "+s.getScore());
		}
	}
	public static void cal(Student student[]) {
		if(student==null) {
			return;
		}
		for(Student s:student) {
			if(s.getScore()>=80)good+=1;
			else if(s.getScore()>=60)soso+=1;
			else bad+=1;
		}
	}
	public static double average(Student student[]) {
		if(student==null) {
			return 0.0;
		}
		double Sum=0;
		for(Student s:student) {
			Sum+=s.getScore();
		}
		return Sum/student.length;
	}
	public static void main(String args[]) {
		Student[] s=new Student[5];
		try {
			s[0]=new Student(45,"XiaoMing");
			s[1]=new Student(71,"XieQian");
			s[2]=new Student(90,"FeiYang");
			s[3]=new Student(99,"ChenHao");
			s[4]=new Student(21,"DongDong");
		}
		catch(ScoreException e) {
			System.out.println("Sorry,score must between 0-100.but your score are"+e.getNum());
			e.printStackTrace();
		}
		System.out.println("δ����");
		out_S(s);
		System.out.println("�����");
		sorts(s);
		out_S(s);
		System.out.println("ƽ���֣�");
		System.out.println(average(s)+"��");
		System.out.println("���ɼ���������");
		cal(s);
		System.out.println("����(80-100)��"+good+"��");
		System.out.println("�ϸ�(60-80)��"+soso+"��");
		System.out.println("���ϸ�(0-60)��"+bad+"��");
	}
}
