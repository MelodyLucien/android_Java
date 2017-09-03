/* 
 * 版权 2005-2008 www.javathinker.org
 * 本程序采用GPL协议，你可以从以下网址获得该协议的内容:
 * http://www.gnu.org/copyleft/gpl.html 
 */
package com.abc.dollapp.doll.extend;

import com.abc.dollapp.doll.Doll;

/**
 * <p><strong>SmartDoll</strong> 代表智能福娃，它能够发出用户指定的声音。</p>
 * @author 孙卫琴
 * @version 3.0
 * @since 1.0
 * @see com.abc.dollapp.doll.Doll
 */
public class SmartDoll extends Doll{
  
  /**
   * 代表智能福娃默认情况下所说的话
   */
  protected String word;
  
  /**
   * 构造一个智能福娃，未设定默认情况下所说的话
   */  
  public SmartDoll(String name){
    super(name);
  };

  /**
   * 构造福娃的同时，指定默认情况下所说的话
   * @param word 默认情况下所说的话。
   */  
  public SmartDoll(String name,String word){
    super(name); 
    this.word=word;
  };
  
  /**
   * 获得默认情况下所说的话
   * @return 返回默认情况下所说的话
   * @see #setWord
   * @deprecated 该方法已经被废弃
   */ 
  public String getWord(){
    return this.word;
  }  

  /**
   * 设置默认情况下所说的话
   * @param word 默认情况下所说的话
   * @see #getWord
   * @since 2.0
   */ 
  public void setWord(String word){
    this.word=word;
  }  

  /**
   * <ul>
   * <li>如果{@link #word word成员变量}不为null，就调用{@link #speak(String) speak(String)方法}</li>
   * <li>如果{@link #word word成员变量}为null，就调用{@link com.abc.dollapp.doll.Doll#speak() super.speak()方法}</li>
   * </ul>
   */ 
  public void speak(){
     if(this.word!=null){
       try{
         speak(word);
       }catch(Exception e){}  
     }
     else
       super.speak();
  }
  
   /**
   * @param word 指定智能福娃该说的话。
   * @return 智能福娃已说的话
   * @exception Exception 如果word参数为null，就抛出该异常
   */ 
  public String speak(String word) throws Exception{
    if(word==null)
       throw new Exception("不知道该说啥");
    System.out.println(word);
    return word;
  }


}