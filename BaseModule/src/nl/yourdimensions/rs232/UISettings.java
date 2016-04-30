/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.yourdimensions.rs232;

import org.openide.util.NbPreferences;

/**
 *
 * @author hendriksit
 */
public class UISettings {

    private UISettings() {
    }
    private static UISettings instance = null;

    public static UISettings getInstance() {
        if (instance == null) {
            instance = new UISettings();
        }
        return instance;
    }

    public boolean isEchoOn() {
        return NbPreferences.forModule(RS232OptionsPanel.class).getBoolean("isEchoOn", true);
    }

    public void setEchoOn(boolean value) {
        NbPreferences.forModule(RS232OptionsPanel.class).putBoolean("isEchoOn", value);
    }

    public String getBautDefault() {
        return NbPreferences.forModule(RS232OptionsPanel.class).get("bautDefault", "9600");
    }

    public void setBautDefault(String baut) {
        NbPreferences.forModule(RS232OptionsPanel.class).put("bautDefault", baut);
    }
    
    public boolean isLogging() {
        return NbPreferences.forModule(RS232OptionsPanel.class).getBoolean("isLogging", false);
    }

    public void setLogging(boolean logging) {
        NbPreferences.forModule(RS232OptionsPanel.class).putBoolean("isLogging", logging);
    }

    public String getLogPath() {
        return NbPreferences.forModule(RS232OptionsPanel.class).get("logPath", System.getProperty("user.home")+"\\log.txt");
    }

    public void setLogPath(String path) {
        NbPreferences.forModule(RS232OptionsPanel.class).put("logPath", path);
    }
   

    
}
