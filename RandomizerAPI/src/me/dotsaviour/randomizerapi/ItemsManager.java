package me.dotsaviour.randomizerapi;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

/**
 * This class manages the packs and organizes their appearance or rarity level.<br>
 * <br>
 * Note: you need to make the total percentage 100% not less and not more to proceed to the next step.
 */
public class ItemsManager
{
	private ArrayList<Item> items = new ArrayList<Item>();
	private boolean isReady = false;
	private double total = 0;
	

	public void add(Item item) throws IllegalArgumentException
	{
		if(isReady)
			return;
		
		if(total + item.getPercent() > 100)
			throw new IllegalArgumentException("the total percentage of all packs can not exceed 100");
		
//		for(Item localPack : items)
//			if(localPack.getItem().isSimilar(item.getItem()))
//				throw new IllegalArgumentException("you can not add two similar ");
		
		items.add(item);
		
		item.setLowerBound(total+1);
		item.setUpperBound(total+item.getPercent());
		
		total += item.getPercent();
		
		if(total == 100)
			isReady = true;
	}
	
	protected Item getInBoundaries(double percent)
	{
		for(Item item : items)
			if(item.isInBoundries(percent))
				return item;
		
		return null;
	}
	
	public Item getItem(ItemStack item)
	{
		ItemStack targetItem;
		boolean isSameItems;
		
		for(Item localItem : items)
		{
			targetItem = localItem.getItemStack();
			isSameItems = targetItem.isSimilar(item) && localItem.getItemStack().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName());
			
			if(isSameItems)
				return localItem;
		}
		
		return null;
	}
	
	public boolean isReady() { return isReady; }
}
