//� ������� �������

	long startTime = System.currentTimeMillis();
    String res = "hi";
    StringBuilder builder = new StringBuilder(res);
    for (int i = 1; i < 100000 ; i++) {
        builder.append(res);
    }
    long endTime = System.currentTimeMillis();
    System.out.println("��� ������ " + (endTime - startTime) + " �����������.");  
	String result = builder.toString();

//end.