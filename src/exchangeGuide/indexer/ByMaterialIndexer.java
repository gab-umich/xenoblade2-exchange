package exchangeGuide.indexer;

import com.fasterxml.jackson.databind.module.SimpleModule;
import exchangeGuide.data.*;
import exchangeGuide.serializer.MaterialUsageLibrarySerializer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ByMaterialIndexer extends Indexer<MaterialUsageLibrary> {

    // a demonstration of how to write borderline unreadable code.
    public ByMaterialIndexer(List<ShopContent> shopContents) {
        super(shopContents, OUTPUT_DATA_PATH + "byMaterial.json", new SimpleModule());
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
                                Collectors.mapping(Map.Entry::getValue, Collectors.toCollection(TreeSet::new))
                        )
                );
        data = tempMap.entrySet().stream().map(
                entry -> new MaterialUsageLibrary(entry.getKey(), entry.getValue())
        ).collect(Collectors.toCollection(TreeSet::new));

        module.addSerializer(MaterialUsageLibrary.class, new MaterialUsageLibrarySerializer());
    }
}
