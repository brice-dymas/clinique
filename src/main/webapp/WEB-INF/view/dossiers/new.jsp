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
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="body">
        <div class="row">
            <div class="col-md-12">
                <h4>
                    <spring:message code="malade.nouveau" />
                </h4>
                <hr/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 col-md-offset-0">
                <spring:url value="/dossiers/create" var="malade_create"
                            htmlEscape="true" />
                <form:form method="post" commandName="malade" action="${malade_create}">
                    <div class="panel panel-default">
                        <div class="panel-body">

                            <fieldset class="form-group divider">
                                <legend class="text-info text-center btn-primary">
                                    <spring:message code="malade.title" />
                                </legend>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <form:label for="nom" path="">
                                                <spring:message code="malade.nom" /> :
                                            </form:label>
                                            <form:input id="nom" path="nom" cssClass="form-control"/>
                                            <form:errors path="nom" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <form:label for="prenom" path="">
                                                <spring:message code="malade.prenom" /> :
                                            </form:label>
                                            <form:input id="prenom" path="prenom" cssClass="form-control"/>
                                            <form:errors path="prenom" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <form:label for="profession" path="">
                                                <spring:message code="malade.profession" /> :
                                            </form:label>
                                            <form:input id="profession" path="profession" cssClass="form-control"/>
                                            <form:errors path="profession" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <form:label for="telephone" path="">
                                                <spring:message code="malade.telephone" /> :
                                            </form:label>
                                            <form:input id="telephone" path="telephone" cssClass="form-control"/>
                                            <form:errors path="telephone" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <form:label for="date" path="">
                                                <spring:message code="malade.dateDeNaissance" /> :
                                            </form:label>
                                            <form:input id="date" path="dateDeNaissance" cssClass="form-control"/>
                                            <form:errors path="dateDeNaissance" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <form:label for="lieu" path="">
                                                <spring:message code="malade.lieuDeNaissance" /> :
                                            </form:label>
                                            <form:input id="lieu" path="lieuDeNaissance" cssClass="form-control"/>
                                            <form:errors path="lieuDeNaissance" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <form:label for="ethnie" path="">
                                                <spring:message code="malade.ethnie" /> :
                                            </form:label>
                                            <form:input id="ethnie" path="ethnie" cssClass="form-control"/>
                                            <form:errors path="ethnie" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <form:label for="lieuDeResidence" path="">
                                                <spring:message code="malade.lieuDeResidence" /> :
                                            </form:label>
                                            <form:input id="lieuDeResidence" path="lieuDeResidence" cssClass="form-control"/>
                                            <form:errors path="lieuDeResidence" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="panel-footer">
                            <button type="submit" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-save"></span>
                                <spring:message code="action.enregistrer" />
                            </button>
                            <spring:url value="/dossiers/" htmlEscape="true"
                                        var="malade_home" />
                            <a href="${malade_home}" class="btn btn-default btn-sm">
                                <span class="glyphicon glyphicon-list"></span>
                                <spring:message code="malade.liste" />
                            </a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script type="text/javascript">
            $(function () {
                $("#date").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "dd/mm/yy",
                    showButtonPanel: false
                }).datepicker("option", "showAnim", "clip");
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>