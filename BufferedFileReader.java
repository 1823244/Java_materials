package com.javarush.task.task13.task1326;

/* 
	Прочитать файл построчно и вывести в консоль
	c:\Materials\Java_materials\BufferedFileReader.java
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        reader = null;
		
		try { 
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader r = new BufferedReader(fr);
			String line;
			while ((line = r.readLine()) != null ){
				System.out.println(line);
			}
			r.close();
			fr.close();
			file = null;
		// в идеале, конечно, надо более тщательно распределить блоки try..catch
		// чтобы закрыть файл более корректно
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
