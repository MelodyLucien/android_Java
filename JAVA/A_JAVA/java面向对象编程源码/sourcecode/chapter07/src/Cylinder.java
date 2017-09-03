public class Cylinder{  //圆柱体
  private double r; //半径
  private double h; //高
  public static final double PI=3.14;

  public Cylinder(double r,double h){this.r=r;this.h=h;}
  
  public double getCircumference(){ //返回圆柱体底面的周长
    return 2*PI*r;
  }
  public double getBottomArea(){  //返回圆柱体底面的面积
    return PI*r*r;
  }
  
  public double getAllArea(){  //返回整个圆柱体的面积
    return 2*PI*r*r+2*PI*r*h;
  }
  public double getVolume(){  //返回整个圆柱体的体积
    return PI*r*r*h;
  }   
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
