package com.github.linggify.minecraft.craftminer.javascript.wrappers.element.block;

import com.github.linggify.minecraft.craftminer.javascript.wrappers.element.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

import java.lang.reflect.Field;

/**
 * Wrapper for block properties
 */
public class BlockPropertiesWrapper extends ElementWrapperBase<AbstractBlock.Properties> {

    private static final Field hasCollision = getField("hasCollision");
    private static final Field explosionResistance = getField("explosionResistance");
    private static final Field destroyTime = getField("destroyTime");
    private static final Field requiresCorrectToolForDrops = getField("requiresCorrectToolForDrops");
    private static final Field isRandomlyTicking = getField("isRandomlyTicking");
    private static final Field friction = getField("friction");
    private static final Field speedFactor = getField("speedFactor");
    private static final Field jumpFactor = getField("jumpFactor");
    private static final Field drops = getField("drops");
    private static final Field canOcclude = getField("canOcclude");
    private static final Field isAir = getField("isAir");
    private static final Field harvestLevel = getField("harvestLevel");
    private static final Field dynamicShape = getField("dynamicShape");
    private static final Field harvestTool = getField("harvestTool");

    private static Field getField(String name) {
        Field result = null;

        try {
            result = AbstractBlock.Properties.class.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private  static <T> T access(Field field, Object target, Class<T> type) throws IllegalAccessException {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);

        T result = (T) field.get(target);
        field.setAccessible(isAccessible);

        return result;
    }

    public BlockPropertiesWrapper(AbstractBlock.Properties target) {
        super(target);
    }

    public IElementWrapper<?> hasCollision() {
        try {
            return new BooleanWrapper(access(hasCollision, getValue(), Boolean.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> explosionResistance() {
        try {
            return new NumberWrapper(access(explosionResistance, getValue(), Number.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> destroyTime() {
        try {
            return new NumberWrapper(access(destroyTime, getValue(), Number.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> requiresCorrectToolForDrops() {
        try {
            return new BooleanWrapper(access(requiresCorrectToolForDrops, getValue(), Boolean.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> isRandomlyTicking() {
        try {
            return new BooleanWrapper(access(isRandomlyTicking, getValue(), Boolean.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> friction() {
        try {
            return new NumberWrapper(access(friction, getValue(), Number.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> speedFactor() {
        try {
            return new NumberWrapper(access(speedFactor, getValue(), Number.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> jumpFactor() {
        try {
            return new NumberWrapper(access(jumpFactor, getValue(), Number.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> drops() {
        try {
            ResourceLocation rl = access(drops, getValue(), ResourceLocation.class);
            return new StringWrapper(rl != null ? rl.toString() : "null");
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> canOcclude() {
        try {
            return new BooleanWrapper(access(canOcclude, getValue(), Boolean.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> isAir() {
        try {
            return new BooleanWrapper(access(isAir, getValue(), Boolean.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> harvestLevel() {
        try {
            return new NumberWrapper(access(harvestLevel, getValue(), Number.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> dynamicShape() {
        try {
            return new BooleanWrapper(access(dynamicShape, getValue(), Boolean.class));
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    public IElementWrapper<?> harvestTool() {
        try {
            ToolType tt = access(harvestTool, getValue(), ToolType.class);
            return new StringWrapper(tt != null ? tt.getName() : "null");
        } catch (IllegalAccessException e) {
            return new ExceptionWrapper(e);
        }
    }

    @Override
    public JsonElement getJsonValue() {
        JsonObject result = new JsonObject();
        result.add("hasCollision", hasCollision().getJsonValue());
        result.add("explosionResistance", explosionResistance().getJsonValue());
        result.add("destroyTime", destroyTime().getJsonValue());
        result.add("requiresCorrectToolForDrops", requiresCorrectToolForDrops().getJsonValue());
        result.add("isRandomlyTicking", isRandomlyTicking().getJsonValue());
        result.add("friction", friction().getJsonValue());
        result.add("speedFactor", speedFactor().getJsonValue());
        result.add("jumpFactor", jumpFactor().getJsonValue());
        result.add("drops", drops().getJsonValue());
        result.add("canOcclude", canOcclude().getJsonValue());
        result.add("isAir", isAir().getJsonValue());
        result.add("harvestLevel", harvestLevel().getJsonValue());
        result.add("dynamicShape", dynamicShape().getJsonValue());
        result.add("harvestTool", harvestTool().getJsonValue());

        return result;
    }
}
