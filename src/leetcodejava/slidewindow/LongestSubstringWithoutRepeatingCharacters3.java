package leetcodejava.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * This is the solution of No.3 problem in the LeetCode,
 * the website of the problem is as follow:
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * <p>
 * The description of problem is as follow:
 * ==========================================================================================================
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * ==========================================================================================================
 *
 * @author zhangyu (zhangyuyu417@gmail.com)
 */
public class LongestSubstringWithoutRepeatingCharacters3 {

    @Test
    public void pairsOfParenthesesTest() {
        String s = "abba";
        int result = lengthOfLongestSubstring2(s);
        System.out.println(result);
        Assert.assertEquals(result, 2);
    }

    /**
     * 最长子字符串
     *
     * @param s 字符串
     * @return 长度S
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            length = Math.max(length, i - left + 1);
        }
        return length;
    }

    /**
     * 最长子字符串
     *
     * @param s 字符串
     * @return 长度S
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] map = new int[128];
        int res = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            i = Math.max(map[ch], i);
            res = Math.max(res, j - i + 1);
            map[ch] = j + 1;
        }
        return res;
    }

    /**
     * 最长子字符串
     *
     * @param s 字符串
     * @return 长度S
     */
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int res = 0;
        int slow = 0;
        int[] lastAppear = new int[128];
        // 初始化map
        for (int i = 0; i < lastAppear.length; i++) {
            lastAppear[i] = -1;
        }
        for (int fast = 0; fast < s.length(); fast++) {
            char cur = s.charAt(fast);
            // 该字符出现，更新slow位置
            if (lastAppear[cur] > -1) {
                slow = Math.max(slow, lastAppear[cur] + 1);
            }
            lastAppear[cur] = fast;
            res = Math.max(res, fast - slow + 1);
        }
        return res;
    }
}
