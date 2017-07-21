package me.dotsaviour.randomizerapi;

import org.bukkit.inventory.ItemStack;

public class Item
{
	private final ItemStack item;
	private double lowerBound, upperBound;
	private final double percent; //chance of appearance
	
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
		return percent >= lowerBound && percent < upperBound;
	}
	
	public double getPercent() { return percent; }
	protected ItemStack getItemStack() { return item.clone(); }
	protected void setLowerBound(double lowerBound) { this.lowerBound = lowerBound; } 
	protected void setUpperBound(double upperBound) { this.upperBound = upperBound; }
}
