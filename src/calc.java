import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calc extends JFrame implements ActionListener {   // class name = calc
    JTextField display;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public calc() {
        setTitle("Calculator");
        setSize(350, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        display = new JTextField();
        display.setBounds(30, 40, 280, 40);
        display.setEditable(false);
        add(display);

        String[] btnText = {
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "0",".","=","+"
        };

        JButton[] buttons = new JButton[16];
        int x = 30, y = 110;

        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(btnText[i]);
            buttons[i].setBounds(x, y, 60, 50);
            add(buttons[i]);
            buttons[i].addActionListener(this);
            x += 70;

            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 60;
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if ((input.charAt(0) >= '0' && input.charAt(0) <= '9') || input.equals(".")) {
            display.setText(display.getText() + input);
        } else if (input.charAt(0) == '=') {
            if (display.getText().isEmpty()) return;
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 == 0) {
                        display.setText("Error");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            display.setText(String.valueOf(result));
        } else {
            if (display.getText().isEmpty()) return;
            num1 = Double.parseDouble(display.getText());
            operator = input.charAt(0);
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new calc());   // updated main method
    }
}

