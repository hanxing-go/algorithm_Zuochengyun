package Node;

import java.util.*;

public class NodeTest {
    public static void main(String[] args) {
        Node h1 = bulidLinklist();
        printLinkList(h1);

        DoubleNode d1 = buildDoubleLinklist();
        printDoubleLinklist(d1);
        System.out.println("================================");


//        //1. 反转单链表 时间复杂度为O(N), 额外空间复杂度为O(1)
//        Node h2 = reverseLinklist(h1);
//        printLinkList(h2);
//        //2, 反转双链表
//        DoubleNode d2 = reverseDoubleLinklist(d1);
//        printDoubleLinklist(d2);
//        System.out.println("================================");

//        //3. 打印两个有序链表的公共部分
//        // (1) 要求时间复杂度为O(N) ，额外空间复杂度不要求
//        Node t1 = new Node<>(10,h1);
////        printCommonPart1(t1,h1.next);
//        // (2) 要求时间复杂度为O(N), 额外空间复杂度为O(1)
//        printCommonPart1(t1,h1);

//        //4. 判断一个链表是否为回文结构
//        //(1) 要求时间复杂度达到O(N), 额外空间复杂度达到O(N)
//        System.out.println(checkPalindrome(h1));
//        System.out.println(checkPalindrome1(h1));

        //5.将单向链表按某值划分为左边小，中间相等，右边大的形式
        //要求时间复杂度为O(N)，空间复杂度为O(N)
//        printLinkList(divideLinklist(h1,3));
//        printLinkList(divideLinklist1(h1,10));

        //6. 复制含有随机指针节点的链表
        //懒得验证了直接...

        //7. 两个单链表相交的一系列问题，要求找到相交的节点，若无，则返回null
        //分情况讨论



    }
    public static Node intersect(Node head1, Node head2) {
        //分两类情况讨论就行
        // 第一种情况: 两个链表都无环，那么就可以按照打印两个链表的公共部分一样，找到相交的那个节点
        //第二种情况: 两个链表都有环，则要么不想交，要么两个链表共用一个入环口，要么有两个入环口
        Node res = null;
        //所以我们首先是要判断链表是否有环，并且找到入环口
        Node loop1 = checkToroidal(head1);//找到链表1的入环口
        Node loop2 = checkToroidal(head2);//找到链表2的入环口

        if (loop1 == null && loop2 == null) {
            //如果两个链表都没有环，则利用前面写的一个打印相同链表部分方法，来找相交点
            res = findIntersect(head1, head2);
        } else if ((loop1 == null && loop2 != null) || (loop1 != null && loop2 == null)) {
            res = null;//不可能存在一个有环，一个无环但是相交
        } else {
            //如果两个都有环，那么分两种情况
            if (loop1 == loop2) {
                res = loop1;
            } else {
                Node tmp = loop1.next;
                while (tmp != loop1) {
                    if (tmp == loop2) {
                        return tmp;
                    }
                    tmp = tmp.next;
                }
            }
        }

        return res;
    }

    public static Node findIntersect(Node n1, Node n2) {
        //2. 第二种思路，用两个指针，和一个计数器
        Node p1 = n1;
        Node p2 = n2;
        int count = 0;
        while (p1 != null) {
            count ++;
            p1 = p1.next;
        }
        p1 = n2;
        while (p2 != null) {
            count --;
            p2 = p2.next;
        }
        p1 = count < 0 ? n2 : n1;//如果count为负数，说明n2链表更长，p1指针指向n2
        p2 = p1 == n1 ? n2 : n1;//如果p1指向n1，则p2指向n2，否则指向n1
        count = count < 0 ? -count : count;//判断是否为负数，如果是则变为正数

        while (count > 0) {
            p1 = p1.next;
        }

        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;//如果找到了相同的节点，就说明找到了相交的点，直接返回即可
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return null;
    }
    public static Node checkToroidal(Node head) {
        //使用快慢指针方法
        Node fast = head;
        Node slow = head;

        //如果链表为空或者只有一个节点则直接返回null 表示不是有环链表
        if (head == null || head.next == null) {
            return null;
        }
        while (fast.next != null && fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast直接跑到了链表结尾，则直接说明链表无环
        if (fast == null) {
            return null;
        } else {
            //快指针回到head的位置，并且速度和慢指针一样
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return fast;
    }

    public static RandNode copyLinklist1(RandNode head) {
        //第二种思路很巧妙，我们只需要把我们copy的节点放入到待拷贝的节点的后一个位置
        RandNode p = head;
        while (p != null) {
            RandNode copyElement = new RandNode(p.value);
            RandNode tmp = p.next;
            //插入新拷贝的这个节点
            p.next = copyElement;
            copyElement.next = tmp;
            //注意此时我们并没有为rand附上值
            p = tmp;
        }

        //将所有元素拷贝好之后，我们容易得出如下规律,拷贝的元素的rand指针指向的是元素rand指针指向节点的后一个节点
        RandNode copyPoint = head.next;
        RandNode Point = head;
        while (copyPoint.next != null) {
            copyPoint.rand = Point.rand;
            Point = Point.next.next;
            copyPoint = copyPoint.next.next;
        }
        //最后把拷贝的链表拎出来
        RandNode neaHead = head.next;

        Point = head;
        copyPoint = head.next;
        while (copyPoint.next != null) {
            Point.next = Point.next.next;
            Point = Point.next;

            copyPoint.next = copyPoint.next.next;
            copyPoint = copyPoint.next;
        }
        return neaHead;
    }
    public static RandNode copyLinklist(RandNode head) {
        //第一种简单的想法，我先把所有的节点，都加到一个ArrayList上面，如果他们就会有各自序号，可以通过序号来copy
        List<RandNode> list = new ArrayList<>();
        List<RandNode> copylist = new ArrayList<>();
        RandNode p = head;
        while (p != null) {
            list.add(p);
            RandNode copyelement = new RandNode(p.value);
            p = p.next;
        }

        for (int i = 0; i < list.size(); i++) {
            int nextIndex = list.indexOf(list.get(i).next);//获取上一个节点的index
            copylist.get(i).next = copylist.get(nextIndex);

            int randIndex = list.indexOf(list.get(i).next);
            copylist.get(i).next = copylist.get(randIndex);
        }
        RandNode newhead = copylist.get(0);

        return newhead;
    }
    public static<V> Node divideLinklist1(Node head, V value) {
        //其实要求变高了难度也没有提高多少，刚才我们用的是一个ArrayList来去存，但其实我们可以直接用节点来串起来
        //分别定义三组头尾节点，更小的，相等的，更大的，但是要判断边界条件，即某一组不存在的情况
        Node smalleRear = new Node<>(0,null);
        Node smallerHead = new Node<Integer>(0,smalleRear);

        Node biggerRear = new Node<>(0,null);
        Node biggerHead = new Node<Integer>(0, biggerRear);

        Node equalRear = new Node<>(0,null);
        Node equalHead = new Node<Integer>(0, equalRear);


        Node tmpSmaller = smallerHead;
        Node tmpBigger = biggerHead;
        Node tmpEqual = equalHead;
        Node p = head;
        //先把他们分开
        while (p != null) {
            Node tmp = p;
            p = p.next;
            if (tmp.value.compareTo(value) == 0) {
                tmpEqual.next = tmp;
                tmp.next = equalRear;
                tmpEqual = tmp;
            } else if (tmp.value.compareTo(value) < 0) {
                tmpSmaller.next = tmp;
                tmp.next = smalleRear;
                tmpSmaller = tmp;
            } else {
                tmpBigger.next = tmp;
                tmp.next = biggerRear;
                tmpBigger = tmp;
            }
        }
        //接下来就是把他们串起来
        Node newhead = new Node<>(0,null);
        p = newhead;
        while (smallerHead.next != smalleRear) {
            p.next = smallerHead.next;
            smallerHead = smallerHead.next;
            p = p.next;
        }
        while (equalHead.next != equalRear) {
            p.next = equalHead.next;
            equalHead = equalHead.next;
            p = p.next;
        }
        while (biggerHead.next != biggerRear) {
            p.next = biggerHead.next;
            biggerHead = biggerHead.next;
            p = p.next;
        }
        p.next = null;//无论什么时候停下来，p指针的最后一个肯定要改为null

        return newhead.next;
    }

    public static<V> Node divideLinklist(Node head, V value) {
        //先是最简单的一种方法，这并不难，我们只需要借助ArrayList就可以了
        ArrayList<Node> smaller = new ArrayList<>();
        ArrayList<Node> bigger = new ArrayList <>();
        ArrayList<Node> equal = new ArrayList <>();

        Node p = head;
        while (p != null) {
            if (p.value.compareTo(value) == 0) {
                equal.add(p);
            } else if (p.value.compareTo(value) < 0) {
                smaller.add(p);
            } else {
                bigger.add(p);
            }
            p = p.next;
        }
        //遍历三个容器

        Node newhead = new Node<>(0,null);
        Node tmp = newhead;
        while (!smaller.isEmpty()) {
            tmp.next = smaller.get(0);
            smaller.remove(0);
            tmp = tmp.next;
        }
        while (!equal.isEmpty()) {
            tmp.next = equal.get(0);
            equal.remove(0);
            tmp = tmp.next;
        }
        while (!bigger.isEmpty()) {
            tmp.next = bigger.get(0);
            bigger.remove(0);
            tmp = tmp.next;
        }
        tmp.next = null;
        return newhead.next;
    }
    public static boolean checkPalindrome1(Node head) {
        //考虑空间复杂度的话，就可以用快慢指针了
        //已知快指针速度是慢指针速度的两倍，所以当快指针到达终点的时候，慢指针刚好到达中间的位置。
        //接下来我们只要分清楚奇数个节点和偶数个节点的区别就行了
        //假设为偶数个，即有2k个节点，那么 快指针就是走 （2k-1）/2个步数 慢指针就是 [2k-1]（向上取整），刚好走到后半截的第一个位置
        //举例：4个节点时候，慢指针位于第三个节点
        //   ：6个节点时候，慢指针位于第四个节点
        //假设为奇数个，即2k-1个节点，那么快指针就是走（2k-2)/2 = k - 1个步数，那么慢指针就位于第K个节点，刚好正中间
        //举例：5个节点的时候，慢指针刚好位于第3个节点
        if (head == null || head.next == null) {
            return true;//这里考虑空串也是回文的一种
        }
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //奇数和偶数要分开考虑
        //奇数的话要从slow的下一个节点开始，如果fast位于最后一个节点，则说明是奇数个
        //偶数的话直接就可以了，如果fast位于的是null，则说明是偶数个
        slow = fast == null ? slow : slow.next;
        //接下来在reverse一下，从slow的位置开始
        slow = reverseLinklist(slow);


        fast = head;//这个时候快指针速度要和慢指针一样
        while (slow != null) {
            if (slow.value.compareTo(fast.value) != 0) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static boolean checkPalindrome(Node head) {
        //不考虑空间复杂度的话很简单，可以利用栈的结构
        //众所周知，stack可以让输入的东西，反着的顺序输出
        //而回文要求的就是输入和输出保持一致

        Stack<Node> stack = new Stack<>();
        Node p = head;
        while(p != null) {
            stack.push(p);
            p = p.next;
        }
        p = head;
        while (p != null) {
            Node tmp = stack.pop();
            if (tmp.value.compareTo(p.value) != 0) {
                return false;
            }
//            System.out.println(p.value + " " + tmp.value);
            p = p.next;
        }
        return true;
    }

    public static void printCommonPart3(Node n1, Node n2) {
        //3. 如果两个链表有序的话，题目就会变得非常简单
        while (n1 != null && n2 != null) {
            if (n1.value.compareTo(n2.value) == 0) {
                System.out.print(n1.value + " ");
            } else if (n1.value.compareTo(n2.value) < 0) {
                n1 = n1.next;
            } else {
                n2 = n2.next;
            }
        }
    }

    public static void printCommonPart2(Node n1, Node n2) {
        //2. 第二种思路，用两个指针，和一个计数器
        Node p1 = n1;
        Node p2 = n2;
        int count = 0;
        while (p1 != null) {
            count ++;
            p1 = p1.next;
        }
        p1 = n2;
        while (p2 != null) {
            count --;
            p2 = p2.next;
        }
        p1 = count < 0 ? n2 : n1;//如果count为负数，说明n2链表更长，p1指针指向n2
        p2 = p1 == n1 ? n2 : n1;//如果p1指向n1，则p2指向n2，否则指向n1
        count = count < 0 ? -count : count;//判断是否为负数，如果是则变为正数

        while (count > 0) {
            p1 = p1.next;
        }

        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                System.out.println(p1.value + " ");
            }
            p1 = p1.next;
            p2 = p2.next;
        }

    }
    public static void printCommonPart1(Node n1, Node n2) {
        //1. 第一种思路，用HashList
        Set<Node> healper = new HashSet<>();

        while (n1 != null) {
            healper.add(n1);
            n1 = n1.next;
        }

        while (n2 != null) {
            if (healper.contains(n2)) {
                System.out.print(n2.value + " ");
            }
            n2 = n2.next;
        }
    }
    public static DoubleNode reverseDoubleLinklist(DoubleNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode p1 = head.next;
        DoubleNode p2 = head;

        while (p1 != null) {
            p2.next = p2.last;
            p2.last = p1;
            p2 = p1;
            p1 = p2.next;
        }

        p2.next = p2.last;
        p2.last = null;
        return p2;
    }

    public static Node reverseLinklist(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node p1 = head.next;
        Node p2 = head;
        p2.next = null;//我靠，竟然忘记这一步，逆天了

        while (p1 != null) {
            Node tmp = p1.next;
            p1.next = p2;
            p2 = p1;
            p1 = tmp;
        }
        return p2;
    }

    //建立单链表
    public static Node bulidLinklist() {
        Node<Integer> n1 = new Node<>(8,null);
        Node<Integer> n2 = new Node<Integer>(4,n1);
        Node<Integer> n3 = new Node<Integer>(3,n2);
        Node<Integer> n4 = new Node<Integer>(9,n3);
        Node<Integer> n5 = new Node<Integer>(6,n4);

        return n5;
    }

    //打印单链表
    public static void printLinkList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    //建立双链表
    public static DoubleNode buildDoubleLinklist() {
        DoubleNode<Integer> n1 = new DoubleNode<>(1,null,null);
        DoubleNode<Integer> n2 = new DoubleNode<Integer>(2, n1,null);
        n1.next = n2;
        DoubleNode<Integer> n3 = new DoubleNode<Integer>(3, n2,null);
        n2.next = n3;
        DoubleNode<Integer> n4 = new DoubleNode<Integer>(4,n3,null);
        n3.next = n4;
        DoubleNode<Integer> n5 = new DoubleNode<Integer>(5,n4,null);
        n4.next = n5;
        return n1;
    }

    public static void printDoubleLinklist(DoubleNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}

 class Node<V extends Comparable<V>> {//单链表
    V value;
    Node next;

    public Node(V value,Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}

class DoubleNode<V> {//双链表
    V value;
    DoubleNode last;
    DoubleNode next;

    public DoubleNode(V value, DoubleNode last, DoubleNode next) {
        this.value = value;
        this.last = last;
        this.next = next;
    }
}

class RandNode {
    int value;
    RandNode next;
    RandNode rand;
    public RandNode (int val) {
        value = val;
    }
}