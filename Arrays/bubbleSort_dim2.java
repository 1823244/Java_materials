/*double and more dimension arrays*/
/*sorting by field #2*/
    public static void bubbleSort2(int[][] nums){
        int t = 0;
        for(int a = 1; a < nums.length; a++)
            for(int b = nums.length - 1; b >= a; b--) {
                if(nums[b - 1][1] > nums[b][1]) { // Если требуемый порядок следования не соблюдается
                    // элементы меняются местами
                    t = nums[b - 1][0];
                    nums[b - 1][0] = nums[b][0];
                    nums[b][0] = t;
					
                    t = nums[b - 1][1];
                    nums[b - 1][1] = nums[b][1];
                    nums[b][1] = t;
                }
            }
    }