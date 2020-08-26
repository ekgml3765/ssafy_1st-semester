package com.java.first;

import java.util.Scanner;

public class CheckPoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int height, weight, figure;
		Scanner sc = new Scanner(System.in);
		System.out.print("몸무게와 키를 입력하세요:");
		height = sc.nextInt();
		weight = sc.nextInt();
		figure = weight+100-height;
		System.out.println("비만수치는:" +figure + "입니다.");
		if(figure>0)
			System.out.println("당신은 비만이군요.");
	}

}
