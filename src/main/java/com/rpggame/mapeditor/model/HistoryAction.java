package com.rpggame.mapeditor.model;

import com.rpggame.mapeditor.enums.HistoryActionType;

public class HistoryAction {

	private String actionName;
	private HistoryActionType actionType;

	public HistoryAction(String actionName, HistoryActionType actionType) {
		this.actionName = actionName;
		this.actionType = actionType;
	}

	public String getActionName() {
		return actionName;
	}

	public HistoryActionType getActionType() {
		return actionType;
	}

}
