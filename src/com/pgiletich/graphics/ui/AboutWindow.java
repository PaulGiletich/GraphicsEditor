package com.pgiletich.graphics.ui;

import javax.swing.*;

public class AboutWindow extends JFrame {

    public AboutWindow(){
        setSize(300, 100);
        this.add(new JLabel("Author: Paul Giletich"));
        this.add(new JLabel("group 021701"));
    }
}
