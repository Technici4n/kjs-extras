package dev.technici4n.kjsextras.mixin.client;

import dev.technici4n.kjsextras.mixinimpl.BasicDisplayExtensions;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mixin(value = BasicDisplay.class, remap = false)
public abstract class BasicDisplayMixin implements BasicDisplayExtensions {
	@Shadow
	protected List<EntryIngredient> outputs;

	@Shadow
	public abstract Optional<ResourceLocation> getDisplayLocation();

	/**
	 * @author Technici4n
	 * @reason Make sure REI doesn't see the output if the ID has the hidden namespace.
	 */
	@Overwrite
	public List<EntryIngredient> getOutputEntries() {
		if (getDisplayLocation().isPresent() && getDisplayLocation().get().getNamespace().equals("kjsextras_output_hidden")) {
			return Collections.emptyList(); // Magic!
		} else {
			return outputs;
		}
	}

	/**
	 * This is used to replace the getOutputEntries() call in {@link DefaultCraftingCategoryMixin}.
	 */
	@Override
	public List<EntryIngredient> getActualOutputs() {
		return outputs;
	}
}
