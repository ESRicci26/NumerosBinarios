package javaricci.com.br;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBinaryConverter extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
    private JTextArea binaryArea;
    private JButton toBinaryButton;
    private JButton toTextButton;

    public TextBinaryConverter() {
        // Configuração da janela principal
        setTitle("Conversor de Texto e Binário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criação dos componentes
        textArea = new JTextArea("Digite o texto aqui...");
        binaryArea = new JTextArea("Digite o binário aqui...");
        toBinaryButton = new JButton("Texto para Binário");
        toTextButton = new JButton("Binário para Texto");

        // Painel para os JTextAreas
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.add(new JScrollPane(textArea));
        textPanel.add(new JScrollPane(binaryArea));

        // Painel para os JButtons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(toBinaryButton);
        buttonPanel.add(toTextButton);

        // Adicionando os painéis à janela
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);



        // Ação para converter texto para binário
        toBinaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                StringBuilder binary = new StringBuilder();
                for (char c : text.toCharArray()) {
                    binary.append(String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0"));
                }
                binaryArea.setText(binary.toString());
            }
        });



        // Ação para converter binário para texto
        toTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String binary = binaryArea.getText();
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < binary.length(); i += 8) {
                    String byteString = binary.substring(i, i + 8);
                    text.append((char) Integer.parseInt(byteString, 2));
                }
                textArea.setText(text.toString());
            }
        });
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextBinaryConverter().setVisible(true);
            }
        });
    }
}


