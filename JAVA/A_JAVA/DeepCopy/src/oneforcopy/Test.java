package oneforcopy;

public class Test {
	
	
  public static void main(String[] args) {
	  
	/* ItemInfo info=new ItemInfo("zhoah", "25");
	 ListForFile<ItemInfo> object=new ListForFile<ItemInfo>();
	 object.addListFromItem(info);
	 SaveObjectToFileHelper.save("object.dat", null, object);
	 */
	 
	 
	 ListForFile<ItemInfo> object=new ListForFile<ItemInfo>();
	 object=(ListForFile<ItemInfo>) SaveObjectToFileHelper.open("object.dat", null);
	 ItemInfo info=object.getItem(0);
	 System.out.println(info.getName());
	 
	
  }
}
