<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
<a class="navbar-brand" href="/">Tweee</a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
</button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
        </li>


        <li class="nav-item">
            <a class="nav-link" href="/main">Messages</a>
        </li>
        <#if user??>
        <li class="nav-item">
            <a class="nav-link" href="/user-messages/${currentUserId}">My messages</a>
        </li>
    </#if>


        <li class="nav-item">
            <a class="nav-link" href="/parser">Weather</a>
        </li>
        <#if isAdmin>
        <li class="nav-item">
            <a class="nav-link" href="/user">User list</a>
        </li>
    </#if>
</ul>

<#if user??>
<div class="navbar-text mr-3">${name}</div>
        </#if>

<#if user??>
<@l.logout />
<#else>
<a class="nav-link" href="/login">Login</a>

        </#if>
</div>
        </nav>