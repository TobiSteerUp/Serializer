package dev.steerup.serializer.impl;

import dev.steerup.serializer.Serialize;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ObjectSerializer implements Serialize<Object, String> {

    private static final ByteArraySerializer BYTE_ARRAY_SERIALIZER = new ByteArraySerializer();

    @Override
    public Object deserialize(String element) {
        return this.getObjectFromString(element);
    }

    public <T> T deserialize(Class<T> clazz, String element) {
        return (T) this.deserialize(element);
    }

    @Override
    public String serialize(Object element) {
        return this.getStringFromObject(element);
    }

    private String getStringFromObject(Object object) {
        byte[] byteArray = BYTE_ARRAY_SERIALIZER.serialize(object);
        return this.byteArrayToString(byteArray);
    }

    private Object getObjectFromString(String string) {
        byte[] byteArray = this.stringToByteArray(string);
        return BYTE_ARRAY_SERIALIZER.deserialize(byteArray);
    }

    private String byteArrayToString(byte[] byteArray) {
        return Arrays.toString(byteArray);
    }

    private byte[] stringToByteArray(String string) {
        string = string.replace("]", "").replace("[", "");
        String[] stringArray = string.split(",");
        byte[] byteArray = new byte[stringArray.length];

        IntStream.range(0, byteArray.length).forEach(i -> byteArray[i] = Byte.parseByte(stringArray[i].trim()));
        return byteArray;
    }
}