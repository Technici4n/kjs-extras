package dev.technici4n.kjsextras.mixin;

import dev.latvian.kubejs.server.TagEventJS;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mixin(value = TagEventJS.TagWrapper.class, remap = false)
public class TagWrapperMixin {
	@Final
	@Shadow
	private List<Tag.TrackedEntry> proxyList;

	public Set<String> kjsextras_getAllIds() {
		Set<String> set = new HashSet<>();
		for (Tag.TrackedEntry entry : proxyList) {
			set.add(((ObjectEntryAccessor) entry.getEntry()).kjsextras_getId().toString());
		}
		return set;
	}
}
