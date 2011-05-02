package plugin.x1337x.bukkit.ChunkLoad;

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
		plugin.getLogger().info("Chunk @ " + x + ":" + z + "Stoped from unloading");
	
	}
	else{
		event.setCancelled(false);
	}
}

}
