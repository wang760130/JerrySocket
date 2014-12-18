package com.jerry.socket.utils;

/**
 * 斐波那契数列
 * @author Jerry Wang
 * 
 */
public class FibonacciUtil {
	public static String fibonacci(int size) {
		StringBuffer sb = new StringBuffer();
		int[] fib = new int[size];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i < fib.length; i++)
			fib[i] = fib[i - 1] + fib[i - 2];
		for (int i = 0; i < fib.length; i++)
//			System.out.print(fib[i] + " ");
			sb.append(fib[i]).append(" ");
//		System.out.println();
		return sb.toString();
	}
}
