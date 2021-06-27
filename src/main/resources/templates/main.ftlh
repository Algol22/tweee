<#import "parts/common.ftlh" as c>


<@c.page>
<div class="form-row">
<div class="form-group col-md-6">
    <form method="get" action="/main" class="form-inline">
        <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
        <button type="submit" class="btn btn-secondary ml-2">Search</button>
    </form>
</div>
</div>

<a class="btn btn-secondary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
Add new Message
</a>
<div class="collapse" id="collapseExample">
<div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="tag" placeholder="Тэг">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="form-group">
            <button type="submit" class="btn btn-secondary">Добавить</button>
        </div>
    </form>
</div>
</div>

        <!--</div>-->
        </br>

<div class="card-columns">
<#list messages as message>
<div
        style="
    background-image: url('https://source.unsplash.com/1600x900/?<#if message.tag??>${message.tag}</#if>');
        background-size: cover;
        height: 100%;"

        class="card text-white h-300 bg-light my-3" >
    <div class="card-body">

        <div class="card-title m-2">

            <i>#${message.tag}</i>
        </div>
        <div class="card-text">
            <span>${message.text}</span>
        </div>
        <div class="card-footer">
            ${message.authorName}

        </div>

    </div>
</div>
<#else>
No message
</#list>
        </div>





        </@c.page>