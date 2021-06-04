package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.item;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.ElementWrapperBase;
import com.google.gson.JsonElement;
import net.minecraft.item.Item;

public class ItemWrapper extends ElementWrapperBase<Item> {
    public ItemWrapper(Item target) {
        super(target);
    }

    @Override
    public JsonElement getJsonValue() {
        return null;
    }
}
