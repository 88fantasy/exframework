package org.exframework.gateway.sso.entity;

import java.security.KeyPair;
import java.util.Base64;

/**
 * 密钥
 *
 * @author rwe
 * @date 2022/4/22 17:45
 **/
public class KeyPairString {

    private String publicKey;

    private String privateKey;


    public KeyPairString() {
    }

    public KeyPairString(KeyPair keyPair) {
        this.publicKey = Base64.getEncoder().encodeToString((keyPair.getPublic().getEncoded()));
        this.privateKey = Base64.getEncoder().encodeToString((keyPair.getPrivate().getEncoded()));
    }

    public KeyPairString setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public KeyPairString setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
