public class RegularExTester {
  public static void main(String[] args) {
    //把字符串中的“aaa”全部替换为“z”
    //打印zbzcz
    System.out.println("aaabaaacaaa".replaceAll("a{3}","z"));
    
    //把字符串中的“aaa”、“aa”或者“a”全部替换为“*”
    //打印*b*c*
    System.out.println("aaabaaca".replaceAll("a{1,3}","\\*"));
    
    //把字符串中的数字全部替换为“z”
    //打印zzzazzbzzcc
    System.out.println("123a44b35cc".replaceAll("\\d","z"));
    
    //把字符串中的非数字全部替换为“z”
    //打印1234000435000
    System.out.println("1234abc435def".replaceAll("\\D","0"));
    
    //把字符串中的“.”全部替换为“\”
    //打印com\abc\dollapp\Doll
    System.out.println("com.abc.dollapp.Doll".replaceAll("\\.","\\\\"));
    
    //把字符串中的“a.b”全部替换为“-”，
    //“a.b”表示长度为3的字符串，以“a”开头，以“b”结尾  
    //打印-hello-all
    System.out.println("azbhelloahball".replaceAll("a.b","-"));
    
    //把字符串中的所有词字符替换为“#”
    //正则表达式“[a-zA-Z_0-9]”等价于是“\w”
    //打印#.#.#.#.#.#.#
    System.out.println("a.b.c.1.2.3.4".replaceAll("[a-zA-Z_0-9]","#"));

  }
} 


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
