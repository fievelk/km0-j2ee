<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<script type="text/javascript" charset="utf-8">

	$(document).ready(function() {
		
		$('#user').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
			"sPaginationType": "full_numbers",
			"sAjaxDataProp": "rows",
			"aoColumns":[
						{"mData":"oid"},
		                {"mData":"name"},
		                {"mData":"surname"},
		                {"mData":"date_of_birth"},
		                {"mData":"address"},
		                {"mData":"email"},
		                {"mData":"created"},
		                {"mData":"last_access", "sDefaultContent": ""},		                
		                { "sName": "name",
		                    "bSearchable": false,
		                    "bSortable": false,
		                    "sDefaultContent": "",
		                    "fnRender": function (oObj) {
		                       return "<a href='${pageContext.request.contextPath}/users/update_start.do?oid=" + oObj.aData['oid'] + "'><span class='ui-icon ui-icon-pencil'></span></a>" +  
		                       		  "<a href='${pageContext.request.contextPath}/users/delete_start.do?oid=" + oObj.aData['oid'] + "'><span class='ui-icon ui-icon-circle-close'></span></a>" + 
		                       		  "<a href='${pageContext.request.contextPath}/users/edit_password.do?oid=" + oObj.aData['oid'] + "'><span class='ui-icon ui-icon-locked'></a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/users/viewAllUsersPaginated.do",
            "oLanguage": {"sUrl": "${pageContext.request.contextPath}/resources/datatables/i18n/italian.properties"},        
            "fnServerParams": addsortparams
		});
	    
		
	});
</script>
<!-- My Account -->

<div class="items">
  <div class="container">
    <div class="row">

      <div class="span3 side-menu">

        <!-- Sidebar navigation -->
        <h5 class="title">Menu</h5>
        <!-- Sidebar navigation -->
          <nav>
            <ul id="navi">
              <li><a href="myaccount.html">Gestione Ordini</a></li>
              <li><a href="wish-list.html">Storico Ordini</a></li>
              <li><a href="order-history.html">Gestione Utenti</a></li>
              <li><a href="edit-profile.html">Gestione Venditori</a></li>
            </ul>
          </nav>

      </div>

<!-- Main content -->
      <div class="span9">

          <!-- <h5 class="title">My Account</h5> -->

          <h5 class="title">Gestione Utenti</h5>
          	<div class="row-fluid">
				<a class="btn" href="${pageContext.request.contextPath}/users/insert_start.do">Aggiungi un utente</a>
			</div>

            <table id="user" class="table table-striped tcart">
              <thead>
                <tr>
                  <th>ID</th>
		            <th><bean:message key="user.name"/></th>
		            <th><bean:message key="user.surname"/></th>
		            <th><bean:message key="user.date_of_birth"/></th>
		            <th><bean:message key="user.address"/></th>
		            <th><bean:message key="user.email"/></th>
		            <th><bean:message key="user.created"/></th>
		            <th><bean:message key="user.last_access"/></th>
		            <th><bean:message key="common.actions"/></th>
                </tr>
              </thead>
              <tbody>                                              
              </tbody>
            </table>

      </div>                                                                    



    </div>
  </div>
</div>

