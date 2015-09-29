/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinique.web.controller;

import com.clinique.persistence.model.Client;
import com.clinique.persistence.service.IClientService;
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
@RequestMapping("/client")
public class ClientController
{

    @Autowired
    IClientService clientService;

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

        final Page<Client> resultPage = clientService.filterByNom(nom, page, size);
        model.addAttribute("page", page);
        model.addAttribute("querynom", nom);
        model.addAttribute("Totalpage", resultPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("clients", resultPage.getContent());
        return "client/index";
    }

    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
    public String ShowAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        final Client client = clientService.findOne(id);
        model.addAttribute("client", client);
        return "client/show";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newAction(final ModelMap model)
    {
        final Client client = new Client();
        model.addAttribute("client", client);
        return "client/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(@Valid Client client,
            final BindingResult result, final ModelMap model,
            final RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            model.addAttribute("error", "error");
            model.addAttribute("client", client);
            return "client/new";
        }
        else
        {
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            client = clientService.create(client);
            return "redirect:/client/" + client.getId() + "/show";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAction(final Client client, final ModelMap model)
    {
        clientService.deleteById(client.getId());
        return "redirect:/client/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editAction(@PathVariable("id") final Long id, final ModelMap model)
    {
        Client client = clientService.findOne(id);
        model.addAttribute("client", client);
        return "client/edit";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateAction(final ModelMap model, @PathVariable("id") final Long id,
            @Valid final Client client, final BindingResult result,
            final RedirectAttributes redirectAttributes)
    {
        System.out.println("here we are in the controller update method");
        if (result.hasErrors())
        {
            System.out.println("erreur lors de l'update");
            model.addAttribute("error", "error");
            model.addAttribute("client", client);
            return "client/edit";
        }
        else
        {
            System.out.println("tout va bien ... ou presque! ");
            redirectAttributes.addFlashAttribute("info", "alert.success.new");
            final Client clientUpdated = clientService.update(client);
            return "redirect:/client/" + clientUpdated.getId() + "/show";

        }
    }
}
