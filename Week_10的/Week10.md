#### [转换成小写字母](https://leetcode-cn.com/problems/to-lower-case/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
    public String toLowerCase(String str) {
        char[] Str = str.toCharArray();
        for (int i = 0; i < Str.length; i++) {
            if (Str[i] >= 'A' && Str[i] <= 'Z') {
                Str[i] = (char)(Str[i] + 32);
            }
        }
        return new String(Str);
    }
}
```
```java
class Solution {
//用位运算的技巧：https://leetcode-cn.com/problems/to-lower-case/solution/709zhuan-huan-cheng-xiao-xie-zi-mu-java-by-yijiaoq/
//大写变小写、小写变大写：字符 ^= 32;
//大写变小写、小写变小写：字符 |= 32;
//大写变大写、小写变大写：字符 &= -33;
    public String toLowerCase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] ch = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            ch[i] |= 32;
        }
        return String.valueOf(ch);
    }
}
```
#### [最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
// 从后向前遍历字符串并统计
    public int lengthOfLastWord(String s) {
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                count++;
            } else if (s.charAt(i) == ' ' && count > 0) {
                break;
            } else {
                continue;
            }
        }
        return count;
    }
}
```
#### [宝石与石头](https://leetcode-cn.com/problems/jewels-and-stones/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
// 哈希集合 
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }
        for (int j = 0; j < stones.length(); j++) {
            if (set.contains(stones.charAt(j))) {
                count++;
            }
        }
        return count;
    }
}
```
#### [字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
//哈希集合 
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i; 
        }
        return -1;
    }
}
```
```java
class Solution {
// 使用数组，比哈希表快很多
    public int firstUniqChar(String s) {
       int[] arr = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i)-'a']++ ;
        }
        for (int i = 0; i < n; i++) {
            if (arr[s.charAt(i)-'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
```
#### [最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
//以最短的字符串长度遍历字符串数组
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return ""; 
        int minLen = Integer.MAX_VALUE;
        StringBuilder str = new StringBuilder();
        // 最短的字符串长度，公共前缀的最大长度
        for (int i = 0; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        char ch = ' ';
        for (int i = 0; i < minLen; i++) {
            for (int j = 0; j < strs.length; j++) {
            // 遍历字符串数组  
                if (j > 0 && strs[j].charAt(i) == ch) {
                    continue;
                } else if (j == 0) {
                    ch = strs[j].charAt(i);
                } else {
                    return str.toString();
                }
            }
            str.append(ch);
        }
        return str.toString();
    }
}
```
#### [反转字符串](https://leetcode-cn.com/problems/reverse-string/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char tmp = s[i];
            s[i++] = s[j];
            s[j--] = tmp;
        }
    }
}
```
#### [反转字符串 II](https://leetcode-cn.com/problems/reverse-string-ii/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
// 顺序遍历，索引移动步长为2k，依次反转
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int start = 0; start < s.length(); start += 2 * k) {
            int i = start;
            // 剩余翻转字符数小于k个时，以字符串长度为准
            int j = Math.min(i + k - 1, s.length() - 1);
            while (i < j) {
                char tmp = arr[i];
                arr[i++] = arr[j];
                arr[j--] = tmp;
            }
        }
        return new String(arr);
    }
}
```
#### [反转字符串中的单词 III](https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] == ' ') {
                reverse(arr, start, i - 1);
                start = i + 1;
                continue;
            }
            if (i == s.length() - 1) {
                reverse(arr, start, s.length() - 1);
            }
        }
        return new String(arr);
    }
    public void reverse (char[] arr, int i, int j) {
        while (i < j) {
            char tmp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tmp;
        }
    }
}
```
#### [仅仅反转字母](https://leetcode-cn.com/problems/reverse-only-letters/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
    public String reverseOnlyLetters(String S) {
        char[] arr = S.toCharArray();
        int i = 0, j = S.length() - 1;
        while (i < j) {
            while (i < S.length() &&!Character.isLetter(arr[i])) {
                i++;
            }
            while (j > 0 && !Character.isLetter(arr[j])) {
                j--;
            }
            if (i < j) {
                help(arr, i, j);
                i++;
                j--;
            }
        }
        return new String(arr);
    }
    public void help(char[] arr, int left, int right) {
        char tmp = arr[right];
        arr[right] = arr[left];
        arr[left] = tmp;
    }
}
```
#### [验证回文串](https://leetcode-cn.com/problems/valid-palindrome/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if (i < j && Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) return false;
        }
        return true;
    }
}
```
#### [验证回文字符串 Ⅱ](https://leetcode-cn.com/problems/valid-palindrome-ii/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        char[] arr = s.toCharArray();
        while (i < j) {
            if (arr[i] != arr[j]){
                // 两种情况，一种去掉右边字符，一种去掉左边字符
                return help(arr, i, j - 1) || help(arr, i + 1, j);
            } 
            i++;j--;
        }
        return true;
    }
    public boolean help (char[] arr, int i, int j) {
        while (i < j) {
            if (arr[i++] != arr[j--]) return false; 
        }
        return true;
    }
}
```
#### [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
}
```
#### [字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/?fileGuid=GGRccXRGCpwhdhxq)

```java
class Solution {
    public int myAtoi(String str) {
        int len = str.length();
        // str.charAt(i) 会去检查下标的合法性，一般先转换成字符数组
        char[] charArray = str.toCharArray();
        // 1、去除前导空格
        int index = 0;
        while (index < len && charArray[index] == ' ') {
            index++;
        }
        // 2、如果已经遍历完成（针对极端用例 "      "）
        if (index == len) {
            return 0;
        }
        // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        int sign = 1;
        char firstChar = charArray[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }
        // 4、将后续出现的数字字符进行转换
        // 不能使用 long 类型，这是题目说的
        int res = 0;
        while (index < len) {
            char currChar = charArray[index];
            // 4.1 先判断不合法的情况
            if (currChar > '9' || currChar < '0') {
                break;
            }
            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }
            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            res = res * 10 + sign * (currChar - '0');
            index++; 
        }
        return res;
    }
}
```
