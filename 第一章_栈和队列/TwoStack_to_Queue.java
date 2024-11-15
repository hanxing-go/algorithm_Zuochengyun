import java.util.Stack;

public class TwoStack_to_Queue {
    private Stack<Integer> stackpush;
    private Stack<Integer> stackpop;

    public TwoStack_to_Queue() {
        stackpush = new Stack<Integer>();
        stackpop = new Stack<Integer>();
    }

    public void add(int num) {
        stackpush.push(num);
    }

    public int poll() {
        if (stackpush.empty() && stackpop.empty()) {
            throw new RuntimeException("Your queue is empty");
        }
        else if (stackpop.empty()) {
            while (!stackpush.empty()) {
                stackpop.push(stackpush.pop());
            }
        }
        return stackpop.pop();
    }

    public int peek() {
        if (stackpush.empty() && stackpop.empty()) {
            throw new RuntimeException("Your queue is empty");
        }
        else if (stackpop.empty()) {
            while (!stackpush.empty()) {
                stackpop.push(stackpush.pop());
            }
        }
        return stackpop.peek();
    }
}
