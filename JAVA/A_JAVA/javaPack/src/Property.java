import java.util.Enumeration;
import java.util.Properties;


public class Property {
	public static void main(String[] args) {
		
		Properties sp =System.getProperties();
		sp.setProperty("key", "Enumeration");
        Enumeration e= sp.propertyNames();
		
				
		while(e.hasMoreElements())
		{
			String key=(String) e.nextElement();
			System.out.println(key+"="+sp.getProperty(key));
		}
	}
}