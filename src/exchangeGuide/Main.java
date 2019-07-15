package exchangeGuide;

import exchangeGuide.data.Shop;
import exchangeGuide.data.ShopContent;
import exchangeGuide.loader.RawDataLoader;
import exchangeGuide.loader.ShopContentLoader;
import exchangeGuide.indexer.ByShopIndexer;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        /* demonstration of java 8 Stream, and, more importantly, the interface we should implement to take advantage of
           the Stream library
         */
        RawDataLoader rawDataLoader = new RawDataLoader();
        List<Shop> shops = rawDataLoader.buildShopList();
        List<ShopContent> shopContents = shops.stream()
                .peek(shop -> System.out.println("New Shop:\n" + shop.toString()))      // debug logging to stdout
                .map(ShopContentLoader::new)                                            // build contentLoader
                .map(ShopContentLoader::buildShopContent)                               // get recipes
                .collect(Collectors.toList());                                          // collect to list
        ByShopIndexer byShopIndexer = new ByShopIndexer(shopContents);
        byShopIndexer.serialize();
        System.out.println("Program Finished.");
    }
}
