<%--
    Document   : index
    Created on : 29 janv. 2015, 19:59:50
    Author     : fabrice
--%>

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
                        <span class="fa fa-user fa-lg"></span>
                        <spring:message code="malade.liste" />
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
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&size=2">2</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&size=5">5</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&size=10">10</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&size=20">20</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&size=30">30</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&size=40">40</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&size=50">50</a></li>
                    </ul>
                </div>
                <table class="table table-condensed table-hover table-bordered">
                    <thead class="bg-primary" >
                        <tr>
                            <th>
                                <span class="btn">
                                    <spring:message code="malade.numero" />
                                </span>
                            </th>
                            <th>
                                <span class="btn">
                                    <spring:message code="malade.nom" />
                                </span>
                            </th>

                            <th>
                                <span class="btn">
                                    <spring:message code="malade.prenom" />
                                </span>
                            </th>
                            <th>
                                <span class="btn">
                                    <spring:message code="malade.lieuDeResidence" />
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
                        <c:if test="${malades.size() eq 0}">
                            <tr>
                                <td class="text-center text-warning" colspan="4">
                                    <spring:message code="empty.data" />
                                </td>
                            </tr>
                        </tbody>

                    </table>

                    <div class="row">
                        <div class="col-lg-12">
                            <hr/>
                            <spring:url value="/dossiers/new" htmlEscape="true" var="malade_new" />
                            <a href="${malade_new}" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-new-window"></span>
                                <spring:message code="action.nouveau" />
                            </a>
                            &nbsp;&nbsp;
                            <spring:url value="/dossiers/${malade.id}/show" htmlEscape="true" var="malade_show" />
                            <a href="${malade_show}" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-open"></span>
                                <spring:message code="action.detail" />
                            </a>

                            <div class="pull-right">
                                <ul class="pager">

                                    <li><a href="?query=${query}&page=0&size=${size}" class ="btn btn-sm disabled">
                                            <span class="glyphicon glyphicon-fast-backward"></span>
                                        </a></li>
                                    <li><a href="?query=${query}&page=${page-1}&size=${size}"class ="btn btn-sm disabled">
                                            <span class="glyphicon glyphicon-backward"></span>
                                        </a></li>
                                    <li><input type="text" class="pager_detail text-center" readonly value="0/0"/></li>
                                    <li><a href="?query=${query}&page=${page+1}&size=${size}" class ="btn btn-sm disabled">
                                            <span class="glyphicon glyphicon-forward"></span>
                                        </a></li>
                                    <li><a href="?query=${query}&page=${Totalpage-1}&size=${size}" class ="btn btn-sm disabled">
                                            <span class="glyphicon glyphicon-fast-forward"></span>
                                        </a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${malades.size() ne 0}">
                    <c:forEach items="${malades}" var="malade">
                        <tr>
                            <td>
                                ${malade.numero}
                            </td>
                            <td>
                                ${malade.nom}
                            </td>
                            <td>
                                ${malade.prenom}
                            </td>
                            <td>
                                ${malade.lieuDeResidence}
                            </td>
                            <td class="text-center">
                                <spring:url value="/dossiers/${malade.id}/edit" htmlEscape="true" var="malade_edit" />
                                <a href="${malade_edit}" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-edit"></span>
                                    <spring:message code="action.modifier" />
                                </a>
                                &nbsp;&nbsp;
                                <spring:url value="/dossiers/${malade.id}/show" htmlEscape="true" var="malade_show" />
                                <a href="${malade_show}" class="btn btn-primary btn-sm">
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
                            <hr/>
                            <spring:url value="/dossiers/new" htmlEscape="true" var="malade_new" />
                            <a href="${malade_new}" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-new-window"></span>
                                <spring:message code="action.nouveau" />
                            </a>
                            &nbsp;&nbsp;
                            <div class="pull-right">
                                <ul class="pager">

                                    <li><a href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&page=0&size=${size}" <c:if test="${page eq 0}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-fast-backward"></span>
                                            </a></li>
                                        <li><a href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&page=${page-1}&size=${size}" <c:if test="${page eq 0}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-backward"></span>
                                            </a></li>
                                        <li><input type="text" class="pager_detail text-center" readonly value="${page+1}/${Totalpage}"/></li>
                                    <li><a href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&page=${page+1}&size=${size}" <c:if test="${page+1 eq Totalpage}">class ="btn btn-sm disabled"</c:if>>
                                                <span class="glyphicon glyphicon-forward"></span>
                                            </a></li>
                                        <li><a href="?queryprenom=${queryprenom}&querynom=${querynom}&querylieu=${querylieu}&querydate=${querydate}&page=${Totalpage-1}&size=${size}" <c:if test="${page+1 eq Totalpage}">class ="btn btn-sm disabled"</c:if>>
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
                <spring:url value="/dossiers/" var="malade_home"
                            htmlEscape="true" />

                <form:form method="get" commandName="malade" action="${malade_home}">
                    <div class="form-group">
                        <label>
                            <spring:message code="malade.nom" />
                        </label>
                        <input type="text" value="${querynom}" class="form-control input-sm" name="querynom"/>
                        <label>
                            <spring:message code="malade.prenom" />
                        </label>
                        <input type="text" value="${queryprenom}" class="form-control input-sm" name="queryprenom"/>
                        <label>
                            <spring:message code="malade.lieuDeResidence" />
                        </label>
                        <input type="text" value="${querylieu}" class="form-control input-sm" name="querynumero"/>
                        <input type="hidden" value="${size}" name="size"/>
                    </div>
                    <div class="form-group">
                        <label for="date">
                            <spring:message code="malade.dateDeNaissance" />
                        </label>
                        <input id="date" type="text" value="${querydate}" class="form-control input-sm" name="querydate"/>
                    </div>
                    <hr/>
                    <button class="btn btn-default btn-sm">
                        <span class="glyphicon glyphicon-search"></span> <spring:message code="search"/>
                    </button>
                    <spring:url value="/dossiers/" htmlEscape="true" var="malade_home" />
                    <a href="${malade_home}" class="btn btn-default btn-sm">
                        <span class="glyphicon glyphicon-refresh"></span>
                        <spring:message code="search.delete" />
                    </a>

                </form:form>
            </div>
        </div>

        <script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
        <script type="text/javascript">
            $(function () {
                $("#date").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: "dd/MM/yy",
                    showButtonPanel: false
                }).datepicker("option", "showAnim", "clip");
            });
        </script>



    </div>
</div>
</tiles:putAttribute>
</tiles:insertDefinition>