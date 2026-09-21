package com.kosoban.bettertreasures.loot.LootCreators;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;

public class SwordLootCreator extends BaseLootItemCreator {

    private static final double[] SHARPNESS_CHANCES = {
            0.02000,  // VI
            0.00532,  // VII
            0.00142,  // VIII
            0.00038,  // IX
            0.00010   // X
    };

    @Override
    public ItemStack create(RandomSource random) {
        return createSword(random);
    }

    private ItemStack createSword(RandomSource random) {
        ItemStack sword = rollSword(random);

        enchant(sword, random);

        return sword;
    }

    private ItemStack rollSword(RandomSource random) {

        double ironWeight = 1.0;
        double goldWeight = 0.5;
        double diamondWeight = 1.0 / 3.0;
        double netheriteWeight = 0.1;

        double totalWeight =
                ironWeight
                        + goldWeight
                        + diamondWeight
                        + netheriteWeight;

        double roll = random.nextDouble() * totalWeight;

        if (roll < ironWeight) {
            return new ItemStack(Items.IRON_SWORD);
        }

        roll -= ironWeight;

        if (roll < goldWeight) {
            return new ItemStack(Items.GOLDEN_SWORD);
        }

        roll -= goldWeight;

        if (roll < diamondWeight) {
            return new ItemStack(Items.DIAMOND_SWORD);
        }

        return new ItemStack(Items.NETHERITE_SWORD);
    }

    private void enchant(ItemStack sword, RandomSource random) {
        int sharpnessLevel = rollSharpness(random);

        sword.enchant(
                Enchantments.SHARPNESS,
                sharpnessLevel
        );
    }

    private int rollSharpness(RandomSource random) {

        double totalWeight = 0.0;

        for (double chance : SHARPNESS_CHANCES) {
            totalWeight += chance;
        }

        double roll =
                random.nextDouble() * totalWeight;

        double accumulated = 0.0;

        for (int i = 0; i < SHARPNESS_CHANCES.length; i++) {

            accumulated += SHARPNESS_CHANCES[i];

            if (roll < accumulated) {
                return 6 + i;
            }
        }

        return 6;
    }
}
