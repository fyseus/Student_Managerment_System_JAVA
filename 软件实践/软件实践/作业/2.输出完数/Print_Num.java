
public class Print_Num {
	public static void main(String []args) {
		System.out.println("1000之内的所有完数:");
		for(int i=1;i<=1000;i++) {
			int total=0;
			for(int j=1;j<i;j++) {
				if(i%j==0) total+=j;
			}
			if(total==i) System.out.println(i);
		}
	}
}
