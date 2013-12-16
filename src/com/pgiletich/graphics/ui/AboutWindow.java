package com.pgiletich.graphics.ui;

import javax.swing.*;
import java.awt.*;

public class AboutWindow extends JFrame {

    public AboutWindow(){
        setLayout(new FlowLayout());
        setSize(250, 100);
        add(new JLabel("Authors: Paul Giletich, Alexander Vasilenko"));
        add(new JLabel("group 021701"));
    }
}
