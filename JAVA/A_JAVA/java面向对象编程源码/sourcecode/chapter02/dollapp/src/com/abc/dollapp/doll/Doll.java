/* 
 * 版权 2005-2008 www.javathinker.org
 * 本程序采用GPL协议，你可以从以下网址获得该协议的内容:
 * http://www.gnu.org/copyleft/gpl.html 
 */

package com.abc.dollapp.doll;

/**
 * <p><strong>Doll</strong>代表福娃，它能够发出特定的声音。</p>
 * @author 孙卫琴
 * @version 3.0
 * @since 1.0
 */
public class Doll{
  /**
   * 福娃的名字
   */
  private String name;

  public Doll(String name){
    this.name=name;
  }
  
  /**
   * 福娃说话
   */
  public void speak(){
    System.out.println(name);
  }  
}

