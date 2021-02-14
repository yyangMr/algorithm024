class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        help(ans, res, 0, nums, visited);
        return res;
    }
    public void help(List<Integer> ans, List<List<Integer>> res, int cur, int[] nums, boolean[] visited) {
        if (cur == nums.length) {
            res.add(new ArrayList<>(ans));
        }
        for (int i = 0; i < nums.length; i++) {
            // 去重
            // visited[i - 1] == true，说明同一树支nums[i - 1]使用过
            // visited[i - 1] == false，说明同一树层nums[i - 1]使用过
            // 如果同一树层nums[i - 1]使用过则直接跳过
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false)) {
                continue;
            }
            visited[i] = true;
            ans.add(nums[i]);
            help(ans, res, cur + 1, nums, visited);
            visited[i] = false;
            ans.remove(ans.size() - 1);
        }
    }
}