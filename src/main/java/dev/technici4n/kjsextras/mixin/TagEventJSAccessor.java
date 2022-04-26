package dev.technici4n.kjsextras.mixin;

import dev.latvian.mods.kubejs.server.TagEventJS;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = TagEventJS.class, remap = false)
public interface TagEventJSAccessor {
	@Accessor("registry")
	Registry<?> kjsextras_getRegistry();

	@Accessor("tags")
	Map<ResourceLocation, TagEventJS.TagWrapper<?>> kjsextras_getTags();
}
