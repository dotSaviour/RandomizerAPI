package me.dotsaviour.randomizerapi;

import org.bukkit.inventory.ItemStack;

/**
 * This class contains the all the chosen items.<br>
 * <br>
 * Note: all the items in the pack have the same appearance chance.
 */
public class Item
{
	private final ItemStack item;
	private double lowerBound, upperBound;
	private final double percent; //percentage of appearance
	
	public Item(ItemStack item, double percent) throws IllegalArgumentException
	{

		if(item == null)
			throw new IllegalArgumentException("item can not be null");
		else if(percent >= 100 || percent <= 0)
			throw new IllegalArgumentException("percentage must be between 1-100");
		
		this.percent = percent;
		this.item = item;
	}
	
	public boolean isInBoundries(double percent)
	{
		return percent >= lowerBound && percent <= upperBound;
	}
	
	public double getPercent() { return percent; }
	protected ItemStack getItemStack() { return item.clone(); }
	protected void setLowerBound(double lowerBound) { this.lowerBound = lowerBound; } 
	protected void setUpperBound(double upperBound) { this.upperBound = upperBound; }
}
