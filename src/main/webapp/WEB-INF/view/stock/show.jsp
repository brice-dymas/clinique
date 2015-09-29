<%--
    Document   : show
    Created on : Dec 10, 2014, 9:48:58 AM
    Author     : sando
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="body">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <h4>
                    <span class="fa fa-institution fa-lg"></span>
                    <spring:message code="stock.afficher" />
                </h4>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 col-md-offset-3" id="table_show">
                <table class="table table-condensed">
                    <tbody>
                        <tr>
                            <th><spring:message code="produit.reference" /> : </th>
                            <td>${stock.produit.reference}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="produit.nom" /> : </th>
                            <td>${stock.produit.nom}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="stock.datePeremption" /> : </th>
                            <td><fmt:formatDate value="${stock.datePeremption}" pattern="dd-MM-yyyy" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="stock.dateAlerte" /> : </th>
                            <td><fmt:formatDate value="${stock.dateAlerte}" pattern="dd-MM-yyyy" /></td>
                        </tr>
                        <tr>
                            <th><spring:message code="stock.quantiteEnStock" /> : </th>
                            <td>${stock.quantiteEnStock}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="stock.quantiteSeuille" /> : </th>
                            <td>${stock.quantiteSeuille}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="stock.conditionnement" /> : </th>
                            <td>${stock.conditionnement.nom}</td>
                        </tr>
                        <tr>
                            <th><spring:message code="stock.prixUnitaire" /> : </th>
                            <td>${stock.prixUnitaire}</td>
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <hr/>
                <spring:url value="/stock/delete" var="stock_delete"/>
                <form:form method="post" commandName="stock" action="${stock_delete}">
                    <form:hidden path="id"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <spring:url value="/stock/" var="stock_home"/>
                    <a href="${stock_home}" class="btn btn-primary  btn-sm">
                        <span class="glyphicon glyphicon-list"></span>
                        <spring:message code="stock.liste" />
                    </a>
                    <spring:url value="/stock/${stock.id}/edit" var="stock_edit"/>
                    <a href="${stock_edit}" class="btn btn-default  btn-sm">
                        <span class="glyphicon glyphicon-edit"></span>
                        <spring:message code="action.modifier" />
                    </a>
                    <button type="submit" class="btn btn-default  btn-sm">
                        <span class="glyphicon glyphicon-remove-sign"></span>
                        <spring:message code="action.effacer" />
                    </button>
                </form:form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>