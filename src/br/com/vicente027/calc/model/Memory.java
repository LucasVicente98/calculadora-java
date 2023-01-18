package br.com.vicente027.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {

	private enum CommandType {
		ZERO, SIGNAL, NUMBER, DIV, MULT, SUB, SUM, EQUAL, COMMA
	};

	private static final Memory instance = new Memory();

	private final List<MemoryObserver> observers = new ArrayList<>();

	private CommandType lastOperation = null;
	private boolean replace = false;
	private String currentText = "";
	private String bufferText = "";

	private Memory() {

	}

	public static Memory getInstance() {
		return instance;
	}

	public void addObserver(MemoryObserver observer) {
		observers.add(observer);
	}

	public String getCurrentText() {
		return currentText.isEmpty() ? "0" : currentText;
	}

	public void commandProcess(String text) {

		CommandType commandType = detectCommandType(text);

		if (commandType == null) {
			return;
		} else if (commandType == CommandType.ZERO) {
			currentText = "";
			bufferText = "";
			replace = false;
			lastOperation = null;
		} else if (commandType == CommandType.SIGNAL && currentText.contains("-")) {
			currentText = currentText.substring(1);
		} else if (commandType == CommandType.SIGNAL && !currentText.contains("-")) {
			currentText = "-" + currentText;
		} else if (commandType == CommandType.NUMBER || commandType == CommandType.COMMA) {
			currentText = replace ? text : currentText + text;
			replace = false;
		} else {
			replace = true;
			currentText = getOperationResult();
			bufferText = currentText;
			lastOperation = commandType;
		}

		observers.forEach(o -> o.changedValue(getCurrentText()));
	}

	private String getOperationResult() {
		if (lastOperation == null || lastOperation == CommandType.EQUAL) {
			return currentText;
		}

		double bufferNumber = Double.parseDouble(bufferText.replace(",", "."));
		double currentNumber = Double.parseDouble(currentText.replace(",", "."));

		double result = 0;

		if (lastOperation == CommandType.SUM) {
			result = bufferNumber + currentNumber;
		} else if (lastOperation == CommandType.SUB) {
			result = bufferNumber - currentNumber;
		} else if (lastOperation == CommandType.MULT) {
			result = bufferNumber * currentNumber;
		} else if (lastOperation == CommandType.DIV) {
			result = bufferNumber / currentNumber;
		}

		String stringResult = Double.toString(result).replace(".", ",");
		boolean integer = stringResult.endsWith(",0");
		return integer ? stringResult.replace(",0", "") : stringResult;
	}

	private CommandType detectCommandType(String text) {
		if (currentText.isEmpty() && text == "0") {
			return null;
		}

		try {
			Integer.parseInt(text);
			return CommandType.NUMBER;
		} catch (NumberFormatException e) {
			// Quando não for número...

			if ("AC".equalsIgnoreCase(text)) {
				return CommandType.ZERO;
			} else if ("÷".equals(text)) {
				return CommandType.DIV;
			} else if ("*".equals(text)) {
				return CommandType.MULT;
			} else if ("-".equals(text)) {
				return CommandType.SUB;
			} else if ("+".equals(text)) {
				return CommandType.SUM;
			} else if ("=".equals(text)) {
				return CommandType.EQUAL;
			} else if ("±".equals(text)) {
				return CommandType.SIGNAL;
			} else if (",".equals(text) && !currentText.contains(",")) {
				return CommandType.COMMA;
			}
		}
		return null;
	}
}
