package cn.handyplus.residence.enhance.listener;

import cn.handyplus.lib.annotation.HandyListener;
import com.bekvon.bukkit.residence.event.ResidenceChangedEvent;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import cn.handyplus.residence.enhance.util.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

/**
 * @author handy
 **/
@HandyListener
public class ResidenceChangedEventListener implements Listener {

    /**
     * 当玩家进入新的领地时触发，包括在子空间（子领地）
     * 这一事件会在玩家移动或者被传送到一个新的领地时被触发，同时，玩家登录时如果一登录就出现在某一个领地里也会触发
     *
     * @param event 事件
     */
    @EventHandler
    public void onResidenceChanged(ResidenceChangedEvent event) {
        ClaimedResidence to = event.getTo();
        if (to == null) {
            return;
        }
        Player player = event.getPlayer();

        // 判断是否op
        boolean isOp = ConfigUtil.CONFIG.getBoolean("isOp");
        if (!isOp && player.isOp()) {
            return;
        }

        // 判断是否领地所有者
        boolean isOwner = ConfigUtil.CONFIG.getBoolean("isOwner");
        if (!isOwner) {
            String owner = to.getOwner();
            if (player.getName().equals(owner)) {
                return;
            }
        }

        // 判断是否包含领地
        List<String> residenceNames = ConfigUtil.CONFIG.getStringList("name");
        if (!residenceNames.contains(to.getName())) {
            return;
        }


        boolean allowFlight = player.getAllowFlight();
        if (allowFlight) {
            player.setAllowFlight(false);
        }
    }

}
