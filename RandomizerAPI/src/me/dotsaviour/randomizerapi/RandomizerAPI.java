package me.dotsaviour.randomizerapi;

import java.util.ArrayList;

import javax.naming.NamingException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomizerAPI extends JavaPlugin
{	
	private static RandomizerAPI instance;
	private ArrayList<Randomizer> randomizers = new ArrayList<Randomizer>();
	
	@Override
	public void onEnable()
	{
		instance = this;
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "RandomizerAPI"+ ChatColor.WHITE + "-" + ChatColor.GREEN + "Online");
	}
	
	public void addRandomizer(Randomizer randomizer) throws NamingException
	{
		if(getRandomizer(randomizer.getIdentifier()) != null)
			throw new NamingException("there is another randomizer object that has the same identifier you chose");
		
		randomizers.add(randomizer);
	}
	
	public Randomizer getRandomizer(String identifier)
	{
		for(Randomizer randomizer : randomizers)
			if(randomizer.getIdentifier().equals(identifier))
				return randomizer;
		
		return null;
	}
	
	public PacksManager getPacksManager(String identifier)
	{
		return getRandomizer(identifier).getManager();
	}
	
	public Pack getPack(String randomizerId, String packId)
	{
		Randomizer randomizer = getRandomizer(randomizerId);
		
		if(randomizer != null)
			return randomizer.getManager().getPack(packId);
		
		return null;
	}
	
	public static RandomizerAPI getInstance()
	{
		return instance;
	}
}