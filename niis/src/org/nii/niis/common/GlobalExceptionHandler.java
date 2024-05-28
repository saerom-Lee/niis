package org.nii.niis.common;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.common
* @FileName : GlobalExceptionHandler.java 
* @Date : 2016. 12. 19.
* @description : 글로벌 익셉션 핸들러
* </pre>
*/
@ControllerAdvice
public class GlobalExceptionHandler {

	protected Logger log;

	private String ajaxHaeder = "AJAX";
	
	public GlobalExceptionHandler() {
		log = Logger.getLogger(getClass());
	}

	@ResponseBody
	public ModelAndView handleUnauthenticationException(HttpServletRequest request, Exception e) {
		return errorResponse(request, e, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class })
	@ResponseBody
	public ModelAndView handleConflictException(HttpServletRequest request, Exception e) {
		return errorResponse(request, e, HttpStatus.CONFLICT);
	}

	@ExceptionHandler({ SQLException.class, DataAccessException.class, RuntimeException.class })
	@ResponseBody
	public ModelAndView handleSQLException(HttpServletRequest request, Exception e) {
		return errorResponse(request, e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ IOException.class, ParseException.class })
	@ResponseBody
	public ModelAndView handleParseException(HttpServletRequest request, Exception e) {
		return errorResponse(request, e, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ InvalidKeyException.class, NoSuchAlgorithmException.class })
	@ResponseBody
	public ModelAndView handleHashException(HttpServletRequest request, Exception e) {
		return errorResponse(request, new Exception("Encrypt/Decrypt key is requested"), HttpStatus.LOCKED);
	}

	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ModelAndView handleAnyException(HttpServletRequest request, Exception e) {
		return errorResponse(request, e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected ModelAndView errorResponse(HttpServletRequest request, Throwable t, HttpStatus status) {
		if (null != t) {
			return response(request, t, status);
		} else {
			return response(request, null, status);
		}
	}

	protected ModelAndView response(HttpServletRequest request, Throwable t, HttpStatus status) {
		log.error(this.getClass() + "::::response", t);
		
		ModelAndView mav = null;
		if(isAjaxRequest(request)){
			mav = new ModelAndView("jsonView");
			mav.addObject("errCd", status.value());
			mav.addObject("errMsg", status.name());
		}else{
			mav = new ModelAndView();
			mav.setViewName("/common/error");
			mav.addObject("errCd", status.value());
			mav.addObject("errMsg", status.name());
		}
		return mav;
	}
	
	private boolean isAjaxRequest(HttpServletRequest req) {
		return req.getHeader(ajaxHaeder) != null && req.getHeader(ajaxHaeder).equals(Boolean.TRUE.toString());
	}
}
