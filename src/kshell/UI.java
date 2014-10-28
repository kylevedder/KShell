/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

/**
 *
 * @author kyle
 */
public class UI extends JFrame
{

    private final String TITLE = "KShell";
    private final String ICON_PATH64 = "/kshell/resources/icon64.png";
    private final String ICON_PATH16 = "/kshell/resources/icon16.png";
    private int NUM_COLS = 80;
    private int NUM_ROWS = 40;
    private static JTextArea mainText = null;
    private static JScrollPane mainTextScroll = null;
    private static JPanel mainPanel = null;
    private UI ui = this;

    public UI()
    {
        init();
    }

    private void init()
    {

        mainTextScroll = new javax.swing.JScrollPane();
        mainText = new javax.swing.JTextArea();
        mainPanel = new JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainText.setColumns(NUM_COLS);
        mainText.setRows(NUM_ROWS);
        mainText.setFont(new Font("Monospaced", Font.PLAIN, 12));
        mainText.setLineWrap(true);
        mainText.setEditable(false);
        mainText.setBackground(Color.BLACK);
        mainText.setForeground(Color.WHITE);
        mainText.setCaretColor(Color.WHITE);
        mainText.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                InputMap inputMap = mainText.getInputMap();
                ActionMap actionMap = mainText.getActionMap();
                KeyStroke keyStroke = KeyStroke.getKeyStrokeForEvent(evt);
                inputMap.put(keyStroke, "doNothing");
                actionMap.put("doNothing", new AbstractAction()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        //ignored
                    }
                });
                KeyInput.handleKeyInput(evt, ui);
            }
        });
        mainTextScroll.setViewportView(mainText);

        mainPanel.add(mainTextScroll);

        this.getContentPane().add(mainPanel);
        this.setTitle(TITLE);
        this.setResizable(false);
        this.setVisible(true);
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon(this.getClass().getResource(ICON_PATH16)).getImage());
        icons.add(new ImageIcon(this.getClass().getResource(ICON_PATH64)).getImage());
        this.setIconImages(icons);
        pack();
        initTextArea();
    }
    
    private void initTextArea()
    {
        updateUI(new Input());
    }
    
    /**
     * Update the UI with the Input obj
     */
    public static void updateUI(Input commandLine)
    {
        try
        {            
            int numLines = mainText.getLineCount();
            int start = mainText.getLineStartOffset(numLines - 1);
            int end = mainText.getLineEndOffset(numLines - 1);
            mainText.replaceRange(commandLine.toLine(), start, end);
        }
        catch (BadLocationException ex)
        {
            Logger.getLogger(KeyInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        

    /**
     * Add text to the mainText
     *
     * @param s String to append.
     */
    public void appendText(String s)
    {
        mainText.append(s);
    }

}
