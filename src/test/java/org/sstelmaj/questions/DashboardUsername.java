package org.sstelmaj.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.sstelmaj.ui.DashboardPage;

public class DashboardUsername implements Question<Boolean> {

    private final String expectedUsername;

    public DashboardUsername(String expectedUsername) {
        this.expectedUsername = expectedUsername;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return DashboardPage.USERNAME_DISPLAY.of(expectedUsername).resolveFor(actor).isDisplayed();
    }

    public static DashboardUsername isDisplayedAs(String username) {
        return new DashboardUsername(username);
    }
}
