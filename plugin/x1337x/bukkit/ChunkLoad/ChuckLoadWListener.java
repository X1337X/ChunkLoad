package plugin.x1337x.bukkit.ChunkLoad;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Chunk;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldListener;

public class ChuckLoadWListener extends WorldListener {
ChunkLoad plugin;
public ChuckLoadWListener(ChunkLoad i){
	this.plugin = i;
	
}
public void onChunkUnload(ChunkUnloadEvent event){
	Chunk chunk = event.getChunk();
	int x = chunk.getX();
	int z = chunk.getZ();
	if(!this.plugin.allowunload(x, z)){
		event.setCancelled(true);
	}
}

}
