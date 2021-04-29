package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Attribute;
import lombok.val;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = ", баланс: ";
    private final String balanceFinish = " р.";
    private int startBalance;

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(String id) {
        val text = cards.find(Condition.attribute("data-test-id", id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public ReplenishmentPage clickReplenishment(String id) {
        startBalance = getCardBalance(id);
        SelenideElement ReplenishmentButton = $("[data-test-id='" + id + "']").$("[data-test-id=action-deposit]");
        ReplenishmentButton.click();
        return new ReplenishmentPage();
    }


}
