package trie;

import javax.swing.table.TableRowSorter;

public class Trie {
    public TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }

        TrieNode cur = root;
        cur.pass++;             //其实这个点的pass就表示总共字符串数量
        for (int i = 0; i < word.length(); i++) {
            char curCh = word.charAt(i);
            int index;
            //获得当前字符
            if (curCh >= 'a' && curCh <= 'z') {
                index = curCh - 'a';
            } else {
                index = curCh - 'A';
            }

            if (cur.nexts[index] == null) {
                //如果这个点还没有创建，要先new一个
                cur.nexts[index] = new TrieNode();
            }
            cur.nexts[index].pass++;
            cur = cur.nexts[index];
        }

        cur.end++;
    }

    public void delete(String word) {
        //首先要判断前缀树种出现过这个字符串
        if (search(word) != 0) {
            return;
        }

        TrieNode cur = root;
        cur.pass--;

        for (int i = 0; i < word.length(); i++) {
            char curCh = word.charAt(i);
            int index;
            //获取当前字符

            if (curCh >= 'a' && curCh <= 'z') {
                index = curCh - 'a';
            } else {
                index = curCh - 'A';
            }
            if (--cur.nexts[index].pass == 0) {
                cur.nexts[index] = null;
                return;
            }
        }

        cur.end--;
    }

    public int search(String word) {
        if (word == null) {
            return 0;//null不是空串
        }

        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (cur.nexts[index] == null) {
                return 0;
            }
            cur = cur.nexts[index];
        }

        return cur.end;
    }
}
