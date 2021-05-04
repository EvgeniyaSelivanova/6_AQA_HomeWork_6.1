package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferStandartSumFromSecondCard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val NumberSecondCard = DataHelper.getNumberSecondCard();
        val idFirstCard = DataHelper.getIdFirsCard();
        val idSecondCard = DataHelper.getIdSecondCard();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceStartFirstCard = dashboardPage.getCardBalance(idFirstCard.getCardInfo());
        val balanceStartSecondCard = dashboardPage.getCardBalance(idSecondCard.getCardInfo());
        val replenishmentPage = dashboardPage.clickReplenishment(idFirstCard.getCardInfo());
        replenishmentPage.replenishment("200", NumberSecondCard.getCardInfo());
        val balanceFirstCard = dashboardPage.getCardBalance(idFirstCard.getCardInfo());
        val balanceSecondCard = dashboardPage.getCardBalance(idSecondCard.getCardInfo());
        assertEquals(balanceFirstCard, balanceStartFirstCard + 200);
        assertEquals(balanceSecondCard, balanceStartSecondCard - 200);
    }

    @Test
    void shouldTransferStandartSumFromFirstCard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val NumberFirstCard = DataHelper.getNumberFirstCard();
        val idFirstCard = DataHelper.getIdFirsCard();
        val idSecondCard = DataHelper.getIdSecondCard();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceStartFirstCard = dashboardPage.getCardBalance(idFirstCard.getCardInfo());
        val balanceStartSecondCard = dashboardPage.getCardBalance(idSecondCard.getCardInfo());
        val replenishmentPage = dashboardPage.clickReplenishment(idSecondCard.getCardInfo());
        replenishmentPage.replenishment("200", NumberFirstCard.getCardInfo());
        val balanceFirstCard = dashboardPage.getCardBalance(idFirstCard.getCardInfo());
        val balanceSecondCard = dashboardPage.getCardBalance(idSecondCard.getCardInfo());
        assertEquals(balanceFirstCard, balanceStartFirstCard - 200);
        assertEquals(balanceSecondCard, balanceStartSecondCard + 200);
    }

    @Test
    void shouldTransferAllSumInCard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val NumberSecondCard = DataHelper.getNumberSecondCard();
        val idFirstCard = DataHelper.getIdFirsCard();
        val idSecondCard = DataHelper.getIdSecondCard();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceStartFirstCard = dashboardPage.getCardBalance(idFirstCard.getCardInfo());
        val balanceStartSecondCard = dashboardPage.getCardBalance(idSecondCard.getCardInfo());
        val replenishmentPage = dashboardPage.clickReplenishment(idFirstCard.getCardInfo());
        val quantity = Integer.toString(balanceStartFirstCard);
        replenishmentPage.replenishment(quantity, NumberSecondCard.getCardInfo());
        val balanceFirstCard = dashboardPage.getCardBalance(idFirstCard.getCardInfo());
        val balanceSecondCard = dashboardPage.getCardBalance(idSecondCard.getCardInfo());
        assertEquals(balanceFirstCard, balanceStartFirstCard + balanceStartFirstCard);
        assertEquals(balanceSecondCard, balanceStartSecondCard - balanceStartFirstCard);
    }

    @Test
    void shouldInformErrorSumMoreThenInCard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val NumberSecondCard = DataHelper.getNumberSecondCard();
        val idFirstCard = DataHelper.getIdFirsCard();
        val idSecondCard = DataHelper.getIdSecondCard();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceStartFirstCard = dashboardPage.getCardBalance(idFirstCard.getCardInfo());
        val balanceStartSecondCard = dashboardPage.getCardBalance(idSecondCard.getCardInfo());
        val replenishmentPage = dashboardPage.clickReplenishment(idFirstCard.getCardInfo());
        String quantity = Integer.toString(balanceStartFirstCard + 100);
        replenishmentPage.replenishment(quantity, NumberSecondCard.getCardInfo());
        val balanceFirstCard = dashboardPage.getCardBalance(idFirstCard.getCardInfo());
        val balanceSecondCard = dashboardPage.getCardBalance(idSecondCard.getCardInfo());
        replenishmentPage.ErrorInform();
    }

}

