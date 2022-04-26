package dev.technici4n.kjsextras.mixin;

import dev.latvian.mods.kubejs.server.TagEventJS;
import net.minecraft.tags.Tag;
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
	private List<Tag.BuilderEntry> proxyList;

	public Set<String> kjsextras_getAllIds() {
		Set<String> set = new HashSet<>();
		addAllIds(set);
		return set;
	}

	@Unique
	private void addAllIds(Set<String> set) {
		for (var builderEntry : proxyList) {
			Tag.Entry entry = builderEntry.entry();
			if (entry instanceof Tag.ElementEntry el) {
				set.add(el.id.toString());
			} else if (entry instanceof Tag.OptionalElementEntry el) {
				if (((TagEventJSAccessor) event).kjsextras_getRegistry().containsKey(el.id)) {
					set.add(el.id.toString());
				}
			} else if (entry instanceof Tag.TagEntry acc) {
				((TagWrapperMixin) (Object) event.get(acc.id)).addAllIds(set);
			} else if (entry instanceof Tag.OptionalTagEntry acc) {
				if (((TagEventJSAccessor) event).kjsextras_getTags().containsKey(acc.id)) {
					((TagWrapperMixin) (Object) event.get(acc.id)).addAllIds(set);
				}
			} else {
				throw new UnsupportedOperationException("Unsupported tag entry class: " + entry.getClass().getCanonicalName());
			}
		}
	}
}
