<#import "layouts/main.ftl" as mainLayout>

<@mainLayout.application "Code Snippet Manager">

   <div class="jumbotron">
       <h1>Code Snippet Manager</h1>
       <p class="lead">A new way to create and manage Code Snippets. Useful for any developer, but why wait? Get you account!</p>
       <p>
         <a class="btn btn-lg btn-success" href="#" role="button">Sign up today</a>
       </p>

       <p>${today}</p>
   </div>

   <div class="row marketing">
       <#list offers as offer>
            <div class="col-lg-6">
                <h4>${offer.header}</h4>
                <p>${offer.description}</p>
            </div>
       </#list>
   </div>
</@mainLayout.application>