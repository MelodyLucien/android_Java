/* 
 * 版权 2005-2008 www.javathinker.org
 * 本程序采用GPL协议，你可以从以下网址获得该协议的内容:
 * http://www.gnu.org/copyleft/gpl.html 
 */

package com.abc.dollapp.main;
import com.abc.dollapp.doll.Doll;

/**
 * DollApp应用的主程序
 * @author 孙卫琴
 * @version 3.0
 * @since 1.0
 */
public class AppMain{
  
  /**
   * 应用程序的入口方法
   */
  public static void main(String args[]){
    Doll beibei=new Doll("贝贝");  //创建福娃贝贝
    Doll jingjing=new Doll("晶晶");  //创建福娃晶晶
    Doll huanhuan=new Doll("欢欢");  //创建福娃欢欢
    Doll yingying=new Doll("迎迎");  //创建福娃迎迎
    Doll nini=new Doll("妮妮");  //创建福娃妮妮

    beibei.speak();  //福娃贝贝说话
    jingjing.speak();  //福娃晶晶说话
    huanhuan.speak();  //福娃欢欢说话
    yingying.speak();  //福娃迎迎说话
    nini.speak();  //福娃妮妮说话
  }
}