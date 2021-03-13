# Week06 动态规划

#### [不同路径](https://leetcode-cn.com/problems/unique-paths/)

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // 从start走到[i,j]的路径数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
```
```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // 从[i,j]走到end的路经数
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}
```
```java
class Solution {
// 空间优化
    public int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
                cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }
}
```
#### [不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
}
```
#### [不同路径 III](https://leetcode-cn.com/problems/unique-paths-iii/)

```java
class Solution {
    int res = 0;
    int empty = 1; // 空格子数量,将开始点包括在内
    int sx, sy, ex, ey; // 开始点和结束点坐标 
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        dfs(grid, sx, sy);
        return res;
    }
    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0)
            return;
        if (grid[x][y] == 2) {
            if (empty == 0) res++;
            return;
        }
        grid[x][y] = -2;
        empty--;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        grid[x][y] = 0;
        empty++;
    }
}
```
#### [三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
```
```
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 自底向上
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
```
#### [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(dp[i], max); 
        }
        return max;
    }
}
```
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}
```
#### [乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/)

```java
class Solution {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int min = nums[0], max = nums[0], ans = nums[0];
        for (int i = 1; i < len; i++) {
            int minVal = min, maxVal = max;
            min = Math.min(minVal * nums[i], Math.min(nums[i], maxVal * nums[i]));
            max = Math.max(minVal * nums[i], Math.max(nums[i], maxVal * nums[i]));
            ans = Math.max(ans, max); 
        }
        return ans;
    }
}
```
#### [零钱兑换](https://leetcode-cn.com/problems/coin-change/)

```java
class Solution {
    // dp[i] = Math.min(dp[i-coin] + 1, dp[i])
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[i] = i == 0 ? 0 : amount + 1;
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == amount + 1? -1 : dp[amount];
    }
}
```
```java
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```
#### [打家劫舍](https://leetcode-cn.com/problems/house-robber/)

```java
class Solution {
    // dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1])
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[n];
    }
}
```
```java
public int rob(int[] nums) {
    int prev = 0;
    int curr = 0;
    // 每次循环，计算“偷到当前房子为止的最大金额”
    for (int i : nums) {
        // 循环开始时，curr 表示 dp[k-1]，prev 表示 dp[k-2]
        // dp[k] = max{ dp[k-1], dp[k-2] + i }
        int temp = Math.max(curr, prev + i);
        prev = curr;
        curr = temp;
        // 循环结束时，curr 表示 dp[k]，prev 表示 dp[k-1]
    }
    return curr;
}
```
#### [打家劫舍 II](https://leetcode-cn.com/problems/house-robber-ii/)

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(help(Arrays.copyOfRange(nums, 0, nums.length - 1)), help(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    public int help(int[] nums) {
        int pre = 0, cur = 0, tmp = 0;
        for (int i : nums) {
            tmp = Math.max(pre + i, cur);
            pre = cur;
            cur = tmp;
        }
        return cur;
    }
}
```
#### [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }
}
```
#### [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i >= 1 && prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }
}
```
#### [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        // buy1: 第一次买入时的最大利润, sell1: 第一次卖出时的最大利润
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, prices[i] + buy1);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
```
#### [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            f[i][1] = f[i - 1][0] + prices[i];
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }
}
```
```java
class Solution {
    // https://www.youtube.com/watch?v=oL6mRyTn56M&list=PLLuMmzMTgVK7vEbeHBDD42pqqG36jhuOr&index=39&t=0s
    public int maxProfit(int[] prices) {
    int sold = 0, rest = 0, hold = Integer.MIN_VALUE;
    for (int price : prices) {
        int prev_sold = sold;
        sold = hold + price;
        hold = Math.max(hold, rest - price);
        rest = Math.max(rest, prev_sold);
    }
    return Math.max(rest, sold);
}
}
```
#### [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)

```java
class Solution {
//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/javayi-ge-si-lu-da-bao-suo-you-gu-piao-t-pd1p/
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // 一次交易需要 2 天,所以最多只能交易 n/2 次
        k = Math.min(k, n / 2);
        // 定义状态数组 dp[i][j][k] 表示在第i天交易了k次所取得的最大利润,其中j代表持有或者(j == 1)不持有(j == 0)股票 
        int[][][] dp = new int[n][2][k + 1]; 
        for (int i = 0; i <= k; i++) {
            dp[0][0][i] = 0;
            dp[0][1][i] = -prices[0];
        }
        // 将买入作为一次交易,交易次数加一
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // 第 i 天不持有股票
                // 1.i - 1天也不持有,则代表状态没变,即 dp[i - 1][0][k]
                // 2.i - 1天持有,则代表在第i天卖出了股票,即 dp[i - 1][1][k] + prices[i];  
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                // 第i天持有股票
                // 1.i - 1 天持有,则状态没变,即dp[i - 1][1][j]
                // 2.i - 1 天不持有,则代表在第i天买入了股票, dp[i - 1][0][j - 1] - prices[i]
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }
        return dp[n - 1][0][k];
    }
}
```
![图片](https://uploader.shimo.im/f/0VLFtWILRjn2u6tm.png!thumbnail?fileGuid=DX8VVCpDcHDwWkVP)

#### [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[i];
        for (int i = 1; i < n; i++) {
            // dp[i][0]第i天不持有股票的最大收益
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            // dp[i][1]第i天持有股票的最大收益
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0]    rices[i]);
         
         return  p[n    ][0];
     
}
```
#### [完全平方数](https://leetcode-cn.com/problems/perfect-squares/)

```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) { 
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];
    }
}
```
#### [编辑距离](https://leetcode-cn.com/problems/edit-distance/)

```java
public class Solution {
    // 只讨论 word1 → word2
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // 多开一行一列是为了保存边界条件，即字符长度为 0 的情况，这一点在字符串的动态规划问题中比较常见
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化：当 word2 长度为 0 时，将 word1 的全部删除即可
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        // 当 word1 长度为 0 时，插入所有 word2 的字符即可
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        // 由于 word1.charAt(i) 操作会去检查下标是否越界，因此在 Java 里，将字符串转换成字符数组是常见额操作
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();
        // 递推开始，注意：填写 dp 数组的时候，由于初始化多设置了一行一列，横纵坐标有个偏移
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 这是最佳情况
                if (word1Array[i - 1] == word2Array[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                // 否则在以下三种情况中选出步骤最少的，这是「动态规划」的「最优子结构」
                // 1、在下标 i 处插入一个字符
                int insert = dp[i][j - 1] + 1;
                // 2、替换一个字符
                int replace = dp[i - 1][j - 1] + 1;
                // 3、删除一个字符
                int delete = dp[i - 1][j] + 1;
                dp[i][j] = Math.min(Math.min(insert, replace), delete);
            }
        }
        return dp[len1][len2];
    }
}
```
#### [零钱兑换 II](https://leetcode-cn.com/problems/coin-change-2/)

```java
class Solution {
    public int change(int amount, int[] coins) {
        // dp[i][j]表示用i个硬币组成总金额j的组合数
        // 1.不使用第i个硬币 dp[i - 1][j]
        // 2.使用第i个硬币,dp[i][j - coin[i - 1]]
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= coins.length; i++) { // 遍历物品
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) { // 遍历背包
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }
}
```
```java
class Solution {
     public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }
}
```
#### [最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

```java
class Solution {
    public int minPathSum(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue; // 在起点
                else if(i == 0)  grid[i][j] = grid[i][j - 1] + grid[i][j]; // 上边是边界
                else if(j == 0)  grid[i][j] = grid[i - 1][j] + grid[i][j]; // 左边是边界
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j]; //左边和上边都不是边界 
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
```
#### [解码方法](https://leetcode-cn.com/problems/decode-ways/)

```java
class Solution {
    public int numDecodings(String s) {
    if(s == null || s.length() == 0) {
      return 0;
    }
    int n = s.length();
    int[] dp = new int[n];
    dp[0] = s.charAt(0) != '0' ? 1 : 0;
    for(int i = 1; i < n; i++) {
      int first = Integer.valueOf(s.substring(i, i+1));
      int second = Integer.valueOf(s.substring(i-1, i+1));
      if(first >= 1 && first <= 9) {
        dp[i] += dp[i-1];
      }
      if(second >= 10 && second <= 26) {
        dp[i] += i >=2 ? dp[i-2] : 1;
      }
    }
    return dp[n-1];
  }
}
```
#### [最大正方形](https://leetcode-cn.com/problems/maximal-square/)

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}
```

```java
class Solution {
    public int maximalSquare(char[][] a) {
        if(a.length == 0) return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m+1][n+1];
        for (int i = 1 ; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(a[i-1][j-1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
                    result = Math.max(b[i][j], result); 
                }
            }
        }
        return result*result;
    }
}
```
#### [任务调度器](https://leetcode-cn.com/problems/task-scheduler/)

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] buckets = new int[26];
        for(int i = 0; i < tasks.length; i++){
            buckets[tasks[i] - 'A']++;
        }
        Arrays.sort(buckets);
        int maxTimes = buckets[25];
        int maxCount = 1;
        for(int i = 25; i >= 1; i--){
            if(buckets[i] == buckets[i - 1])
                maxCount++;
            else
                break;
        }
        int res = (maxTimes - 1) * (n + 1) + maxCount;
        return Math.max(res, tasks.length);
    }
}
```
#### [回文子串](https://leetcode-cn.com/problems/palindromic-substrings/)

```java
class Solution {
    public int countSubstrings(String s) {
        // 动态规划法
        // dp[i][j] 表示s在i到j之间的字符是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }
        return ans;
    }
}
```
```java
class Solution {
// 中心扩展法
    public int countSubstrings(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += extend(s, i, i, s.length()); // 以i为中心 
            result += extend(s, i, i + 1, s.length()); // 以i和i+1为中心
        }
        return result;
    }
    public int extend(String s, int i, int j, int n) {
        int res = 0;
        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            res++;
        }
        return res;
    }
}
```
#### [最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/)

```java
public class Solution {
    //栈
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}
```
```java
public class Solution {
// 动态规划 
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
```
#### [分割等和子集](https://leetcode-cn.com/problems/partition-equal-subset-sum/)

```plain
class Solution {
    // 01背包
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        sum /= 2;
        // dp[i]大小为i的背包内的元素和
        int[] dp = new int [sum + 1];
        for (int num : nums) { // 遍历物品
            for (int j = sum; j >= num; j--) { // 由于每个元素只能使用一次,倒序遍历背包
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        if (dp[sum] == sum) return true;
        return false;
    }
}
```
#### [最后一块石头的重量 II](https://leetcode-cn.com/problems/last-stone-weight-ii/)

```java
class Solution {
    // 01背包
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int target = sum / 2;
        // dp[i] 表示容量为i的背包能装下的最大重量dp[i]
        int[] dp = new int[target + 1];
        for (int stone : stones) {
            for (int j = target; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - 2 * dp[target];
    }
}
```
