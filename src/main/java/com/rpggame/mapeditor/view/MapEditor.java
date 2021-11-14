package com.rpggame.mapeditor.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.utils.ScreenUtils;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;

public class MapEditor extends ApplicationAdapter {

    ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    long windowHandle;

    @Override
    public void create() {
        // create 3D scene
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        ImGui.createContext();

        windowHandle = ((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle();

        imGuiGlfw.init(windowHandle, true);
        imGuiGl3.init("#version 330");
    }

    @Override
    public void render() {
        // render 3D scene
        ScreenUtils.clear(0, 0, 0.2f, 1);
        imGuiGlfw.newFrame();
        ImGui.newFrame();
        ImGui.button("I'm a Button!");
        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());
        glfwPollEvents();
    }

    @Override
    public void dispose() {
        imGuiGl3.dispose();
        imGuiGlfw.dispose();
        ImGui.destroyContext();
    }
}
