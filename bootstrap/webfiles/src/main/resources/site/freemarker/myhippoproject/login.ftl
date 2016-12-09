<#include "../include/imports.ftl">
<#--
  Copyright 2016 Hippo B.V. (http://www.onehippo.com)
  Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
-->
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <link rel="stylesheet" href="<@hst.webfile  path="/css/bootstrap.css"/>" type="text/css"/>
<#if hstRequest.requestContext.cmsRequest>
  <link rel="stylesheet" href="<@hst.webfile  path="/css/cms-request.css"/>" type="text/css"/>
</#if>
<@hst.headContributions categoryExcludes="htmlBodyEnd, scripts" xhtml=true/>
</head>
<body>
<div class="container">

  <form class="form-horizontal" action="<@hst.link path='/'/>login" method="post">
    <div class="col-sm-offset-2 panel-group">
      <h3>Please log in</h3>
    </div>

    <div class="form-group">
      <label for="inputUsername" class="col-sm-2 control-label">Username</label>
      <div class="col-sm-10">
        <input type="text" id="inputUsername" class="form-control" placeholder="Username" required="" autofocus="" name="username">
      </div>
    </div>

    <div class="form-group">
      <label for="inputPassword" class="col-sm-2 control-label">Password</label>
      <div class="col-sm-10">
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="" name="password">
      </div>
    </div>

    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button class="btn btn-primary btn-default" type="submit">Log in</button>
      </div>
    </div>
  </form>
</div>
<@hst.headContributions categoryIncludes="htmlBodyEnd, scripts" xhtml=true/>
</body>
</html>