package uselocal;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalConfig {
  private static final ThreadLocal<GlobalConfig> threadConfig=
                                       new ThreadLocal<GlobalConfig>();
  private Properties properties = new Properties();
  private GlobalConfig(){
    try{
      //加载配置信息
      InputStream in=getClass().getResourceAsStream("myapp.properties");
      properties.load(in);
      in.close();
    }catch(IOException e){throw new RuntimeException("加载配置信息失败");}
  }
  public static GlobalConfig getInstance(){
    GlobalConfig config=threadConfig.get();
    if(config==null){
      config=new GlobalConfig();
      threadConfig.set(config);
    }
    return config;
  }

  public Properties getProperties() {
    return properties;
  }
  
  public static void main(String args[]){
     GlobalConfig config=getInstance();
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java面向对象编程>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
