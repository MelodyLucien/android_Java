import java.io.*;
public class Panel {

  public void selectShape()throws Exception{
    System.out.println("请输入形状类型：");

    //从控制台读取用户输入形状类型
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    int shapeType=Integer.parseInt(input.readLine());

    //获得形状实例
    Shape shape=ShapeFactory.getShape(shapeType);

    if(shape==null)
        System.out.println("输入的形状类型不存在");
    else
        shape.draw(); //画形状

  }
  public static void main(String[] args)throws Exception {
    new Panel().selectShape();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
