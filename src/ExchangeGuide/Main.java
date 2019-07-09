package ExchangeGuide;

import ExchangeGuide.data.Shop;
import ExchangeGuide.data.ShopContent;
import ExchangeGuide.loader.RawDataLoader;
import ExchangeGuide.loader.ShopContentLoader;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        RawDataLoader rawDataLoader = new RawDataLoader();
        List<Shop> shops = rawDataLoader.buildShopList();
        List<ShopContent> shopContents = new ArrayList<>();
        for (final Shop shop : shops) {
            System.out.println("shop: ");
            System.out.println(shop.toString());
            ShopContentLoader shopContentLoader = new ShopContentLoader(shop);
            shopContents.add(shopContentLoader.buildShopContent());
        }
        System.out.println("Program Finished.");
    }
}
