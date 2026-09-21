package com.kosoban.bettertreasures.loot.LootCreators;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.Map;

public class LootCreator {

    private static final Map<BaseLootItemCreator, Double> ITEM_CREATORS = Map.of(
            new SwordLootCreator(), 1.0,
            new BowLootCreator(), 1.0
    );

    public static ItemStack getAdditionalLoot(
            LootContext context,
            RandomSource random
    ) {
        ResourceLocation lootTableId = context.getQueriedLootTableId();

        BaseLootItemCreator creator = getItemCreator(random);

        if (!creator.canCreate(lootTableId, random)) {
            return ItemStack.EMPTY;
        }

        return creator.create(random);
    }

    private static BaseLootItemCreator getItemCreator(
            RandomSource random
    ) {
        double totalWeight = 0.0;

        for (double weight : ITEM_CREATORS.values()) {
            totalWeight += weight;
        }

        double roll = random.nextDouble() * totalWeight;

        for (Map.Entry<BaseLootItemCreator, Double> entry :
                ITEM_CREATORS.entrySet()) {

            roll -= entry.getValue();

            if (roll < 0) {
                return entry.getKey();
            }
        }

        return ITEM_CREATORS
                .keySet()
                .iterator()
                .next();
    }
}
