package ¾ØÕóÄæÖÃ;

public class TT {
	int i,j;
	void print(int a[][])
	{
		for(i=0;i<5;i++)
		{
				for(j=0;j<5;j++)
			{
			   System.out.print(a[i][j]+" ");
			   if(j==4)
				   System.out.println();
			}
			
		}
	}
	
  public static void main(String[] args) {
	 TT tt=new TT();
	int i,j,temp;
	int a[][]=new int[5][5];
	int count=0;
	for(i=0;i<5;i++)
	{
			for(j=0;j<5;j++)
		{
		    a[i][j]=count;
			count++;
		}
		
	}
	
	tt.print(a);
	System.out.println("******ÅÅĞòÖ®ºó******");

	for(i=0;i<5;i++)
	{
			for(j=i;j<5;j++)
		{
			temp=a[i][j];
			a[i][j]=a[j][i];
			a[j][i]=temp;
		}
		
	}
	
	tt.print(a);
	
	StringBuffer str=new StringBuffer("1245");
	
}
}
