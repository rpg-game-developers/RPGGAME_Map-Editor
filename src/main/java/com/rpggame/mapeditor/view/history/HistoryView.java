package com.rpggame.mapeditor.view.history;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.enums.HistoryActionType;
import com.rpggame.mapeditor.model.HistoryAction;

public class HistoryView extends JPanel {

	public HistoryView() {
		this.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(20, 50, 25, 15),
				BorderFactory.createMatteBorder(1, 1, 1, 1, MapEditorConstants.DARK_BACKGROUND)));

		this.setLayout(new BorderLayout());

		HistoryAction[] testData = { new HistoryAction("Tile Z", HistoryActionType.ADD),
				new HistoryAction("Tile Owwww", HistoryActionType.DELETE),
				new HistoryAction("Tile Ywww", HistoryActionType.EDIT),
				new HistoryAction("Tile Xwww", HistoryActionType.DELETE),
				new HistoryAction("Tile Z", HistoryActionType.ADD),
				new HistoryAction("Tile Owwww", HistoryActionType.DELETE),
				new HistoryAction("Tile Ywww", HistoryActionType.EDIT),
				new HistoryAction("Tile Xwww", HistoryActionType.DELETE),
				new HistoryAction("Tile Z", HistoryActionType.ADD),
				new HistoryAction("Tile Owwww", HistoryActionType.DELETE),
				new HistoryAction("Tile Ywww", HistoryActionType.EDIT),
				new HistoryAction("Tile Xwww", HistoryActionType.DELETE),
				new HistoryAction("Tile Z", HistoryActionType.ADD),
				new HistoryAction("Tile Owwww", HistoryActionType.DELETE),
				new HistoryAction("Tile Ywww", HistoryActionType.EDIT),
				new HistoryAction("Tile Xwww", HistoryActionType.DELETE) };

		JList<HistoryAction> actionList = new JList<>(testData);

		actionList.setCellRenderer(new ActionCellRenderer());

		JScrollPane scrollPane = new JScrollPane(actionList);

		this.add(new HistoryViewHeader(), BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	}

}
