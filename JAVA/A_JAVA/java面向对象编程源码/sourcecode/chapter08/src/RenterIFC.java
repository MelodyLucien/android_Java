public interface RenterIFC{
  /** 是否同意按租赁人提出的价格出租房屋 */
  public boolean isAgree(double expectedRent);

  /** 收房租 */
  public void fetchRent(double rent);
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
