package pers.sfl.exception.handler.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.sfl.exception.handler.exception.JsonException;
import pers.sfl.exception.handler.exception.PageException;
import pers.sfl.exception.handler.model.ApiResponse;

/**
 *
 * @description: 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class DemoExceptionHandler {
	private static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 统一 json 异常处理
	 *
	 * @param exception JsonException
	 * @return 统一返回 json 格式
	 */
	@ExceptionHandler(value = JsonException.class)
	@ResponseBody
	public ApiResponse jsonErrorHandler(JsonException exception) {
		log.error("【JsonException】:{}", exception.getMessage());
		return ApiResponse.ofException(exception);
	}

	/**
	 * 统一 页面 异常处理
	 *
	 * @param exception PageException
	 * @return 统一跳转到异常页面
	 */
	@ExceptionHandler(value = PageException.class)
	public ModelAndView pageErrorHandler(PageException exception) {
		log.error("【DemoPageException】:{}", exception.getMessage());
		ModelAndView view = new ModelAndView();
		view.addObject("message", exception.getMessage());
		view.setViewName(DEFAULT_ERROR_VIEW);
		return view;
	}
}
