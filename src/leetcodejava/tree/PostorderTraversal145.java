package leetcodejava.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This is the solution of No.145 problem in the LeetCode,
 * the website of the problem is as follow:
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * <p>
 * The description of problem is as follow:
 * ==========================================================================================================
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * ==========================================================================================================
 *
 * @author zhangyu (zhangyuyu417@gmail.com)
 */
public class PostorderTraversal145 {

    @Test
    public void testPostorderTraversal() {
        TreeNode t1 = TreeNode.createBinaryTreeByArray(new Integer[]{1, 2, 3, 4, 5}, 0);
        List<Integer> postOrder = postOrderTraversal2(t1);
        System.out.println(postOrder);
    }

    /**
     * 后序遍历递归方式
     *
     * @param root 根节点
     * @return 链表
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        helper(list, root);
        return list;
    }

    /**
     * 帮助方法
     *
     * @param list 链表
     * @param root 根节点
     */
    private void helper(List<Integer> list, TreeNode root) {
        if (root.left != null) {
            helper(list, root.left);
        }
        if (root.right != null) {
            helper(list, root.right);
        }
        list.add(root.val);
    }

    /**
     * 后序遍历非递归方式
     *
     * @param root 根节点
     * @return 链表
     */
    public List<Integer> postOrderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            res.add(0, node.val);
        }
        return res;
    }
}
