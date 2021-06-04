package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.item;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.BooleanWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.NumberWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.primitive.StringWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.ResourceLocationWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.SoundEventWrapper;
import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.util.text.TextComponentWrapper;
import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemWrapper extends ElementWrapperBase<Item> {
    public ItemWrapper(@Nonnull Item target) {
        super(target);
    }

    public boolean isTagged(ResourceLocation tag) {
        ITag<Item> realTag = ItemTags.getAllTags().getTag(tag);

        if (realTag == null) {
            throw new IllegalArgumentException(tag + " is not a valid tag");
        }

        return realTag.contains(getValue());
    }

    //naming etc
    @Export
    @Nullable
    public ResourceLocationWrapper registryName() {
        return optional(ResourceLocationWrapper::new, getValue().getRegistryName());
    }

    @Export
    @Nullable
    public StringWrapper descriptionId() {
        return optional(getValue().getDescriptionId());
    }

    @Export
    @Nullable
    public TextComponentWrapper description() {
        return optional(TextComponentWrapper::new, getValue().getDescription());
    }

    //stack
    @Export
    @Nonnull
    public NumberWrapper maxStackSize() {
        return new NumberWrapper(getValue().getDefaultInstance().getMaxStackSize());
    }

    @Export
    @Nonnull
    public NumberWrapper maxDamage() {
        return new NumberWrapper(getValue().getDefaultInstance().getMaxDamage());
    }

    @Export
    @Nonnull
    public ItemRarityWrapper rarity() {
        return new ItemRarityWrapper(getValue().getDefaultInstance().getRarity());
    }

    @Export
    @Nonnull
    public BooleanWrapper hasFoil() {
        return new BooleanWrapper(getValue().getDefaultInstance().hasFoil());
    }

    //sounds
    @Export
    @Nullable
    public SoundEventWrapper eatingSound() {
        return optional(SoundEventWrapper::new, getValue().getEatingSound());
    }

    @Export
    @Nullable
    public SoundEventWrapper drinkingSound() {
        return optional(SoundEventWrapper::new, getValue().getDrinkingSound());
    }

    //other
    @Export
    @Nullable
    public BooleanWrapper canDeplete() {
        return new BooleanWrapper(getValue().canBeDepleted());
    }

    @Export
    @Nullable
    public ItemFoodPropertiesWrapper foodProperties() {
        return optional(ItemFoodPropertiesWrapper::new, getValue().getFoodProperties());
    }
}
