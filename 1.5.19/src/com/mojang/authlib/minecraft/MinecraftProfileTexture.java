package com.mojang.authlib.minecraft;

import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinecraftProfileTexture {

    private final String url;
    private final Map<String, String> metadata;
    private static final Logger logger = LogManager.getLogger();

    public static enum Type {

        SKIN, CAPE;

        private Type() {
        }
    }

    public MinecraftProfileTexture(String url, Map<String, String> metadata) {
        this.url = url;
        this.metadata = metadata;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMetadata(String key) {
        if (this.metadata == null) {
            return null;
        }
        return (String) this.metadata.get(key);
    }

    public String getHash() {
        return FilenameUtils.getBaseName(this.url);
    }

    public String toString() {
        logger.info("url: " + this.url + " ====> " + "hash: " + getHash());
        return new ToStringBuilder(this).append("url", this.url).append("hash", getHash()).toString();
    }
}
