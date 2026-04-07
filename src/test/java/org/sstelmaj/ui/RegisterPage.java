package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegisterPage {

    public static final String URL = "/register";

    public static final Target USERNAME_FIELD = Target.the("campo de usuario registro")
            .located(By.id("username"));

    public static final Target PASSWORD_FIELD = Target.the("campo de contrasena registro")
            .located(By.id("password"));

    public static final Target CONFIRM_PASSWORD_FIELD = Target.the("campo confirmar contrasena")
            .located(By.id("confirmPassword"));

    public static final Target SUBMIT_BUTTON = Target.the("boton registrarme")
            .located(By.cssSelector("button[type='submit']"));

    public static final Target LOGIN_LINK = Target.the("enlace a login")
            .locatedBy("//a[contains(text(),'Inicia sesion')]");

    public static final Target ERROR_ALERT = Target.the("alerta de error registro")
            .located(By.cssSelector("[role='alert']"));

    public static final Target SUCCESS_ALERT = Target.the("alerta de exito registro")
            .located(By.cssSelector(".border-green-200"));
}
