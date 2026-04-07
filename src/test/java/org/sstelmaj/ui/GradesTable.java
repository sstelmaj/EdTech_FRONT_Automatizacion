package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class GradesTable {

    public static final Target TABLE = Target.the("tabla de calificaciones")
            .located(By.cssSelector("table"));

    public static Target gradeCell(String studentName, String activityName) {
        return Target.the("celda nota de " + studentName + " en " + activityName)
                .locatedBy("//table//tbody//tr[td[contains(.,'" + studentName + "')]]" +
                        "/td[count(//table//thead//th[contains(.,'" + activityName + "')]/preceding-sibling::th)+1]");
    }

    public static Target gradeCellDiv(String studentName, String activityName) {
        return Target.the("div celda nota de " + studentName + " en " + activityName)
                .locatedBy("//table//tbody//tr[td[contains(.,'" + studentName + "')]]" +
                        "/td[count(//table//thead//th[contains(.,'" + activityName + "')]/preceding-sibling::th)+1]" +
                        "//*[self::div or self::em or self::span][1]");
    }

    public static Target gradeCellInput(String studentName, String activityName) {
        return Target.the("input nota de " + studentName + " en " + activityName)
                .locatedBy("//table//tbody//tr[td[contains(.,'" + studentName + "')]]" +
                        "/td[count(//table//thead//th[contains(.,'" + activityName + "')]/preceding-sibling::th)+1]" +
                        "//input[@type='number']");
    }

    public static Target weightedAverage(String studentName) {
        return Target.the("promedio ponderado de " + studentName)
                .locatedBy("//table//tbody//tr[td[contains(.,'" + studentName + "')]]" +
                        "/td[count(//table//thead//th[contains(.,'Promedio Ponderado')]/preceding-sibling::th)+1]");
    }

    public static Target simpleAverage(String studentName) {
        return Target.the("promedio simple de " + studentName)
                .locatedBy("//table//tbody//tr[td[contains(.,'" + studentName + "')]]" +
                        "/td[count(//table//thead//th[contains(.,'Promedio Simple')]/preceding-sibling::th)+1]");
    }

    public static Target exportButton(String studentName) {
        return Target.the("boton exportar de " + studentName)
                .locatedBy("//table//tbody//tr[td[contains(.,'" + studentName + "')]]//button[contains(.,'Exportar')]");
    }
}
