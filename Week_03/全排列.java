class Solution {
    // 回溯
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            num.add(nums[i]);
        }
        help(0, num, res);
        return res;
    }
    public void help (int cur, List<Integer> nums, List<List<Integer>> res) {
        // 终止条件,所有的数都填完了
        if (cur == nums.size()) {
            res.add(new ArrayList<>(nums));
        }
        for (int i = cur; i < nums.size(); i++) {
            // 维护动态数组,将选取过的元素移动到数组左侧
            Collections.swap(nums, cur, i);
            help(cur + 1, nums, res);
            // 撤销操作
            Collections.swap(nums, cur, i);
        }
    }
}