package org.nii.niis.connNiim.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/niim")
public class NiisCurrentPositionController {
	
    @RequestMapping(value="/rest/getCurPosition.do")
	public String getCurPosition(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException, IOException {
		return "forward:/rest/getCurPosition.do";
	}
}
