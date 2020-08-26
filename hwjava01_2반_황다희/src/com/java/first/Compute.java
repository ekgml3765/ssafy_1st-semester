package com.java.first;

import java.util.Scanner;

public class Compute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int num1, num2;
		Scanner sc = new Scanner(System.in);
		System.out.print("두개의 정수를 입력하시오: ");
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		System.out.println("곱 ="+num1*num2);
		System.out.println("몫 =" + num1/num2);
	}

}
