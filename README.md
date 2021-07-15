# KJS Extras
Extra features for use in KubeJS scripts. Made for the AOF4 pack, feel free to use for other packs!

## Features
### REI entry hiding
In a client script, use the `kjsextras_rei` event to remove some items from REI's entry list.
```js
// Example that removes diamond and diamond blocks.
const DELETED_ITEMS = ["minecraft:diamond", "minecraft:diamond_block"];

events.listen("kjsextras_rei", event => {
    DELETED_ITEMS.forEach(id => event.remove(id));
});
```

### REI output hiding
Crafting recipes with namespace `kjsextras_output_hidden` have their output "hidden" from REI.
That means you can only see the recipe if you press U on an input, but not if you press R on an output.
Useful for unification recipes.
```js
// Example output hidden recipe to convert diamond ores to diamonds.
events.listen("recipes", event => {
    // Shapeless recipe syntax is output first, then input.
    // We need to specify a custom id for the namespace to be `kjsextras_output_hidden`.
    event.recipes.minecraft.crafting_shapeless("diamond", "diamond_ore").id("kjsextras_output_hidden:ore_to_diamond");
});
```

### KubeJS Tag event: list all ids
Use `kjsextras_getAllIds()` on a tag wrapper to retrieve all the current ids inside the tag.
For example, to print the list of flowers:
```js
onEvent('items.tags', event => {
    const allFlowers = event.get("minecraft:flowers").kjsextras_getAllIds();
    console.log(`All flowers: ${allFlowers}`);
})
```