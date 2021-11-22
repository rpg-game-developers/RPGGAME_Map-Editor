package com.rpggame.mapeditor.view.entity.component.loot;

import com.rpggame.mapeditor.view.entity.component.ComponentView;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.component.loot.SingleItemLootTableComp;
import com.rpggame.rpggame.entity.Entity;
import imgui.ImGui;
import imgui.type.ImInt;

import java.util.Map;

public class SingleItemLootTableCompView extends ComponentView<SingleItemLootTableComp> {
    private final ImInt rarity;

    public SingleItemLootTableCompView() {
        super(SingleItemLootTableComp.class, SingleItemLootTableComp::new, "Single item loot table");
        this.rarity = new ImInt();
    }

    @Override
    public void imGui() {
        Map<Entity, Integer> table = comp.getTable();

        int current = 0;
        for (Map.Entry<Entity, Integer> entry : table.entrySet()) {
            current += 1;
            ImGui.pushID(current);
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

            if (ImGui.button("Add")) {
                table.put(new Entity(), 1);
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
