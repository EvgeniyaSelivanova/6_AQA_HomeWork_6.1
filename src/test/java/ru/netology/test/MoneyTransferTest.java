package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferStandartSum() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        String idFirstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String idSecondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        Integer balanceStartFirstCard = new DashboardPage().getCardBalance(idFirstCard);
        Integer balanceStartSecondCard = new DashboardPage().getCardBalance(idSecondCard);
        val replenishmentPage = new DashboardPage().clickReplenishment(idFirstCard);
        replenishmentPage.replenishment("200", "5559000000000002");
        Integer balanceFirstCard = new DashboardPage().getCardBalance(idFirstCard);
        Integer balanceSecondCard = new DashboardPage().getCardBalance(idSecondCard);
        assertEquals(balanceFirstCard, balanceStartFirstCard + 200);
        assertEquals(balanceSecondCard, balanceStartSecondCard - 200);
    }

    @Test
    void shouldTransferAllSumInCard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        String idFirstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String idSecondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        Integer balanceStartFirstCard = new DashboardPage().getCardBalance(idFirstCard);
        Integer balanceStartSecondCard = new DashboardPage().getCardBalance(idSecondCard);
        val replenishmentPage = new DashboardPage().clickReplenishment(idFirstCard);
        String quantity = Integer.toString(balanceStartFirstCard);
        replenishmentPage.replenishment(quantity, "5559000000000002");
        Integer balanceFirstCard = new DashboardPage().getCardBalance(idFirstCard);
        Integer balanceSecondCard = new DashboardPage().getCardBalance(idSecondCard);
        assertEquals(balanceFirstCard, balanceStartFirstCard + balanceStartFirstCard);
        assertEquals(balanceSecondCard, balanceStartSecondCard - balanceStartFirstCard);
    }

    @Test
    void shouldInformErrorSumMoreThenInCard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        verificationPage.validVerify(verificationCode);
        String idFirstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String idSecondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        Integer balanceStartFirstCard = new DashboardPage().getCardBalance(idFirstCard);
        Integer balanceStartSecondCard = new DashboardPage().getCardBalance(idSecondCard);
        val replenishmentPage = new DashboardPage().clickReplenishment(idFirstCard);
        String quantity = Integer.toString(balanceStartFirstCard + 100);
        replenishmentPage.replenishment(quantity, "5559000000000002");
        Integer balanceFirstCard = new DashboardPage().getCardBalance(idFirstCard);
        Integer balanceSecondCard = new DashboardPage().getCardBalance(idSecondCard);
        $("[data-test-id='error-notification'] .notification__content").shouldBe(visible);
    }

}

