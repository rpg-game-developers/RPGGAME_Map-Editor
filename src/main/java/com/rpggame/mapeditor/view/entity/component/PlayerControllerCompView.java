package com.rpggame.mapeditor.view.entity.component;

import com.rpggame.rpggame.component.input.PlayerControllerComp;
import imgui.ImGui;
import imgui.type.ImFloat;

public class PlayerControllerCompView extends ComponentView<PlayerControllerComp> {

    private final ImFloat speed;

    public PlayerControllerCompView() {
        super(PlayerControllerComp.class, PlayerControllerComp::new, "Player controller");
        this.speed = new ImFloat();
    }

    @Override
    public void imGui() {
        this.speed.set(comp.getSpeed());
        ImGui.inputFloat("Speed", speed);
        this.comp.setSpeed(this.speed.get());
    }
}
