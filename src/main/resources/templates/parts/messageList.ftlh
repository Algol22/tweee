<#include "security.ftlh">

<div class="card-columns">
<#list messages as message>
<div
        style="
    background-image: url('https://source.unsplash.com/1600x900/?<#if message.tag??>${message.tag}</#if>');
        background-size: cover;
        height: 100%;"

        class="card text-white h-300 bg-light my-3" >
    <div class="m-2">
        <span>${message.text}</span><br/>
        <i>#${message.tag}</i>
    </div>
    <div class="card-footer text-muted">
        <a href="/user-messages/${message.author.id}">${message.authorName}</a>
        <#if message.author.id == currentUserId>
        <a class="btn btn-secondary" href="/user-messages/${message.author.id}?message=${message.id}">
            Edit
        </a>
        <a class="btn btn-secondary" href="/del-user-messages/${message.author.id}?message=${message.id}">Delete</a>
    </#if>
</div>
</div>
<#else>
        No message
        </#list>
        </div>