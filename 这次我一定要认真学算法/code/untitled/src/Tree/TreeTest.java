package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTest {
    public static void main(String[] args) {
        Node tree1 = initTree();
        preOrder(tree1);
        System.out.println();
        inOrder(tree1);
        System.out.println();
        postOrder(tree1);
        System.out.println();


        System.out.println("===================");
        Integer[] pre = {1, 2, 4, 5, 3, 6};
        Integer[] in = {4, 2, 5, 1, 6, 3};
        Integer[] post = {4, 5, 2, 6, 3, 1};
//        Integer[] copy = Arrays.copyOfRange(pre,0, 0);

        Node tree2 = buildTreeByPreAndIn(pre, in);
        preOrder(tree2);
        System.out.println();
        System.out.println("===================");

        Node tree3 = buildTreeByInAndPost(in, post);
        preOrder(tree3);
        System.out.println();
        System.out.println("===================");

        preOrder1(tree2);

    }


    // 2. 非递归的方法的先序遍历，中序遍历，后序遍历
    public static void inOreder1(Node root) {
        if (root == null) {
            return;
        }
        //没记错的话中序得要用栈
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {

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
        System.out.println();
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

class Node<Integer> {
    Integer value;
    Node left;
    Node right;

    Node (Integer v) {
        this.value = v;
    }
}
