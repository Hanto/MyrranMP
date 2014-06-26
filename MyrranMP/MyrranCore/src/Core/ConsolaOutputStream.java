package Core;// Created by Hanto on 26/06/2014.

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.io.IOException;
import java.io.OutputStream;

public class ConsolaOutputStream extends OutputStream
{
    private JTextPane textPane;
    private Document doc;

    public ConsolaOutputStream(Consola consola)
    {   this.textPane = consola.textPane;
        this.doc = textPane.getStyledDocument();
    }

    @Override public void write(int b) throws IOException
    {
        System.out.write(b);

        SimpleAttributeSet saSet = new SimpleAttributeSet ();
        StyleConstants.setFontSize(saSet, 11);

        //redirects data to the text area
        try { doc.insertString(doc.getLength(), (String.valueOf((char) b)) , saSet); }
        catch (BadLocationException e) { e.printStackTrace(); }
        //scrolls the text area to the end of data
        textPane.setCaretPosition(doc.getLength());
    }
}
