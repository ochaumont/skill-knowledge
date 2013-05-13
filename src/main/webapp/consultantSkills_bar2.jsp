<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<html>
<head>
    <title>Index</title>
    
    <link rel="stylesheet" type="text/css" href="/skill-knowledge/jquery/css/jquery.	.css" />
    <link rel="stylesheet" type="text/css" href="/skill-knowledge/jquery/css/jquery.jqRangeSlider.css" />
    <link rel="stylesheet" type="text/css" href="/skill-knowledge/jquery/themes/smoothness/jquery-ui-1.8.21.css" />
    
    <script src="/skill-knowledge/jquery/js/jquery-1.5.1.min.js" type="text/javascript"></script>
    <script src="/skill-knowledge/jquery/js/jquery.jqChart.min.js" type="text/javascript"></script>
    <script src="/skill-knowledge/jquery/js/jquery.jqRangeSlider.min.js" type="text/javascript"></script>
    <!--[if IE]><script lang="javascript" type="text/javascript" src="/skill-knowledge/jquery/js/excanvas.js"></script><![endif]-->
   
   <%
   List<Map<String,String>> result = (List<Map<String,String>>)request.getAttribute("result");
   
   %>
   
    <script language="javascript" type="text/javascript">
   		
	     $(document).ready(function () {
	    	 
	    	 
	    	 $('#jqChart1').jqChart({
	    	      title: { text: 'Bar Chart' },
	    	      axes: [
	    	              {
	    	                  type: 'category',
	    	                  location: 'left',
	    	                  categories: <%=(String)map.get("categories")%>,
	    	              }
	    	            ],
	    	      series: [
	    	                  {
	    	                      type: 'bar',
	    	                      title: 'Bar',
	    	                      data:  <%=(String)map.get("data")%>
	    	                  },
	    	                  {
	    	                      type: 'bar',
	    	                      title: 'Bar',
	    	                      data:  <%=(String)map.get("data")%>
	    	                  }
	    	              ]
	    	  });
	    	 	    
	    
	        });
       
     
       
    </script>
</head>
<body>
 <div id="jqChart1" style="width: 800px; height: 800px;"/>
    
</body>

</html>