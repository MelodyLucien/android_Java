package com.example.zhouhao2;

import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
public class FileCheckerThread {
	
	private boolean timeout = false;
	
    public static void main(String[] args) {
        //define a folder root
        Path myDir = Paths.get(Test.DOG_MOUTH_DIR);       
        try {
           WatchService watcher = myDir.getFileSystem().newWatchService();
           myDir.register(watcher, ENTRY_CREATE, 
           ENTRY_DELETE, ENTRY_MODIFY);
           
           while(true){
	           WatchKey watckKey = watcher.take();
	           System.out.println("pollevents");
	           List<WatchEvent<?>> events = watckKey.pollEvents();
	           for (WatchEvent event : events) {
	                if (event.kind() == ENTRY_CREATE) {
	                    System.out.println("Created: " + event.context().toString());
	                }
	                if (event.kind() == ENTRY_DELETE) {
	                    System.out.println("Delete: " + event.context().toString());
	                }
	                if (event.kind() == ENTRY_MODIFY) {
	                    System.out.println("Modify: " + event.context().toString());
	                }
	            }
           }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
    
}