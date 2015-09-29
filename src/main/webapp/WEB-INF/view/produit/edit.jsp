<%--
    Document   : edit
    Created on : Dec 10, 2014, 9:44:15 AM
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
                    <spring:message code="produit.modifier" />
                    : ${produit.nom}
                </h4>
                <hr/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <spring:url value="/produit/${produit.id}/update" var="produit_update" htmlEscape="true"/>
                <form:form method="post" commandName="produit" action="${produit_update}">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-group">
                                <form:label for="reference" path="">
                                    <spring:message code="produit.reference" /> :
                                </form:label>
                                <form:input id="reference" path="reference" cssClass="form-control"/>
                                <form:errors path="reference" cssClass="label label-danger"/>
                            </div>
                            <div class="form-group">
                                <form:label for="nom" path="">
                                    <spring:message code="produit.nom" /> :
                                </form:label>
                                <form:input id="nom" path="nom" cssClass="form-control"/>
                                <form:errors path="nom" cssClass="label label-danger"/>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="panel-footer">
                            <button type="submit" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-save"></span>
                                <spring:message code="action.enregistrer" />
                            </button>
                            <spring:url value="/produit/" htmlEscape="true"
                                        var="produit_home" />
                            <a href="${produit_home}" class="btn btn-default btn-sm">
                                <span class="glyphicon glyphicon-list"></span>
                                <spring:message code="produit.liste" />
                            </a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>