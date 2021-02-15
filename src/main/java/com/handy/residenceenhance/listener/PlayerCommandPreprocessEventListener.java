package com.handy.residenceenhance.listener;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.handy.residenceenhance.util.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hs
 * @date 2021-02-15 17:09
 **/
public class PlayerCommandPreprocessEventListener implements Listener {

    /**
     * 这个事件是,当一个玩家执行一个命令的时候将会被触发
     * (也就是在聊天框里面输入信息以/开头的时候，算作命令，就会触发此事件)。
     *
     * @param event 事件
     */
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        // 判断是否领地
        ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(player.getLocation());
        if (res == null) {
            return;
        }

        String message = event.getMessage();
        message = message.replace("/", "");
        if (message.length() < 1) {
            return;
        }

        String[] messageList = message.split(" ");
        String command = messageList[0];

        // 判断是否包含领地
        List<String> residenceNames = ConfigUtil.config.getStringList("name");
        if (!residenceNames.contains(res.getName())) {
            return;
        }

        // 判断是否op
        boolean isOp = ConfigUtil.config.getBoolean("isOp");
        if (isOp && player.isOp()) {
            return;
        }

        // 判断是否领地所有者
        boolean isOwner = ConfigUtil.config.getBoolean("isOwner");
        if (!isOwner) {
            String owner = res.getOwner();
            if (player.getName().equals(owner)) {
                return;
            }
        }

        // 判断是否包含领地禁止的命令
        List<String> residenceCommands = ConfigUtil.config.getStringList("command");

        List<String> residenceCommandsLowerCases = new ArrayList<>();
        for (String residenceCommand : residenceCommands) {
            residenceCommandsLowerCases.add(residenceCommand.toLowerCase());
        }

        if (!residenceCommandsLowerCases.contains(command.toLowerCase())) {
            return;
        }

        event.setCancelled(true);
        String msg = ConfigUtil.config.getString("msg");
        player.sendMessage(msg != null ? msg : "§a这个领地禁止执行该命令!");
    }

}