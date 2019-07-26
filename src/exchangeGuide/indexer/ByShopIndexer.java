package exchangeGuide.indexer;

import exchangeGuide.data.Recipe;
import exchangeGuide.data.Shop;
import exchangeGuide.data.ShopContent;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ByShopIndexer extends Indexer<Shop, TreeSet<Recipe>> {

    public ByShopIndexer(List<ShopContent> shopContents) {
        super(shopContents, OUTPUT_DATA_PATH + "byShop.json");
        // build data map as a Shop -> TreeSet<Recipe> map.
        dataMap = shopContents.stream().collect(Collectors.toMap(
                ShopContent::getShop,
                ShopContent::getRecipes,
                (v1, v2) -> {throw new RuntimeException("Key Duplication happened when indexing shopContent by Shop");},
                TreeMap::new
        ));
    }
}
