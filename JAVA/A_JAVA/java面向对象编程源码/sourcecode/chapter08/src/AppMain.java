public class AppMain {
 public static void main(String[] args)throws Exception {
   //创建一个房屋出租者，房屋租金最低价格2000元，存款1万元
   Renter renter=new Renter(2000,10000);
   //创建一个房产代理人
   Deputy deputy=new Deputy();
   //房产代理人到房产市场登记
   HouseMarket.registerRenter(deputy);
   //建立房屋出租者和代理人的委托关系
   deputy.registerRenter(renter);

   //创建一个房屋租赁者，存款为2万元
   Tenant tenant=new Tenant(20000);

   //房屋租赁者试图租赁期望租金为1800元的房屋，遭到代理人拒绝
   tenant.rentHouse(1800);
   //房屋租赁者试图租赁期望租金为2000元的房屋，租房成功
   tenant.rentHouse(2300);
 }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
