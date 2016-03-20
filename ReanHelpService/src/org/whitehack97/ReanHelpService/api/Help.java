package org.whitehack97.ReanHelpService.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.whitehack97.ReanHelpService.ReanHelpService;

public class Help
{
	private List<String> Text = new ArrayList<String>();
	private Map<String, Help> ChildrenClass = new HashMap<String, Help>();
	private Help ParentClass;
	private String Permission;
	private boolean NeedPermission = false;
	private String Name;
	
	public Help(String Name)
	{
		this.Name = Name;
		if(Name.equalsIgnoreCase("Main"))
		{
			Permission = ReanHelpService.Permission;
		}
		else
		{
			Permission = ReanHelpService.Permission + ".Name";
		}
	}

	public void ParentClass(Help Class)
	{
		this.ParentClass = Class;
		Permission = ReanHelpService.Permission + "." + Class.getName();
	}
	
	public void ChildrenClass(String Name, Help Class)
	{
		this.ChildrenClass.put(Name, Class);
	}
	
	public Help getParentClass()
	{
		return this.ParentClass;
	}
	
	public Map<String, Help> getChildrenClasses()
	{
		return this.ChildrenClass;
	}

	public void setText(List<String> Texts)
	{
		this.Text = Texts;
	}
	
	public List<String> getText()
	{
		return this.Text;
	}
	
	public String getPermission()
	{
		return this.Permission;
	}
	
	public void setText(int Index, String Text)
	{
		this.Text.set(Index, ChatColor.translateAlternateColorCodes('&',Text));
	}
	
	public void removeText(int Index)
	{
		this.Text.remove(Index);
	}
	
	public void setPermission(boolean Permission)
	{
		this.NeedPermission  = Permission;
	}
	
	public boolean hasPermission()
	{
		return this.NeedPermission;
	}
	
	public String getName()
	{
		return this.Name;
	}
}
