package exchangeGuide.indexer;

import com.fasterxml.jackson.databind.module.SimpleModule;
import exchangeGuide.data.Material;
import exchangeGuide.data.MaterialUsageLibrary;
import exchangeGuide.data.Recipe;
import exchangeGuide.data.ShopContent;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ByMaterialIndexer extends Indexer<MaterialUsageLibrary> {

    // a demonstration of how to write borderline unreadable code.
    public ByMaterialIndexer(List<ShopContent> shopContents) {
        super(shopContents, OUTPUT_DATA_PATH + "byMaterial.json", new SimpleModule());
        Map<> meterialUsageMap = new HashMap<>();
        shopContents.forEach(
                shopContent -> {
                    TreeSet<Recipe> recipes = shopContent.getRecipes();
                    recipes.forEach(
                            recipe -> {
                                Map<Material, Integer> materials = recipe.getRequiredMaterials();
                                materials.keySet().forEach(
                                        material -> {
                                            if () {
                                                data.
                                            }
                                            else {
                                                data.put(material, new TreeSet<>(Collections.singletonList(recipe)));
                                            }
                                        }
                                );
                            }
                    );
                }
        );
    }
}
