package exchangeGuide.indexer;

import com.fasterxml.jackson.databind.module.SimpleModule;
import exchangeGuide.data.*;
import exchangeGuide.serializer.MaterialUsageLibrarySerializer;
import exchangeGuide.serializer.RecipeSerializer;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;

public class ByMaterialIndexer extends Indexer<MaterialUsageLibrary> {

    // a demonstration of how to write borderline unreadable code.
    public ByMaterialIndexer(List<ShopContent> shopContents) {
        super(shopContents, OUTPUT_DATA_PATH + "byMaterial.json", new SimpleModule());
        // Supplier for a descending ordered TreeSet<Reward>, with Gold value high to low
        Supplier<TreeSet<Recipe>> descendingRecipeSetSupplier = () -> new TreeSet<>(Comparator.reverseOrder());
        /*
         * for each shopContent -> recipe -> material,
         * put (recipe) into the Map<Material, TreeSet<Recipe>>'s value set
         */
        Map<Material, TreeSet<Recipe>> tempMap = shopContents.stream().map(ShopContent::getRecipes)
                .flatMap(Collection::stream)
                .flatMap(
                        recipe -> Stream.of(
                                new AbstractMap.SimpleImmutableEntry<>(
                                        recipe.getRequiredMaterials().keySet(),
                                        recipe
                                )
                        )
                )
                .flatMap(
                        entry -> entry.getKey().stream().map(
                                material -> new AbstractMap.SimpleImmutableEntry<>(material, entry.getValue())
                        )
                )
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.mapping(
                                        Map.Entry::getValue,
                                        Collectors.toCollection(descendingRecipeSetSupplier)
                                )
                        )
                );
        data = tempMap.entrySet().stream().map(
                entry -> new MaterialUsageLibrary(entry.getKey(), entry.getValue())
        ).collect(Collectors.toCollection(TreeSet::new));

        module.addSerializer(MaterialUsageLibrary.class, new MaterialUsageLibrarySerializer());
        module.addSerializer(Recipe.class, new RecipeSerializer(true));
    }
}
