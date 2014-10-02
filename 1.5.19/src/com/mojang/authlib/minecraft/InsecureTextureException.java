package com.mojang.authlib.minecraft;

import com.mojang.authlib.GameProfile;
import java.util.*;

public class InsecureTextureException extends RuntimeException {

    public static class MissingTextureException extends InsecureTextureException {

        public MissingTextureException() {
            super("No texture information found");
        }
    }

    public static class WrongTextureOwnerException extends InsecureTextureException {

        private final GameProfile expected;
        private final UUID resultId;
        private final String resultName;

        public WrongTextureOwnerException(GameProfile expected, UUID resultId, String resultName) {
            super((new StringBuilder()).append("Decrypted textures payload was for another user (expected ").append(expected.getId()).append("/").append(expected.getName()).append(" but was for ").append(resultId).append("/").append(resultName).append(")").toString());
            this.expected = expected;
            this.resultId = resultId;
            this.resultName = resultName;
        }
    }

    public static class OutdatedTextureException extends InsecureTextureException {

        private final Date validFrom;
        private final Calendar limit;

        public OutdatedTextureException(Date validFrom, Calendar limit) {
            super((new StringBuilder()).append("Decrypted textures payload is too old (").append(validFrom).append(", but we need it to be at least ").append(limit).append(")").toString());
            this.validFrom = validFrom;
            this.limit = limit;
        }
    }

    public InsecureTextureException(String message) {
        super(message);
    }
}
