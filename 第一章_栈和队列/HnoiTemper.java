//可以先试试着考虑最普通的汉诺塔问题
public class HnoiTemper {
    private int count;
    public HnoiTemper() {
        this.count = 0;
    }
    public int NormalHnoi (int n, String source, String help, String destination) {
        //解题的核心在于将 第 n 个盘和上面的 n - 1 个盘看成两个部分
        //即假设第n-1个盘已经顺利移动到了中间的柱子，第n个盘可以直接移动到最左侧的柱子了。
        if (n == 1) {
            System.out.println("Move " + n + " from " + source + " to " + destination);
            count++;
            return count;
        }
        NormalHnoi(n - 1, source, destination, help);
        //移动n-1个盘子到中间help的柱子上
        System.out.println(" Move " + n + " from " + source + " to " + destination);
        count++;
        //将最底部的盘子移动到destination柱子上
        NormalHnoi(n - 1, help, source, destination);
        //将n-1个盘子从中间的help柱子移动到destination柱子上
        return count;
    }

    public void PowerHnoi (int n, String source, String help, String destination) {
        // 这次限定了只可以移动到隔壁的柱子，但是我们本质思想还是分为 n - 1 个盘子和最下面的第 n 个盘子

    }
}

