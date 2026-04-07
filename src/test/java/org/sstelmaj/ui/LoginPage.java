package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final String URL = "/login";

    public static final Target USERNAME_FIELD = Target.the("campo de usuario")
            .located(By.id("username"));

    public static final Target PASSWORD_FIELD = Target.the("campo de contrasena")
            .located(By.id("password"));

    public static final Target SUBMIT_BUTTON = Target.the("boton iniciar sesion")
            .located(By.cssSelector("button[type='submit']"));

    public static final Target REGISTER_LINK = Target.the("enlace a registro")
            .locatedBy("//a[contains(text(),'Registrate aqui')]");

    public static final Target ERROR_ALERT = Target.the("alerta de error")
            .located(By.cssSelector("[role='alert']"));
}
