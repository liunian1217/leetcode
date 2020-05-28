package leetcodejava.string;

import org.junit.Test;

import java.util.*;

/**
 * This is the solution of No. 49 problem in the LeetCode,
 * the website of the problem is as follow:
 * https://leetcode-cn.com/problems/group-anagrams
 *
 * The description of problem is as follow:
 * ==========================================================================================================
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * ==========================================================================================================
 *
 * @author  zhangyu (zhangyuyu417@gmail.com)
 */
public class GroupAnagrams49 {
    @Test
    public void testGroupAnagrams() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> list = groupAnagrams(strs);
        System.out.println(list);
        System.out.println(Arrays.toString(strs));
    }

    /**
     * 生成镶嵌链表
     *
     * @param strs 字符串数组
     * @return 链表
     */
    private List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> newList = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals("1"))
                continue;
            List<String> subList = new ArrayList<>();
            if (i < strs.length - 1) {
                subList.add(strs[i]);
                strs[i] = 1 + "";
            } else {
                if (!"1".equals(strs[i])) {
                    subList.add(strs[i]);
                    strs[i] = 1 + "";
                    newList.add(subList);
                    continue;
                }
            }
            for (int j = i + 1; j < strs.length; j++) {
                String test1 = sortString(strs[j]);
                if (sortString(strs[i]).equals(test1)) {
                    subList.add(strs[j]);
                    strs[j] = 1 + "";
                }
            }
            newList.add(subList);
        }
        return newList;
    }

    /**
     * 生成镶嵌链表
     *
     * @param strs 字符串数组
     * @return 链表
     */
    private List<List<String>> groupAnagrams2(String[] strs) {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个z
        System.out.println(prime.length);
        List<List<String>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String s : strs) {
            int key = 1;
            for (char c : s.toCharArray()) {
                key *= prime[c - 'a'];
            }
            List<String> t;
            if (map.containsKey(key)) {
                t = res.get(map.get(key));
            } else {
                t = new ArrayList<>();
                res.add(t);
                map.put(key, res.size() - 1);
            }
            t.add(s);
        }
        return res;
    }

    /**
     * 生成镶嵌链表
     *
     * @param strs 字符串数组
     * @return 链表
     */
    private List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String newStr = new String(chs);
            if (!map.containsKey(newStr)) {
                map.put(newStr, new ArrayList<String>());
            }
            map.get(newStr).add(str);
        }
        return new ArrayList(map.values());
    }

    /**
     * 字符串进行排序
     *
     * @param s 字符串
     * @return 排序好的字符串
     */
    public String sortString(String s) {
        char chs[] = s.toCharArray();
        Arrays.sort(chs);
        return new String(chs);
    }
}
