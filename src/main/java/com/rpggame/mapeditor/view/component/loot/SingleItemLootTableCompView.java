package com.rpggame.mapeditor.view.component.loot;

import com.rpggame.mapeditor.view.component.ComponentView;
import com.rpggame.rpggame.component.NameComp;
import com.rpggame.rpggame.component.loot.SingleItemLootTableComp;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;
import imgui.type.ImInt;
import imgui.type.ImString;

import java.util.Map;

public class SingleItemLootTableCompView extends ComponentView<SingleItemLootTableComp> {
    private final ImInt rarity;
    private final ImString newEntityName;
    private final ImInt newEntityRarity;

    public SingleItemLootTableCompView() {
        super(SingleItemLootTableComp.class, SingleItemLootTableComp::new, "Single item loot table");
        this.rarity = new ImInt();
        this.newEntityName = new ImString();
        this.newEntityRarity = new ImInt();
    }

    @Override
    public void imGui() {
        Map<Entity, Integer> table = comp.getTable();

        int current = 0;
        for (Map.Entry<Entity, Integer> entry : table.entrySet()) {
            current += 1;
            ImGui.pushID(current);

            Entity entity = entry.getKey();
            String name = "Entity";
            if (entity.hasComponent(NameComp.class)) {
                name = entity.getComponent(NameComp.class).getName();
            }
            ImGui.text(name);

            rarity.set(entry.getValue());
            ImGui.inputInt("Rarity", rarity);
            entry.setValue(rarity.get());

            ImGui.popID();
        }

        if (ImGui.button("Add drop")) {
            ImGui.openPopup("New loot table entry");
        }

        if (ImGui.beginPopupModal("New loot table entry")) {
            if (ImGui.isWindowAppearing()) {
                ImGui.setWindowSize(400, 200);
            }

            ImGui.inputText("Entity name", newEntityName);
            ImGui.inputInt("Rarity", newEntityRarity);

            if (ImGui.button("Add")) {
                Entity entity = new Entity();
                entity.addComponent(new NameComp(newEntityName.get()));
                table.put(entity, newEntityRarity.get());
                ImGui.closeCurrentPopup();
            }
            ImGui.sameLine();
            if (ImGui.button("Cancel")) {
                ImGui.closeCurrentPopup();
            }

            ImGui.endPopup();
        }
    }
}
