package dev.technici4n.kjsextras.mixin;

import com.google.common.collect.Iterators;
import dev.latvian.kubejs.world.gen.fabric.WorldgenRemoveEventJSFabric;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Iterator;

@Mixin(value = WorldgenRemoveEventJSFabric.class, remap = false)
public class WorldgenRemoveEventJSFabricMixin {
	@Shadow
	private Registry<ConfiguredFeature<?, ?>> featureRegistry;

	@ModifyVariable(
			method = "removeFeature",
			at = @At(
					value = "INVOKE_ASSIGN",
					target = "Lnet/minecraft/util/registry/Registry;iterator()Ljava/util/Iterator;",
					remap = true
			),
			index = 3
	)
	protected Iterator hookRemoveFeature(Iterator iterator) {
		return Iterators.transform(featureRegistry.getEntries().iterator(), entry -> entry.getValue());
	}
}
