package cn.handyplus.residence.enhance;

import cn.handyplus.lib.InitApi;
import cn.handyplus.residence.enhance.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 初始化
 * @author handy
 **/
public class ResidenceEnhance extends JavaPlugin {
    public static ResidenceEnhance INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        InitApi initApi = InitApi.getInstance(this);
        // 加载配置文件
        ConfigUtil.init();

        initApi.initCommand("cn.handyplus.residence.enhance.command")
                .initListener("cn.handyplus.residence.enhance.listener")
                .enableSql("cn.handyplus.residence.enhance.enter");

    }

}
