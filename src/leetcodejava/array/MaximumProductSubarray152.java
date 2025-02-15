package leetcodejava.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * This is the solution of No. 152 problem in the LeetCode,
 * the website of the problem is as follow:
 * https://leetcode-cn.com/problems/maximum-product-subarray
 * <p>
 * The description of problem is as follow:
 * ==========================================================================================================
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * ==========================================================================================================
 *
 * @author zhangyu (zhangyuyu417@gmail.com)
 */
public class MaximumProductSubarray152 {

    @Test
    public void maximumProductSubarrayTest() {
        int[] nums = {2, 3, -2, 4};
        int result = maxProduct2(nums);
        System.out.println(result);
        Assert.assertEquals(result, 6);
    }

    /**
     * 最大子数组乘积
     *
     * @param nums 数组
     * @return 最大乘积
     */
    private int maxProduct1(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int length = nums.length;
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int product = multiply(nums, i, j);
                maxProduct = Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }

    /**
     * 数组相乘
     *
     * @param nums 数组
     * @param i    位置i
     * @param j    位置j
     * @return 最大值
     */
    private int multiply(int[] nums, int i, int j) {
        if (nums == null && nums.length < 1) {
            return 0;
        }
        int total = 1;
        for (int k = i; k <= j; k++) {
            total *= nums[k];
        }
        return total;
    }

    /**
     * 最大连续子数组乘积
     *
     * @param nums 数组
     * @return 最大乘积
     */
    private int maxProduct2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }

    /**
     * 最大子数组乘积
     *
     * @param nums 数组
     * @return 最大乘积
     */
    public int maxProduct3(int[] nums) {
        int maxResult = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int num : nums) {
            if (num < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);
            // 记录最大值
            maxResult = Math.max(maxResult, imax);
        }
        return maxResult;
    }
}
