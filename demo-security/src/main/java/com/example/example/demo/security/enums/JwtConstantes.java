package com.example.example.demo.security.enums;

public enum JwtConstantes {

    SECRET("mySuperSecretKeyThatIsLongEnough1234567890"),
    VALIDITY(3600000L); // 1 hora en milisegundos

    private final Object value;

    JwtConstantes(Object value) {
        this.value = value;
    }

   

    public Object getValue() {
        return value;
    }

    public String getSecret() {
        if (this == SECRET) {
            return (String) value;
        }
        throw new IllegalStateException("Not a secret");
    }

    public long getValidity() {
        if (this == VALIDITY) {
            return (long) value;
        }
        throw new IllegalStateException("Not a validity");
    }
}
