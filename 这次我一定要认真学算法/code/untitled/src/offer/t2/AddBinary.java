package offer.t2;

public class AddBinary {
    public String addBinary(String a, String b) {
        String result = "";
        // 用一个flag来标记有没有进位
        boolean flag = false;

        int lenA = a.length() - 1;
        int lenB = b.length() - 1;

        while (lenA >= 0 && lenB >= 0) {
            if (flag) {
                // 如果有进位，那么只有都为00的情况下，才不会产生进位
                if (a.charAt(lenA) == '0' && b.charAt(lenB) == '0') {
                    flag = false;
                    result += "1";
                } else if ((a.charAt(lenA) == '1' && b.charAt(lenB) == '0')
                || (a.charAt(lenA) == '0' && b.charAt(lenB) == '1')) {
                    result += "0";
                    flag = true;
                } else if (a.charAt(lenA) == '1' && b.charAt(lenB) == '1') {
                    result += '1';
                    flag = true;
                }
            } else {
                // 只有两个都为1的情况下，才会产生进位
                if (a.charAt(lenA) == '1' && b.charAt(lenB) == '1') {
                    flag = true;
                    result += '0';
                } else if ((a.charAt(lenA) == '1' && b.charAt(lenB) == '0') || (a.charAt(lenA) == '0' && b.charAt(lenB) == '1')) {
                    result += '1';
                    flag = false;
                } else if (a.charAt(lenA) == '0' && b.charAt(lenB) == '0') {
                    flag = false;
                    result += '0';
                }
            }

            lenA--;
            lenB--;
        }
        while (lenA >= 0) {
            if (flag) {
                if (a.charAt(lenA) == '1') {
                    flag = true;
                    result += '0';
                } else {
                    result += '1';
                    flag = false;
                }
            } else {
                result += a.charAt(lenA);
            }
            lenA--;
        }
        while (lenB >= 0) {
            if (flag) {
                if (b.charAt(lenB) == '1') {
                    flag = true;
                    result += '0';
                } else {
                    result += '1';
                    flag = false;
                }
            } else {
                result += b.charAt(lenB);
            }
            lenB--;
        }

        if (flag) {
            // 避免还会有一个进位
            result += '1';
        }

        result = reverse(result);
        return result;
    }

    public String reverse(String a) {
        String res = "";
        for (int i = a.length() - 1; i >= 0; i--) {
            res += a.charAt(i);
        }
        return res;
    }

    public String addBinary1(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;

        int carry = 0;
        while (i >= 0 || j>= 0) {
            int digitA = i >= 0 ? a.charAt(i--) - '0' : 0;
            int digitB = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = digitA + digitB + carry;
            carry = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            result.append(sum);
        }

        if (carry == 1) {
            result.append(1);
        }

        return result.reverse().toString();

    }

    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("111","10"));
    }
}
