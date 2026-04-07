package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ExportModal {

    public static final Target TITLE = Target.the("titulo modal exportar boletin")
            .locatedBy("//div[contains(@role,'dialog')]//h2[contains(text(),'Exportar Boletin')]");

    public static final Target STUDENT_NAME = Target.the("nombre estudiante en modal exportar")
            .located(By.cssSelector("div[role='dialog'] .font-medium"));

    public static final Target EMPTY_GRADES_WARNING = Target.the("advertencia notas vacias")
            .located(By.cssSelector("div[role='dialog'] [role='alert']"));

    public static Target formatOption(String format) {
        return Target.the("opcion formato " + format)
                .locatedBy("//div[contains(@role,'dialog')]//button[contains(text(),'" + format + "')] | " +
                        "//div[contains(@role,'dialog')]//label[contains(text(),'" + format + "')] | " +
                        "//div[contains(@role,'dialog')]//*[contains(text(),'" + format + "')]");
    }

    public static Target exportButton(String format) {
        return Target.the("boton exportar " + format)
                .locatedBy("//div[contains(@role,'dialog')]//button[contains(text(),'Exportar " + format + "')]");
    }

    public static final Target SUCCESS_ALERT = Target.the("alerta exportacion exitosa")
            .located(By.cssSelector(".border-green-200"));
}
