package com.mandreatti.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> Error = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStump) {
		super(status, msg, timeStump);
	}

	public List<FieldMessage> getError() {
		return Error;
	}

	public void addError(String fieldName, String message) {
		Error.add(new FieldMessage(fieldName, message));
	}


}
