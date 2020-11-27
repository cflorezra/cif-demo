package com.adobe.commerce.cif.core.models;

import com.adobe.cq.commerce.core.components.models.product.Asset;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAssetImpl implements Asset {
    private static final Logger LOG = LoggerFactory.getLogger(MyAssetImpl.class);
    private String label;
    private Integer position;
    private String type;
    private String path;

    public MyAssetImpl() {
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String asJson() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException var3) {
            LOG.error("Cannot serialize asset at {}", this.path);
            return "";
        }
    }
}
