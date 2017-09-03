package o17ExchangerTest;

import java.awt.image.DataBuffer;
import java.util.concurrent.Exchanger;

/**
 * java  api �ĵ��Ƽ�������exchanger��ʵ�ֵĻ����������������������
 * @author zhouhao
 *
 * class FillAndEmpty {
 
	   Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
	   DataBuffer initialEmptyBuffer = ... a made-up type    //��ʼ��һ���յĻ�������������䶫��
	   DataBuffer initialFullBuffer = ...                    //��ʼ��һ�����Ļ�����������ȡ������

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
	     new Thread(new FillingLoop()).start();   //��Ա�ڲ������ֱ�ӷ���
	     new Thread(new EmptyingLoop()).start();
	   }
	 }

*
*/