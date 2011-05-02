package plugin.x1337x.bukkit.ChunkLoad.Commands;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.x1337x.bukkit.ChunkLoad.ChunkLoad;

public class ChunkLoadCommand implements CommandExecutor {
ChunkLoad plugin;
	public ChunkLoadCommand(ChunkLoad i) {
		// TODO Auto-generated constructor stub
		this.plugin = i;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		System.out.println("Chunk");
		if(arg0.isOp()){
		Player player = (Player) arg0;
		Chunk chunk = player.getLocation().getBlock().getChunk();
		int x = chunk.getX();
		int z = chunk.getZ();
		
	    String write = x + ":" + z;
	    if(!this.plugin.chunks.contains(write)){
	    this.plugin.chunks.add(write);
		player.sendMessage("Chunk added!");
	    }
	    else if(this.plugin.chunks.contains(write)){
	    	this.plugin.chunks.remove(write);
	    	player.sendMessage("Chunk removed");
	    }
		}
		else{
			arg0.sendMessage("You must be op to do that!");
		}
		return true;
	}

}
