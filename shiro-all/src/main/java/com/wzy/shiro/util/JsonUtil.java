package com.wzy.shiro.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    // ObjectMapper is thread-safe without re-config, according to
    // http://wiki.fasterxml.com/JacksonFAQThreadSafety
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T toObject(InputStream stream, Class<T> clazz) {
        try {
            return mapper.readValue(stream, clazz);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
        return null;
    }

    public static <T> T toObject(String string, Class<T> clazz) {
        try {
            return mapper.readValue(string, clazz);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
        return null;
    }

    public static <T> String toJson(T t) {
        try {
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            logger.error("Exception", e);
        }
        return null;
    }

    public static <T> String writeObjectToGson(T t) {
        Gson gson = new Gson();

        return gson.toJson(t);
    }

    public static <T> Map<String, T> toMap(String jsonAsString) throws JsonGenerationException {
        try {
            return mapper.readValue(jsonAsString, new TypeReference<Map<String, T>>() {
            });
        } catch (Exception e) {
            logger.error("parse jsonString error.[" + jsonAsString + "]");
            throw new JsonGenerationException(e);
        }
    }

    public static <T> Map<String, T>[] toMapArray(String jsonAsString) throws JsonGenerationException {
        try {
            return mapper.readValue(jsonAsString, new TypeReference<Map<String, T>[]>() {
            });
        } catch (Exception e) {
            logger.error("parse jsonString error.[" + jsonAsString + "]");
            throw new JsonGenerationException(e);
        }
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T toList(String string, Class clazzList, Class clazzBean) {
        try {
            JavaType javaType = getCollectionType(clazzList, clazzBean);
            return mapper.readValue(string, javaType);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
        return null;
    }

    public static boolean isGoodJson(String json) {
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            logger.error("bad json: " + json);
            return false;
        }
    }
}
