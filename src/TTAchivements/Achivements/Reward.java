package TTAchivements.Achivements;

import java.util.Optional;

import org.bukkit.inventory.ItemStack;

public class Reward {
	
	ItemStack ITEM;
	double MONEY;
	
	public Reward(ItemStack item){
		ITEM = item;
	}
	
	public Reward(double cash){
		MONEY = cash;
	}
	public Reward(ItemStack item, double cash){
		ITEM = item;
		MONEY = cash;
	}
	
	public Optional<ItemStack> getItem(){
		return Optional.ofNullable(ITEM);
	}
	
	public double getMoney(){
		return MONEY;
	}

}
