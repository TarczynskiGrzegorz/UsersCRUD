<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<!-- Begin Page Content -->
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">usersCRUD</h1>
    <a href="/user/add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i>Dodaj użytkownika</a>
</div>

<%--<div class="card-header py-3">--%>
<%--    <h6 class="m-0 font-weight-bold text-primary">Lista użytkowników</h6>--%>
<%--</div>--%>
<div class="card-body">

</div>
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Lista użytkowników</h6>
    </div>
    <div class="card-body">
        <table style="width:100%">
            <tbody>
            <tr class="headerTable">
                <td>id</td>
                <td>Nazwa użytkownika</td>
                <td>Email</td>
                <td>Akcja</td>
            </tr>

                <c:forEach items="${users}" var="user">
            <tr>
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td>${user.email}</td>
                <td>
                    <span><a href="/user/show?id=${user.id}"> Pokaż </a> </span>
                    <span><a href="/user/edit?id=${user.id}"> Edytuj </a> </span>
                    <span><a href="/user/delete?id=${user.id}"> Usuń </a> </span>

                    </td>
            </tr>
                </c:forEach>

            </tbody>
        </table>

<%--        <h4 class="small font-weight-bold">Id<span class="float-right">Nazwa Użytkownika</span><span--%>
<%--                class="float-right">Email</span><span class="float-right">Akcja</span></h4>--%>

<%--        <c:forEach items="${users}" var="user">--%>
<%--            <div class="card-body">--%>
<%--                <h4 class="small font-weight-bold">${user.id}<span class="float-right">${user.userName}</span><span--%>
<%--                        class="float-right">${user.email}</span><span class="float-right">Usuń Edytuj pokaż</span></h4>--%>

<%--            </div>--%>
<%--        </c:forEach>--%>


    </div>
</div>


<!-- Content Row -->

<!-- Content Row -->


<!-- Content Row -->

</div>
<!-- /.container-fluid -->

<%@ include file="/footer.jsp" %>