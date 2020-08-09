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


    public List<String> getRegionList() {
        synchronized (this) {
            return getStringList("regions");
        }
    }

    public void addRegion(String id){
        synchronized (this) {
            List<String> list = getStringList("regions");
            if(list.contains(id)!=true){
                list.add(id);
                setProperty("regions", list);
                save();
            }
        }
    }

    public void deleteRegion(String id){
        synchronized (this) {
            List<String> list = getStringList("regions");
            if(list.contains(id)==true){
                list.remove(id);
                setProperty("regions", list);
                save();
            }
        }
    }
}