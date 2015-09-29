<%--
    Document   : new
    Created on : Dec 10, 2014, 9:20:13 AM
    Author     : sando
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="body">

        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <h4>
                    <span class="fa fa-institution fa-lg"></span>
                    <spring:message code="stock.nouveau" /> ${stock.produit.nom}
                </h4>
                <hr/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-9 col-md-offset-3">
                <spring:url value="/stock/${stock.produit.id}/create" var="stock_create"
                            htmlEscape="true" />
                <form:form method="post" commandName="stock" action="${stock_create}">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <form:select path="conditionnement.id" cssClass="form-control" >
                                            <form:options items="${conditionnements}" />
                                        </form:select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <form:label for="prix" path="">
                                            <spring:message code="stock.prixUnitaire" /> :
                                        </form:label>
                                        <form:input id="prix" path="prixUnitaire" cssClass="form-control"/>
                                        <form:errors path="prixUnitaire" cssClass="label label-danger"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <form:label for="stock" path="">
                                            <spring:message code="stock.quantiteEnStock" /> :
                                        </form:label>
                                        <form:input id="stock" path="quantiteEnStock" cssClass="form-control"/>
                                        <form:errors path="quantiteEnStock" cssClass="label label-danger"/>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <form:label for="seuil" path="">
                                            <spring:message code="stock.quantiteSeuille" /> :
                                        </form:label>
                                        <form:input id="seuil" path="quantiteSeuille" cssClass="form-control"/>
                                        <form:errors path="quantiteSeuille" cssClass="label label-danger"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <form:label for="datePeremption" path="">
                                            <spring:message code="stock.datePeremption" /> :
                                        </form:label>
                                        <form:input id="datePeremption" path="datePeremption" cssClass="form-control"/>
                                        <form:errors path="datePeremption" cssClass="label label-danger"/>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <form:label for="dateAlerte" path="">
                                            <spring:message code="stock.dateAlerte" /> :
                                        </form:label>
                                        <form:input id="dateAlerte" path="dateAlerte" cssClass="form-control"/>
                                        <form:errors path="dateAlerte" cssClass="label label-danger"/>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="panel-footer">
                                <button type="submit" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-save"></span>
                                    <spring:message code="action.enregistrer" />
                                </button>
                                <spring:url value="/stock/" htmlEscape="true"
                                            var="stock_home" />
                                <a href="${stock_home}" class="btn btn-default btn-sm">
                                    <span class="glyphicon glyphicon-list"></span>
                                    <spring:message code="stock.liste" />
                                </a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap-filestyle.js" />"></script>
        <script type="text/javascript">

            $("#dateAlerte, #datePeremption").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "dd/mm/yy",
                showButtonPanel: false
            });

        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>