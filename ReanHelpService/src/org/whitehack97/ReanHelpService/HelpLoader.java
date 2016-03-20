package org.whitehack97.ReanHelpService;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.whitehack97.ReanHelpService.api.Help;

public class HelpLoader
{
	public static boolean LoadHelpFile()
	{
		File file = new File("plugins/ReanHelpService/Help.yml");
		if(! file.exists())
		{
			ReanHelpService.plugin.saveResource("Help.yml", true);
		}
		YamlConfiguration fileSection = YamlConfiguration.loadConfiguration(file);
		Help Main = new Help("Main");
		if(fileSection.contains("Main"))
		{
			if(fileSection.contains("Main.Need-Permission"))
			{
				Main.setPermission(fileSection.getBoolean("Main.Need-Permission"));
			}
			
			if(fileSection.contains("Main.Text"))
			{
				Main.setText(fileSection.getStringList("Main.Text"));
			}
			else
			{
				// Main texts doesn't exist.
				return false;
			}
		}
		else
		{
			// Main doesn't exist.
			return false;
		}
		
		if(fileSection.contains("Children"))
		{
			for(String Children : fileSection.getConfigurationSection("Children").getKeys(false))
			{
				if(fileSection.contains("Children." + Children + ".Text"))
				{
					Help PrevChildrenPage = new Help(Children);
					PrevChildrenPage.ParentClass(Main);
					PrevChildrenPage.setText(fileSection.getStringList("Children." + Children + ".Text"));
					if(fileSection.contains("Children." + Children + ".Need-Permission"))
					{
						PrevChildrenPage.setPermission(fileSection.getBoolean("Children." + Children + ".Need-Permission"));
					}
					
					if(fileSection.contains("Children." + Children + ".Children"))
					{
						for(String Child : fileSection.getConfigurationSection("Children." + Children + ".Children").getKeys(false))
						{
							if(fileSection.contains("Children." + Children + ".Children." + Child))
							{
								Help ChildrenPage = new Help(Child);
								ChildrenPage.ParentClass(PrevChildrenPage);
								ChildrenPage.setText(fileSection.getStringList("Children." + Children + ".Children." + Child));
								PrevChildrenPage.ChildrenClass(Child.toLowerCase(), ChildrenPage);
							}
						}
					}
					Main.ChildrenClass(Children.toLowerCase(), PrevChildrenPage);
				}
			}
		}
		ReanHelpService.HelpClass = Main;
		return true;
	}
}
