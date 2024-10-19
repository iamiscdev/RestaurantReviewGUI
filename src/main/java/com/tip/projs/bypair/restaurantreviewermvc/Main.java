/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tip.projs.bypair.restaurantreviewermvc;

import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RestaurantReviewGUI();
            }
        });
    }
}
