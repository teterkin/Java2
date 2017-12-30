import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleChat
{
    private JTextArea textArea;
    private JPanel panel;
    private JCheckBox ai;
    private JTextField text;
    private JButton button;
    private JPanel bottomPanel;

    private boolean textFieldClicked = false;
    private boolean justSent = false;

    // Constructor
    public SimpleChat()
    {
        text.setForeground(Color.gray);

        text.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                super.keyReleased(e);

                if (justSent)
                {
                    prepareForTextEntry();
                    text.setText(String.valueOf(e.getKeyChar()));
                    justSent = false;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    if (text.getText().trim().length() > 0)
                    {
                        if (!text.getText().equals("Введите текст..."))
                        {
                            textArea.append("\n" + text.getText());
                            clean();
                            justSent = true;
                        }
                    }
                }
            }
        });

        text.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                if (!textFieldClicked)
                {
                    prepareForTextEntry();
                }
            }
        });
    } // End of Constructor

    private void clean()
    {
        text.setText("Введите текст...");
        text.setForeground(Color.gray);
        textFieldClicked = false;
    }

    private void prepareForTextEntry()
    {
        text.setText("");
        text.setForeground(Color.black);
        textFieldClicked = true;
    }



    public static void main(String[] args)
    {
        final int START_LOCATION = 200;
        final int WINDOW_WIDTH = 350;
        final int WINDOW_HEIGHT = 450;

        JFrame frame = new JFrame("Телекилограм");
        frame.setContentPane(new SimpleChat().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setVisible(true);
    }
}
