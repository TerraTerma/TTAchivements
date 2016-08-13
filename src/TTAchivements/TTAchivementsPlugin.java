package TTAchivements;

import org.bukkit.plugin.java.JavaPlugin;

import TTAchivements.Achivements.Achivement;
import TTAchivements.Mech.AchivementData;
import TTCore.Mech.DataHandler;

public class TTAchivementsPlugin extends JavaPlugin {
	
	static TTAchivementsPlugin PLUGIN;
	
	public void onEnable(){
		PLUGIN = this;
		DataHandler.MECHS.add(AchivementData.class);
		Achivement.loadAchivements();
	}
	
	public static TTAchivementsPlugin getPlugin(){
		return PLUGIN;
	}

}
