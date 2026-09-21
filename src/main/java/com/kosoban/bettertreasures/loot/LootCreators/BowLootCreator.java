package com.kosoban.bettertreasures.loot.LootCreators;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;

public class BowLootCreator extends BaseLootItemCreator {

    private static final double[] POWER_CHANCES = {
            0.02000,  // VI
            0.00532,  // VII
            0.00142,  // VIII
            0.00038,  // IX
            0.00010   // X
    };

    @Override
    public ItemStack create(RandomSource random) {
        ItemStack bow = new ItemStack(Items.BOW);

        enchant(bow, random);

        return bow;
    }

    private void enchant(ItemStack bow, RandomSource random) {

        int powerLevel = rollPower(random);

        bow.enchant(
                Enchantments.POWER_ARROWS,
                powerLevel
        );
    }

    private int rollPower(RandomSource random) {

        double totalWeight = 0.0;

        for (double chance : POWER_CHANCES) {
            totalWeight += chance;
        }

        double roll = random.nextDouble() * totalWeight;

        double accumulated = 0.0;

        for (int i = 0; i < POWER_CHANCES.length; i++) {

            accumulated += POWER_CHANCES[i];

            if (roll < accumulated) {
                return 6 + i;
            }
        }

        return 6;
    }
}
