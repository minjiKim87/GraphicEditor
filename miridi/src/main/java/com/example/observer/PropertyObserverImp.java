package com.example.observer;

import javafx.scene.text.Text;

import java.text.DecimalFormat;


public class PropertyObserverImp implements PropertyObserver{
    private Text xText;
    private Text yText;
    private Text wText;
    private Text hText;
    private Text rText;
    private Text gText;
    private Text bText;

    public PropertyObserverImp(Text xText, Text yText, Text wText, Text hText, Text rText, Text gText, Text bText) {
        this.xText = xText;
        this.yText = yText;
        this.wText = wText;
        this.hText = hText;
        this.rText = rText;
        this.gText = gText;
        this.bText = bText;
    }
    @Override
    public void update(double x, double y, double width, double height, int r, int g, int b) {
            DecimalFormat df = new DecimalFormat("#.#");
            xText.setText(Double.toString(Double.parseDouble(df.format(x))));
            yText.setText(Double.toString(Double.parseDouble(df.format(y))));
            wText.setText(Double.toString(Double.parseDouble(df.format(width))));
            hText.setText(Double.toString(Double.parseDouble(df.format(height))));
            rText.setText(String.valueOf(r));
            gText.setText(String.valueOf(g));
            bText.setText(String.valueOf(b));
    }
}