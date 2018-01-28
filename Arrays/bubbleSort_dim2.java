/*double and more dimension arrays*/

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