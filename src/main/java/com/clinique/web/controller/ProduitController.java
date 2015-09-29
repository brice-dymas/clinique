/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.web.controller;

import com.clinique.persistence.model.Conditionnement;
import com.clinique.persistence.model.Produit;
import com.clinique.persistence.model.Stock;
import com.clinique.persistence.service.IConditionnementService;
import com.clinique.persistence.service.IProduitService;
import com.clinique.persistence.service.IStockService;
import com.clinique.web.form.ProduitForm;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/produit")
public class ProduitController
{

    @Autowired
    IConditionnementService cservice;
    @Autowired
    IProduitService produitService;

    @Autowired
    IStockService stockService;

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

        final Page<Produit> resultPage = produitService.filterByNom(nom, page, size);
        model.addAttribute("page", page);
        model.addAttribute("Totalpage", resultPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("produits", resultPage.getContent());
        return "produit/index";
    }

    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
    public String ShowAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        final Produit produit = produitService.findOne(id);
        List<Stock> stocks = stockService.filterByProduit(id);
        model.addAttribute("produit", produit);
        model.addAttribute("stocks", stocks);
        return "produit/show";
    }

    @RequestMapping(value = "/{id}/stock", method = RequestMethod.GET)
    public String stockAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        final Produit produit = produitService.findOne(id);
        List<Stock> stocks = stockService.filterByProduit(id);
        model.addAttribute("produit", produit);
        model.addAttribute("stocks", stocks);
        return "produit/stock";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(final ModelMap model)
    {
        ProduitForm produitForm = new ProduitForm();
        Produit produit = new Produit();
        produitForm.setProduit(produit);
        model.addAttribute("produitForm", produitForm);
        return "produit/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(@Valid ProduitForm produitForm,
            final BindingResult result, final ModelMap model,
            final RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            model.addAttribute("error", "error");
            model.addAttribute("produitForm", produitForm);
            return "produit/new";
        }
        else
        {
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            produitService.create(produitForm.getProduit());
            return "redirect:/produit/" + produitForm.getProduit().getId() + "/show";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAction(final Produit produit, final ModelMap model)
    {
        produitService.deleteById(produit.getId());
        return "redirect:/produit/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        Produit produit = produitService.findOne(id);
        model.addAttribute("produit", produit);
        return "produit/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateAction(final ModelMap model, @PathVariable("id") final Long id,
            @Valid final Produit produit, final BindingResult result,
            final RedirectAttributes redirectAttributes)
    {
        System.out.println("here we are in the controller update method");
        if (result.hasErrors())
        {
            System.out.println("erreur lors de l'update");
            model.addAttribute("error", "error");
            model.addAttribute("produit", produit);
            return "produit/edit";
        }
        else
        {
            System.out.println("tout va bien ... ou presque! ");
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            final Produit produitUpdated = produitService.update(produit);
            return "redirect:/produit/" + produitUpdated.getId() + "/show";
        }
    }

    @ModelAttribute("conditionnements")
    public Map<Long, String> populateconditionnementsFields()
    {
        Map<Long, String> results = new HashMap<>();
        List<Conditionnement> conditionnements = cservice.findAll();
        for (Conditionnement conditionnement : conditionnements)
        {
            results.put(conditionnement.getId(), conditionnement.getNom());
        }
        return results;
    }

    @ModelAttribute("todayDate")
    public Date getDate()
    {
        return new Date();
    }
}
