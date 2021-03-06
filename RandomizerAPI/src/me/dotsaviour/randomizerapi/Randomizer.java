package me.dotsaviour.randomizerapi;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

/**
 * This class allows you to randomize and get randomized items
 * @author dotSaviour
 * 
 */
public class Randomizer
{
	private String identifier;
	private ItemsManager manager;
	
	public Randomizer(String identifier, ItemsManager manager) throws IllegalArgumentException, IllegalStateException
	{
		if(identifier == null)
			throw new IllegalArgumentException("identifier can not be null");
		
		if(manager == null)
			throw new IllegalArgumentException("manager can not be null");
		
		if(!manager.isReady())
		{
			Bukkit.getPluginManager().disablePlugin(RandomizerAPI.getInstance());
			throw new IllegalStateException("you can not start randomizing while manager is not ready... disabling API");
		}
		else
		{
			this.identifier = identifier;
			this.manager = manager;
		}
	}
	
	public ItemStack[] randomize(int numOfItems) throws IllegalArgumentException
	{	
		if(numOfItems <= 0)
			throw new IllegalArgumentException("number of items can not be less than 1");

		ItemStack[] items = new ItemStack[numOfItems];
		
		double percent;
		Item item;
		
		for(int i = 0; i < numOfItems; i++)
		{
			percent = Math.random() * manager.getTotal();
			item = manager.getInBoundaries(percent);
			items[i] = item.getItemStack();
		}
		
		return items;
	}
	
	protected String getIdentifier() { return identifier; }
	protected ItemsManager getManager() { return manager; }
}
