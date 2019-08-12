package exchangeGuide.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import exchangeGuide.data.Booster;
import exchangeGuide.data.MaterialUsageLibrary;

import java.io.IOException;
import java.util.Map;

public class BoostersSerializer extends StdSerializer<Map<Booster, Integer>> {
    public BoostersSerializer() {
        this(null);
    }

    public BoostersSerializer(Class<Map<Booster, Integer>> t) {
        super(t);
    }

    @Override
    public void serialize(
            Map<Booster, Integer> boosterMap,
            JsonGenerator jgen,
            SerializerProvider provider
    ) throws IOException
    {
        jgen.writeStartObject();
        jgen.writeStartArray();
        boosterMap.forEach(
                (booster, integer) -> {
                    try {
                        jgen.writeStringField("boosterName", booster.toString());
                        jgen.writeNumberField("count", integer);
                        jgen.writeEndObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}
