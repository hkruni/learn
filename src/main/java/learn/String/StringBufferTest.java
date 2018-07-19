package learn.String;


public class StringBufferTest
{
    public static void main(String[] args)
    {
        StringBuffer s1 = new StringBuffer("good"); //good
        StringBuffer s2 = new StringBuffer("bad"); //bad
        test(s1,s2);
        System.out.println(s1); //goodhhh
        System.out.println(s2); //bad
    }
    static void test(StringBuffer s1,StringBuffer s2) {
        System.out.println(s1);//  good
        System.out.println(s2);//  bad
        s2 = s1;// 形参的s2指向s1的对象，实参s2可没有,实参s2还是bad
        s1 = new StringBuffer("new");// 形参s重新指向新的对象，但是形参s2没有
        System.out.println(s1);//   new
        System.out.println(s2);//   good
        s1.append("aaa");// 形参s1为newaaa
        s2.append("hhh");// 形参s2指向实参s对象，所以实参s对象是goodhah
    }
}
