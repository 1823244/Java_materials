package com.nicxxx.examples.example01;
/*
Шаблон кода для ввода строки с клавиатуры с применением массива
*/
import java.io.*;
public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] sN = new String[3];
		sN[0] = reader.readLine();
		sN[1] = reader.readLine();
		sN[2] = reader.readLine();
		int[] nN = new int[3];
		nN[0] = Integer.parseInt(sN[0]);
		nN[1] = Integer.parseInt(sN[1]);
		nN[2] = Integer.parseInt(sN[2]);
		// в цикле
		for (int i =0; i<3; i++){
			sN[i] = reader.readLine();
			nN[i] = Integer.parseInt(sN[i]);
			//или сразу в число
			//nN[i] = Integer.parseInt(reader.readLine());
		}
	}
}