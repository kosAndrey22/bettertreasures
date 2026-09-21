package com.kosoban.bettertreasures.loot.LootCreators;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public abstract class BaseLootItemCreator {
    protected double AncientCityModifier = 1.0;
    protected double EndCityModifier = 1.0;

    protected double BastionBridgeModifier = 1.0;
    protected double BastionHoglinStableModifier = 1.0;
    protected double BastionOtherModifier = 1.0;
    protected double BastionTreasureModifier = 1.0;

    protected double NetherBridgeModifier = 1.0;
    protected double WoodlandMansionModifier = 1.0;

    protected double AbandonedMineshaftModifier = 0.5;
    protected double DesertPyramidModifier = 0.5;
    protected double JungleTempleModifier = 0.5;
    protected double PillagerOutpostModifier = 0.5;
    protected double SimpleDungeonModifier = 0.5;
    protected double StrongholdCorridorModifier = 0.5;
    protected double StrongholdCrossingModifier = 0.5;
    protected double StrongholdLibraryModifier = 0.5;

    protected double ShipwreckMapModifier = 1.0;
    protected double ShipwreckSupplyModifier = 1.0;
    protected double ShipwreckTreasureModifier = 1.0;

    protected double UnderwaterRuinBigModifier = 1.0;
    protected double UnderwaterRuinSmallModifier = 1.0;

    protected double BuriedTreasureModifier = 1.0;
    protected double RuinedPortalModifier = 1.0;
    protected double IglooChestModifier = 1.0;


    protected Map<ResourceLocation, Double> LOOT_TABLES() {
        return Map.ofEntries(
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/ancient_city"
                        ),
                        AncientCityModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/end_city_treasure"
                        ),
                        EndCityModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/bastion_bridge"
                        ),
                        BastionBridgeModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/bastion_hoglin_stable"
                        ),
                        BastionHoglinStableModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/bastion_other"
                        ),
                        BastionOtherModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/bastion_treasure"
                        ),
                        BastionTreasureModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/nether_bridge"
                        ),
                        NetherBridgeModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/woodland_mansion"
                        ),
                        WoodlandMansionModifier
                ),

                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/abandoned_mineshaft"
                        ),
                        AbandonedMineshaftModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/desert_pyramid"
                        ),
                        DesertPyramidModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/jungle_temple"
                        ),
                        JungleTempleModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/pillager_outpost"
                        ),
                        PillagerOutpostModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/simple_dungeon"
                        ),
                        SimpleDungeonModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/stronghold_corridor"
                        ),
                        StrongholdCorridorModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/stronghold_crossing"
                        ),
                        StrongholdCrossingModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/stronghold_library"
                        ),
                        StrongholdLibraryModifier
                ),

                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/shipwreck_map"
                        ),
                        ShipwreckMapModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/shipwreck_supply"
                        ),
                        ShipwreckSupplyModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/shipwreck_treasure"
                        ),
                        ShipwreckTreasureModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/underwater_ruin_big"
                        ),
                        UnderwaterRuinBigModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/underwater_ruin_small"
                        ),
                        UnderwaterRuinSmallModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/buried_treasure"
                        ),
                        BuriedTreasureModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/ruined_portal"
                        ),
                        RuinedPortalModifier
                ),
                Map.entry(
                        new ResourceLocation(
                                "minecraft",
                                "chests/igloo_chest"
                        ),
                        IglooChestModifier
                )
        );
    }

    protected double getBaseTreasureChance() {
        return 1.0;
    }

    public boolean canCreate(
            ResourceLocation lootTableId,
            RandomSource random
    ) {
        Double structureModifier = LOOT_TABLES().get(lootTableId);

        if (structureModifier == null) {
            return false;
        }

        double chance = getBaseTreasureChance() * structureModifier;

        return random.nextDouble() < chance;
    }

    public abstract ItemStack create(RandomSource random);
}
