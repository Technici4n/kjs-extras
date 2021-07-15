package dev.technici4n.kjsextras.mixin;

import dev.latvian.kubejs.server.TagEventJS;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mixin(value = TagEventJS.TagWrapper.class, remap = false)
public class TagWrapperMixin {
	@Final
	@Shadow
	private TagEventJS event;
	@Final
	@Shadow
	private List<Tag.TrackedEntry> proxyList;

	public Set<String> kjsextras_getAllIds() {
		Set<String> set = new HashSet<>();
		addAllIds(set);
		return set;
	}

	@Unique
	private void addAllIds(Set<String> set) {
		for (Tag.TrackedEntry trackedEntry : proxyList) {
			Tag.Entry entry = trackedEntry.getEntry();
			if (entry instanceof ObjectEntryAccessor acc) {
				set.add(acc.kjsextras_getId().toString());
			} else if (entry instanceof TagEntryAccessor acc) {
				((TagWrapperMixin) (Object) event.get(acc.kjsextras_getTagId())).addAllIds(set);
			} else {
				throw new UnsupportedOperationException("Unsupported tag entry class: " + entry.getClass().getCanonicalName());
			}
		}
	}
}
