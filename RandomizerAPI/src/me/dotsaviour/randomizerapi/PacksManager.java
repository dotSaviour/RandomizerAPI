package me.dotsaviour.randomizerapi;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

/**
 * This class manages the packs and organizes their appearance or rarity level.<br>
 * <br>
 * Note: you need to make the total percentage 100% not less and not more to proceed to the next step.
 */
public class PacksManager
{
	private ArrayList<Pack> packs = new ArrayList<Pack>();
	private boolean isReady = false;
	private int total = 0;
	
	/**
	 * This method will add a pack that contains items to the API.<br>
	 * <br>
	 * Note: the API will not be usable until the total percentage of all packs is 100
	 * 
	 * @param pack
	 * @throws IllegalArgumentException
	 */
	public void add(Pack pack) throws IllegalArgumentException
	{
		if(isReady)
			return;
		
		if((total + pack.size()) > 100)
			throw new IllegalArgumentException("the total percentage of all packs can not exceed 100");
		
		for(Pack localPack : packs)
			if(localPack.isConflicting(pack))
				throw new IllegalArgumentException("packs can not have the same identifier");
		
		packs.add(pack);
		
		pack.setLowerBound(total+1);
		pack.setUpperBound(total+pack.size());
		
		total += pack.size();
		
		if(total == 100)
			isReady = true;
	}
	
	public boolean addItem(String identifier, ItemStack item)
	{
		for(Pack pack : packs)
			if(pack.getIdentifier().equals(identifier))
				return pack.add(item);
		
		return false;
	}
	
	protected Pack getInBoundaries(int percent)
	{
		for(Pack pack : packs)
		{
			if(pack.isInBoundries(percent))
				return pack;
		}
		
		return null;
	}
	
	protected Pack getPack(String identifier)
	{
		for(Pack pack : packs)
			if(pack.getIdentifier().equals(identifier))
				return pack;
		
		return null;
	}
	
	public boolean isReady() { return isReady; }
}
