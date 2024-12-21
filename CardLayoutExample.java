import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardLayoutExample {
    public static void main(String[] args) {
        // Create main frame
        JFrame frame = new JFrame("Card Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);

        // Create a panel to hold the cards
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(1, 5, 10, 10)); // 5 cards in one row

        // Add cards
        cardPanel.add(createCard("PHP Developer", "124 Videos", "php.png"));
        cardPanel.add(createCard("Python Programming", "236 Videos", "python.png"));
        cardPanel.add(createCard("Figma Design", "87 Videos", "figma.png"));
        cardPanel.add(createCard("UI With Sketch", "29 Videos", "sketch.png"));
        cardPanel.add(createCard("JavaScript", "18 Videos", "javascript.png"));

        // Add the card panel to the frame
        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private static JPanel createCard(String title, String description, String imagePath) {
        // Create the card panel
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Add image
        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(imageLabel, BorderLayout.NORTH);

        // Add title and description
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        JLabel descLabel = new JLabel(description, SwingConstants.CENTER);
        textPanel.add(titleLabel);
        textPanel.add(descLabel);
        card.add(textPanel, BorderLayout.CENTER);

        // Add button
        JButton button = new JButton("Learn More");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(card, "More details about " + title);
            }
        });
        card.add(button, BorderLayout.SOUTH);

        return card;
    }
}
