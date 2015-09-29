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
@RequestMapping("/stock")
public class StockController
{

    @Autowired
    IStockService stockService;

    @Autowired
    IProduitService produitService;

    @Autowired
    IConditionnementService cservice;

    /**
     *
     * @param model
     * @param webRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String indexAction(final ModelMap model, final WebRequest webRequest)
    {
        final long idProduit = webRequest.getParameter("queryproduit") != null
                && !webRequest.getParameter("queryproduit").equals("")
                        ? Long.valueOf(webRequest.getParameter("queryproduit")) : -1;
        final Integer page = webRequest.getParameter("page") != null ? Integer.valueOf(webRequest.getParameter("page")) : 0;
        final Integer size = webRequest.getParameter("size") != null ? Integer.valueOf(webRequest.getParameter("size")) : 55;

        final Page<Stock> resultPage = stockService.findPaginated(page, size);
        model.addAttribute("page", page);
        model.addAttribute("Totalpage", resultPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("stocks", resultPage.getContent());
        return "stock/index";
    }

    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
    public String ShowAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        final Stock stock = stockService.findOne(id);
        model.addAttribute("stock", stock);
        return "stock/show";
    }

    @RequestMapping(value = "/{id}/new", method = RequestMethod.GET)
    public String newAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        Produit p = produitService.findOne(id);
        final Stock stock = new Stock();
        stock.setProduit(p);
        model.addAttribute("stock", stock);
        return "stock/new";
    }

    @RequestMapping(value = "/{id}/create", method = RequestMethod.POST)
    public String createAction(@PathVariable("id") final Long id, @Valid Stock stock,
            final BindingResult result, final ModelMap model,
            final RedirectAttributes redirectAttributes)
    {
        Produit p = produitService.findOne(id);
        stock.setProduit(p);
        if (result.hasErrors())
        {
            model.addAttribute("error", "error");
            model.addAttribute("stock", stock);
            return "stock/new";
        }
        else
        {
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            stock = stockService.create(stock);
            return "redirect:/stock/" + stock.getId() + "/show";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAction(final Stock stock, final ModelMap model)
    {
        stockService.deleteById(stock.getId());
        return "redirect:/stock/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        Stock stock = stockService.findOne(id);
        model.addAttribute("stock", stock);
        return "stock/edit";
    }

    @RequestMapping(value = "/{id}/{ip}/update", method = RequestMethod.POST)
    public String updateAction(final ModelMap model, @PathVariable("id") final Long id,
            @PathVariable("ip") final Long idProduit, @Valid final Stock stock,
            final BindingResult result, final RedirectAttributes redirectAttributes)
    {
        stock.setProduit(produitService.findOne(idProduit));
        System.out.println("here we are in the controller update method");
        if (result.hasErrors())
        {
            System.out.println("erreur lors de l'update");
            model.addAttribute("error", "error");
            model.addAttribute("stock", stock);
            return "stock/edit";
        }
        else
        {
            System.out.println("tout va bien ... ou presque! ");
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            final Stock stockUpdated = stockService.update(stock);
            return "redirect:/stock/" + stockUpdated.getId() + "/show";

        }
    }

    @ModelAttribute("todayDate")
    public Date getDate()
    {
        return new Date();
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
}
