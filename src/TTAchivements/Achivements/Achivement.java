package TTAchivements.Achivements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.event.Listener;

import TTAchivements.TTAchivementsPlugin;

public interface Achivement extends Listener {
	
	public static List<Achivement> ACHIVEMENTS = new ArrayList<>();
	
	public String getName();
	public Optional<Reward> getReward();
	public List<String> getDescription();
	public boolean isRepeatable();
	
	public static <A extends Achivement> A register(A achivement){
		TTAchivementsPlugin.getPlugin().getPluginLoader().createRegisteredListeners(achivement, TTAchivementsPlugin.getPlugin());
		ACHIVEMENTS.add(achivement);
		return achivement;
	}

}
