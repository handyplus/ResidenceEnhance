package cn.handyplus.residence.enhance.util;

import cn.handyplus.lib.util.HandyConfigUtil;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author handy
 */
public class ConfigUtil {
    public static FileConfiguration CONFIG;

    /**
     * 初始化加载文件
     */
    public static void init() {
        CONFIG = HandyConfigUtil.loadConfig();
    }

}