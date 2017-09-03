import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import util.assistant.Helper;

public class Test {
	
	public static String say(String a) {
		a+="   mkijki";
		return a;
	   }
	public static void main(String[] args) throws NumberFormatException, IOException {
      Stack<Node> stack=new Stack<>();
      Node node=new Node();
      node.x=1;
      node.y=2;
      node.arc=3;
      stack.push(node);
      Node node2=new Node();
      node2.x=1;
      node2.y=2;
      node2.arc=3;
      
      Node node3=new Node();
      node3.x=1;
      node3.y=2;
      node3.arc=3;
      System.out.println(stack.contains(node));
   
      System.out.println(stack.contains(node2));
      System.out.println(stack.contains(node3));
      node3.arc=5;
      System.out.println(node.x);
      System.out.print(stack.pop().x);
      
       System.out.println(node==node2);
      String filename="/map_"+4+".txt";
      File file=new File(filename);
      System.out.print(filename);
		
		
		
		
		
    /*  int arr2[][]=new int[8][8];
      String line=null;
      String  filename = "src/util/load_map/map_"+8+".txt";
		 //URL url=LoadMap.class.getResource(filename);
		// File file=new File(url.toURI());

		File file = new File(filename);

		BufferedReader in = new BufferedReader(new FileReader(file));
        int row=0;
		// 逐行读取，并将每个数组放入到数组中
		while ((line = in.readLine()) != null) {
			String[] temp = line.split("\t");
			for (int j = 0; j < temp.length; j++) {
				arr2[row][j] = Integer.parseInt(temp[j]);
			}
			row++;
		}
		
		in.close();

		// 显示读取出的数组
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(arr2[i][j] + "\t");
			}
			System.out.println();
		}*/
		
		
	/*	String a="nihsihjs";
		 a= say(a);
		System.out.println(a);
		
		System.out.println(Helper.getScale());
		
		*/
		
	}
	
	
}
