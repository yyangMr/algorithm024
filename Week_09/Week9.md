#### [LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/?fileGuid=DP6HxKP6xgpcHGP3)

```java
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }
    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }
    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
```
#### [合并区间](https://leetcode-cn.com/problems/merge-intervals/?fileGuid=DP6HxKP6xgpcHGP3)

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}
```
#### [翻转对](https://leetcode-cn.com/problems/reverse-pairs/?fileGuid=DP6HxKP6xgpcHGP3)

```java
public class Solution {
    // 归并排序
    public int ret;
    public int reversePairs(int[] nums) {
        ret = 0;
        mergeSort(nums, 0, nums.length-1);
        return ret;
    }
    public void mergeSort(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int middle = left + (right - left)/2;
        mergeSort(nums, left, middle);
        mergeSort(nums,middle+1, right);
        // 统计数量
        int count = 0;
        for (int l = left, r = middle+1; l <= middle;) {
            if (r > right || (long)nums[l] <= 2*(long)nums[r]) {
                l++;
                ret += count;
            } else {
                r++;
                count++;
            }
        }
        
        // 排序
        Arrays.sort(nums, left, right + 1);   
    }
}
```
#### [解码方法](https://leetcode-cn.com/problems/decode-ways/?fileGuid=DP6HxKP6xgpcHGP3)

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
#### [最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/?fileGuid=DP6HxKP6xgpcHGP3)

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
#### [不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/?fileGuid=DP6HxKP6xgpcHGP3)

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
#### [有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/?fileGuid=DP6HxKP6xgpcHGP3)

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[128];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int j = 0; j < t.length(); j++) {
            arr[t.charAt(j) - 'a']--;
            if (arr[t.charAt(j) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```
