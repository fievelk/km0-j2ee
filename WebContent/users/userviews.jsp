<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#user').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
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
		                       return "<a href='${pageContext.request.contextPath}/titles/update_start.do?id=" + oObj.aData['id'] + "'><i class='icon-edit'></i></a>" + " | "+ 
		                       		  "<a href='${pageContext.request.contextPath}/titles/delete_start.do?id=" + oObj.aData['id'] + "'><i class='icon-trash'></i></a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/users/viewAllUsersPaginated.do",
            "oLanguage": {"sUrl": "${pageContext.request.contextPath}/resources/datatables/i18n/italian.properties"},        
            "fnServerParams": addsortparams
		});
	    
		
	});
</script>

<div class="row-fluid">
<a class="btn btn-primary btn-medium" href="${pageContext.request.contextPath}/users/insert_start.do">Add</a>
</div>
<br/>
<div>
	<table id="user">
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