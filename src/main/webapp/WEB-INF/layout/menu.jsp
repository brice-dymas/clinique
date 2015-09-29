<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse navbar-static-top" style="height:52px;color:skyblue">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"></a>
        </div>
        <c:set var="url" value="${pageContext.request.requestURI}" />

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li
                    <c:if test="${fn:containsIgnoreCase(url, 'dossiers')}">
                        class="active"
                    </c:if>
                    >
                    <a href="<spring:url value="/dossiers/" />">
                        <span class="fa fa-user-plus"></span>
                        <spring:message code="malade.title" />
                    </a>
                </li>
                <li
                    <c:if test="${fn:containsIgnoreCase(url, 'client')}">
                        class="active"
                    </c:if>
                    >
                    <a href="<spring:url value="/client/" />">
                        <span class="fa fa-cubes"></span>
                        <spring:message code="client" />
                    </a>
                </li>
                <li
                    <c:if test="${fn:containsIgnoreCase(url, 'produit')}">
                        class="active"
                    </c:if>
                    >
                    <a href="<spring:url value="/produit/" />">
                        <span class="fa fa-list"></span>
                        <spring:message code="produit" />
                    </a>
                </li>
                <li
                    <c:if test="${fn:containsIgnoreCase(url, 'stock')}">
                        class="active"
                    </c:if>
                    >
                    <a href="<spring:url value="/stock/" />">
                        <span class="fa fa-list"></span>
                        <spring:message code="stock" />
                    </a>
                </li>
                <li
                    <c:if test="${fn:containsIgnoreCase(url, 'fournisseur')}">
                        class="active"
                    </c:if>
                    >
                    <a href="<spring:url value="/fournisseur/" />">
                        <span class="fa fa-user-plus"></span>
                        <spring:message code="fournisseur" />
                    </a>
                </li>
                <li
                    <c:if test="${fn:containsIgnoreCase(url, 'conditionnement')}">
                        class="active"
                    </c:if>
                    >
                    <a href="<spring:url value="/conditionnement/" />">
                        <span class="fa fa-tasks"></span>
                        <spring:message code="conditionnement" />
                    </a>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li
                        <c:if test="${fn:containsIgnoreCase(url, 'user')}">
                            class="active"
                        </c:if>
                        >
                        <a href="<spring:url value="/user/" />">
                            <span class="fa fa-user-plus"></span>
                            <spring:message code="user.title" />
                        </a>
                    </li>
                </sec:authorize>
            </ul>

            <ul class="nav navbar-nav navbar-right" >
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <span class="glyphicon glyphicon-user">

                        </span>
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <spring:message code="menu.bienvenu" />

                            ${pageContext.request.userPrincipal.name}
                        </c:if>
                        <c:if test="${pageContext.request.userPrincipal.name == null}">
                            Bonjour Visiteur!
                        </c:if>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value="/welcome" />">
                                <span class="glyphicon glyphicon-user ">
                                    <spring:message code="menu.profil" />
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Tabeaux de bord
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value="/logout" />">
                                <span class="glyphicon glyphicon-log-out">
                                    Déconnexion
                                </span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="row">
    <div class="col-lg-12">
        <c:set var="url" value="${pageContext.request.requestURI}" />
        <c:set var="urlPart" value="${fn:split(url, '/')}" />

        <div>
            <ol class="breadcrumb">
                <li>
                    <a href="<c:url value="${pageContext.request.contextPath}" />">
                        Accueil
                    </a>
                </li>
                <c:forEach var="i" begin="${fn:length(urlPart)-2}" end='${fn:length(urlPart)-1}'>
                    <li>
                        <c:choose>
                            <c:when test="${fn:containsIgnoreCase(urlPart[i], 'jsp')}">
                                <span>
                                    <spring:message code="${fn:toLowerCase(fn:substringBefore(urlPart[i], '.'))}" />
                                </span>
                            </c:when>
                            <c:otherwise>
                                <spring:url   value="/${urlPart[i]}/" var="path_element"  htmlEscape="true" />
                                <c:choose>
                                    <c:when test="${fn:containsIgnoreCase(urlPart[i], 'view')}">
                                        <spring:message code="${fn:toLowerCase((urlPart[i]))}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${path_element}">
                                            <spring:message code="${fn:toLowerCase((urlPart[i]))}" />
                                        </a>
                                    </c:otherwise>
                                </c:choose>

                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ol>
        </div>
    </div>
</div>