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
                    <spring:message code="produit.nouveau" />
                </h4>
                <hr/>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 col-md-offset-0">
                <spring:url value="/produit/create" var="produit_create"
                            htmlEscape="true" />
                <form:form method="post" commandName="produitForm" action="${produit_create}">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <form:label for="reference" path="">
                                            <spring:message code="produit.reference" /> :
                                        </form:label>
                                        <form:input id="reference" path="produit.reference" cssClass="form-control"/>
                                        <form:errors path="produit.reference" cssClass="label label-danger"/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <form:label for="nom" path="">
                                            <spring:message code="produit.nom" /> :
                                        </form:label>
                                        <form:input id="nom" path="produit.nom" cssClass="form-control"/>
                                        <form:errors path="produit.nom" cssClass="label label-danger"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <fieldset>
                                        <legend ><spring:message code="produit.stock" />  </legend>

                                        <div id="stock">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr class="btn-primary">
                                                        <th><spring:message code="stock.conditionnement" /></th>
                                                        <th><spring:message code="stock.datePeremption" /></th>
                                                        <th><spring:message code="stock.dateAlerte" /></th>
                                                        <th><spring:message code="stock.quantiteEnStock" /></th>
                                                        <th><spring:message code="stock.quantiteSeuille" /></th>
                                                        <th><spring:message code="stock.prixUnitaire" /></th>
                                                        <th><spring:message code="action.effacer" /></th>
                                                    </tr>
                                                </thead>
                                                <tbody data-size="${produitForm.stocks.size()}">

                                                    <c:if test="${0 le produitForm.stocks.size()}">

                                                        <c:forEach items="${produitForm.stocks}" varStatus="status" begin="0">

                                                            <tr class="list-stock">
                                                                <td>
                                                                    <spring:bind path="stocks[${status.index}].conditionnement.id">
                                                                        <form:select path="${status.expression}" cssClass="form-control input-sm" >
                                                                            <form:options items="${conditionnements}" />
                                                                        </form:select>
                                                                        <form:errors path="${status.expression}" cssClass="text-danger"/>
                                                                    </spring:bind>
                                                                </td>
                                                                <td>
                                                                    <spring:bind path="stocks[${status.index}].datePeremption">
                                                                        <form:input path="${status.expression}" cssClass="form-control input-sm" />
                                                                        <form:errors path="${status.expression}" cssClass="text-danger"/>
                                                                    </spring:bind>
                                                                </td>
                                                                <td>
                                                                    <spring:bind path="stocks[${status.index}].dateAlerte">
                                                                        <form:input path="${status.expression}" cssClass="form-control input-sm" />
                                                                        <form:errors path="${status.expression}" cssClass="text-danger"/>
                                                                    </spring:bind>
                                                                </td>
                                                                <td>
                                                                    <spring:bind path="stocks[${status.index}].quantiteEnStock">
                                                                        <form:input path="${status.expression}" cssClass="form-control input-sm" />
                                                                        <form:errors path="${status.expression}" cssClass="text-danger"/>
                                                                    </spring:bind>
                                                                </td>
                                                                <td>
                                                                    <spring:bind path="stocks[${status.index}].quantiteSeuille">
                                                                        <form:input path="${status.expression}" cssClass="form-control input-sm" />
                                                                        <form:errors path="${status.expression}" cssClass="text-danger"/>
                                                                    </spring:bind>
                                                                </td>
                                                                <td>
                                                                    <spring:bind path="stocks[${status.index}].prixUnitaire">
                                                                        <form:input path="${status.expression}" cssClass="form-control input-sm" />
                                                                        <form:errors path="${status.expression}" cssClass="text-danger"/>
                                                                    </spring:bind>
                                                                </td>
                                                                <td class="row-align">

                                                                    <button type="button" id="removeStockButton" class="btn btn-sm btn-default remove-stock" >
                                                                        <span class="glyphicon glyphicon-minus-sign"></span>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>

                                                    <c:if test="${0 eq produitForm.stocks.size()}">

                                                        <tr class="list-stock">
                                                            <td>
                                                                <form:select path="stocks[0].conditionnement.id" cssClass="form-control input-sm" >
                                                                    <form:options items="${conditionnements}" />
                                                                </form:select>
                                                            </td>
                                                            <td>
                                                                <form:input  path="stocks[0].datePeremption" cssClass="form-control input-sm" />
                                                            </td>
                                                            <td>
                                                                <form:input  path="stocks[0].dateAlerte" cssClass="form-control input-sm" />
                                                            </td>
                                                            <td>
                                                                <form:input  path="stocks[0].quantiteEnStock" cssClass="form-control input-sm" />
                                                            </td>
                                                            <td>
                                                                <form:input  path="stocks[0].quantiteSeuille" cssClass="form-control input-sm" />
                                                            </td>
                                                            <td>
                                                                <form:input path="stocks[0].prixUnitaire" cssClass="form-control input-sm" />
                                                            </td>
                                                            <td class="row-align">
                                                                <button type="button" id="removestockButton"  class="btn btn-sm btn-default remove-stock" >
                                                                    <span class="glyphicon glyphicon-minus-sign"></span>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </tbody>
                                            </table>

                                            <button type="button" id="addstockButton" class="btn btn-sm btn-default add-stock">
                                                <span class="glyphicon glyphicon-plus-sign"></span>
                                                <spring:message code="action.ajouter" />
                                            </button>
                                        </div>
                                    </fieldset>
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
        </div>

        <script src="<c:url value="/resources/js/jquery.dynamiclist.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap-filestyle.js" />"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                function setOnCautionDatePicker() {
                    var i = 0;
                    $(".list-stock").each(function () {
                        $(this).on("focus", "#stocks" + i + "\\.datePeremption", function () {
                            $(this).datepicker({
                                changeMonth: true,
                                changeYear: true,
                                dateFormat: "dd/mm/yy",
                                showButtonPanel: false
                            });
                            return false;
                        });

                        $(".list-stock").on("focus", "#stocks" + i + "\\.dateAlerte", function () {
                            $(this).datepicker({
                                changeMonth: true,
                                changeYear: true,
                                dateFormat: "dd/mm/yy",
                                showButtonPanel: false
                            });
                            return false;
                        });
                        i++;
                    });


                }

                function setCautionDatePicker(item) {
                    $(item).on("focus", "input[name$='datePeremption']", function () {
                        $(this).removeClass("hasDatepicker").datepicker({
                            changeMonth: true,
                            changeYear: true,
                            dateFormat: "dd/mm/yy",
                            showButtonPanel: false
                        });
                        return false;
                    });

                    $(item).on("focus", "input[name$='dateAlerte']", function () {
                        $(this).removeClass("hasDatepicker").datepicker({
                            changeMonth: true,
                            changeYear: true,
                            dateFormat: "dd/mm/yy",
                            showButtonPanel: false
                        });
                        return false;
                    });

                }

                $("#stock").dynamiclist({
                    itemClass: "list-stock",
                    addClass: "add-stock",
                    removeClass: "remove-stock",
                    withEvents: true,
                    addCallbackFn: setCautionDatePicker

                });

                $("#dateDepot").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "dd/mm/yy",
                    showButtonPanel: false
                });

                setOnCautionDatePicker();

            });

        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>