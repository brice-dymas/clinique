<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="body">

        <div class="row">
            <div class="col-md-9">

                <div>
                    <h4>
                        <span class="fa fa-institution fa-lg"></span>
                        <spring:message code="stock.liste" />
                    </h4>
                    <hr/>
                </div>
                <div class="dropdown pull-right ">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                        <spring:message code="search.taille" />
                        : ${size}&nbsp;
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?&query=${query}&size=2">2</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?&query=${query}&size=5">5</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?&query=${query}&size=10">10</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?&query=${query}&size=20">20</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?&query=${query}&size=30">30</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?&query=${query}&size=40">40</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?&query=${query}&size=50">50</a></li>
                    </ul>
                </div>
                <table class="table table-condensed table-hover table-bordered">
                    <thead class="bg-primary" >
                        <tr>
                            <th>
                                #
                            </th>
                            <th>
                                <span class="btn">
                                    <spring:message code="stock.produit" />
                                </span>
                            </th>
                            <th>
                                <span class="btn">
                                    <spring:message code="stock.conditionnement" />
                                </span>
                            </th>
                            <th>
                                <span class="btn">
                                    <spring:message code="stock.quantiteEnStock" />
                                </span>
                            </th>
                            <th>
                                <span class="btn">
                                    <spring:message code="stock.datePeremption" />
                                </span>
                            </th>
                            <th>
                                <span class="btn">
                                    <spring:message code="action.titre" />
                                </span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${stocks.size() eq 0}">
                            <tr>
                                <td class="text-center text-warning" colspan="3">
                                    <spring:message code="empty.data" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="row">
                        <div class="col-lg-12">

                            <spring:url value="/stock/new" htmlEscape="true" var="stock_new" />
                            <a href="${stock_new}" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-new-window"></span>
                                <spring:message code="action.nouveau" />
                            </a>

                        </div>
                    </div>
                </c:if>

                <c:if test="${stocks.size() ne 0}">
                    <c:forEach items="${stocks}" var="stock">
                        <tr>
                            <td>
                                ${stock.id}
                            </td>
                            <td>
                                ${stock.produit.nom}
                            </td>
                            <td>
                                ${stock.conditionnement.nom}
                            </td>
                            <td>
                                ${stock.quantiteEnStock}
                            </td>
                            </td>
                            <td>
                                <fmt:formatDate value="${stock.datePeremption}" pattern="dd/MM/yyyy" />
                            </td>
                            <td class="text-center">
                                <spring:url value="/stock/${stock.id}/edit" htmlEscape="true" var="stock_edit" />
                                <a href="${stock_edit}" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-edit"></span>
                                    <spring:message code="action.modifier" />
                                </a>
                                &nbsp;&nbsp;
                                <spring:url value="/stock/${stock.id}/show" htmlEscape="true" var="stock_show" />
                                <a href="${stock_show}" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-open"></span>
                                    <spring:message code="action.detail" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>

                    </table>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="pull-right">
                                <ul class="pager">

                                    <li><a href="?query=${query}&page=0&size=${size}" <c:if test="${page eq 0}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-fast-backward"></span>
                                            </a></li>
                                        <li><a href="?query=${query}&page=${page-1}&size=${size}" <c:if test="${page eq 0}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-backward"></span>
                                            </a></li>
                                        <li><input type="text" class="pager_detail text-center" readonly value="${page+1}/${Totalpage}"/></li>
                                    <li><a href="?query=${query}&page=${page+1}&size=${size}" <c:if test="${page+1 eq Totalpage}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-forward"></span>
                                            </a></li>
                                        <li><a href="?query=${query}&page=${Totalpage-1}&size=${size}" <c:if test="${page+1 eq Totalpage}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-fast-forward"></span>
                                            </a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                </c:if>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>