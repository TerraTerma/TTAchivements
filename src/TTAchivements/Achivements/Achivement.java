package TTAchivements.Achivements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import TTAchivements.TTAchivementsPlugin;
import TTAchivements.Achivements.Types.FirstJoinAchievement;
import TTAchivements.Achivements.Types.WilhelmScreamAchivement;
import TTAchivements.Mech.AchivementData;
import TTCore.Entity.Living.Human.Player.TTPlayer;

public interface Achivement extends Listener {
	
	public static final List<Achivement> ACHIVEMENTS = new ArrayList<>();
	public static final WilhelmScreamAchivement WILHELM_SCREAM = register(new WilhelmScreamAchivement());
	public static final FirstJoinAchievement FIRST_JOIN = register(new FirstJoinAchievement());
	
	public String getName();
	public Optional<Reward> getReward();
	public List<String> getDescription();
	public boolean isRepeatable();
	
	public static void loadAchivements(){
		//INTERFACE NEEDS TO BE IN CLASS LOADER, THIS WILL DO IT
		String achivements = null;
		for(Achivement ach : ACHIVEMENTS){
			if(achivements == null){
				achivements = ach.getName();
			}else{
				achivements = achivements + ", " + ach.getName(); 
			}
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "loaded " + ACHIVEMENTS.size() + " achivements \n " + achivements);
	}
	
	public static <A extends Achivement> A register(A achivement){
		TTAchivementsPlugin.getPlugin().getServer().getPluginManager().registerEvents(achivement, TTAchivementsPlugin.getPlugin());
		ACHIVEMENTS.add(achivement);
		return achivement;
	}
	
	public static boolean unlockAchivement(TTPlayer player, Achivement achivement){
		player.sendMessage(TTAchivementsPlugin.getPlugin(), "You have unlocked the achievement " + achivement.getName());
		AchivementData data = player.getSingleData(AchivementData.class).get();
		return data.unlockAchivement(achivement);
	}
	
	public static Optional<Achivement> getByName(String name){
		return ACHIVEMENTS.stream().filter(a -> a.getName().equals(name)).findFirst();
	}

}
