package hw_0728;

import java.util.Scanner;

public class Ladder1 {

	static int depart = 0; // 출발 열 좌표


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		for (int T = 0 ; T < 10; T++) {

			int tc = sc.nextInt();
			int arr[][] = new int[100][100];

			// 배열 입력 받음
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			
			// 2가 있는 열을 찾는다.
			for (int i = 0; i < 100; i++) {
				if (arr[99][i] == 2)
					depart = i;
			}
			
				
			//기둥이 있는 열의 좌표를 ladder 배열에 입력함
			int ladder[] = new int[100];
			int idx = 0;
			for (int i = 0; i < 100; i++) {
				if (arr[0][i] == 1) {
					ladder[idx] = i;
					idx++;
				}
			}
			
			// 위로 1이 있을때 쭉 올라가 
			for (int i = 99; i >= 0; i--) {
				// 왼쪽 탐색했는데 1이면	
		
				int left = depart -1;
				int right = depart +1;
				
				if (left > -1 && arr[i][left] == 1 ) {			
					for(int j = 0; j <ladder.length; j++) {
						if(ladder[j]==depart) {
							if(j-1 != 0)
							depart = ladder[j-1]; //왼쪽 기둥으로 간다						
							break;
						}
					}

				}
				
				// 오른쪽 탐색, 1이면
				if (right < 100 && arr[i][right] == 1) {
					for(int j = 0; j <ladder.length; j++) {
						if(ladder[j]==depart) {
							depart = ladder[j+1]; //오른쪽 기둥으로 간다			
							break;
						}
					}

				}

			}

			System.out.println("#" + tc + " " + depart);
		}
	}

}
