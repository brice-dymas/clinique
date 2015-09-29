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
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="body">
        <div class="row">
            <div class="col-md-12">
                <h3>
                    <spring:message code="malade.afficher" />
                </h3>
                <hr/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 col-md-offset-4" id="table_show">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <th>
                                <spring:message code="malade.numero" />
                            </th>
                            <td>${malade.numero}</td>
                        </tr>
                        <tr>
                            <th>
                                <spring:message code="malade.nom" />
                            </th>
                            <td>${malade.nom}</td>
                        </tr>
                        <tr>
                            <th>
                                <spring:message code="malade.prenom" />
                            </th>
                            <td>${malade.prenom}</td>
                        </tr>
                        <tr>
                            <th>
                                <spring:message code="malade.profession" />
                            </th>
                            <td>${malade.profession}</td>
                        </tr>
                        <tr>
                            <th>
                                <spring:message code="malade.ethnie" />
                            </th>
                            <td>${malade.ethnie}</td>
                        </tr>
                        <tr>
                            <th>
                                <spring:message code="malade.dateDeNaissance" />
                            </th>
                            <td>${malade.dateDeNaissance}</td>
                        </tr>
                        <tr>
                            <th>
                                <spring:message code="malade.lieuDeNaissance" />
                            </th>
                            <td>${malade.lieuDeNaissance}</td>
                        </tr>
                        <tr>
                            <th>
                                <spring:message code="malade.lieuDeResidence" />
                            </th>
                            <td>${malade.lieuDeResidence}</td>
                        </tr>
                        <tr>
                            <th>
                                <spring:message code="malade.telephone" />
                            </th>
                            <td>${malade.telephone}</td>
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>


        <div class="row">
            <div class="col-md-6 col-md-offset-4">
                <hr/>
                <spring:url value="/dossiers/delete" var="malade_delete"/>
                <form:form method="post" commandName="malade" action="${malade_delete}">

                    <spring:url value="/dossiers/" var="malade_home"/>
                    <a href="${malade_home}" class="btn btn-primary  btn-primary">
                        <span class="glyphicon glyphicon-list"></span>
                        <spring:message code="malade.liste" />
                    </a>
                    <sec:authorize access="hasRole('ROLE_ADMIN')" >
                        <form:hidden path="id"/>
                        <spring:url value="/dossiers/${malade.id}/edit" var="malade_edit"/>
                        <a href="${malade_edit}" class="btn btn-default  btn-warning">
                            <span class="glyphicon glyphicon-edit"></span>
                            <spring:message code="action.modifier" />
                        </a>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-default  btn-danger">
                            <span class="glyphicon glyphicon-remove-sign"></span>
                            <spring:message code="action.effacer" />
                        </button>
                    </sec:authorize>
                </form:form>
            </div>
        </div>


    </tiles:putAttribute>
</tiles:insertDefinition>