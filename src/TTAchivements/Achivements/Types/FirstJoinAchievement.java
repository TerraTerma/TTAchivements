package TTAchivements.Achivements.Types;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import TTAchivements.Achivements.Achivement;
import TTAchivements.Achivements.Reward;
import TTCore.Entity.Living.Human.Player.TTPlayer;

public class FirstJoinAchievement implements Achivement{

	@Override
	public String getName() {
		return "First Join";
	}

	@Override
	public Optional<Reward> getReward() {
		return Optional.of(new Reward(0));
	}

	@Override
	public List<String> getDescription() {
		return Arrays.asList("Achieve this on FIrst join.");
	}

	@Override
	public boolean isRepeatable() {
		return false;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		TTPlayer p = (TTPlayer) e.getPlayer();
		if (!p.getPlayer().hasPlayedBefore()){
			Achivement.unlockAchivement(p, this);
		}
	}

}
