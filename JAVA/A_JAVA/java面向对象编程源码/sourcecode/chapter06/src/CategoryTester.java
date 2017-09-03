import java.util.Set;
public class CategoryTester{
  public Book create(){
    Category categoryScience=new Category();  //创建Science类型的Category对象
    categoryScience.setName("Science");
    
    Category categoryMath=new Category();  //创建Math类型的Category对象
    categoryMath.setName("Math");
    
    Category categoryComputer=new Category();      //创建Computer类型的Category对象
    categoryComputer.setName("Computer");
    
    //建立Science类型与Math类型的关联
    categoryScience.addChildCategory(categoryMath);  
    categoryMath.setParentCategory(categoryScience);
    
    //建立Science类型与Computer类型的关联
    categoryScience.addChildCategory(categoryComputer);  
    categoryComputer.setParentCategory(categoryScience);
    
    //创建Math类型的Book对象
    Book mathBook=new Book();
    mathBook.setCategory(categoryMath);
    
    return mathBook;
  }
  
  public static void main(String args[]){
    Book mathBook=new CategoryTester().create();
    System.out.println(mathBook.getCategory().getName());  //打印书的类别
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
