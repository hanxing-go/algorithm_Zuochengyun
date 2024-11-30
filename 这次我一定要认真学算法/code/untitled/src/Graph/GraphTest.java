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
        HashSet<Node> set = new HashSet<>();
        //用来存储是否存在过某个节点
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
                    stack.push(cur);//因为当前节点可能有好多个节点，重新把当前节点压入栈中
                    stack.push(next);//在把当前节点的下一个节点压入栈中
                    break;
                }
            }
        }
    }

    //3. 拓扑排序算法
    public static List<Node> topologtSort(Graph graph) {
        //核心思想是先找到入度为0的点

        //先创建一个要返回的List集合
        List<Node> nodelist = new ArrayList<>();
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        //先找到入度为0的点
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
                //如果入度为0就把他加到这个队列
            }
        }


        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            //弹出当前入度为0的点，并且擦除他的影响
            nodelist.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                //如果入度为0则加入队列
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return nodelist;
    }

    // 4. K算法解决最小生成树问题
    public static class MySets{
        public HashMap<Node, List<Node>> setMap;
        public MySets(List<Node> nodes) {
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<Node>();
                set.add(cur);//每一个节点，对应一个单独的集合
                setMap.put(cur,set);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);

            return fromSet == toSet;
        }

        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);

            for (Node toNode : toSet) {
                fromSet.add(toNode);//将每一个to节点都加入到from集合中
                setMap.put(toNode, fromSet);//同时把toNode对应的集合全部更新为fromSet
            }
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        List<Node> nodes = new ArrayList<>();
        Collection<Node> values = graph.nodes.values();
        Iterator<Node> it = values.iterator();
        while (it.hasNext()) {
            nodes.add(it.next());
        }
        MySets mySets = new MySets(nodes);
        //利用一个优先队列，其实就是一个堆结构
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);//将每一条边加入到优先队列中
        }
        Set<Edge> res = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge e = priorityQueue.poll();
            //找到最小边的from点和to点，判断是否在一个集合
            if (!mySets.isSameSet(e.from, e.to)) {
                mySets.union(e.from, e.to);
                res.add(e);
            }
        }
        return res;
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