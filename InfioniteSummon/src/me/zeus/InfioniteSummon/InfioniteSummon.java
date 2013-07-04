
package me.zeus.InfioniteSummon;


import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;



public class InfioniteSummon extends JavaPlugin implements Listener
{
	
	
	Map<String, String> players = new HashMap<String, String>();
	
	
	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	
	
	@EventHandler
	public void onSummon(final PlayerDropItemEvent e)
	{
		final ItemStack dropped = e.getItemDrop().getItemStack();
		final Location l = e.getItemDrop().getLocation();
		
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable()
		{
			
			
			@SuppressWarnings("incomplete-switch")
			@Override
			public void run()
			{
				int radius = 2;
				Block block = l.getWorld().getBlockAt(l);
				for (int x = -(radius); x <= radius; x++)
					for (int y = -(radius); y <= radius; y++)
						for (int z = -(radius); z <= radius; z++)
						{
							Block b = block.getRelative(x, y, z);
							if (b.getType().equals(Material.CAULDRON))
							{
								switch (dropped.getType())
								{
									case ROTTEN_FLESH:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.ZOMBIE);
										e.getItemDrop().remove();
										break;
									case SULPHUR:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.CREEPER);
										e.getItemDrop().remove();
										break;
									case BONE:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.SKELETON);
										e.getItemDrop().remove();
										break;
									case ENDER_PEARL:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.ENDERMAN);
										e.getItemDrop().remove();
										break;
									case BLAZE_ROD:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.BLAZE);
										e.getItemDrop().remove();
										break;
									case LEATHER:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.OCELOT);
										e.getItemDrop().remove();
										break;
									case RAW_BEEF:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.COW);
										e.getItemDrop().remove();
										break;
									case RAW_CHICKEN:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.CHICKEN);
										e.getItemDrop().remove();
										break;
									case WOOL:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.SHEEP);
										e.getItemDrop().remove();
										break;
									case PORK:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.PIG);
										e.getItemDrop().remove();
										break;
									case SPIDER_EYE:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.CAVE_SPIDER);
										e.getItemDrop().remove();
										break;
									case STRING:
										l.getWorld().spawnEntity(block.getLocation(), EntityType.SPIDER);
										e.getItemDrop().remove();
										break;
									case SKULL_ITEM:
										e.getItemDrop().remove();
										SkullMeta meta = (SkullMeta) dropped.getItemMeta();
										if (meta.getOwner() != null && !meta.getOwner().equals(""))
											if (!players.containsKey(e.getPlayer().getName()))
												players.put(e.getPlayer().getName(), meta.getOwner());
										break;
									case GOLD_NUGGET:
										e.getItemDrop().remove();
										l.getWorld().spawnEntity(block.getLocation(), EntityType.PIG_ZOMBIE);
										break;
									case GHAST_TEAR:
										e.getItemDrop().remove();
										l.getWorld().spawnEntity(block.getLocation(), EntityType.GHAST);
										break;
									case SLIME_BALL:
										e.getItemDrop().remove();
										if (players.containsKey(e.getPlayer().getName()))
										{
											Player summoned = getServer().getPlayerExact(players.get(e.getPlayer().getName()));
											players.remove(e.getPlayer().getName());
											if (summoned != null)
												summoned.teleport(l);
										}
										else
											l.getWorld().spawnEntity(block.getLocation(), EntityType.SLIME);
										break;
								}
							}
						}
			}
		}, 5L);
		
	}
}
