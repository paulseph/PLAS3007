package edu.plas.testautoandci.driver;

import org.sikuli.basics.Settings;
import org.sikuli.script.Screen;

public class SikuliDriver {
    private static Screen sikuliDriver = null;
    public static final String SIKULI_IMAGES_PATH = "src/test/resources/sikuliimages/";

    private SikuliDriver() {}

    public static Screen getSikuliDriver() {
        if (sikuliDriver == null) {
            startSikuliEngine();
        }
        return sikuliDriver;
    }

    private static void startSikuliEngine() {
        Settings.ActionLogs = false;
        Settings.InfoLogs = false;
        Settings.DebugLogs = false;
        Settings.OcrTextSearch = true;
        Settings.OcrTextRead = true;
        Settings.MinSimilarity = 0.7;
        sikuliDriver = new Screen();
    }
}