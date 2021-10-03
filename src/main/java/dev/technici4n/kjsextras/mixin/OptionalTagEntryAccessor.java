package dev.technici4n.kjsextras.mixin;

import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Tag.OptionalTagEntry.class)
public interface OptionalTagEntryAccessor {
	@Accessor("id")
	Identifier kjsextras_getTagId();
}
