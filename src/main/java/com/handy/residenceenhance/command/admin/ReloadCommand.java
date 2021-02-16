package com.handy.residenceenhance.command.admin;

import com.handy.residenceenhance.ResidenceEnhance;
import com.handy.residenceenhance.util.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author hs
 * @date 2020/3/23 14:41
 */
public class ReloadCommand {

    private ReloadCommand() {
    }

    private static volatile ReloadCommand instance;

    public static ReloadCommand getSingleton() {
        if (instance == null) {
            synchronized (ReloadCommand.class) {
                if (instance == null) {
                    instance = new ReloadCommand();
                }
            }
        }
        return instance;
    }

    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ConfigUtil.loadConfig();
                sender.sendMessage("§a命令执行成功");
            }
        }.runTaskAsynchronously(ResidenceEnhance.getInstance());
    }

}
