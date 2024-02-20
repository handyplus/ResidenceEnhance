package cn.handyplus.residence.enhance.command.admin;

import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.residence.enhance.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 重载
 *
 * @author handy
 */
public class ReloadCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "reload";
    }

    @Override
    public String permission() {
        return "residenceEnhance.reload";
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ConfigUtil.init();
        MessageUtil.sendMessage(sender, "&a重载成功");
    }

}