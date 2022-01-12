package dev.steerup.serializer.impl;

import dev.steerup.serializer.Serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteArraySerializer implements Serialize<Object, byte[]> {

    @Override
    public Object deserialize(byte[] element) {
        return this.byteArrayToObject(element);
    }

    @Override
    public byte[] serialize(Object element) {
        return this.objToByteArray(element);
    }

    public <T> T deserialize(Class<T> clazz, byte[] element) {
        return (T) this.deserialize(element);
    }

    private byte[] objToByteArray(Object object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception exception) {
            return null;
        }
    }

    private Object byteArrayToObject(byte[] byteArray) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (Exception exception) {
            return null;
        }
    }
}