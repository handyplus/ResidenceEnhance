package com.handy.residenceenhance;

import com.handy.residenceenhance.command.ResidenceEnhanceCommand;
import com.handy.residenceenhance.listener.PlayerCommandPreprocessEventListener;
import com.handy.residenceenhance.listener.ResidenceChangedEventListener;
import com.handy.residenceenhance.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author hs
 * @date 2021-02-15 16:42
 **/
public class ResidenceEnhance extends JavaPlugin {
    private static ResidenceEnhance instance;

    @Override
    public void onEnable() {
        instance = this;

        // 加载命令
        this.getCommand("ResidenceEnhance").setExecutor(new ResidenceEnhanceCommand());
        this.getCommand("ResidenceEnhance").setTabCompleter(new ResidenceEnhanceCommand());

        // 加载配置文件
        ConfigUtil.enableConfig();

        // 当玩家进入新的领地时触发.
        getServer().getPluginManager().registerEvents(new ResidenceChangedEventListener(), this);
        // 当一个玩家执行一个命令的时候将会被触发.
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessEventListener(), this);
    }

    public static ResidenceEnhance getInstance() {
        return instance;
    }

}
