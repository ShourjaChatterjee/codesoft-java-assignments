
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Bank Account Class
class BankAccount {

    private double balance;

    public BankAccount(double initialBalance) {
        balance = (initialBalance >= 0) ? initialBalance : 0;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }
}

// ATM GUI Class
class ATMGUI extends JFrame implements ActionListener {

    private BankAccount account;
    private JLabel messageLabel;
    private JTextField amountField;

    public ATMGUI(BankAccount account) {
        this.account = account;

        // Frame setup
        setTitle("ATM Machine");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Message area
        messageLabel = new JLabel("Welcome! Your current balance: ₹" + account.checkBalance(), JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel, BorderLayout.NORTH);

        // Center Panel for input
        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Enter Amount: "));
        amountField = new JTextField(10);
        centerPanel.add(amountField);
        add(centerPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton withdrawBtn = new JButton("Withdraw");
        JButton depositBtn = new JButton("Deposit");
        JButton balanceBtn = new JButton("Check Balance");
        JButton exitBtn = new JButton("Exit");

        // Add listeners
        withdrawBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        balanceBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        // Add buttons
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(balanceBtn);
        buttonPanel.add(exitBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (command.equals("Withdraw")) {
                double amount = Double.parseDouble(amountField.getText());
                if (account.withdraw(amount)) {
                    messageLabel.setText("✅ Withdrawal successful! Balance: ₹" + account.checkBalance());
                } else {
                    messageLabel.setText("❌ Withdrawal failed! Insufficient balance.");
                }
            } else if (command.equals("Deposit")) {
                double amount = Double.parseDouble(amountField.getText());
                if (account.deposit(amount)) {
                    messageLabel.setText("✅ Deposit successful! Balance: ₹" + account.checkBalance());
                } else {
                    messageLabel.setText("❌ Deposit failed! Invalid amount.");
                }
            } else if (command.equals("Check Balance")) {
                messageLabel.setText("💰 Current Balance: ₹" + account.checkBalance());
            } else if (command.equals("Exit")) {
                JOptionPane.showMessageDialog(this, "👋 Thank you for using the ATM!");
                System.exit(0);
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("⚠ Please enter a valid number.");
        }

        // Clear input field
        amountField.setText("");
    }
}

// Main class
public class ATMSimulatorGUI {

    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000); // initial balance ₹5000
        new ATMGUI(account);
    }
}
