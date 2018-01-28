/*single dimension arrays*/

    public static void bubbleSort(int[] num) {
        int j;
        boolean flag = true;   // устанавливаем наш флаг в true для первого прохода по массиву
        int temp;   // вспомогательная переменная
        int count=0;

        while (flag) {
            flag = false;    // устанавливаем флаг в false в ожидании возможного свопа (замены местами)
            for (j = 0; j < num.length - 1; j++) {
                count++;
                if (num[j] > num[j + 1]) { // измените на > для сортировки по возрастанию
                    temp = num[j];         // меняем элементы местами
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                    flag = true;  // true означает, что замена местами была проведена
                }
            }

        }
        System.out.println("total iterations " + count);
    }
	
	
	
	
    public static void bubbleSort2(int[] nums){
        int t = 0;
        for(int a = 1; a < nums.length; a++)
            for(int b = nums.length - 1; b >= a; b--) {
                if(nums[b - 1] > nums[b]) { // Если требуемый порядок следования не соблюдается
                    // элементы меняются местами
                    t = nums[b - 1];
                    nums[b - 1] = nums[b];
                    nums[b] = t;
                }
            }
    }