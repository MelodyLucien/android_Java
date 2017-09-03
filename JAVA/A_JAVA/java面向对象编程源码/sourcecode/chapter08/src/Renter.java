public class Renter implements RenterIFC{
  /** 房屋租金最低价格 */
  private double rentDeadLine;
  /** 存款 */
  private double money;

  public Renter(double rentDeadLine,double money){
    this.rentDeadLine=rentDeadLine;
    this.money=money;
  }
  public double getRentDeadLine(){
    return rentDeadLine;
  }
  public boolean isAgree(double expectedRent){
    //如果租赁者的期望价格比房屋租金最低价格多100元，则同意出租
    return expectedRent-this.rentDeadLine>100;
  }

  public void fetchRent(double rent){
    money+=rent;
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
