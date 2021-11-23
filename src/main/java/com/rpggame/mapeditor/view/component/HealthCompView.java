package com.rpggame.mapeditor.view.component;

import com.rpggame.rpggame.component.HealthComp;
import imgui.ImGui;
import imgui.type.ImInt;

public class HealthCompView extends ComponentView<HealthComp> {

    private final ImInt currentHealth;
    private final ImInt maxHealth;

    public HealthCompView() {
        super(HealthComp.class, HealthComp::new, "Health");
        this.currentHealth = new ImInt();
        this.maxHealth = new ImInt();
    }

    @Override
    public void imGui() {
        this.currentHealth.set(this.comp.getCurrentHealth());
        this.maxHealth.set(this.comp.getMaxHealth());

        ImGui.inputInt("Current health", this.currentHealth);
        ImGui.inputInt("Max health", this.maxHealth);

        this.comp.setCurrentHealth(this.currentHealth.get());
        this.comp.setMaxHealth(this.maxHealth.get());
    }

}
