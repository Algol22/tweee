<#import "parts/common.ftlh" as c>

<@c.page>

<div style="
    background-image: url('https://source.unsplash.com/1600x900/?<#if cityshow??>${cityshow}</#if>');
    background-size: cover;
    height: 100%;"
     class="jumbotron bg-dark text-white">
<div class="container">
    <p class="lead"> Weather check <#if cityshow??> ${cityshow} </#if> </p>
</div>
</div>

<div class="container mt-5">
    Город: <#if cityshow??> ${cityshow} </#if> </br>
    Температура: <#if temp??> ${temp} C</#if> </br>
    Максимальная температура: <#if temp_max??> ${temp_max} C</#if> </br>
    Минимальная температура: <#if temp_min??> ${temp_min} C</#if>  </br>
    По ощущениям: <#if feels??> ${feels} C</#if>  </br>
    Влажность: <#if humidity??> ${humidity} %</#if>  </br></br>


    <form action="/parser" method="post">
        <input type="text" name="city" placeholder="Введите город" class="form-control"></br>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-secondary">Узнать погоду</button>
    </form>
</div>

</div>

        </tbody>
        </table>

        </@c.page>