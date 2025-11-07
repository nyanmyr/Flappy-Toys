package game;

import database.DatabaseConnection;
import static game.Main.SCREEN_HEIGHT;
import static game.Main.SCREEN_WIDTH;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import sfx.sounds.SoundFile;
import sfx.sounds.SoundPlayer;
import utility.sprites.StaticSprite;

public class Leaderboards extends javax.swing.JFrame {

    private StaticSprite background;

    private String[] columnNames = {"Ranking", "Username", "Highest Score", "Highest Tokens Collected"};
    private List<Object[]> retrievedData = DatabaseConnection.getStatsData();
    private Object[][] data = new Object[retrievedData.size()][4];

    public Leaderboards() {
        initComponents();

        for (int i = 0; i < retrievedData.size(); i++) {
            data[i][1] = retrievedData.get(i)[0]; // display username
            data[i][2] = retrievedData.get(i)[1]; // display highest score
            data[i][3] = retrievedData.get(i)[3]; // display highest tokens collected
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table_Leaderboards.setModel(model);

        // Enable sorting
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        table_Leaderboards.setRowSorter(sorter);

        sorter.setSortKeys(java.util.List.of(
                new RowSorter.SortKey(2, SortOrder.DESCENDING)
        ));
        // disable ranking and username sorting
        sorter.setSortable(0, false);
        sorter.setSortable(1, false);

        // Guard against reentrancy while updating rankings
        AtomicBoolean updating = new AtomicBoolean(false);

        // detect if the table is being sorted
        RowSorterListener listener = e -> {
            if (e.getType() == RowSorterEvent.Type.SORT_ORDER_CHANGED) {
                // Run later so the sorter finishes internal state changes first
                SwingUtilities.invokeLater(() -> {
                    // Prevent re-entry if we're the ones changing the model
                    if (updating.compareAndSet(false, true)) {
                        try {
                            updateRanking(table_Leaderboards);
                        } finally {
                            updating.set(false);
                        }
                    }
                });
            }
        };

        updateRanking(table_Leaderboards);

        LoadSprite();
        panel_Background.add(background);
    }

    private void LoadSprite() {
        try {
            java.net.URL resource = getClass().getResource("/resources/backgrounds/bricks_bg.jpg");
            if (resource != null) {
                Image img = ImageIO.read(resource);
                java.awt.image.BufferedImage buffered
                        = new java.awt.image.BufferedImage(
                                img.getWidth(null),
                                img.getHeight(null),
                                java.awt.image.BufferedImage.TYPE_INT_ARGB
                        );
                Graphics2D g2d = buffered.createGraphics();
                g2d.drawImage(img, 0, 0, null);
                g2d.dispose();

                background = new StaticSprite(buffered);

                background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            } else {
                throw new RuntimeException("Image resource not found: bricks_bg.jpg");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sprite image", e);
        }
    }

    private static void updateRanking(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int viewRow = 0; viewRow < table.getRowCount(); viewRow++) {
            int modelRow = table.convertRowIndexToModel(viewRow);
            // Only set if different (reduces needless events)
            Object current = model.getValueAt(modelRow, 0);
            int newRank = viewRow + 1;
            if (!(current instanceof Number) || ((Number) current).intValue() != newRank) {
                model.setValueAt(newRank, modelRow, 0);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Background = new javax.swing.JPanel();
        label_Title = new javax.swing.JLabel();
        button_Return = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Leaderboards = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        panel_Background.setBackground(new java.awt.Color(51, 102, 255));
        panel_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        panel_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_Background.setLayout(null);

        label_Title.setFont(new java.awt.Font("Comic Sans MS", 0, 48)); // NOI18N
        label_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Title.setText("Leaderboards");
        panel_Background.add(label_Title);
        label_Title.setBounds(50, 50, 320, 80);

        button_Return.setBackground(new java.awt.Color(0, 74, 173));
        button_Return.setForeground(new java.awt.Color(255, 255, 255));
        button_Return.setText("Return");
        button_Return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button_ReturnMouseEntered(evt);
            }
        });
        button_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ReturnActionPerformed(evt);
            }
        });
        panel_Background.add(button_Return);
        button_Return.setBounds(290, 460, 220, 50);

        table_Leaderboards.setShowGrid(true);
        table_Leaderboards.getTableHeader().setResizingAllowed(false);
        table_Leaderboards.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_Leaderboards);

        panel_Background.add(jScrollPane1);
        jScrollPane1.setBounds(60, 150, 660, 290);

        getContentPane().add(panel_Background);
        panel_Background.setBounds(0, 0, 800, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed
        SoundPlayer.playSound(SoundFile.CLICK);
        dispose();
        java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
    }//GEN-LAST:event_button_ReturnActionPerformed

    private void button_ReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_ReturnMouseEntered
        SoundPlayer.playSound(SoundFile.SELECT);
    }//GEN-LAST:event_button_ReturnMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Return;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_Title;
    private javax.swing.JPanel panel_Background;
    private javax.swing.JTable table_Leaderboards;
    // End of variables declaration//GEN-END:variables
}
