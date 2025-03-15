import java.net.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.io.*;

public class Client extends JFrame {

    Socket socket;

    BufferedReader br;
    PrintWriter out;

    private JLabel heading = new JLabel("Client Area");
    private JTextArea textArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    private Font font = new Font("Roboto", Font.PLAIN, 20);

    // Constructor
    public Client() {
        try {
            this.socket = new Socket("127.0.0.1", 7778); // Assign to instance variable
            System.out.println("connected to server...");
            System.out.println("Connection done");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            createGUI();
            handleEvents();

            startReading();
            // startWriting();
        } catch (Exception e) {
            e.printStackTrace(); // Print exception details for debugging
        }
    }

    private void handleEvents() {
        // TODO Auto-generated method stub
        messageInput.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) { // Enter key
                    String contentToSend = messageInput.getText().trim();
                    if (contentToSend.isEmpty())
                        return;

                    System.out.println("Sending message: " + contentToSend);
                    textArea.append("Client: " + contentToSend + "\n");

                    if (out != null) {
                        out.println(contentToSend);
                        out.flush();
                        System.out.println("Message sent successfully.");
                    } else {
                        System.out.println("ERROR: PrintWriter is null!");
                    }

                    messageInput.setText("");
                    messageInput.requestFocus();
                }
            }

        });
    }

    private void createGUI() {
        // Set frame properties
        this.setTitle("Client Messanger[End]");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // coding for component
        heading.setFont(font);// for font
        textArea.setFont(font); // for font
        messageInput.setFont(font);// for font
        heading.setIcon(new ImageIcon("clogo.png"));// for icon
        ImageIcon icon = new ImageIcon("clogo.png");
        Image img = icon.getImage(); // Convert to Image
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Resize to 50x50
        heading.setIcon(new ImageIcon(scaledImg)); // Set resized icon

        heading.setVerticalTextPosition(SwingConstants.BOTTOM);

        heading.setHorizontalTextPosition(SwingConstants.CENTER);// for alignment
        heading.setHorizontalAlignment(SwingConstants.CENTER);// for alignment
        heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));// for border
        textArea.setEditable(false);// for not editable
        messageInput.setHorizontalAlignment(SwingConstants.CENTER);// for alignment

        // Now we set the layout of the frame
        this.setLayout(new BorderLayout());// for layout
        // adding component in frame
        this.add(heading, BorderLayout.NORTH); // for heading
        JScrollPane jscrollPane = new JScrollPane(textArea);// for scrollDown And scrollUp
        this.add(jscrollPane, BorderLayout.CENTER);// for textArea
        this.add(messageInput, BorderLayout.SOUTH);// for messageInput

        this.setVisible(true); // Center the frame on the screen

    }

    public void startReading() throws IOException {
        // one thread for reading...
        Runnable r1 = () -> {
            System.out.println("reader started...");
            try {
                while (true) {

                    String msg = br.readLine();
                    if (msg.equals("Abort")) {
                        System.out.println("Server terminated the chat");
                        JOptionPane.showMessageDialog(this, "Server terminated the chat");
                        messageInput.setEnabled(false);
                        System.out.println("Client: " + msg);
                        socket.close();

                        break;

                    }
                    // System.out.println("Server: " + msg);
                    textArea.append("Server: " + msg + "\n");
                }
            } catch (Exception e) {
                // TODO: handle exception
                // e.printStackTrace();
                System.out.println("connection closed.....");
            }
        };
        new Thread(r1).start();
    }

    public void startWriting() {
        // this thread take data from user and send to client
        Runnable r2 = () -> {
            System.out.println("writer started...");
            try {
                while (true && !socket.isClosed()) {

                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();

                    out.println(content);
                    out.flush();

                    if (content.equals("Abort")) {
                        socket.close();
                        break;

                    }

                }
                System.out.println("connection closed.....");
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("this is client......");
        new Client();

    }
}
