package com.rpggame.mapeditor.view.topbar;

import static com.rpggame.mapeditor.constants.MapEditorConstants.REGULAR_FONT;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class WindowSizeChangeDialog {

	private final int sizePossibilites = 3;

	public WindowSizeChangeDialog(Consumer<String> updateFrameSizeFunction) {

		Dimension maximumSize = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension minimumSize = new Dimension(800, 600);

		double minimumWidth = minimumSize.getWidth();
		double minimumHeight = minimumSize.getHeight();

		double width = (maximumSize.getWidth() - minimumWidth) / (sizePossibilites + 1);
		double height = (maximumSize.getHeight() - minimumHeight) / (sizePossibilites + 1);

		List<Dimension> sizesVariations = new ArrayList<>();
		sizesVariations.add(minimumSize);
		for (int i = 1; i <= sizePossibilites; i++) {
			sizesVariations
					.add(new Dimension((int) (minimumWidth + (width * i)), (int) (minimumHeight + (height * i))));
		}
		sizesVariations.add(maximumSize);
		String[] windowSizes = sizesVariations.stream().map(dim -> (int)dim.getWidth() + "x" + (int)dim.getHeight())
				.collect(Collectors.toList()).toArray(String[]::new);
		String windowSize = (String) JOptionPane.showInputDialog(null, "Adjust the window size", "Change Window Size",
				JOptionPane.PLAIN_MESSAGE, null, windowSizes, windowSizes[0]);
		updateFrameSizeFunction.accept(windowSize);
	}

}
