package me.dotsaviour.randomizerapi;

import java.util.ArrayList;

public class ItemsManager
{
	private ArrayList<Item> items = new ArrayList<Item>();
	private boolean isReady = false;
	private double total = 0;
	

	public void addItem(Item item) throws IllegalArgumentException
	{
		if(isReady)
			return;
		
		if(total + item.getPercent() > 100)
			throw new IllegalArgumentException("the total percentage of all items can not exceed 100");
		
//		for(Item localPack : items)
//			if(localPack.getItem().isSimilar(item.getItem()))
//				throw new IllegalArgumentException("you can not add two similar ");
		
		items.add(item);
		
		item.setLowerBound(total);
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
	
	public ArrayList<Item> getItems() { return items; }
	public boolean isReady() { return isReady; }
	public double getTotal() { return total; }
}
