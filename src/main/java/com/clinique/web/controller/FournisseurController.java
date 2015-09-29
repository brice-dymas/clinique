/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.web.controller;

import com.clinique.persistence.model.Fournisseur;
import com.clinique.persistence.service.IFournisseurService;
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
@RequestMapping("/fournisseur")
public class FournisseurController
{

    @Autowired
    IFournisseurService fournisseurService;

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

        final Page<Fournisseur> resultPage = fournisseurService.filterByNom(nom, page, size);
        model.addAttribute("page", page);
        model.addAttribute("querynom", nom);
        model.addAttribute("Totalpage", resultPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("fournisseurs", resultPage.getContent());
        return "fournisseur/index";
    }

    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
    public String ShowAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        final Fournisseur fournisseur = fournisseurService.findOne(id);
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(final ModelMap model)
    {
        final Fournisseur fournisseur = new Fournisseur();
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(@Valid Fournisseur fournisseur,
            final BindingResult result, final ModelMap model,
            final RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            model.addAttribute("error", "error");
            model.addAttribute("fournisseur", fournisseur);
            return "fournisseur/new";
        }
        else
        {
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            fournisseur = fournisseurService.create(fournisseur);
            return "redirect:/fournisseur/" + fournisseur.getId() + "/show";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAction(final Fournisseur fournisseur, final ModelMap model)
    {
        fournisseurService.deleteById(fournisseur.getId());
        return "redirect:/fournisseur/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        Fournisseur fournisseur = fournisseurService.findOne(id);
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateAction(final ModelMap model, @PathVariable("id") final Long id,
            @Valid final Fournisseur fournisseur, final BindingResult result,
            final RedirectAttributes redirectAttributes)
    {
        System.out.println("here we are in the controller update method");
        if (result.hasErrors())
        {
            System.out.println("erreur lors de l'update");
            model.addAttribute("error", "error");
            model.addAttribute("fournisseur", fournisseur);
            return "fournisseur/edit";
        }
        else
        {
            System.out.println("tout va bien ... ou presque! ");
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            final Fournisseur fournisseurUpdated = fournisseurService.update(fournisseur);
            return "redirect:/fournisseur/" + fournisseurUpdated.getId() + "/show";

        }
    }
}
