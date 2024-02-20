package cn.handyplus.residence.enhance.command;

import cn.handyplus.lib.annotation.HandyCommand;
import cn.handyplus.lib.command.HandyCommandFactory;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.residence.enhance.constants.TabListEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author handy
 **/
@HandyCommand(name = "residenceEnhance")
public class ResidenceEnhanceCommand implements TabExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // 判断指令是否正确
        if (args.length < 1) {
            return true;
        }
        HandyCommandFactory.getInstance().onCommand(sender, cmd, label, args, BaseUtil.getMsgNotColor("noPermission"));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = TabListEnum.returnList(args, args.length);
        if (commands == null) {
            return null;
        }
        StringUtil.copyPartialMatches(args[args.length - 1].toLowerCase(), commands, completions);
        Collections.sort(completions);
        return completions;
    }

}
