package me.dotsaviour.randomizerapi;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

/**
 * This class contains the all the chosen items.<br>
 * <br>
 * Note: all the items in the pack have the same appearance chance.
 */
public class Pack
{
	private final String identifier;
	private int lowerBound, upperBound;
	private final int size; //percentage of appearance
	private final ArrayList<ItemStack> items;
	
	/**
	 * 
	 * @param identifier
	 * @param percent : controls the appearance chance of the pack ( 1 - 100 )
	 * @param items
	 * @throws IllegalArgumentException
	 */
	public Pack(String identifier, int percent, ArrayList<ItemStack> items) throws IllegalArgumentException
	{

		if(items == null)
			throw new IllegalArgumentException("items can not be null");
		else if(identifier == null)
			throw new IllegalArgumentException("identifier can not be null");
		else if(percent > 100 || percent < 1)
			throw new IllegalArgumentException("percentage must be between 1-100");
		else if(items.size() == 0)
			throw new IllegalArgumentException("items can not be empty");
		
		this.identifier = identifier;
		this.size = percent;
		this.items = items;
	}
	
	public boolean isInBoundries(int percent)
	{
		return percent >= lowerBound && percent <= upperBound;
	}
	
	public boolean isConflicting(Pack pack)
	{
		return identifier.equals(pack.getIdentifier());
	}
	
	public boolean add(ItemStack item)
	{
		return items.add(item);
	}
	
	public ItemStack getRandomItem()
	{
		return items.get(new Random().nextInt(items.size()));
	}
	
	public int size() { return size; }
	public String getIdentifier() { return identifier; }
	protected void setLowerBound(int lowerBound) { this.lowerBound = lowerBound; } 
	protected void setUpperBound(int upperBound) { this.upperBound = upperBound; }
}
