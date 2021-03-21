#### [有效的数独](https://leetcode-cn.com/problems/valid-sudoku/?fileGuid=qKVJ3RWQcgVj38Qt)

```java
class Solution {
    //官方题解用的hashmap，但采用数组更方便
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] col = new int[9][9];
        int[][] sbox = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]!='.'){
                    int num = board[i][j] - '1';
                    // 计算字数独的索引方法 (row/3)*3 + col/3
                    int index_box = (i/3)*3+j/3;
                    // 将对应数的索引位置修改为1,来判断数字是否已经出现过
                    if (rows[i][num]==1) { return false;}
                    else { rows[i][num]=1; }
                    if (col[j][num]==1) { return false;}
                    else { col[j][num]=1; }
                    if (sbox[index_box][num]==1)  { return false;}
                    else { sbox[index_box][num]=1; }
                }
            }
        }
        return true;
    }
}
```
#### [解数独](https://leetcode-cn.com/problems/sudoku-solver/?fileGuid=qKVJ3RWQcgVj38Qt)

```java
class Solution {
    public void solveSudoku(char[][] board) {
        // 回溯
        if (board == null || board.length == 0) {
            return;
        }
        help(board);
    }
    public boolean help(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char ch = '1'; ch <= '9'; ch++) {
                        if (isValid(board, ch, i, j)) {
                            board[i][j] = ch;
                            if (help(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        } 
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValid(char[][] board,char ch,int row, int col) {
        // 判断字符ch在行,列,子数独中是否已经出现过
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != '.' && board[i][col] == ch) return false;
            if (board[row][i] != '.' && board[row][i] == ch) return false;
            if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] != '.' && board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == ch) return false; 
        }
        return true;
    }
}
```
#### [滑动谜题](https://leetcode-cn.com/problems/sliding-puzzle/?fileGuid=qKVJ3RWQcgVj38Qt)

```java
class Solution {
     int[][] exchangeArray = new int[][]{
        {1, 3},
        {0, 2, 4},
        {1, 5},
        {0, 4},
        {1, 3, 5},
        {2, 4}
    };
    // 交换字符
     public String exchangeString(String string, int src, int dis) {
        char[] chars = string.toCharArray();
        char temp = chars[dis];
        chars[dis] = chars[src];
        chars[src] = temp;
        return new String(chars);
    }
    public int slidingPuzzle(int[][] board) {
         // 初始状态转字符串
        char[] chars = new char[6];
        int index = 0;
        for (int[] row:board) {
            for (int ch:row) {
                chars[index++] = (char)(ch+'0');
            }
        }
        String start = new String(chars);
        String target = "123450";
        // BFS套路
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.offer(start);
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
          String cur = q.poll();
                // 解开谜板
                if (cur.equals(target)) {
                    return step;
                }
                int position = cur.indexOf('0');
                int[] exchanges = exchangeArray[position];
                for(int next:exchanges) {
                    String s = exchangeString(cur, position, next);
                    if (!visited.contains(s)) {
                        q.offer(s);
                        visited.add(s);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
```
