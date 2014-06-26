package Core;// Created by Hanto on 26/06/2014.

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class Consola extends JFrame implements Runnable
{
    private JScrollPane scrollPane;
    public JTextPane textPane;
    private Document doc;

    private void initComponents()
    {
        scrollPane = new JScrollPane();
        textPane = new JTextPane();
        doc = textPane.getStyledDocument();

        setBackground(new Color(18, 30, 49));

        textPane.setEditable(false);
        textPane.setFont(new Font("Consolas", 0, 11)); // NOI18N
        textPane.setBackground(Color.BLACK);
        textPane.setForeground(Color.LIGHT_GRAY);

        scrollPane.setViewportView(textPane);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );

        //Codigo para AutoScroll
        DefaultCaret caret = (DefaultCaret) textPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        pack();
    }

    public void run()   {}

    public Consola()
    {   //EvenQueue.invokeLater
        new Thread(this);
        initComponents();
        this.setVisible(true);
    }

    private void setText (String string, SimpleAttributeSet saSet)
    {
        try
        {
            if (doc.getLength() > 5000)
            {   doc.remove(0, doc.getLength()-5000); }

            doc.insertString(doc.getLength(), string, saSet);
            textPane.setCaretPosition(doc.getLength());
        }
        catch (Exception e) {}
    }



    public void print (String string, Color color, int size)
    {
        SimpleAttributeSet saSet = new SimpleAttributeSet ();
        StyleConstants.setForeground(saSet, color);
        StyleConstants.setFontSize(saSet, size);

        setText(string, saSet);
    }

    public void print (String string, Color color)
    {
        SimpleAttributeSet saSet = new SimpleAttributeSet ();
        StyleConstants.setForeground(saSet, color);

        setText(string, saSet);
    }

    public void print (String string, int size)
    {
        SimpleAttributeSet saSet = new SimpleAttributeSet ();
        StyleConstants.setFontSize(saSet, size);

        setText(string, saSet);
    }

    public void print (String string)
    {
        SimpleAttributeSet saSet = new SimpleAttributeSet ();

        setText(string, saSet);
    }

    public void println (String string, Color color, int size)
    {
        SimpleAttributeSet saSet = new SimpleAttributeSet ();
        StyleConstants.setForeground(saSet, color);
        StyleConstants.setFontSize(saSet, size);

        setText(string+"\n", saSet);
    }

    public void println (String string, Color color)
    {
        SimpleAttributeSet saSet = new SimpleAttributeSet ();
        StyleConstants.setForeground(saSet, color);

        setText(string+"\n", saSet);
    }

    public void println (String string, int size)
    {
        SimpleAttributeSet saSet = new SimpleAttributeSet ();
        StyleConstants.setFontSize(saSet, size);

        setText(string+"\n", saSet);
    }

    public void println (String string)
    {
        SimpleAttributeSet saSet = new SimpleAttributeSet ();

        setText(string+"\n", saSet);
    }
}
