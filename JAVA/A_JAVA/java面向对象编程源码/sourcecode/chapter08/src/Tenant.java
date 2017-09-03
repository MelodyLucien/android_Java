public class Tenant{
  private double money;

  public Tenant(double money){
    this.money=money;
  }

  public boolean rentHouse(double expectedRent){
    //从房产市场找到一个房屋出租代理
    RenterIFC renter=HouseMarket.findRenter();

    //如果代理不同意预期的租金价格，就拉倒，否则继续执行
    if(! renter.isAgree(expectedRent)) return false;

    //从存款中取出预付租金
    money-=expectedRent;

    //把租金交给房屋代理
    renter.fetchRent(expectedRent);

    return true;
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
