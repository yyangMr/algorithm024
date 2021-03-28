#### [位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/?fileGuid=cCWYgdhGRrjKP3cR)

```java
public class Solution {
    public int hammingWeight(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }
}
```
```java
public class Solution {
    // 使用位操作 n & (n - 1) 可以将 n 的末尾的 1 删除 
    // 时间复杂度O(logn)
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
```
#### [2的幂](https://leetcode-cn.com/problems/power-of-two/?fileGuid=cCWYgdhGRrjKP3cR)

```java
class Solution {
    // 若满足2的幂次方,则一定满足 n&(n-1)==0;
    // n > 0;
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```
#### [颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/?fileGuid=cCWYgdhGRrjKP3cR)

```java
public class Solution {
    // 逐位颠倒
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0;i < 32; ++i){
            res = res << 1;
            res = n & 1 | res;
            n = n >> 1;
        }
        return res;
    }
}
```
#### [比特位计数](https://leetcode-cn.com/problems/counting-bits/?fileGuid=cCWYgdhGRrjKP3cR)

```java
class Solution {
    // O(n*sizeof(integer)) 的解法
    // n & (n - 1) 可以去掉末尾的1,如: 110 & 101 = 100 
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = count(i);
        }
        return res;
    }
    public int count(int n) {
        int res = 0;
        while (n > 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
```
```java
class Solution {
    // 动态规划
    // 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1, dp[i] = dp[i - 1] + 1;
    // 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多,dp[i] = dp[i/2];
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            if ((i & 1) == 1) dp[i] = dp[i - 1] + 1;
            else dp[i] = dp[i / 2]; 
        }
        return dp;
    }
}
```
#### [实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/?fileGuid=cCWYgdhGRrjKP3cR)

```java
class TreeNode {
    public char val;
    public boolean isWord;
    public TreeNode[] children = new TreeNode[26];
    public TreeNode () {}
    public TreeNode (char ch) {
        TreeNode node = new TreeNode();
        node.val = ch;    
    }
}
class Trie {
    public TreeNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
        root.val = ' ';
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TreeNode(c);
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return true;
    }
}
```
#### [单词搜索 II](https://leetcode-cn.com/problems/word-search-ii/?fileGuid=cCWYgdhGRrjKP3cR)

```java
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode node = buildTree(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, res, node);
            }
        }
        return res;
    }
    public void dfs (int i, int j, char[][] board, List<String> res, TrieNode node) {
        char ch = board[i][j];
        if (ch == '#' || node.children[ch - 'a'] == null) {
            return;
        }
        node = node.children[ch - 'a'];
        if (node.word != null) {
            res.add(node.word);
            node.word = null;  // 去重
        }
        board[i][j] = '#';
        if (i > 0) dfs(i - 1, j, board, res, node);
        if (j > 0) dfs(i, j - 1, board, res, node);
        if (i < board.length - 1) dfs(i + 1, j, board, res, node);
        if (j < board[0].length - 1) dfs(i, j + 1, board, res, node); 
        board[i][j] = ch;
    }
    public TrieNode buildTree(String[] words) {
    TrieNode root = new TrieNode();
    for (String w : words) {
        TrieNode p = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (p.children[i] == null) p.children[i] = new TrieNode();
            p = p.children[i];
       }
       p.word = w;
    }
    return root;
}
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word;
}
}
```
#### [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/?fileGuid=cCWYgdhGRrjKP3cR)

```java
class Solution {
// dfs
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从边缘o开始搜索
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length  || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            // board[i][j] == '#' 说明已经搜索过了. 
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j); // 上
        dfs(board, i + 1, j); // 下
        dfs(board, i, j - 1); // 左
        dfs(board, i, j + 1); // 右
    }
}
```
