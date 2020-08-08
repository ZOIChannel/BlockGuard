package jp.hack.minecraft.blockguard.core;

import java.util.Arrays;
import java.util.List;

public abstract class RegionFlagType {
    public static final String SPREADFIRE = "spreadFire";
    public static final String EXPLODETNT = "explodeTNT";
    public static final String BREAKBYWATER = "breakByWater";
    public static final String INVADEMOB = "invadeMob";

    public List<String> getFlagTypes() {
        return Arrays.asList(SPREADFIRE, EXPLODETNT, BREAKBYWATER, INVADEMOB);
    }
}
