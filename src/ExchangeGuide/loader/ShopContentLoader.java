package ExchangeGuide.loader;

import ExchangeGuide.data.Booster;
import ExchangeGuide.data.Material;
import ExchangeGuide.data.Recipe;
import ExchangeGuide.data.Reward;
import ExchangeGuide.data.Shop;
import ExchangeGuide.data.ShopContent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopContentLoader {
    private static final String NOT_ARRAY_NODE = "Warning: Shop: %s is not formatted as an ArrayNode";
    private static final String NO_GOLD_IN_REWARD = "Warning: Greedy Nopon gives no gold in Shop: %s";
    private JsonNode jsonNode;
    private final Shop shop;

    /**
     * Constructor.
     */
    public ShopContentLoader(Shop shop) {
        String fileName = shop.filePath;
        this.shop = shop;
        try {
            jsonNode = new ObjectMapper().reader()
                    .readTree(this.getClass().getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot read raw data: " + fileName);
            System.exit(-1);
        }
    }

    public ShopContent buildShopContent() {
        ShopContent shopContent = new ShopContent(shop);
        if (!jsonNode.isArray()) {
            System.out.println(String.format(NOT_ARRAY_NODE, shop.filePath));
            return shopContent;
        }
        for (final JsonNode node : jsonNode) {
            String setName = node.get("Set Name").textValue();
            String materialsString = node.get("Materials").textValue();
            String rewardsString = node.get("Rewards").textValue();
            shopContent.addRecipe(
                    new Recipe (
                            setName,
                            getMaterialsFromString(materialsString),
                            getRewardsFromString(rewardsString),
                            shop
                    )
            );
        }
        return shopContent;
    }

    /* private helpers */

    /**
     * Matched with String.split method.
     * Tokenizer is deprecated as of Java 8
     *
     * @param string  e.g. "2 Butterfly Lens\n2 Photonic Coil\n6 Moving Board"
     * @return  a Map of Material and respective required amount
     */
    private Map<Material, Integer> getMaterialsFromString(String string) {
        String[] materials = string.split("\n");
        Map<Material, Integer> result = new HashMap<>();
        for (final String material : materials) {
            String[] materialTokens = material.split(" ", 2);
            result.put(
                    new Material(materialTokens[1]),
                    Integer.valueOf(materialTokens[0])
            );
        }
        return result;
    }

    /**
     * Matched with Regex.
     *
     * @param string  e.g. "30,400 G\n1 Bravery Booster\n1 Justice Booster"
     * @return  a Reward instance from the parsed source
     */
    private Reward getRewardsFromString(String string) {
        final String goldRegex = "([,\\d]*)\\hG";
        final String boosterRegex = "([,\\d]+)\\h(\\w*)\\hBooster";
        Pattern goldPattern = Pattern.compile(goldRegex);
        Pattern boosterPattern = Pattern.compile(boosterRegex);
        Matcher goldMatcher = goldPattern.matcher(string);
        Matcher boosterMatcher = boosterPattern.matcher(string);
        if (!goldMatcher.find()) {
            System.out.println(String.format(NO_GOLD_IN_REWARD, shop.filePath));
            System.exit(-1);
        }
        Map<Booster, Integer> boosterMap = new HashMap<>();
        Integer goldValue = 0;
        try {
            goldValue = NumberFormat.getNumberInstance(Locale.US).parse(goldMatcher.group(1)).intValue();
            while (boosterMatcher.find()) {
                boosterMap.put(
                        Booster.valueOf(boosterMatcher.group(2).toUpperCase(Locale.US)),
                        NumberFormat.getNumberInstance(Locale.US).parse(boosterMatcher.group(1)).intValue()
                );
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return new Reward (
                goldValue,
                boosterMap
        );
    }
}
