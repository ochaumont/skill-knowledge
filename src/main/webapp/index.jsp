<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Index</title>
    
    <link rel="stylesheet" type="text/css" href="jquery/css/jquery.jqChart.css" />
    <link rel="stylesheet" type="text/css" href="jquery/css/jquery.jqRangeSlider.css" />
    <link rel="stylesheet" type="text/css" href="jquery/themes/smoothness/jquery-ui-1.8.21.css" />
    
    <script src="jquery/js/jquery-1.5.1.min.js" type="text/javascript"></script>
    <script src="jquery/js/jquery.jqChart.min.js" type="text/javascript"></script>
    <script src="jquery/js/jquery.jqRangeSlider.min.js" type="text/javascript"></script>
    <!--[if IE]><script lang="javascript" type="text/javascript" src="jquery/js/excanvas.js"></script><![endif]-->
   
    <script language="javascript" type="text/javascript">
	
		
        $(document).ready(function () {
		
				$('#jqChart1').jqChart({
			  title: { text: 'Radar Area Chart' },
			  axes: [
					  {
						  type: 'categoryAngle',
						  categories: ['A', 'B', 'C', 'D', 'E', 'F', 'D']
					  }
					],
			  series: [
						  {
							  type: 'radarArea',
							  title: 'Radar',
							  data: [33, 57, 33, 12, 35, 7, 24]
						  }
					  ]
		  });
		
		
            $('#jqChart2').jqChart({
                title: { text: 'Multiple Axes' },
                axes: [
                         {
                             name: 'y1',
                             location: 'left'
                         },
                         {
                             name: 'y2',
                             location: 'right',
                             strokeStyle: '#FCB441',
                             majorGridLines: { strokeStyle: '#FCB441' },
                             majorTickMarks: { strokeStyle: '#FCB441' }
                         }
                      ],
                series: [
                            {
                                type: 'column',
                                axisY: 'y1',
                                data: [['a', 1], ['b', 4], ['c', 3], ['d', 5], ['e', 2], ['f', 1]]
                            },
                            {
                                type: 'line',
                                axisY: 'y2',
                                data: [['a', 40], ['b', 60], ['c', 62], ['d', 52], ['e', 70], ['f', 75]]
                            }
                        ]
            });
        });
    </script>
</head>
<body>


<div class="reformed-form">
	<form method="post" name="testMyform" id="testMyform" action="reformed/formmail/formmail.php">
		<dl>
			<dt>
				<label for="test">test</label>
			</dt>
			<dd>
				<ul>
					<li><input type="radio" checked="checked" value="dsd" name="test" id="test" />
						<label>dsds</label>
					</li>
					<li><input type="radio" value="dsds" name="test" id="test" />
						<label>dsd</label>
					</li>
				</ul>
						</dd>
		</dl>
		<dl>
			<dt>
				<label for="dsdsq">dsqdq</label>
			</dt>
			<dd><input type="text" value="dsq" name="dsdsq" id="dsdsq" /></dd>
		</dl>
		<div id="submit_buttons">
			<button type="reset">Reset</button>
			<button type="submit">Submit</button>
		</div>
		</form>
</div>

    <div>
        <div id="jqChart1" style="width: 500px; height: 300px;">
        </div>
		 <div id="jqChart2" style="width: 500px; height: 300px;">
        </div>
    </div>
</body>

</html>