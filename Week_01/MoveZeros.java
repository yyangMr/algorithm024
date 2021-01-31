class MoveZeros {
    // 双指针,交换0和非0元素
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            // 遇到非零元素,交换到前面
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }       
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}