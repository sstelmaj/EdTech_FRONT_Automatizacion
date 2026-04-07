package org.sstelmaj.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.sstelmaj.ui.LoginPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LoginUser implements Task {

    private final String username;
    private final String password;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LoginPage.USERNAME_FIELD, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
                Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
                Click.on(LoginPage.SUBMIT_BUTTON)
        );
    }

    public static LoginUser withCredentials(String username, String password) {
        return Tasks.instrumented(LoginUser.class, username, password);
    }
}
