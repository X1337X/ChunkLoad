package plugin.x1337x.bukkit.ChunkLoad.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;

/**
 *
 * @author 1337
 */
@Entity()
@Table(name = "ChunkLoadData")
public class Data {
	
   @Id
   private int x;
   @NotEmpty
   private int y;
   public int getY(){
	   return y;
   }
   public int getX(){
	   return x;
   }
   public void setX(int x){
	   this.x = x;
   }
   public void setY(int y){
	   this.y = y;
   }
}