package exchangeGuide.data;

public class Shop implements Comparable<Shop> {
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

    /**
     * Compare, lexicographically, by nation then area then shopName.
     * @param o  the other Shop to compare to
     * @return  an integer > 0 if this is after the other in dictionary, vice versa;
     * and guarantees return 0 iff Shop values are identical.
     */
    @Override
    public int compareTo(Shop o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        return nation + " > " + area + " > " + shopName;
    }
}
