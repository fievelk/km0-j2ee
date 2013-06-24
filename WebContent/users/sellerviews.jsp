<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#seller').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
			"sAjaxDataProp": "rows",
			"aoColumns":[
						{"mData":"oid"},
		                {"mData":"name"},
		                {"mData":"surname"},
		                {"mData":"p_iva"},
		                {"mData":"company"},
		                {"mData":"phone", "sDefaultContent": ""},		                
		                { "sName": "name",
		                    "bSearchable": false,
		                    "bSortable": false,
		                    "sDefaultContent": "",
		                    "fnRender": function (oObj) {
		                       return "<a href='${pageContext.request.contextPath}/sellers/update_start.do?oid=" + oObj.aData['oid'] + "'>Edit</a>" + " | "+ 
		                       		  "<a href='${pageContext.request.contextPath}/sellers/delete_start.do?oid=" + oObj.aData['oid'] + "'>Delete</a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/sellers/viewAllSellersPaginated.do",
            "oLanguage": {"sUrl": "${pageContext.request.contextPath}/resources/datatables/i18n/italian.properties"},        
            "fnServerParams": addsortparams
		});
	    
		
	});
</script>

<div class="row-fluid">
<a class="btn btn-primary btn-medium" href="${pageContext.request.contextPath}/sellers/insert_start.do">Add</a>
</div>
<br/>
<div>
	<table id="seller">
	    <thead>
	        <tr>
	        	<th>ID</th>
	            <th><bean:message key="user.name"/></th>
	            <th><bean:message key="user.surname"/></th>
	            <th><bean:message key="seller.p_iva"/></th>
	            <th><bean:message key="seller.company"/></th>
	            <th><bean:message key="seller.phone"/></th>
	            <th><bean:message key="common.actions"/></th>
	        </tr>
	    </thead>
	    <tbody>
	    </tbody>
	</table>
</div>