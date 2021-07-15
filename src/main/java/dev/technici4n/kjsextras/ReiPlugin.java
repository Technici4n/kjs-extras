package dev.technici4n.kjsextras;

import dev.latvian.kubejs.event.EventJS;
import dev.latvian.kubejs.script.ScriptType;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

import java.util.HashSet;
import java.util.Set;

public class ReiPlugin implements REIClientPlugin {
	@Override
	public double getPriority() {
		return 1e6; // run after other stuff
	}

	@Override
	public void registerEntries(EntryRegistry registry) {
		if (FabricLoader.getInstance().isModLoaded("kubejs")) {
			Set<String> itemIds = getItemsToRemove();
			registry.removeEntryIf(entryStack -> {
				if (entryStack.getValue() instanceof ItemStack is) {
					if (itemIds.contains(Registry.ITEM.getId(is.getItem()).toString())) {
						return true;
					}
				}
				return false;
			});
		}
	}

	public static Set<String> getItemsToRemove() {
		return KjsExtrasReiEvent.gatherItemsToRemove();
	}

	public static class KjsExtrasReiEvent extends EventJS {
		private final Set<String> removeSet = new HashSet<>();

		public void remove(String item) {
			removeSet.add(item);
		}

		private static Set<String> gatherItemsToRemove() {
			KjsExtrasReiEvent event = new KjsExtrasReiEvent();
			event.post(ScriptType.CLIENT, "kjsextras_rei");
			return event.removeSet;
		}
	}
}
