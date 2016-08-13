package TTAchivements.Mech;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import TTAchivements.Achivements.Achivement;
import TTCore.Mech.DataHandlers.PlayerData;
import TTCore.Mech.DataHandlers.SavableData;
import TTCore.Savers.Saver;

public class AchivementData implements SavableData, PlayerData {
	
	List<Achivement> UNLOCKED = new ArrayList<>();
	
	static String DATA_ACHIVEMENT = "Achivements";
	
	public boolean unlockAchivement(Achivement achivement){
		if(!hasUnlockedAchivement(achivement)){
			UNLOCKED.add(achivement);
			return true;
		}
		return achivement.isRepeatable();
	}
	
	public boolean hasUnlockedAchivement(Achivement ach){
		return UNLOCKED.stream().anyMatch(a -> a.equals(ach));
	}

	@Override
	public void save(Saver saver) {
		List<String> list = new ArrayList<>();
		UNLOCKED.stream().forEach(a -> list.add(a.getName()));
		saver.set(list, DATA_ACHIVEMENT);
		
	}

	@Override
	public boolean load(Saver saver) {
		List<String> list = saver.getList(String.class, DATA_ACHIVEMENT);
		if(list != null){
			list.stream().forEach(s -> {
				Optional<Achivement> opAchivement = Achivement.getByName(s);
				if(opAchivement.isPresent()){
					UNLOCKED.add(opAchivement.get());
				}
			});
		}
		return true;
	}

}
