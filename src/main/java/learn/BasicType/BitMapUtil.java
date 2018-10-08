package learn.BasicType;

import java.util.BitSet;

public class BitMapUtil {



    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        bitSet.set(2);
        bitSet.set(3);
        bitSet.set(4);
        bitSet.set(129);
        System.out.println(bitSet.size());//需要多少bit位来存储，是个64的倍数，因为long占64位
        System.out.println(bitSet.length());//有效的bit位数
        System.out.println(bitSet.cardinality());//value为true的bit位数

        System.out.println(bitSet.get(129));
    }
}
