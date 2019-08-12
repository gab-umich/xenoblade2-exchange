package exchangeGuide.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import exchangeGuide.data.Booster;
import exchangeGuide.data.Reward;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.exit;

public class RewardSerializer extends StdSerializer<Reward> {
    public RewardSerializer() {
        this(null);
    }

    public RewardSerializer(Class<Reward> t) {
        super(t);
    }

    @Override
    public void serialize(
            Reward reward,
            JsonGenerator jgen,
            SerializerProvider provider
    ) throws IOException
    {
        jgen.writeStartObject();
        jgen.writeNumberField("gold", reward.getGold());
        jgen.writeArrayFieldStart("boosters");
        reward.getBoosters().forEach(
                (booster, integer) -> {
                    try {
                        jgen.writeStartObject();
                        jgen.writeStringField("boosterName", booster.name());
                        jgen.writeNumberField("count", integer);
                        jgen.writeEndObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                        exit(-1);
                    }
                }
        );
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}
