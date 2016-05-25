/**
 * 
 */
package com.learnSpring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.learnSpring.pojo.Student;
import com.learnSpring.propertyEditors.*;

/**
 * @author kaminy.doraisami
 *
 */
@Controller
public class StudentAdmissionController {
	

	@InitBinder
	public void customizeBinding(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("d-MM-yyyy");
		binder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class,"studentMobile", new StudentMobileEditor());
	}

	@RequestMapping(value = "/admissionForm.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {

		ModelAndView model = new ModelAndView("AdmissionForm");
		return model;

	}
	/*
	 * @RequestMapping(value = "/submitAdmissionForm.html", method =
	 * RequestMethod.POST) public ModelAndView
	 * submitAdmissionForm(@RequestParam("studentName") String name,
	 * 
	 * @RequestParam("studentId") String id) {
	 * 
	 * Student student1 = new Student(); student1.setStudentName(name);
	 * student1.setStudentId(id);
	 * 
	 * ModelAndView model = new ModelAndView("AdmissionSuccess"); //
	 * model.addObject("message", "Submitted Name: " + name + " and ID: " + //
	 * id); model.addObject("student1", student1); return model; }
	 */

	@RequestMapping(value = "/submitAdmissionForm.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("student1") Student student1, BindingResult result) {
		
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("AdmissionForm");
			return model;
		}
		ModelAndView model = new ModelAndView("AdmissionSuccess");
		return model;
	}

}
