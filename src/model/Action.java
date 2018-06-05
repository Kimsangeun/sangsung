package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	ActionData exec(HttpServletRequest request, HttpServletResponse response);
}
