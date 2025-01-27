import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class demo {
    private static final int CARDS_PER_PAGE = 4; // 12 cards per page (4 rows x 4 columns)
    private static final int CARDS_PER_ROW = 4;  // 4 cards per row
    private int currentPage = 0;                 // Track current page
    public String imagePath = "icon.jpeg";
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            demo app = new demo();
            app.createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Paginated Item Cards");
        Dimension mainSize = Toolkit.getDefaultToolkit().getScreenSize();
        int mainWidth = mainSize.width;
        int mainHeight = mainSize.height;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(mainWidth, mainHeight);
        frame.setLocationRelativeTo(null);
        Dimension screenSize = frame.getSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // Create main container for cards and navigation
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create card list (replace with dynamic data if needed)
        List<JPanel> cards = new ArrayList<>();
        for (int i = 1; i <= 20; i++) { // Example: 20 cards
            cards.add(createItemCard("Item " + i, "Description of item " + i +"<br>Something new and stylish shit", imagePath,screenWidth,screenHeight));
        }

        //Get screen width
        int winHeight = frame.getHeight();System.out.println(winHeight);
        double margin = .2 * winHeight;
        int marg = (int) margin;
        
        
        // Card display panel
        JPanel cardDisplayPanel = new JPanel();
        cardDisplayPanel.setLayout(new GridLayout(0, CARDS_PER_ROW, 10, 10));
        cardDisplayPanel.setBorder(BorderFactory.createEmptyBorder(marg, marg, marg, marg));

        // Navigation panel
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        // Add components to navigation panel
        navigationPanel.add(previousButton);
        navigationPanel.add(nextButton);

        // Add panels to main container
        mainPanel.add(cardDisplayPanel, BorderLayout.CENTER);
        mainPanel.add(navigationPanel, BorderLayout.SOUTH);

        // Pagination logic
        updateCardDisplay(cards, cardDisplayPanel, currentPage);

        // Button actions
        previousButton.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                updateCardDisplay(cards, cardDisplayPanel, currentPage);
            }
        });

        nextButton.addActionListener(e -> {
            if ((currentPage + 1) * CARDS_PER_PAGE < cards.size()) {
                currentPage++;
                updateCardDisplay(cards, cardDisplayPanel, currentPage);
            }
        });

        // Add main panel to frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Update the card display panel for the current page
    private void updateCardDisplay(List<JPanel> cards, JPanel cardDisplayPanel, int page) {
        cardDisplayPanel.removeAll(); // Clear current cards
        int start = page * CARDS_PER_PAGE;
        int end = Math.min(start + CARDS_PER_PAGE, cards.size());
        for (int i = start; i < end; i++) {
            cardDisplayPanel.add(cards.get(i));
        }
        cardDisplayPanel.revalidate();
        cardDisplayPanel.repaint();
    }

    // Create an individual card panel
    private JPanel createItemCard(String title, String description, String imagePath,int screenWidth, int screenHeight) {
        
        JPanel cardPanel = new JPanel();
        
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        cardPanel.setBackground(Color.WHITE);
        // cardPanel.setSize(new Dimension(500,400 ));
        // cardPanel.setMaximumSize(new Dimension(200, 200));
        cardPanel.setSize(new Dimension((int)(screenWidth* .8),(int)(screenHeight * .8)));

        // Image label

         // Replace with a valid image path
        
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(imagePath); // Replace with a valid image path
        Image scaledImage = icon.getImage().getScaledInstance(cardPanel.getWidth()/4,cardPanel.getHeight()/3, Image.SCALE_SMOOTH);
        imageLabel.setSize(cardPanel.getWidth(), cardPanel.getHeight());
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        
         
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Title label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Description label
        JLabel descriptionLabel = new JLabel("<html><div style='width:100%;'><h5 style='text-align: left; '>" + description + "</h5></div></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to card
        
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        cardPanel.add(imageLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        cardPanel.add(titleLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer
        cardPanel.add(descriptionLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        cardPanel.add(purchaseButton);

        return cardPanel;
    }
}
