import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"aaa","bbb","ccc","ddd");

        list.stream().map(String::toUpperCase).forEach(s -> System.out.println(s));
    }
}