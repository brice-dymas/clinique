<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="body">

        <div class="row">
            <div class="col-md-9">

                <div>
                    <h4>
                        <span class="fa fa-institution fa-lg"></span>
                        <spring:message code="produit.liste" />
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
                                    <spring:message code="produit.reference" />
                                </span>
                            </th>
                            <th>
                                <span class="btn">
                                    <spring:message code="produit.nom" />
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
                        <c:if test="${produits.size() eq 0}">
                            <tr>
                                <td class="text-center text-warning" colspan="3">
                                    <spring:message code="empty.data" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="row">
                        <div class="col-lg-12">
                            <spring:url value="/produit/new" htmlEscape="true" var="produit_new" />
                            <a href="${produit_new}" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-new-window"></span>
                                <spring:message code="action.nouveau" />
                            </a>

                        </div>
                    </div>
                </c:if>

                <c:if test="${produits.size() ne 0}">
                    <c:forEach items="${produits}" var="produit">
                        <tr>
                            <td>
                                ${produit.id}
                            </td>
                            <td>
                                ${produit.reference}
                            </td>
                            <td>
                                ${produit.nom}
                            </td>
                            <td class="text-center">
                                <spring:url value="/produit/${produit.id}/edit" htmlEscape="true" var="produit_edit" />
                                <a href="${produit_edit}" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-edit"></span>
                                    <spring:message code="action.modifier" />
                                </a>
                                &nbsp;&nbsp;
                                <spring:url value="/produit/${produit.id}/show" htmlEscape="true" var="produit_show" />
                                <a href="${produit_show}" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-open"></span>
                                    <spring:message code="action.detail" />
                                </a>
                                &nbsp;&nbsp;
                                <spring:url value="/produit/${produit.id}/stock" htmlEscape="true" var="produit_stock" />
                                <a href="${produit_stock}" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-open"></span>
                                    <spring:message code="stock" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>

                    </table>
                    <div class="row">
                        <div class="col-lg-12">
                            <spring:url value="/produit/new" htmlEscape="true" var="produit_new" />
                            <a href="${produit_new}" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-new-window"></span>
                                <spring:message code="action.nouveau" />
                            </a>


                            <div class="pull-right">
                                <ul class="pager">

                                    <li><a href="?querynom=${querynom}&page=0&size=${size}" <c:if test="${page eq 0}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-fast-backward"></span>
                                            </a></li>
                                        <li><a href="?querynom=${querynom}&page=${page-1}&size=${size}" <c:if test="${page eq 0}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-backward"></span>
                                            </a></li>
                                        <li><input type="text" class="pager_detail text-center" readonly value="${page+1}/${Totalpage}"/></li>
                                    <li><a href="?querynom=${querynom}&page=${page+1}&size=${size}" <c:if test="${page+1 eq Totalpage}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-forward"></span>
                                            </a></li>
                                        <li><a href="?querynom=${querynom}&page=${Totalpage-1}&size=${size}" <c:if test="${page+1 eq Totalpage}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-fast-forward"></span>
                                            </a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                </c:if>
            </div>
            <div class="col-md-3">
                <div>
                    <h4>
                        <span class="fa fa-search fa-lg"></span>
                        <spring:message code="action.rechercher" />
                    </h4>
                    <hr/>
                </div>
                <spring:url value="/produit/" var="produit_home"
                            htmlEscape="true" />

                <form:form method="get" commandName="jobSeeker" action="${produit_home}">

                    <div class="form-group">
                        <label>
                            <spring:message code="produit.nom" />
                        </label>
                        <input type="text" value="${querynom}" class="form-control input-sm" name="querynom"/>
                        <input type="hidden" value="${size}" name="size"/>
                    </div>
                    <hr/>
                    <button class="btn btn-default btn-sm">
                        <span class="glyphicon glyphicon-search"></span> <spring:message code="search"/></button>
                        <spring:url value="/produit/?size=${size}" htmlEscape="true" var="produit_home" />
                    <a href="${produit_home}" class="btn btn-default btn-sm">
                        <span class="glyphicon glyphicon-refresh"></span>
                        <spring:message code="search.delete" />
                    </a>

                </form:form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>