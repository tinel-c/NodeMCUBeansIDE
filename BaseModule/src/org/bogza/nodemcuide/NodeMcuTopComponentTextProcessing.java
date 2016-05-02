/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bogza.nodemcuide;

import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.JTextField;
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
    private byte[] dataArray = new byte[2400];
    private int dataArrayOffset;
    private JTextField jTextTempHandle;
    public NodeMcuTopComponentTextProcessing(JTextPane jTextPaneHandleParsed,JTextField jTextTempParsed)
    {
        dataArrayOffset = 0;
        jTextPaneHandle = jTextPaneHandleParsed;
        jTextTempHandle = jTextTempParsed;
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
    public void SendBytes(byte[] dataReceived)
    {
        for(int i=0; i<dataReceived.length; i++)
        {
            dataArray[dataArrayOffset]=dataReceived[i];
            dataArrayOffset++;
            if((dataReceived[i] == 10) || (dataArrayOffset > 2000))
            {
                String RS232data= new String(dataArray,0,dataArrayOffset); 
                SendText(RS232data);
                dataArrayOffset = 0;
                if(dataArrayOffset > 2000) SendDebugText("Buffer overflow for the input RS232!");
            }
        }
        String RS232dataTemp= new String(dataArray,0,dataArrayOffset); 
        jTextTempHandle.setText(RS232dataTemp);
    }
}
