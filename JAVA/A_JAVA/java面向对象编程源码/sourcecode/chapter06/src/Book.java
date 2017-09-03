public class Book{
  private String name;
  private Category category;
  
  public Book(){}
  public Book(String name,Category category){
    this.name=name;
    this.category=category;
  }
  
  public String getName() {
        return this.name;
  }

  public void setName(String name) {
        this.name = name;
  }

  public Category getCategory() {
        return category;
  }

  public void setCategory(Category category) {
        this.category = category;
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
