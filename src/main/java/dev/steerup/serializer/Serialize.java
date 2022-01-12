package dev.steerup.serializer;

public interface Serialize<Deserialized, Serialized> {

    Deserialized deserialize(Serialized element);
    Serialized serialize(Deserialized element);
}