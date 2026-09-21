package com.kosoban.bettertreasures.loot;

import com.kosoban.bettertreasures.BetterTreasuresMod;
import com.kosoban.bettertreasures.loot.LootCreators.LootCreator;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BetterTreasuresLootModifier extends LootModifier {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(
                    ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    BetterTreasuresMod.MODID
            );

    public static final RegistryObject<Codec<BetterTreasuresLootModifier>> CODEC =
            LOOT_MODIFIERS.register(
                    "better_treasures",
                    () -> RecordCodecBuilder.create(instance ->
                            LootModifier.codecStart(instance)
                                    .apply(instance, BetterTreasuresLootModifier::new)
                    )
            );

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }

    public BetterTreasuresLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot,
            LootContext context
    ) {

        RandomSource random = context.getRandom();;

        ItemStack additionalLoot =
                LootCreator.getAdditionalLoot(
                        context,
                        random
                );

        if (!additionalLoot.isEmpty()) {
            generatedLoot.add(additionalLoot);
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
