/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kshell;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
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
        mainText.addKeyListener(new KeyListener()
        {

            @Override
            public void keyTyped(KeyEvent e)
            {
                //ignored
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                KeyInput.handleKeyInput(e);                                                             
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                //ignored
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
    }

    /**
     * Add text to the mainText
     *
     * @param s String to append.
     */
    public static void appendText(String s)
    {
        mainText.append(s);
    }

}
