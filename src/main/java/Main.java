import com.study.data_structure.tree.BTree;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        BTree<Integer> tree = new BTree<>(2);
        List<Integer> list = IntStream.range(1, 27).boxed().collect(Collectors.toList());
        //Collections.shuffle(list);

        for (int i : list) {
            tree.add(i);
        }
//        tree.add(5);
//        tree.add(9);
//        tree.add(14);
//        tree.add(6);
//        tree.add(12);
//        tree.add(5);
//        tree.add(7);
//        tree.add(19);
//        tree.add(2);
//        tree.add(4);
//        tree.add(11);
//        tree.add(21);
//        tree.add(23);
//        for (int i = 34; i < 78; i++) {
//            tree.add(i);
//        }
//        System.out.println(tree.toString());
//        System.out.println(tree.breadthFirstTraverse());
//        System.out.println(tree.find(10));
//        System.out.println(tree.remove(9));
//        System.out.println(tree.remove(17));
//        System.out.println(tree.remove(11));
//        System.out.println(tree.remove(11));
//        System.out.println(tree.remove(1));
//        System.out.println(tree.remove(26));
//        System.out.println(tree.remove(25));
//        //System.out.println(tree.remove(3));
//        System.out.println(tree.toString());
//        System.out.println(tree.find(23));
//        System.out.println(tree.find(123));
//        System.out.println(tree.find(-123));
//        System.out.println(tree.find(21123));
//        System.out.println(tree.find(2123));
        Integer.valueOf(1);
        //System.out.println(r());
        System.out.println(10 & 7);
    }

    static int r() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

}
