package TTAchivements.Achivements.Types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import TTAchivements.TTAchivementsPlugin;
import TTAchivements.Achivements.Achivement;
import TTAchivements.Achivements.Reward;
import TTCore.Entity.Living.Human.Player.TTPlayer;

public class WilhelmScreamAchivement implements Achivement {
	
	List<AttackData> ATTACKDATA = new ArrayList<>();
	
	@Override
	public String getName() {
		return "*Wilhelm Screams*";
	}

	@Override
	public Optional<Reward> getReward() {
		return Optional.of(new Reward(10));
	}

	@Override
	public List<String> getDescription() {
		return Arrays.asList("get a kill with the ", "assist of gravity");
	}

	@Override
	public boolean isRepeatable() {
		return true;
	}
	
	@EventHandler
	public void playerHitEvent(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player){
			TTPlayer player = TTPlayer.getPlayer((Player)event.getDamager());
			ATTACKDATA.add(new AttackData(event.getEntity(), player));
		}
	}
	
	@EventHandler
	public void playerHealEvent(EntityRegainHealthEvent event){
		Optional<AttackData> opAttack = ATTACKDATA.stream().filter(a -> a.getEntity().equals(event.getEntity())).findFirst();
		if(opAttack.isPresent()){
			ATTACKDATA.remove(opAttack.get());
		}
	}
	
	@EventHandler
	public void playerDeathEvent(PlayerDeathEvent event){
		Optional<AttackData> opAttack = ATTACKDATA.stream().filter(a -> a.getEntity().equals(event.getEntity())).findFirst();
		if(opAttack.isPresent()){
			AttackData data = opAttack.get();
			ATTACKDATA.remove(data);
			data.getAttacker().sendMessage(TTAchivementsPlugin.getPlugin(), getAchivedMessage());
		}
	}
	
	public static class AttackData{
		
		Entity ENTITY;
		TTPlayer ATTACKER;
		
		public AttackData(Entity entity, TTPlayer attacker){
			ENTITY = entity;
			ATTACKER = attacker;
		}
		
		public Entity getEntity(){
			return ENTITY;
		}
		
		public TTPlayer getAttacker(){
			return ATTACKER;
		}
	}
}
