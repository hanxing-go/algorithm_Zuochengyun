package Tree;

import java.util.*;

public class TreeTest {
    public static void main(String[] args) {
//        Node tree1 = initTree();
//        preOrder(tree1);
//        System.out.println();
//        inOrder(tree1);
//        System.out.println();
//        postOrder(tree1);
//        System.out.println();


        System.out.println("===================");
        Integer[] pre = {1, 2, 4, 5, 3, 6};
        Integer[] in = {4, 2, 5, 1, 6, 3};
        Integer[] post = {4, 5, 2, 6, 3, 1};

        Node tree2 = buildTreeByPreAndIn(pre, in);

        Integer[] pre1 = {7, 4, 3, 5, 9, 8, 11};
        Integer[] in1 = {3, 4, 5, 7, 8, 9, 11};

        Node tree3 = buildTreeByPreAndIn(pre1, in1);

        Integer[] pre2 = {8, 3, 1, 6, 4, 7, 10, 14, 13};
        Integer[] in2 = {1, 3, 4, 6, 7, 8, 10, 13, 14};

        Node tree4 = buildTreeByPreAndIn(pre2, in2);
//        preOrder(tree3);
//        System.out.println();
//        System.out.println("===================");
//
//        Node tree3 = buildTreeByInAndPost(in, post);
//        preOrder(tree3);
//        System.out.println();
//        System.out.println("===================");

//        preOrder1(tree2);
//        System.out.println();
//        preOrder(tree2);
//        System.out.println();
//        inOreder1(tree2);
//        System.out.println();
//        postOrder1(tree2);

        //判断一棵二叉树是否是搜索二叉树
//        System.out.println(checkSearchBinTree(tree2));
//        System.out.println(checkSearchBinTree(tree4));
//        System.out.println(checkSearchBinTreeMethod(tree2));
//        System.out.println(checkSearchBinTreeMethod(tree4));

        // 判断一棵树是否是完全二叉树
        //叶子结点只可能在最下面的两层上出现，对任意结点，若其右分支下的子孙最大层次为L，则其左分支下的子孙的最大层次必为L或L+1
        Integer[] pre3 = {1, 2, 4, 8, 9, 5, 10, 3, 6, 7};
        Integer[] in3 = {8, 4, 9, 2, 10, 5, 1, 6, 3, 7};

        Node tree5 = buildTreeByPreAndIn(pre3, in3);

        Integer[] pre4 = {1, 2, 4, 8, 9, 5, 10, 6, 3, 7};
        Integer[] in4 = {8, 4, 9, 2, 10, 5, 6, 1, 3, 7};

        Node tree6 = buildTreeByPreAndIn(pre4, in4);


//        postOrder(tree5);
//        System.out.println();
//        postOrder1(tree5);
//        System.out.println();
//        System.out.println(checkCompleteBinTree(tree5));
//        postOrder(tree6);
//        System.out.println(checkCompleteBinTree(tree6));
//        System.out.println(checkBalanceBinTree(tree6));
    }

    public static Node findfatherNode(Node node1, Node node2, Node root) {
        //您别说，您还真别说，我恰恰好记得一点
        //我们只需要把从root走到node1和走到node2的节点都保存下来
        // 然后顺序遍历，找到第一个不相同的节点，再返回到上一个相同的节点，那么就是我们的最低父节点
        List<Node> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();


        return null;
    }

    private static LinkedList<Node> markList(Node root, Node findnode) {
        // 来一个非递归的前序遍历不就行了


        return null;
    }

    public static boolean checkBalanceBinTree(Node root) {
        //如何判断是否为平衡树，关键在于右子树与左子树的高度差小于等于一，所以有多个返回值
        return checkBalanceBinTreeProcess(root).isBBT;
    }

    private static class ReturnBalanceType {
        boolean isBBT;
        int high;

        ReturnBalanceType(Boolean isBBT, int high) {
            this.isBBT = isBBT;
            this.high = high;
        }
    }

    private static ReturnBalanceType checkBalanceBinTreeProcess(Node root) {
        if (root == null) {
            return new ReturnBalanceType(true, 0);
        }

        //以我来看，这棵树的高度等于左子树与右子树的最高高度+1
        ReturnBalanceType left = checkBalanceBinTreeProcess(root.left);
        ReturnBalanceType right = checkBalanceBinTreeProcess(root.right);
        int high = Math.max(left.high, right.high);

        if (left.isBBT && right.isBBT && Math.abs(left.high - right.high) <= 1) {
            //左右子树都满足
            return new ReturnBalanceType(true, high + 1);
        }
        return new ReturnBalanceType(false, high + 1);
    }

    public static boolean checkCompleteBinTree(Node root) {
        //重点是：除了最后一层外，其他各层都填满了，且最后一层的节点都接种在左边
        //感觉可以用层序遍历
        if (root == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        boolean flag = true;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (flag) {
                if (cur.right != null && cur.left == null) {
                    return false;
                } else if (cur.left == null) {
                    flag = false;
                    //接下来的每一个节点都要满足没有子节点
                } else{
                    //说明既不是只有右节点，而没有左节点
                    //也不是没有左节点的情况
                    queue.add(cur.left);
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
            } else {
                if (cur.left != null || cur.right != null) {
                    return false;
                }
            }
        }

        return true;
    }

    private static class returnType {
        boolean isBST;
        int min;
        int max;

        returnType (boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    public static boolean checkSearchBinTreeMethod(Node root) {
        return checkSearchBinTree1(root).isBST;
    }
    private static returnType checkSearchBinTree1(Node root) {
        if (root == null) {
            return new returnType(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        returnType leftTree = checkSearchBinTree1(root.left);
        returnType rightTree = checkSearchBinTree1(root.right);

        int max = Math.max(root.value, Math.max(leftTree.max,rightTree.max));
        //要找的是最大值
        int min = Math.min(root.value, Math.min(leftTree.min,rightTree.min));

        if (!leftTree.isBST || !rightTree.isBST) {
            return new returnType(false, min, max);
            //如果左子树或者右子树有一个不成立，直接返回不成立就行了
        } else {
            if (leftTree.max >= root.value || rightTree.min <= root.value) {
                return new returnType(false, min, max);
            }
        }

        return new returnType(true, min, max);
    }
    public static Boolean checkSearchBinTree(Node root) {
        //搜索二叉树即，左子树的节点最大值要小于当前的节点，右子树的最小值要大于当前节点。
        if (root == null) {
            return true;
        }
        // 只有左子树和右子树同时都成立才为true，否则就是false
        if (checkSearchBinTree(root.left) && checkSearchBinTree(root.right)) {
            //除了左子树和右子树都要为真
            if (root.value > getMax(root.left) && root.value < getMin(root.right)) {
                return true;
            }
        }
     return false;
    }

    private static int getMin(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        int res = root.value;
        int leftMin = getMin(root.left);
        int rightMin = getMin(root.right);

        res = res < leftMin ? res : leftMin;
        res = res < leftMin ? res : leftMin;

        return res;
    }

    public static int getMax(Node root) {
        //获取这棵树的最大值
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int res = root.value;
        int leftMax = getMax(root.left);
        int rightMax = getMax(root.right);
        res = res > leftMax ? res : leftMax;
        res = res > rightMax ? res : rightMax;
        return res;
    }



    // 2. 非递归的方法的先序遍历，中序遍历，后序遍历
    public static void postOrder1(Node root) {
        if (root == null) {
            return;
        }
        //后序的非递归遍历要用两个栈
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            Node cur = stack1.pop();
            stack2.push(cur);

            if (cur.left != null) {
                stack1.push(cur.left);
            }

            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }

    }
    public static void inOreder1(Node root) {
        if (root == null) {
            return;
        }
        //没记错的话中序得要用栈
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                //如果当前节点不为空，就拼命压栈
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
    }

    public static void preOrder1(Node root) {
        if (root == null) {
            return;
        }
        //前序遍历用栈
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (cur != null) {
                System.out.print(cur.value + " ");
                stack.push(cur.right);
                stack.push(cur.left);
            }
        }
    }

    //1. 递归的先序遍历，中序遍历，后序遍历
    public static void postOrder(Node root) {
        if(root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);

        System.out.print(root.value + " ");
    }
    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }
    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static Node initTree() {
        Integer[] arr = {1, 2, 4, null, null, 5, null, null, 3, null, 6, null, null};
        return buildTree(arr,  arr.length);
    }
    // 利用先序遍历建立一棵树
    private static int index = 0;
    public static Node buildTree(Integer[] arr, int length) {
        Integer value = arr[index++];
        if (value == null) {
            return null;
        }

        Node root = new Node(value);
        root.left = buildTree(arr, length);
        root.right = buildTree(arr, length);
        return root;
    }

    // 利用中序和后序遍历来建树
    public static Node buildTreeByInAndPost(Integer[] in, Integer[] post) {
        // 中序与后序遍历和前序与后序遍历只是遍历的方向相反了，其他不变
        if (in == null || post == null || in.length == 0)
            return null;

        // 在中序遍历中找到根节点的位置，序号左边的就是左子树，序号右边的就是右子树
        int rootIndex = findIndex(in, post[post.length - 1]);
        // 建立根节点
        Node root = new Node(post[post.length - 1]);
        // 建立左子树
        Integer[] inLeft = Arrays.copyOfRange(in, 0, rootIndex);
        Integer[] postLeft = Arrays.copyOfRange(post, 0, rootIndex);
        root.left = buildTreeByInAndPost(inLeft, postLeft);

        // 建立右子树
        Integer[] inRight = Arrays.copyOfRange(in, rootIndex + 1, in.length);
        Integer[] postRight = Arrays.copyOfRange(post, rootIndex, post.length -1);
        root.right = buildTreeByInAndPost(inRight, postRight);

        return root;
    }

    // 利用先序和中序遍历来建树
    public static Node buildTreeByPreAndIn(Integer[] pre, Integer[] in) {
        if (pre == null || in == null || pre.length == 0) {
            return null;
        }

        int rootIndex = findIndex(in, pre[0]);
        //建立root根节点
        Node root = new Node(pre[0]);
        //建立左边子树
        Integer[] preLeft = Arrays.copyOfRange(pre, 1, rootIndex + 1);
        Integer[] inLeft = Arrays.copyOfRange(in, 0, rootIndex);
        root.left = buildTreeByPreAndIn(preLeft, inLeft);

        //建立右边子树
        Integer[] preRight = Arrays.copyOfRange(pre, rootIndex + 1, pre.length);
        Integer[] inRight = Arrays.copyOfRange(in, rootIndex + 1, in.length);
        root.right = buildTreeByPreAndIn(preRight, inRight);

        return root;
    }
    public static int findIndex(Integer[] in, int value) {
        //找到在中序遍历数组的位置
        for (int i = 0; i < in.length; i++) {
            if (in[i] == value) {
                return i;
            }
        }

        return -1;
    }
}

class Node {
    Integer value;
    Node left;
    Node right;

    Node (Integer v) {
        this.value = v;
    }
}
