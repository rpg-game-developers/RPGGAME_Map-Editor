package com.rpggame.mapeditor.controller;

import com.rpggame.mapeditor.model.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CameraController {
    private Camera camera;
    private double zoomSpeed;
    private int lastMouseX;
    private int lastMouseY;

    public CameraController() {
        this.zoomSpeed = 0.9;
        this.camera = new Camera();
        this.lastMouseX = 0;
        this.lastMouseY = 0;
    }

    public void connectComponent(Component component) {
        component.addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            camera.zoom(Math.pow(zoomSpeed, (double)notches));
            component.repaint();
        });
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMouseX = e.getX();
                lastMouseY = e.getY();
            }
        });
        component.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    int dx = e.getX() - lastMouseX;
                    int dy = e.getY() - lastMouseY;
                    camera.translate(dx/camera.getZoom(), dy/camera.getZoom());
                }
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                component.repaint();
            }
        });
    }

    public double getZoomSpeed() {
        return zoomSpeed;
    }

    public void setZoomSpeed(double zoomSpeed) {
        this.zoomSpeed = zoomSpeed;
    }

    public Camera getCamera() {
        return camera;
    }

}
