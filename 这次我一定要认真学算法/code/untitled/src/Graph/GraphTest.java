package Graph;

import java.util.*;

public class GraphTest {
    public static void main(String[] args) {

    }

    //1. 宽度优先搜索，利用队列来实现
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        HashSet<Node> set = new HashSet<>();//用来存储是否存在过某个节点
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    //2. 深度优先搜索，利用栈来实现
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();

        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    System.out.println(next.value);
                    set.add(next);
                    stack.push(cur);
                    stack.push(cur);
                    break;
                }
            }
        }
    }

    //3. 拓扑排序算法
    public static void topologtSort() {

    }
}

class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    //接口函数
    public static Graph creatGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];

            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }

        return graph;
    }

}

class Node {
    public int value;   //值
    public int in;  // 入度
    public int out; // 出度
    public ArrayList<Node> nexts;    //直接邻居
    public ArrayList<Edge> edges;   //这个点拥有的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }


}

class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}