package leetcodejava.top100likedquestions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This is the solution of No.56 problem in the LeetCode,
 * the website of the problem is as follow:
 * https://leetcode-cn.com/problems/merge-intervals
 * <p>
 * The description of problem is as follow:
 * ==========================================================================================================
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 来源：力扣（LeetCode）
 * ==========================================================================================================
 *
 * @author zhangyu (zhangyuyu417@gmail.com)
 */
public class MergeIntervals56 {

    @Test
    public void mergeIntervalsTest() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merge = merge(intervals);
        System.out.println(merge);
    }

    /**
     * 合并区间
     *
     * @param intervals 区间数组
     * @return 合并后的区间
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(final int[] entry1, final int[] entry2) {
                final int time1 = entry1[0];
                final int time2 = entry2[0];
                return time1 - time2;
            }
        });
        List<int[]> list = new ArrayList<>();
        if (intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }
        int[] curInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= curInterval[1]) {
                curInterval[1] = Math.max(intervals[i][1], curInterval[1]);
            } else {
                list.add(curInterval);
                curInterval = intervals[i];
            }
        }
        list.add(curInterval);
        int[][] ret = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}
