package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
//        inOreder1(tree2);
//        System.out.println();
//        postOrder1(tree2);

        //判断一棵二叉树是否是搜索二叉树
        System.out.println(checkSearchBinTree(tree2));
        System.out.println(checkSearchBinTree(tree3));
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
        //没记错的话先序是用队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.value + " ");

            if (cur.left != null)
            queue.add(cur.left);

            if (cur.right != null)
            queue.add(cur.right);
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
