package offer.chapter1.t1;

public class Divide {

    public int divide(int dividend, int divisor) {
        // int型整数的除法只有一种情况会导致移除，即(-2^(31))/-1
        if (dividend == 0x80000000 && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int negative = 2;
        if (dividend > 0) {
            negative--;
            dividend = -dividend;
        }

        if (divisor > 0) {
            negative--;
            divisor = -divisor;
        }

        // 经过处理后，传进入的都是负数
        int result = divideCore(dividend, divisor);
        return negative == 1 ? -result : result;
    }

    public int divideCore(int dividend, int divisor) {
        int result = 0;
        while (dividend <= divisor) {
            // 外层嵌套循环，表示如果被除数dividend小于等于除数divisor，则还可以继续减去除数
            int value = divisor;
            int quotient = 1;

            //这里的 0xc0000000 是一个负数（即 32 位整数的 -1073741824），用于避免整数溢出的问题。
            //通常在负数场景下，避免数值超出 Integer.MIN_VALUE 会触发溢出。
            while (value >= 0xc0000000 && dividend <= value + value) {
                quotient += quotient;

//                通过不断倍增（即将 value 加倍），找到可以从被除数中减去的最大值，以减少运算次数。
                value += value;
            }

            result += quotient;
            dividend -= value;
        }

        return result;
    }
}
