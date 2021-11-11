package com.rpggame.mapeditor.model;

import java.awt.*;

public class Camera {
    private double zoom;
    private double x;
    private double y;

    public Camera() {
        zoom = 1.0;
        x = 0.0;
        y = 0.0;
    }

    public void zoom(double amount) {
        zoom *= amount;
    }

    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public double inverseX(double x) {
        return x/zoom - this.x;
    }

    public double inverseY(double y) {
        return y/zoom - this.y;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void transformGraphics2D(Graphics2D g2) {
        g2.scale(zoom, zoom);
        g2.translate(x, y);
    }
}
