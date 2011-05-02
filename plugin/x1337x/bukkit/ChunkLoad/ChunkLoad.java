package plugin.x1337x.bukkit.ChunkLoad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.x1337x.bukkit.ChunkLoad.Commands.ChunkLoadCommand;

public class ChunkLoad extends JavaPlugin {
	
	public ArrayList<String> chunks = new ArrayList<String>();
	String dir = "plugins" + File.separator + "ChunkLoad" + File.separator;
	String filename = "ChunkLoadData.txt";
	File dirf = new File(dir);
	public static final String date_format = "dd-MM-yyyy HH:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(date_format);
	File file = new File(dir + filename);
Logger log = Logger.getLogger("Minecraft");
public ChuckLoadWListener wlistener = new ChuckLoadWListener(this);
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		if(disable()){
		getLogger().info("Saved " + chunks.size() + " Chunkks");
		getLogger().log(Level.INFO, "ChunkLoad disabled"); 
		}
		else{
			getLogger().warning("ERROR Saving Chunks!");
		}
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		if(!dirf.exists()){
		dirf.mkdir();	
		}
		init();
		loadIntoArray();
		getLogger().log(Level.INFO, "ChunkLoad enabled");
	}
public Logger getLogger(){
	return this.log;
}
public void registerevents(){
	PluginManager pm = this.getServer().getPluginManager();
	pm.registerEvent(Type.CHUNK_UNLOAD, wlistener, Event.Priority.Highest, this);

}
public void init(){
	registerevents();
	loadcommands();
}
public boolean disable(){
	boolean done = false;
	try {
		BufferedWriter w = new BufferedWriter(new FileWriter(this.file));
		
        int position = 0;
        int size = chunks.size();
        while(size != position){
        	w.write(chunks.get(position));
    		w.newLine();
    		position++;
        }
		
		w.close();
		done = true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		this.getLogger().log(Level.WARNING, "Error writing chunk to " + this.file.getName());
		this.getLogger().log(Level.WARNING, "Please copy and paste this error onto the ChunkLoad thread");
		this.getLogger().log(Level.WARNING, "------------BEGIN ERROR REPORT----------------------------");
		this.getLogger().log(Level.WARNING, "Report made on " + this.getDate());
		this.getLogger().log(Level.WARNING, "The cause of this error was : " + e.getCause());
		this.getLogger().log(Level.WARNING, "The message was : " +  e.getMessage());
		this.getLogger().log(Level.WARNING, "CraftBukkit build used : " + this.getServer().getVersion());
		this.getLogger().log(Level.WARNING, "ChunkLoad version used : " + this.getDescription().getVersion());
		this.getLogger().log(Level.WARNING, "Stack Trace : ");
		e.printStackTrace();
		this.getLogger().log(Level.WARNING, "-----------END ERROR REPORT--------------------------------");
		done = false;
	}
if(done){
	return true;
}
else{
	return false;
}
}
private void loadcommands() {
	// TODO Auto-generated method stub
	getCommand("chunk").setExecutor(new ChunkLoadCommand(this));
}

public boolean allowunload(int x,int z){
	boolean unload = false;
	String chunk = x + ":" + z;
if(chunks.contains(chunk)){
	unload = false;
}
else{
	unload = true;
}
	if(unload){
		return true;
	}
	else{
		return false;
	}
}
public String getDate(){

		    Calendar cal = Calendar.getInstance();
		   
		    return sdf.format(cal.getTime());
		  
}
public void writeloc(int x,int z){
	
}
public void loadIntoArray(){
	try {
		Scanner scanner = new Scanner(this.file);
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			chunks.add(line);
		}
		getLogger().info("Loaded " + chunks.size() +  " Chunks into memory");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		this.getLogger().log(Level.WARNING, "Error Reading chunks from " + this.file.getName());
		this.getLogger().log(Level.WARNING, "Please copy and paste this error onto the ChunkLoad thread");
		this.getLogger().log(Level.WARNING, "------------BEGIN ERROR REPORT----------------------------");
		this.getLogger().log(Level.WARNING, "Report made on " + this.getDate());
		this.getLogger().log(Level.WARNING, "The cause of this error was : " + e.getCause());
		this.getLogger().log(Level.WARNING, "The message was : " +  e.getMessage());
		this.getLogger().log(Level.WARNING, "CraftBukkit build used : " + this.getServer().getVersion());
		this.getLogger().log(Level.WARNING, "ChunkLoad version used : " + this.getDescription().getVersion());
		this.getLogger().log(Level.WARNING, "Stack Trace : ");
		e.printStackTrace();
		this.getLogger().log(Level.WARNING, "-----------END ERROR REPORT--------------------------------");
	}
}
}
