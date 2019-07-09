package ExchangeGuide.data;

public class Shop {
    public final String shopName;
    public final String area;
    public final String nation;
    public final String filePath;

    public Shop(String shopName, String area, String nation, String filePath) {
        this.shopName = shopName;
        this.area = area;
        this.nation = nation;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return nation + " > " + area + " > " + shopName + "\n@" + filePath;
    }


}
