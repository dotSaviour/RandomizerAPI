package me.dotsaviour.randomizerapi;

import java.util.Random;

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
	private PacksManager manager;
	
	/**
	 * 
	 * @param manager
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public Randomizer(String identifier, PacksManager manager) throws IllegalArgumentException, IllegalStateException
	{
		if(identifier == null)
			throw new IllegalArgumentException("identifier can not be null");
		
		if(manager == null)
			throw new IllegalArgumentException("manager can not be null");
		
		if(!manager.isReady())
		{
			Bukkit.getPluginManager().disablePlugin(RandomizerAPI.getInstance());
			throw new IllegalStateException("you can not start randomizing while manager is not ready... disabling plugin");
		}
		else
		{
			this.identifier = identifier;
			this.manager = manager;
		}
	}
	
	/**
	 * 
	 * @param numOfItems : the needed number of items to return
	 * @return
	 * @throws IllegalArgumentException when you enter any number in the parameter less than 1
	 */
	public ItemStack[] randomize(int numOfItems) throws IllegalArgumentException
	{	
		if(numOfItems <= 0)
			throw new IllegalArgumentException("number of items can not be less than 1");

		ItemStack[] items = new ItemStack[numOfItems];
		
		Random r = new Random();
		
		for(int i = 0; i < numOfItems; i++)
		{
			int percent = r.nextInt(100) + 1;
			Pack appearence = manager.getInBoundaries(percent);
			items[i] = appearence.getRandomItem();
		}
		
		return items;
	}
	
	/**
	 * This method adds an item using the identifier of the Appearance.
	 * 
	 * @param identifier : it is the pack identifier
	 * @param item
	 * @return
	 * true: if it added the item.
	 * false: otherwise.
	 */
	public boolean addItem(String identifier, ItemStack item)
	{
		return manager.addItem(identifier, item);
	}
	
	protected String getIdentifier() { return identifier; }
	protected PacksManager getManager() { return manager; }
}
