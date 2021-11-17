package com.rpggame.mapeditor.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.rpggame.mapeditor.game.TestGame;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.tile.Tile;
import com.rpggame.mapeditor.system.TileMapEditingSystem;
import com.rpggame.mapeditor.view.entity.EntityListView;
import com.rpggame.mapeditor.view.entity.EntityView;
import com.rpggame.mapeditor.view.filesystem.FileSystemView;
import com.rpggame.rpggame.RpgGame;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImBoolean;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Application extends ApplicationAdapter {

    ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    TileSelector tileSelectorView;
    Selector<Tile> tileSelector;
    Selector<Entity> entitySelector;
    GameView gameView;
    GameView editorView;
    EntityListView entityListView;
    EntityView entityView;
    FileSystemView fileSystemView;
    long windowHandle;

    @Override
    public void create() {
        tileSelector = new Selector<>();
        entitySelector = new Selector<>();
        tileSelectorView = new TileSelector(tileSelector);
        RpgGame rpgGame = new RpgGame();
        gameView = new GameView("Game view", rpgGame);
        editorView = new GameView("Editor view", new TestGame(tileSelector));
        entityListView = new EntityListView(rpgGame.getEntityWorld(), entitySelector);
        entityView = new EntityView(entitySelector);
        fileSystemView = new FileSystemView();

        rpgGame.getEntityWorld().addSystem(new TileMapEditingSystem(rpgGame.getViewport(), entitySelector, tileSelector));

        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        ImGui.createContext();
        final ImGuiIO io = ImGui.getIO();
        io.setIniFilename("imgui.ini");
        io.setConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.setConfigWindowsMoveFromTitleBarOnly(true);

        windowHandle = ((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle();

        // input handling
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gameView);
        multiplexer.addProcessor(editorView);
        Gdx.input.setInputProcessor(multiplexer);

        imGuiGlfw.init(windowHandle, true);
        imGuiGl3.init("#version 330 core");
    }

    @Override
    public void render() {
        imGuiGlfw.newFrame();


        ImGui.newFrame();
        setUpDockSpace();
        tileSelectorView.imGui();
        gameView.imGui();
        editorView.imGui();
        entityListView.imGui();
        entityView.imGui();
        fileSystemView.imGui();
        ImGui.showDemoWindow();
        ImGui.end();
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

    private void setUpDockSpace() {
        int windowFlags = ImGuiWindowFlags.MenuBar | ImGuiWindowFlags.NoDocking;
        ImGui.setNextWindowPos(0.0f, 0.0f, ImGuiCond.Always);
        ImGui.setNextWindowSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 0.0f);
        ImGui.pushStyleVar(ImGuiStyleVar.WindowBorderSize, 0.0f);
        windowFlags |= ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoCollapse |
                ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoBringToFrontOnFocus |
                ImGuiWindowFlags.NoNavFocus;
        ImGui.begin("Map editor", new ImBoolean(true), windowFlags);
        ImGui.popStyleVar(2);

        // dockspace
        ImGui.dockSpace(ImGui.getID("Dockspace"));

    }
}
