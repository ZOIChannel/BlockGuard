package jp.hack.minecraft.blockguard.utils;

import jp.hack.minecraft.blockguard.core.utils.Configuration;

import java.io.File;
import java.util.List;

public class MainConfiguration extends Configuration {

    public MainConfiguration(File configFile) {
        super(configFile);
        setTemplateName("config.yml");
        save();
    }


    public List<String> getGameList() {
        synchronized (this) {
            return getStringList("regions");
        }
    }

    public void addGame(String gameId){
        synchronized (this) {
            List<String> list = getStringList("regions");
            if(list.contains(gameId)!=true){
                list.add(gameId);
                setProperty("regions", list);
            }
        }
    }

    public void deleteGame(String gameId){
        synchronized (this) {
            List<String> list = getStringList("regions");
            if(list.contains(gameId)==true){
                list.remove(gameId);
                setProperty("regions", list);
            }
        }
    }
}