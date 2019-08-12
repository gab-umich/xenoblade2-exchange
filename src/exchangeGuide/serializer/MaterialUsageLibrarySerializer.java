package exchangeGuide.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import exchangeGuide.data.MaterialUsageLibrary;

import java.io.IOException;

public class MaterialUsageLibrarySerializer extends StdSerializer<MaterialUsageLibrary> {
    public MaterialUsageLibrarySerializer() {
        this(null);
    }

    public MaterialUsageLibrarySerializer(Class<MaterialUsageLibrary> t) {
        super(t);
    }

    @Override
    public void serialize(
            MaterialUsageLibrary library,
            JsonGenerator jgen,
            SerializerProvider provider
    ) throws IOException
    {
        jgen.writeStartObject();
        jgen.writeStringField("material", library.getMaterial().toString());
        jgen.writeObjectField("recipes", library.getRecipes());
        jgen.writeEndObject();
    }
}
