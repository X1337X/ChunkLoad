package plugin.x1337x.bukkit.ChunkLoad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
	String dir = "plugins" + File.separator + "ChunkLoad" + File.separator;
	String filename = "ChunkLoadData.txt";
	public static final String date_format = "dd-MM-yyyy HH:mm:ss";
	SimpleDateFormat sdf = new SimpleDateFormat(date_format);
	File file = new File(dir + filename);
Logger log = Logger.getLogger("Minecraft");
public ChuckLoadWListener wlistener = new ChuckLoadWListener(this);
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		getLogger().log(Level.INFO, "ChunkLoad disabled"); 
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		registerevents();
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
private void loadcommands() {
	// TODO Auto-generated method stub
	getCommand("chunk").setExecutor(new ChunkLoadCommand(this));
}

public boolean allowunload(int x,int z){
	boolean unload = false;
	try {
		
		Scanner scanner = new Scanner(this.file);
		while(scanner.hasNextLine()){
			String[] line = scanner.nextLine().split(":");
			int xi = Integer.parseInt(line[0]);
			int zi = Integer.parseInt(line[1]);
			
			if(zi == z && xi == x){
				unload = false;
			}
			else{
				unload = true;;
			}
		}

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		this.getLogger().log(Level.WARNING, "Error Reading chunk from " + this.file.getName());
		this.getLogger().log(Level.WARNING, "Please copy and paste this error report onto the ChunkLoad thread");
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
	try {
		BufferedWriter w = new BufferedWriter(new FileWriter(this.file));
		w.write(x + ":" + z);
		w.newLine();
		w.close();
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
	}
}
}
