package ExchangeGuide;

import ExchangeGuide.data.Shop;
import ExchangeGuide.loader.RawDataLoader;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        RawDataLoader rawDataLoader = new RawDataLoader();
        List<Shop> shops = rawDataLoader.getShopList();
        for (final Shop shop : shops) {
            System.out.println("shop: ");
            System.out.println(shop.toString());
        }
    }
}
