package ExchangeGuide.data;

import java.util.Map;

public class Reward {
    final Integer gold;
    final Map<Booster, Integer> boosters;

    public Reward(Integer gold, Map<Booster, Integer> boosters) {
        this.gold = gold;
        this.boosters = boosters;
    }

}
