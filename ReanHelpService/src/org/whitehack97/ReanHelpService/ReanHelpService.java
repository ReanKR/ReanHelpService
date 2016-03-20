package org.whitehack97.ReanHelpService;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.whitehack97.ReanHelpService.api.Help;


public class ReanHelpService extends JavaPlugin implements Listener
{
	public static Help HelpClass;
	public static String Permission = "ExtendedCustomHelp.use";
	public static Plugin plugin;
	
	@Override
	public void onEnable()
	{
		File file = new File("plugins/ReanHelpService/config.yml");
		if(! file.exists())
		{
			this.saveDefaultConfig();
		}
		plugin = this;
		if(HelpLoader.LoadHelpFile())
		{
			getServer().getPluginManager().registerEvents(this, this);
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReanHelpService now Enabled"));
		}
		else
		{
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cReanHelpService cannot running."));
		}
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cReanHelpService now Disabled"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			String cmd = command.getName();
			if(CheckHelp(cmd))
			{
				if(args.length < 1)
				{
					for(String Text : HelpClass.getText())
					{
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', Text));
					}
					return true;
				}
				else
				{
					if(args.length < 2)
					{
						if(HelpClass.getChildrenClasses().containsKey((args[0].toLowerCase())))
						{
							Help Class = HelpClass.getChildrenClasses().get(args[0].toLowerCase());
							for(String Text : Class.getText())
							{
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', Text));
							}
							return true;
						}
						else
						{
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help-Not-Found")));
							return false;
						}
					}
					else
					{
						if(HelpClass.getChildrenClasses().containsKey((args[0].toLowerCase())))
						{
							if(HelpClass.getChildrenClasses().get((args[0].toLowerCase())).getChildrenClasses().containsKey(args[1].toLowerCase()))
							{
								Help Class = HelpClass.getChildrenClasses().get(args[0].toLowerCase()).getChildrenClasses().get(args[1].toLowerCase());
								for(String Text : Class.getText())
								{
									player.sendMessage(ChatColor.translateAlternateColorCodes('&', Text));
								}
								return true;
							}
							else
							{
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help-Not-Found")));
								return false;
							}
						}
						else
						{
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Help-Not-Found")));
							return false;
						}
					}
				}
			}
			return false;
		}
		else
		{
			String cmd = command.getName();
			if(CheckHelp(cmd))
			{
				if(args.length < 1)
				{
					for(String Text : HelpClass.getText())
					{
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Text));
					}
					return true;
				}
				else
				{
					if(args.length < 2)
					{
						if(HelpClass.getChildrenClasses().containsKey((args[0].toLowerCase())))
						{
							Help Class = HelpClass.getChildrenClasses().get(args[0].toLowerCase());
							for(String Text : Class.getText())
							{
								Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Text));
							}
							return true;
						}
						else
						{
							
						}
					}
					else
					{
						if(HelpClass.getChildrenClasses().containsKey((args[0].toLowerCase())))
						{
							if(HelpClass.getChildrenClasses().get((args[0].toLowerCase())).getChildrenClasses().containsKey(args[1].toLowerCase()))
							{
								Help Class = HelpClass.getChildrenClasses().get(args[0].toLowerCase()).getChildrenClasses().get(args[1].toLowerCase());
								for(String Text : Class.getText())
								{
									Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', Text));
								}
								return true;
							}
							else
							{
								
							}
						}
						else
						{
							
						}
					}
				}
			}
			return false;
		}
	}
	
	public boolean CheckHelp(String command)
	{
		if(command.equalsIgnoreCase("help"))
		{
			return true;
		}
		
		else if(command.equalsIgnoreCase("?"))
		{
			return true;
		}
		return false;
	}
}
