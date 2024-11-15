import java.util.Stack;

public class GetMinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetMinStack() {
        this.stackData = new Stack<Integer>();
        this.stackMin = new Stack<Integer>();
    }

    public int Getmin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }
        int min = stackMin.peek();
        return min;
    }

    public void push(int num) {
        stackData.push(num);
        if(stackMin.empty()) {
            stackMin.push(num);
        } else if(this.Getmin() >= num) {
            stackMin.push(num);
        } else {
            stackMin.push(this.Getmin());
        }
    }

    public int pop(int num) {
        int value = -1;
        if (stackData.empty()) {
            throw new RuntimeException("Your stack is empty");
        } else {
            value = stackData.pop();
            stackMin.pop();
        }
        return value;
    }
}
