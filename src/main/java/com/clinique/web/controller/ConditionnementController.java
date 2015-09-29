/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.web.controller;

import com.clinique.persistence.model.Conditionnement;
import com.clinique.persistence.service.IConditionnementService;
import com.clinique.persistence.service.IRoleService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Brice GUEMKAM <briceguemkam@gmail.com>
 */
@Controller
@RequestMapping("/conditionnement")
public class ConditionnementController
{

    @Autowired
    IConditionnementService conditionnementService;

    @Autowired
    IRoleService roleService;

    /**
     *
     * @param model
     * @param webRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(final ModelMap model, final WebRequest webRequest)
    {
        final String nom = webRequest.getParameter("querynom") != null ? webRequest.getParameter("querynom") : "";
        final Integer page = webRequest.getParameter("page") != null ? Integer.valueOf(webRequest.getParameter("page")) : 0;
        final Integer size = webRequest.getParameter("size") != null ? Integer.valueOf(webRequest.getParameter("size")) : 55;

        final Page<Conditionnement> resultPage = conditionnementService.findPaginated(page, size);
        model.addAttribute("page", page);
        model.addAttribute("Totalpage", resultPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("conditionnements", resultPage.getContent());
        return "conditionnement/index";
    }

    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
    public String ShowAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        final Conditionnement conditionnement = conditionnementService.findOne(id);
        model.addAttribute("conditionnement", conditionnement);
        return "conditionnement/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(final ModelMap model)
    {
        final Conditionnement conditionnement = new Conditionnement();
        model.addAttribute("conditionnement", conditionnement);
        return "conditionnement/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(@Valid Conditionnement conditionnement,
            final BindingResult result, final ModelMap model,
            final RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            model.addAttribute("error", "error");
            model.addAttribute("conditionnement", conditionnement);
            return "conditionnement/new";
        }
        else
        {
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            conditionnement = conditionnementService.create(conditionnement);
            return "redirect:/conditionnement/" + conditionnement.getId() + "/show";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAction(final Conditionnement conditionnement, final ModelMap model)
    {
        conditionnementService.deleteById(conditionnement.getId());
        return "redirect:/conditionnement/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        Conditionnement conditionnement = conditionnementService.findOne(id);
        model.addAttribute("conditionnement", conditionnement);
        return "conditionnement/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateAction(final ModelMap model, @PathVariable("id") final Long id,
            @Valid final Conditionnement conditionnement, final BindingResult result,
            final RedirectAttributes redirectAttributes)
    {
        System.out.println("here we are in the controller update method");
        if (result.hasErrors())
        {
            System.out.println("erreur lors de l'update");
            model.addAttribute("error", "error");
            model.addAttribute("conditionnement", conditionnement);
            return "conditionnement/edit";
        }
        else
        {
            System.out.println("tout va bien ... ou presque! ");
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            final Conditionnement conditionnementUpdated = conditionnementService.update(conditionnement);
            return "redirect:/conditionnement/" + conditionnementUpdated.getId() + "/show";

        }
    }
}
