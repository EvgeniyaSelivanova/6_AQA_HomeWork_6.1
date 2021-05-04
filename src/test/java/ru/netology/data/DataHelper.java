package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardsData {
        private String CardInfo;
    }

    public static CardsData getIdFirsCard() {
        return new CardsData("92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardsData getIdSecondCard() {
        return new CardsData("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static CardsData getNumberFirstCard() {
        return new CardsData("5559000000000001");
    }

    public static CardsData getNumberSecondCard() {
        return new CardsData("5559000000000002");
    }
}
