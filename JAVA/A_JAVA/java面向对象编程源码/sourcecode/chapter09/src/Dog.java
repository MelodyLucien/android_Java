class Owner{
  private Dog dog;
  public Dog getDog(){return dog;}

  public void setDog(Dog dog){
    this.dog=dog;
  }
}

public class Dog{
  private Owner owner;
  public Owner getOwner(){return owner;}

  public void setOwner(Owner owner){
    if(this.owner!=null)  //如果原先就有主人
      this.owner.setDog(null); //那么取消与原先主人的关联关系
    this.owner=owner; //建立狗和新的主人的关联关系
    owner.setDog(this); //建立新的主人和狗的关联关系
  }

  public static void main(String args[]){
    Owner owner1=new Owner();
    Owner owner2=new Owner();
    Dog dog1=new Dog();

    dog1.setOwner(owner1);  //建立dog1和owner1的关联关系
    dog1.setOwner(owner2);  //建立了dog1和owner2的关联关系
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/

 
