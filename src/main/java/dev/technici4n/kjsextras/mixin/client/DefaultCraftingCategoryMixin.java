package dev.technici4n.kjsextras.mixin.client;

import dev.technici4n.kjsextras.mixinimpl.BasicDisplayExtensions;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.plugin.client.categories.crafting.DefaultCraftingCategory;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCraftingDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = DefaultCraftingCategory.class, remap = false)
public class DefaultCraftingCategoryMixin {
	@Redirect(at = @At(value = "INVOKE", target = "Lme/shedaniel/rei/plugin/common/displays/crafting/DefaultCraftingDisplay;getOutputEntries()Ljava/util/List;"), method = "setupDisplay")
	public List<EntryIngredient> redirectGetOutputEntries(DefaultCraftingDisplay<?> display) {
		return ((BasicDisplayExtensions) display).getActualOutputs();
	}
}
