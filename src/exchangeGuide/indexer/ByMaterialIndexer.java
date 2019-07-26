package exchangeGuide.indexer;

import exchangeGuide.data.Material;
import exchangeGuide.data.Recipe;
import exchangeGuide.data.ShopContent;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ByMaterialIndexer extends Indexer<Material, TreeSet<Recipe>>{

    // a demonstration of how to write borderline unreadable code.
    public ByMaterialIndexer(List<ShopContent> shopContents) {
        super(shopContents, OUTPUT_DATA_PATH + "byMaterial.json");
        dataMap = new TreeMap<>();
        shopContents.forEach(
                shopContent -> {
                    TreeSet<Recipe> recipes = shopContent.getRecipes();
                    recipes.forEach(
                            recipe -> {
                                Map<Material, Integer> materials = recipe.getRequiredMaterials();
                                materials.keySet().forEach(
                                        material -> {
                                            if (dataMap.containsKey(material)) {
                                                dataMap.get(material).add(recipe);
                                            }
                                            else {
                                                dataMap.put(material, new TreeSet<>(Collections.singletonList(recipe)));
                                            }
                                        }
                                );
                            }
                    );
                }
        );
    }
}
