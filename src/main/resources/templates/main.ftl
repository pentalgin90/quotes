<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/" class="form-inline">
                <input type="text" name="tag" class="form-control" value="${tag?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Quote
    </a>
    <div class="collapse <#if quote??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if quote??>${quote.text}</#if>" placeholder="Введите цитату"/>
                    <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" name="tag" class="form-control"
                           value="<#if quote??>${quote.tag}</#if>" placeholder="Введите tag"/>
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
    <#list quotes as quote>
        <div class="card my-3">
            <#if quote.filename??>
                <img src="/img/${quote.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${quote.text}</span>
                <i><a href="/?tag=${quote.tag}">#${quote.tag}</a></i>
            </div>
            <div class="card-footer text-muted">
                ${quote.authorName()}
            </div>
        </div>
    <#else>
        No quotes
    </#list>
    </div>
</@c.page>