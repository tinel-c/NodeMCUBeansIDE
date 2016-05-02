/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bogza.nodemcuide;

import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.text.StyleContext;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author Constantin Bogza
 * @since 02.05.2016
 * @version 1.0.0
 * 
 * Processes the text received from the NodeMCU RS232 interface and gives the
 * correct html formating for the colors and better view.
 */
public class NodeMcuTopComponentTextProcessing {
    private JTextPane jTextPaneHandle;
    private HTMLEditorKit htmlEditorKit = new HTMLEditorKit();;
    private StyleSheet htmlStyleSheet;
    private StringBuilder textPaneText = new StringBuilder();
    public NodeMcuTopComponentTextProcessing(JTextPane jTextPaneHandleParsed)
    {
        jTextPaneHandle = jTextPaneHandleParsed;
        jTextPaneHandle.setContentType("text/html");
        htmlStyleSheet = htmlEditorKit.getStyleSheet();
        
        htmlStyleSheet.addRule("#tinel {color: red;}");
        htmlStyleSheet.addRule("#command {color: red;}");
        htmlStyleSheet.addRule("#nodeSintax {color: blue;}");
        htmlStyleSheet.addRule("#debug {color: green;}");
        jTextPaneHandle.setEditorKit(htmlEditorKit);
        textPaneText.append("<html><b id='tinel'>NodeMcu IDE in Netbeans </b><br><b>© Constantin Bogza 2016</b><br><b> www.bogza.ro</b><br>");
    }
    public void SendText(String textToBePrinted)
    {
        textPaneText.append(ColorString(textToBePrinted)+"<br>");
        jTextPaneHandle.setText(textPaneText.toString()+"</html>");
        jTextPaneHandle.select(jTextPaneHandle.getDocument().getLength(), jTextPaneHandle.getDocument().getLength());
    }
    public void SendDebugText(String textToBePrinted)
    {
        textPaneText.append("<span id='debug'> Debug: "+ColorString(textToBePrinted)+"<span><br>");
        jTextPaneHandle.setText(textPaneText.toString()+"</html>");
        jTextPaneHandle.select(jTextPaneHandle.getDocument().getLength(), jTextPaneHandle.getDocument().getLength());
    }
    public String ColorString(String textToBeColored)
    {
        String tempString;
        tempString = Pattern.compile(">").matcher(textToBeColored).replaceAll("<span id='command'> > </span>");
        tempString = Pattern.compile("node").matcher(tempString).replaceAll("<span id='nodeSintax'>node</span>");
        tempString = Pattern.compile("restart").matcher(tempString).replaceAll("<span id='nodeSintax'>restart</span>");
        return tempString;
    }
}
