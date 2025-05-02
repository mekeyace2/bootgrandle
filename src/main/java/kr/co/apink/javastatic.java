package kr.co.apink;

public class javastatic {
	public static void main(String[] args) {
		//javatest jt = new javatest();
		System.out.println(javatest.test1(10));
		System.out.println(new javatest().test2(10));
	}
}

class javatest{
	public static int test1(int aa) {
		int sum = aa + 10;
		return sum;
	}
	public int test2(int aa) {
		int sum = aa + 10;
		return sum;
	}
}
