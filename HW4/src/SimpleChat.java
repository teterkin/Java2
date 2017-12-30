import bot.SimpleBot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SimpleChat
{
    private JTextArea textArea;
    private JPanel panel;
    private JCheckBox ai;
    private JTextField text;
    private JButton button;
    private JPanel bottomPanel;

    private boolean textFieldClicked = false;

    SimpleBot bot = new SimpleBot();

    static final String FILE_NAME = "chat_log.txt";

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

                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    sendText();
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
        button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                sendText();
            }
        });
    } // End of Constructor

    private void sendText()
    {
        if (text.getText().trim().length() > 0)
        {
            if (!text.getText().equals("Введите текст..."))
            {

                String myText = "\nВы: " + text.getText();
                textArea.append(myText);
                logText(myText);

                String botText = "\nБот: " +
                        bot.sayInReturn(text.getText(), ai.isSelected());
                textArea.append(botText);
                logText(botText);

                clean();
            }
        }
    }

    private void logText(String text)
    {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_NAME, true), "utf-8"))) {
            writer.write("\n" + text);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void clean()
    {
        text.setText("");
        textFieldClicked = false;
    }

    private void prepareForTextEntry()
    {
        text.setText("");
        text.setForeground(Color.black);
        textFieldClicked = true;
    }

    // main
    public static void main(String[] args)
    {
        final int START_LOCATION = 200;
        final int WINDOW_WIDTH = 350;
        final int WINDOW_HEIGHT = 450;
        JFrame frame = new JFrame("Телекилограм");
        frame.setIconImage(new ImageIcon(SimpleChat.class.getResource("images/SendButton.PNG")).getImage());
        frame.setContentPane(new SimpleChat().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                // Пока!
            }
        });
        frame.setVisible(true);
    }

}
