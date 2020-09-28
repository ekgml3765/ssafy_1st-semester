package hw_0928;
import java.util.Scanner;

public class 조합 {
	public static void main(String[] args) {
		long[] factorial = new long[1000001];
		factorial[1] = 1;
		for(int i = 2; i <= 1000000; i++) {
			factorial[i] = factorial[i-1] * i % MOD;
		}
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // (1 ≤ N ≤ 1000000, 0 ≤ R ≤ N)
			int R = sc.nextInt();
			//N! * (N-R! * R!) ^ (MOD-2)
			long ans = factorial[N] * power((factorial[N-R] * factorial[R]) % MOD, MOD-2) % MOD;
			System.out.println("#" + tc + " " + ans);
		}
	}
	static final long MOD = 1234567891;
	//n의 m승을 구하는 함수.
	public static long power(long n, long m) {
		if( m == 1 )
			return n;
		long tmp = power(n, m/2);
		//홀수 승일때는
		if( m % 2 == 1) {
			return tmp * tmp % MOD  * n % MOD;
		}
		else
			return tmp * tmp % MOD;
	}
}
