package fr.istic.m2il.idm.videogenapp.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * @author ismael
 */

public class InstanceDeserializer /*extends JsonDeserializer<MediaWrapper> */{
   /* @Override
    public MediaWrapper deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = (ObjectNode) mapper.readTree(jp);
        Class<? extends MediaWrapper> instanceClass = null;
        if(instanceClass instanceof MandatoryMediaWrapper) {
            instanceClass = MandatoryMediaWrapper.class;
        } else {
            instanceClass = OptionalMediaWrapper.class;
        }
        if (instanceClass == null){
            return null;
        }
        return mapper.readValue(root, instanceClass );
    }*/
}
