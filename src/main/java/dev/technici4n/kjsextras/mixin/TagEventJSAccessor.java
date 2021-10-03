package dev.technici4n.kjsextras.mixin;

import dev.latvian.kubejs.server.TagEventJS;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Mixin(TagEventJS.class)
public interface TagEventJSAccessor {
	@Accessor("registry")
	Function<Identifier, Optional<?>> kjsextras_getRegistry();

	@Accessor("tags")
	Map<Identifier, TagEventJS.TagWrapper<?>> kjsextras_getTags();
}
