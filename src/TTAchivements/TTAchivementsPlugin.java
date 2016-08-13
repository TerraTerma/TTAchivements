package TTAchivements;

import org.bukkit.plugin.java.JavaPlugin;

public class TTAchivementsPlugin extends JavaPlugin {
	
	static TTAchivementsPlugin PLUGIN;
	
	public void onEnable(){
		PLUGIN = this;
	}
	
	public static TTAchivementsPlugin getPlugin(){
		return PLUGIN;
	}

}
