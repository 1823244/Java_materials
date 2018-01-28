//объ€вили функцию, как выбрасывающую исключение

	public static int readInt() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int[] nN = new int[1];
		nN[0] = Integer.parseInt(reader.readLine());
		return nN[0];
	}


// используем ее в коде

	public static void main(String[] args) {
		int a = 0;
		try {
			a = readInt();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println(a);
	}

//end