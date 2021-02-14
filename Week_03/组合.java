class Solution {
    // 更简洁的回朔方法
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k > n || n < 0) {
            return res;
        }
        // 递归终止条件
        if (k == 0) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        // 包含 n 的结果集
        res = combine(n - 1, k - 1);
        for (List<Integer> list : res) {
            list.add(n);
        }
        // 不包含 n 的结果集
        res.addAll(combine(n - 1, k));
        return res;
    }
}