import java.util.Stack;

//一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，
// 只需申请一个栈。除此之外，可以申请新的变量，但是不能申请额外的数据结构。该如何排序？
public class SortStack {
    private Stack<Integer> toolstack;

    public SortStack() {
        this.toolstack = new Stack<Integer>();
    }

    public Stack<Integer> sort(Stack<Integer> s) {
        int tmp;
        if (s.isEmpty()) {
            System.out.println("stack is empty");
            return s;
        }
        while (! s.isEmpty()) {
            tmp = s.pop();
            if (toolstack.isEmpty()) {
                toolstack.push(tmp);
            } else {
                if (tmp >= toolstack.peek()) {
                    toolstack.push(tmp);
                } else if(tmp < toolstack.peek()) {
                    while(! toolstack.isEmpty() && toolstack.peek() > tmp) {
                        s.push(toolstack.pop());
                    }
                    toolstack.push(tmp);
                }
            }
        }
        return toolstack;
    }
}
