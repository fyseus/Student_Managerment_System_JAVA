
public class Print_Triangle {
	public static void main(String []args) {
		int[] num=new int[8];
		for(int i=0;i<8;i++) {
			num[i]=1;
			for(int j=1;j<i;j++) {
				num[j]+=num[j-1];
			}
			for(int j=0;j<=i;j++) {
				System.out.print(num[j]+" ");
			}
			System.out.println();
		}
	}
}
