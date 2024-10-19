/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tip.projs.bypair.restaurantreviewermvc;

/**
 *
 * @author Admin
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantReviewGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public RestaurantReviewGUI() {
        // Frame properties
        setTitle("Restaurant Reviewer");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Table columns: Restaurant, Reviewer, Review, Rating, Action (for remove button)
        String[] columnNames = {"Restaurant", "Reviewer", "Review", "Rating", "Action"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Allow auto resizing
        table.setRowHeight(50);  // Make rows bigger

        // Add example rows
        addReview("Mang Inasal", "Juan Dela Cruz", "Nice Nice, Unli Rice", 9);
        addReview("McDonalds", "Roberto Magno", "Best Fries", 8);
        addReview("Max's", "Maria Reyes", "Good Serving, Nice tasting...", 9);
        addReview("Goldilocks", "Juan Dela Cruz", "Egg Pies, Egg Pies, Egg...", 8);

        // Set custom renderer for padding
        DefaultTableCellRenderer paddingRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    label.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Increase padding
                }
                return component;
            }
        };

        // Apply the padding renderer to all columns except the Action column (button column)
        for (int i = 0; i < table.getColumnCount() - 1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(paddingRenderer);
        }

        // Set up button rendering and editing
        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));

        // Add button at the bottom
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 40));  // button size
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewReview();
            }
        });

        // Layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(addButton, BorderLayout.SOUTH);  // Place button at the bottom

        add(mainPanel);
        setVisible(true);
    }

    private void addReview(String restaurant, String reviewer, String review, int rating) {
        tableModel.addRow(new Object[]{restaurant, reviewer, review, rating, "Remove"});
    }

    private void addNewReview() {
        // Add ka nalang ng ReviewEntry dito
    }

    // Button Renderer class for rendering the "Remove" button
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Remove" : value.toString());
            return this;
        }
    }

    // Button Editor class for handling button clicks
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "Remove" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Action to remove the row
                tableModel.removeRow(table.getSelectedRow());
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
