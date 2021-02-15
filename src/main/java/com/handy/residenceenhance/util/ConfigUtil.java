package com.handy.residenceenhance.util;

import com.handy.residenceenhance.ResidenceEnhance;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * @author hs
 * @date 2021-02-10 13:18
 */
public class ConfigUtil {
    public static FileConfiguration config;

    /**
     * 初始化加载文件
     */
    public static void enableConfig() {
        // 1:没有文件夹就创建
        if (!ResidenceEnhance.getInstance().getDataFolder().exists()) {
            ResidenceEnhance.getInstance().getDataFolder().mkdir();
        }
        // 2:查询config没有就读取jar中的
        File configFile = new File(ResidenceEnhance.getInstance().getDataFolder(), "config.yml");
        if (!(configFile.exists())) {
            ResidenceEnhance.getInstance().saveDefaultConfig();
        }
        // 加载config
        lordConfig();
    }

    /**
     * 加载config
     */
    public static void lordConfig() {
        // 读取信息
        ResidenceEnhance.getInstance().reloadConfig();
        // 加载config
        config = ResidenceEnhance.getInstance().getConfig();
    }

}