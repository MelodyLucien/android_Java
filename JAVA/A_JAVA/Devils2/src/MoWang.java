import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class MoWang {
	
	 Stack<Character> s_in;
	 Stack<Character> s_out;
     Stack<Character> s_insert;
     char[] ch;
     char temp;
     int tag=0;
     int count;
     public MoWang() throws IOException {
		super();
		this.s_in = new Stack<Character>();
		this.s_out = new Stack<Character>();
		this.s_insert = new Stack<Character>();
	    read();
	    add();
	    pop();
	}
     
     public void add(){
    	 for (int i = 0; i < ch.length; i++) {
  			add(ch[i]);
  		}
     }
     
     public void pop(){
    	 for (int i = 0; i < count; i++) {
			s_out.push(s_in.pop());
		}
    	 for (int i = 0; i < count; i++) {
			System.out.print(s_out.pop()+" ");
		}
     }
     
    public void print(char i){
    	switch(i)
    	{
    	case 't':System.out.print("天  ");break;
    	case 'd':System.out.print("地  ");break;
    	case 's':System.out.print("上  ");break;
    	case 'a':System.out.print("一只  ");break;
    	case 'e':System.out.print("鹅  ");break;
    	case 'z':System.out.print("追  ");break;
    	case 'g':System.out.print("赶  ");break;
    	case 'x':System.out.print("下  ");break;
    	case 'n':System.out.print("蛋  ");break;
    	case 'h':System.out.print("恨  ");break;
    	default:break;
    	}
    }
     private void read() throws IOException {
     	String result=null;
     	String str=new String();
     	StringBuffer buffer =new StringBuffer();
 		InputStreamReader in=new InputStreamReader(System.in);
 		BufferedReader reader=new BufferedReader(in);
 		if((str=reader.readLine())!=null)
 		{
 			buffer=buffer.append(str);
 		}
 		result=buffer.toString();
 		ch=result.toCharArray();
 		count=ch.length;
 	}
     
     private void add(char i) {
    	 if(i=='(')
		  {
		    count--;
		    tag=1;
		    return;
		  }
		  
		  if(i==')')
		  {
		    count--;
		    tag=3;
		    return;
		  }

	      if(tag==0) {
	    		if(i=='B')
	     		{
	     			s_in.push('t');
	     			s_in.push('s');
	     			s_in.push('a');
	     			s_in.push('e');
	     			s_in.push('d');
	     			s_in.push('s');
	     			s_in.push('a');
	     			s_in.push('e');
	     			count=count+7;
	     			return;
	     		}
	     		if(i=='A')
	     		{
	     			s_in.push('s');
	     			s_in.push('a');
	     			s_in.push('e');
	     			count=count+2;
	     			return;
	     		}
	     		
	     		if (i!='A'&&i!='B') {
	    			s_in.push(i);
	    		}
	     	}
    	 if (tag==1) {
			temp=i;
			tag=2;
			return;
		 }
    	 if (tag==2) {
			s_insert.push(i);
			return;
		}
    	 if (tag==3) {
    		 int size=s_insert.size();
			
			if(size!=0)
			{
				for (int j = 0; j < size; j++) {
					s_in.push(temp);
					s_in.push(s_insert.pop());
					count=count++;
				}
			    s_in.push(temp);
			    count=count++;
			}
			tag=0;
			return;
		}
 	}
	public static void main(String[] args) throws IOException {
		MoWang m=new MoWang();
	}

}
