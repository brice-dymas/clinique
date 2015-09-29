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

<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="body">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <h4>
                    <span class="fa fa-institution fa-lg"></span>
                    <spring:message code="conditionnement.afficher" />
                </h4>
                <hr/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 col-md-offset-4" id="table_show">
                <table class="table table-condensed">
                    <tbody>
                        <tr>
                            <th><spring:message code="conditionnement.nom" /> : </th>
                            <td>${conditionnement.nom}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>


        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <hr/>
                <spring:url value="/conditionnement/delete" var="conditionnement_delete"/>
                <form:form method="post" commandName="conditionnement" action="${conditionnement_delete}">
                    <form:hidden path="id"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <spring:url value="/conditionnement/" var="conditionnement_home"/>
                    <a href="${conditionnement_home}" class="btn btn-primary  btn-sm">
                        <span class="glyphicon glyphicon-list"></span>
                        <spring:message code="conditionnement.liste" />
                    </a>
                    <spring:url value="/conditionnement/${conditionnement.id}/edit" var="conditionnement_edit"/>
                    <a href="${conditionnement_edit}" class="btn btn-default  btn-sm">
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