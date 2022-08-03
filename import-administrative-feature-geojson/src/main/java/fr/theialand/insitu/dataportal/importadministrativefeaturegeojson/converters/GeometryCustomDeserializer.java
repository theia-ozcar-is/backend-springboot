//package fr.theialand.insitu.dataportal.importadministrativefeaturegeojson.converters;
//
//import com.google.gson.*;
//import com.mongodb.client.model.geojson.Geometry;
//import fr.theialand.insitu.dataportal.repository.mongo.model.POJO.geometry.GeometryGeoJSON;
//
//import java.lang.reflect.Type;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public enum GeometryCustomDeserializer  implements JsonDeserializer<GeometryGeoJSON> {
//    INSTANCE;
//
//    private Gson gson = new Gson();;
//    private static final Map<String, Class<? extends GeometryGeoJSON>> geometryTypeRegistry =  new HashMap<>();
//
//    static {
//        Arrays.stream(EnumGeometryGeoJsonType.values()).collect(Collectors.toList()).forEach(geoJsonGeometryType -> {
//            try {
//                geometryTypeRegistry.put(geoJsonGeometryType.toString(),
//                        (Class<? extends GeometryGeoJSON>) Class.forName("fr.theialand.insitu.dataportal.repository.mongo.model.POJO.geometry."+geoJsonGeometryType.toString()));
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//    @Override
//    public GeometryGeoJSON deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//        String geometryType = jsonElement.getAsJsonObject().get("type").getAsString();
//        Class<? extends GeometryGeoJSON> gType = geometryTypeRegistry.get(geometryType);
//        return gson.fromJson(jsonElement.getAsJsonObject(),gType);
//    }
//}
//
////public enum GeometryCustomDeserializer implements JsonDeserializer<Geometry> {
////    INSTANCE;
////
////    private Gson gson = new Gson();;
////    private static final Map<String, Class<? extends Geometry>> geometryTypeRegistry =  new HashMap<>();
////
////    static {
////        Arrays.stream(GeoJsonObjectType.values()).collect(Collectors.toList()).forEach(geoJsonGeometryType -> {
////            try {
////                geometryTypeRegistry.put(geoJsonGeometryType.getTypeName(),
////                        (Class<? extends Geometry>) Class.forName("com.mongodb.client.model.geojson."+geoJsonGeometryType.getTypeName()));
////            } catch (ClassNotFoundException e) {
////                e.printStackTrace();
////            }
////        });
////    }
////    @Override
////    public Geometry deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
////        String geometryType = jsonElement.getAsJsonObject().get("type").getAsString();
////        Class<? extends Geometry> gType = geometryTypeRegistry.get(geometryType);
////        CodecRegistry cr = CodecRegistries.fromProviders(new GeoJsonCodecProvider());
////        return cr.get(gType).decode(new JsonReader(jsonElement.toString()), DecoderContext.builder().build());
////    }
////}
