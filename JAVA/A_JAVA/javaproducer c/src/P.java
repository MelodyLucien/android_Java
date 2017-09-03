 
public class P {
	String name;
	String sex;
	boolean bfull=false;
	public synchronized void set(String name,String sex){
		if(bfull)
		{
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		this.name=name;
		this.sex=sex;
		bfull=true;
		notify();
	}
	
	public synchronized String get(){
		if(!bfull)
		{
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		bfull=false;
		notify();
		return name+" "+sex;
		
	}

}
