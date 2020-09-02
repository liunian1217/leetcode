# encoding='utf-8'

'''
/**
 * This is the solution of No. 322 problem in the LeetCode,
 * the website of the problem is as follow:
 * https://leetcode-cn.com/problems/coin-change/
 *
 * The description of problem is as follow:
 * ==========================================================================================================
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * 来源：力扣（LeetCode）
 * ==========================================================================================================
 *
 * @author zhangyu (zhangyuyu417@gmail.com)
 */
'''
from typing import List


def helper(coins, count, index, amount, min_count):
    '''
        dfs帮助类
    Args:
        coins: 硬币数组
        count: 数量
        index: 下标
        amount: 金额
        min_count: 最小数
    '''
    if index < 0 or count + amount // coins[index] > min_count[0]:
        return
    if amount % coins[index] == 0:
        min_count[0] = min(min_count[0], count + amount // coins[index])
        return
    i = amount // coins[index]
    while i >= 0:
        helper(coins, count + i, index - 1, amount - i * coins[index], min_count)
        i -= 1

def coin_change(coins: List[int], amount: int) -> int:
    '''
        兑换硬币最小数
    Args:
        coins: 硬币数组
        amount: 金额大小
    Returns:
        最小数量
    '''
    min_count = [amount + 1]
    coins.sort()
    helper(coins, 0, len(coins) - 1, amount, min_count)
    return -1 if min_count[0] == amount + 1 else min_count[0]

if __name__ == '__main__':
    amount = 11
    coins = [1, 3, 5]
    result = coin_change(coins, amount)
    print(result)
