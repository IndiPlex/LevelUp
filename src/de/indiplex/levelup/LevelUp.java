/*
 * LevelUp
 * Copyright (C) 2011 IndiPlex
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.indiplex.levelup;

import de.indiplex.manager.IPMPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author IndiPlex <Cartan12@indiplex.de>
 */
public class LevelUp extends IPMPlugin {
        public static final String pre = "[LvUp] ";

	@Override
	public void onDisable() {
            printDisabled(pre);
	}

	@Override
	public void onEnable() {
            if (getAPI()==null) {
                printDisabled(pre);
            }
            printEnabled(pre);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) return true;
            Player player = (Player) sender;
            
            float exp = player.getExp();
            int level = player.getLevel();
            
            if (args.length>0) {
                if (player.isOp()) {
                    if (args.length==1) {
                        if (args[0].equalsIgnoreCase("nextlvl")) {
                            player.setLevel(level+1);
                        } else {
                            return false;
                        }
                    }else if (args.length==2) {
                        if (args[0].equalsIgnoreCase("setlvl")) {
                            player.setLevel(Integer.parseInt(args[1]));
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                player.sendMessage("Exp:"+exp+" Lvl:"+level);
            }
        
            return true;
	}
	
	private int getRemeaningExp(int Level, int Exp) {
            return 10*(Level+1)-Exp;
	}

}
