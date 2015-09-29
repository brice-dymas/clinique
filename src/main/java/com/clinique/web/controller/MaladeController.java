/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.web.controller;

import com.clinique.persistence.model.Malade;
import com.clinique.persistence.service.IMaladeService;
import com.clinique.persistence.service.IRoleService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/dossiers")
public class MaladeController
{

    @Autowired
    IMaladeService maladeService;

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
        Date dateNaissance;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        final String nom = webRequest.getParameter("querynom") != null ? webRequest.getParameter("querynom") : "";
        final String prenom = webRequest.getParameter("queryprenom") != null ? webRequest.getParameter("queryprenom") : "";
        final String lieuNaissance = webRequest.getParameter("querylieu") != null ? webRequest.getParameter("querylieu") : "";
        final String dateNaissanceString = webRequest.getParameter("querydate") != null ? webRequest.getParameter("querydate") : "";
        final Integer page = webRequest.getParameter("page") != null ? Integer.valueOf(webRequest.getParameter("page")) : 0;
        final Integer size = webRequest.getParameter("size") != null ? Integer.valueOf(webRequest.getParameter("size")) : 55;
//"dd/MM/yyyy"
        dateNaissance = parsedDateFrom(dateNaissanceString, "01/01/1910", dateFormatter);
        final Page<Malade> resultPage = maladeService.search(nom, prenom, dateNaissance, lieuNaissance, page, size);
        model.addAttribute("page", page);
        model.addAttribute("Totalpage", resultPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("malades", resultPage.getContent());
        return "dossiers/index";
    }

    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
    public String ShowAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        final Malade malade = maladeService.findOne(id);
        model.addAttribute("malade", malade);
        return "dossiers/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(final ModelMap model)
    {
        final Malade malade = new Malade();
        model.addAttribute("malade", malade);
        return "dossiers/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(@Valid Malade malade,
            final BindingResult result, final ModelMap model,
            final RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            model.addAttribute("error", "error");
            model.addAttribute("malade", malade);
            return "dossiers/new";
        }
        else
        {
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            malade = maladeService.create(malade);
            return "redirect:/dossiers/" + malade.getId() + "/show";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAction(final Malade malade, final ModelMap model)
    {
        maladeService.deleteById(malade.getId());
        return "redirect:/dossiers/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        Malade malade = maladeService.findOne(id);
        model.addAttribute("malade", malade);
        return "dossiers/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateAction(final ModelMap model, @PathVariable("id") final Long id,
            @Valid final Malade malade, final BindingResult result,
            final RedirectAttributes redirectAttributes)
    {
        System.out.println("here we are in the controller update method");
        if (result.hasErrors())
        {
            System.out.println("erreur lors de l'update");
            model.addAttribute("error", "error");
            model.addAttribute("malade", malade);
            return "dossiers/edit";
        }
        else
        {
            System.out.println("tout va bien ... ou presque! ");
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            final Malade maladeUpdated = maladeService.update(malade);
            return "redirect:/dossiers/" + maladeUpdated.getId() + "/show";

        }
    }

    private Date parsedDateFrom(String dateString, String dateLimite, SimpleDateFormat dateFormat)
    {
        Date result = new Date();
        SimpleDateFormat dateFormatter = dateFormat;
        try
        {
            result = dateFormatter.parse(dateString);
        }
        catch (ParseException ex)
        {
            try
            {
                result = dateFormatter.parse(dateLimite);
            }
            catch (ParseException ex1)
            {
                Logger.getLogger(MaladeController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return result;
    }
}
