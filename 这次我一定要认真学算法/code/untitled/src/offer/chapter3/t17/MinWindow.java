package offer.chapter3.t17;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MinWindow {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> charToCount = new HashMap<>();
        for (char ch : t.toCharArray()) {
            charToCount.put (ch, charToCount.getOrDefault(ch, 0) + 1);
        }

        int count = charToCount.size();
        int start = 0, end = 0, minStart = 0, minEnd = 0;
        int minLength = Integer.MAX_VALUE;

        while (end < s.length() || (count == 0 && end == s.length())) {
            if (count > 0) {
                char endCh = s.charAt(end);
                if (charToCount.containsKey(endCh)) {
                    charToCount.put(endCh, charToCount.get(endCh) - 1);
                    if (charToCount.get(endCh) == 0) {
                        count--;
                    }
                }
                end++;
            } else {
                if (end - start > minLength) {
                    minLength = end - start;
                    minStart = start;
                    minEnd = end;
                }

                char startCh = s.charAt(start);
                if (charToCount.containsKey(startCh)) {
                    charToCount.put(startCh, charToCount.get(startCh) + 1);
                    if (charToCount.get(startCh) == 1) {
                        count++;
                    }
                }

                start++;
            }
        }

        return minLength < Integer.MAX_VALUE ? s.substring(minStart, minEnd) : "";
    }
}
