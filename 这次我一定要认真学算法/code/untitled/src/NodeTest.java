import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        //3. 打印两个有序链表的公共部分
        // (1) 要求时间复杂度为O(N) ，额外空间复杂度不要求
        Node t1 = new Node<>(10,h1);
//        printCommonPart1(t1,h1.next);
        // (2) 要求时间复杂度为O(N), 额外空间复杂度为O(1)
        printCommonPart1(t1,h1);

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
        Node<Integer> n1 = new Node<>(1,null);
        Node<Integer> n2 = new Node<>(2,n1);
        Node<Integer> n3 = new Node<>(3,n2);
        Node<Integer> n4 = new Node<>(4,n3);
        Node<Integer> n5 = new Node<>(5,n4);

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
        DoubleNode<Integer> n2 = new DoubleNode<>(2,n1,null);
        n1.next = n2;
        DoubleNode<Integer> n3 = new DoubleNode<>(3,n2,null);
        n2.next = n3;
        DoubleNode<Integer> n4 = new DoubleNode<>(4,n3,null);
        n3.next = n4;
        DoubleNode<Integer> n5 = new DoubleNode<>(5,n4,null);
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