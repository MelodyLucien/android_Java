package o17ExchangerTest;

import java.awt.image.DataBuffer;
import java.util.concurrent.Exchanger;

/**
 * java  api 文档推荐的利用exchanger是实现的缓冲区，代码就是美啊！！
 * @author zhouhao
 *
 * class FillAndEmpty {
 
	   Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
	   DataBuffer initialEmptyBuffer = ... a made-up type    //初始化一个空的缓冲区，用于填充东西
	   DataBuffer initialFullBuffer = ...                    //初始化一个满的缓冲区，用于取出东西

	   class FillingLoop implements Runnable {
	     public void run() {
	       DataBuffer currentBuffer = initialEmptyBuffer;
	       try {
	         while (currentBuffer != null) {
	           addToBuffer(currentBuffer);
	           if (currentBuffer.isFull())
	             currentBuffer = exchanger.exchange(currentBuffer);
	         }
	       } catch (InterruptedException ex) { ... handle ... }
	     }
	   }

	   class EmptyingLoop implements Runnable {
	     public void run() {
	       DataBuffer currentBuffer = initialFullBuffer;
	       try {
	         while (currentBuffer != null) {
	           takeFromBuffer(currentBuffer);
	           if (currentBuffer.isEmpty())
	             currentBuffer = exchanger.exchange(currentBuffer);
	         }
	       } catch (InterruptedException ex) { ... handle ...}
	     }
	   }

	   void start() {
	     new Thread(new FillingLoop()).start();   //成员内部类可以直接访问
	     new Thread(new EmptyingLoop()).start();
	   }
	 }

*
*/