package hw_0923;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Knapsack_slidingWindow {
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      int T, N, K, V, C, dp[][];
      T = Integer.parseInt(in.readLine());
      for (int tc = 1; tc <= T; tc++) {
         st = new StringTokenizer(in.readLine());
         N = Integer.parseInt(st.nextToken()); //아이템 갯수
         K = Integer.parseInt(st.nextToken()); //가방 부피
         dp = new int[2][K + 1]; //dp테이블 2행만 씀
         
         for (int i = 1; i <= N; i++) {
        	 //한줄씩 입력받음.
            st = new StringTokenizer(in.readLine()); 
            V = Integer.parseInt(st.nextToken()); //부피
            C = Integer.parseInt(st.nextToken()); //가치 
            for (int j = 0; j <K+1; j++) {
               dp[1][j] = dp[0][j]; //위에서 아래꺼 그대로 내려.
               if (j >= V) //넣을수 있는 부피면 비교해
                  dp[1][j] = Math.max(dp[0][j], dp[0][j - V] + C);
            }
            for (int j = 0; j <=K; j++) 
               dp[0][j] = dp[1][j]; //복사
         }
         System.out.println("#" + tc + " " + dp[0][K]);
      }
   }
}