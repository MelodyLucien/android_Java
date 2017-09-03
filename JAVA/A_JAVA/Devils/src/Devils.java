import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Devils {
	 Stack<Character> s_in;
     Stack<Character> s_out;
     Stack<Character> s_insert;
     char[] ch;
     char top;
     char c;
     int tag=0;
     
     public Devils(Stack<Character> s_in,
			Stack<Character> s_out) throws IOException {
		super();
		this.s_in = s_in;
		this.s_out = s_out;
		s_insert=new Stack<Character>();
		read();
		push();
		System.out.println(s_in);
		System.out.println(s_in.peek());
		pop();
	}
   
     private void push() throws IOException {
		for (int i = 0; i < ch.length; i++) {
			//if(tag==0)
			add(ch[i]);
			/*if(tag==1)
			{
			   add(ch[i]);
			   add(ch[i]);
			   top=ch[i];
			   tag=2;
			   continue;
			}
			if(tag==2)
			{
				 if(ch[i]==')')
					{
						tag=0;
						continue;
					}
					
			  s_insert.add(top);
			  s_insert.add(ch[i]); 
			}*/
		}
	}
     
    private void add(char i) {
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
		}
		if(i=='A')
		{
			s_in.push('s');
			s_in.push('a');
			s_in.push('e');
		}
		else{
		    s_in.push(i);
		}
		
		/*if(ch[i]=='(')
		{
			tag=1;
		}*/
		
	}

     
     private void pop() {//¹îÒìÖ®¼«
    	 System.out.println(s_in);
    	 for (int i = 0; i < ch.length; i++) {
 			 s_out.push(s_in.pop());
 			 System.out.println(s_out.peek());
 			}
    	 System.out.println(s_out);
    	 for (int i = 0; i < ch.length; i++) { 
			 System.out.print(s_out.pop()+" ");
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
		System.out.println(ch);
	}
     
	public static void main(String[] args) throws IOException {
		Stack<Character> s_in=new Stack<Character>();
		Stack<Character> s_out=new Stack<Character>();
		new Devils(s_out, s_out);
		
	}
}
