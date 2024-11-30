import java.io.*;
import java.lang.reflect.GenericDeclaration;
import java.util.*;
import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 把文章顺序进行恢复到一个新文件中

        //1.第一步一定是先读入文件
        BufferedReader fis = new BufferedReader(new FileReader("Src\\a.txt"));
        BufferedWriter fos = new BufferedWriter(new FileWriter("Src\\b.txt"));

        // 2. 根据需求，我们可以发现他是一行一句话，那么我们可以用读整行的方法
        Map<Integer, String> s = new TreeMap<>();
        String readstring;
        while ((readstring = fis.readLine()) != null) {
            System.out.println(readstring);
            if (readstring.length() > 0) {
                int index = Integer.parseInt(readstring.substring(0,1));
                s.put(index, readstring);
            }
        }
        byte[] bytes = new byte[1024];

        s.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                try {
                    fos.write(s);
                    fos.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        fis.close();
        fos.close();
    }
}

