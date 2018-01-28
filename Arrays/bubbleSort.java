/*single dimension arrays*/

    public static void bubbleSort(int[] num) {
        int j;
        boolean flag = true;   // ������������� ��� ���� � true ��� ������� ������� �� �������
        int temp;   // ��������������� ����������
        int count=0;

        while (flag) {
            flag = false;    // ������������� ���� � false � �������� ���������� ����� (������ �������)
            for (j = 0; j < num.length - 1; j++) {
                count++;
                if (num[j] > num[j + 1]) { // �������� �� > ��� ���������� �� �����������
                    temp = num[j];         // ������ �������� �������
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                    flag = true;  // true ��������, ��� ������ ������� ���� ���������
                }
            }

        }
        System.out.println("total iterations " + count);
    }
	
	
	
	
    public static void bubbleSort2(int[] nums){
        int t = 0;
        for(int a = 1; a < nums.length; a++)
            for(int b = nums.length - 1; b >= a; b--) {
                if(nums[b - 1] > nums[b]) { // ���� ��������� ������� ���������� �� �����������
                    // �������� �������� �������
                    t = nums[b - 1];
                    nums[b - 1] = nums[b];
                    nums[b] = t;
                }
            }
    }