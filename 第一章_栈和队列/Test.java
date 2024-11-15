import java.awt.dnd.DragGestureEvent;
import java.util.*;
import java.util.function.BiConsumer;

public class Test {
    public static void main(String[] args) {
        //
        Map<String,List> provice = new HashMap<>();
        List<String> city = new ArrayList<>();
        Collections.addAll(city,"赣州","南昌","景德镇");

        provice.put("江西省",city);

        List<String> city2 = new ArrayList<>();
        Collections.addAll(city2,"广州","佛州","深证","珠海","东莞");
        provice.put("广东省",city2);

        provice.forEach((String s, List list) -> System.out.println(s + "=" + list));
    }
}

class Student {
    String name;
    int sex;

    public Student() {
    }

    public Student(String name, int sex) {
        this.name = name;
        this.sex = sex;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return sex
     */
    public int getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    public String toString() {
        return "Student{name = " + name + ", sex = " + sex + "}";
    }
}