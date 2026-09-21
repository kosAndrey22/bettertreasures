package com.kosoban.bettertreasures;

import com.kosoban.bettertreasures.loot.BetterTreasuresLootModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterTreasuresMod.MODID)
public class BetterTreasuresMod {

    public static final String MODID = "bettertreasures";

    public BetterTreasuresMod(FMLJavaModLoadingContext context) {
        BetterTreasuresLootModifier.register(context.getModEventBus());
    }
}
