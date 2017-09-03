package listVSmap;

import java.util.*;

import Set.HashSet.MonkeySort1;
public class Test {
	
	public static void main(String[] args) {
		
		//添加不重复的元素
		Set<Monkey> set=new HashSet<>();
		Monkey monkey=new Monkey("1","23");
		Monkey monkey2=new Monkey("2","23");
		Monkey monkey3=new Monkey("1","23");
		set.add(monkey);
		set.add(monkey2);
		set.add(monkey3);
		System.out.println(set.size());
		
		
		
		
		
		//对元素进行排序
		List<MonkeySort1> l2=new LinkedList();
	    for (int i = 10; i>0; i--) {
		MonkeySort1 monkeySort1=new MonkeySort1(""+i+"","20");
		l2.add(monkeySort1);
	    }
	    
	    
		for (MonkeySort1 monkeySort1 : l2) {
			System.out.println(monkeySort1);
		}
		
		
	
	//对于集合类的排序总结
	
	/*	
		list和array Collections.sort()的排序方法和Array.sort()
		set  treemap
		map   treemap
		
		以上都要求实现comparable接口
	*/
	}

}

