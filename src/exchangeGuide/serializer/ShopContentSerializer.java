package exchangeGuide.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import exchangeGuide.data.ShopContent;

import java.io.IOException;

public class ShopContentSerializer extends StdSerializer<ShopContent> {
    public ShopContentSerializer() {
        this(null);
    }

    public ShopContentSerializer(Class<ShopContent> t) {
        super(t);
    }

    @Override
    public void serialize(
            ShopContent shopContent,
            JsonGenerator jgen,
            SerializerProvider provider
    ) throws IOException
    {
        jgen.writeStartObject();
        jgen.writeStringField("shop", shopContent.getShop().toString());
        jgen.writeObjectField("recipes", shopContent.getRecipes());
        jgen.writeEndObject();
    }
}
