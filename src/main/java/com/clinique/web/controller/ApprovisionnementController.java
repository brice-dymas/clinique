/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.web.controller;

import com.clinique.persistence.model.Approvisionnement;
import com.clinique.persistence.service.IApprovisionnementService;
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
@RequestMapping("/approvisionnement")
public class ApprovisionnementController
{

    @Autowired
    IApprovisionnementService approvisionnementService;

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
        final Integer page = webRequest.getParameter("page") != null ? Integer.valueOf(webRequest.getParameter("page")) : 0;
        final Integer size = webRequest.getParameter("size") != null ? Integer.valueOf(webRequest.getParameter("size")) : 55;

        final Page<Approvisionnement> resultPage = approvisionnementService.findPaginated(page, size);
        model.addAttribute("page", page);
        model.addAttribute("Totalpage", resultPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("approvisionnements", resultPage.getContent());
        return "approvisionnement/index";
    }

    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
    public String ShowAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        final Approvisionnement approvisionnement = approvisionnementService.findOne(id);
        model.addAttribute("approvisionnement", approvisionnement);
        return "approvisionnement/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(final ModelMap model)
    {
        final Approvisionnement approvisionnement = new Approvisionnement();
        model.addAttribute("approvisionnement", approvisionnement);
        return "approvisionnement/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(@Valid Approvisionnement approvisionnement,
            final BindingResult result, final ModelMap model,
            final RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            model.addAttribute("error", "error");
            model.addAttribute("approvisionnement", approvisionnement);
            return "approvisionnement/new";
        }
        else
        {
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            approvisionnement = approvisionnementService.create(approvisionnement);
            return "redirect:/approvisionnement/" + approvisionnement.getId() + "/show";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAction(final Approvisionnement approvisionnement, final ModelMap model)
    {
        approvisionnementService.deleteById(approvisionnement.getId());
        return "redirect:/approvisionnement/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        Approvisionnement approvisionnement = approvisionnementService.findOne(id);
        model.addAttribute("approvisionnement", approvisionnement);
        return "approvisionnement/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateAction(final ModelMap model, @PathVariable("id") final Long id,
            @Valid final Approvisionnement approvisionnement, final BindingResult result,
            final RedirectAttributes redirectAttributes)
    {
        System.out.println("here we are in the controller update method");
        if (result.hasErrors())
        {
            System.out.println("erreur lors de l'update");
            model.addAttribute("error", "error");
            model.addAttribute("approvisionnement", approvisionnement);
            return "approvisionnement/edit";
        }
        else
        {
            System.out.println("tout va bien ... ou presque! ");
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            final Approvisionnement approvisionnementUpdated = approvisionnementService.update(approvisionnement);
            return "redirect:/approvisionnement/" + approvisionnementUpdated.getId() + "/show";

        }
    }
}
