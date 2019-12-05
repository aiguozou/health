 $(document).ready(function () {
     "use strict";
     // toat popup js
     $.toast({
         heading: '欢迎来到传智健康后台首页',
         text: new Date(),
         position: 'top-right',
         loaderBg: '#fff',
         icon: 'warning',
         hideAfter: 3000,
         stack: 6
     });


     //ct-visits
     new Chartist.Line('#ct-visits', {
         labels: ['2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015'],
         series: [
    [5, 2, 7, 4, 5, 3, 5, 4]
    , [2, 5, 2, 6, 2, 5, 2, 4]
  ]
     }, {
         top: 0,
         low: 1,
         showPoint: true,
         fullWidth: true,
         plugins: [
    Chartist.plugins.tooltip()
  ],
         axisY: {
             labelInterpolationFnc: function (value) {
                 return (value / 1) + 'k';
             }
         },
         showArea: true
     });
     // counter
     $(".counter").counterUp({
         delay: 100,
         time: 1200
     });

     var sparklineLogin = function () {
         $('.sparklinedash').sparkline([3, 5, 6, 9, 9, 12, 5, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#7ace4c'
         });
         $('#sparklinedash2').sparkline([2, 7, 4, 8, 10, 12, 2, 6], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#7460ee'
         });
         $('#sparklinedash3').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#11a0f8'
         });
         $('#sparklinedash4').sparkline([1, 3, 3, 5, 7, 12, 6, 7], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#304042'
         });
         $('#sparklinedash5').sparkline([5, 5, 6, 4,7, 3, 8, 2], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#7460ee'
         });
         $('#sparklinedash6').sparkline([4, 2,6, 8, 4, 7, 4, 9], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#f33155'
         });
         $('#sparklinedash7').sparkline([3, 7, 7,10,12, 12, 8, 6], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#11a0f8'
         });
         $('#sparklinedash8').sparkline([4, 2,6, 8, 4, 7, 4, 9], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#f33155'
         });
         $('#sparklinedash9').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#90ffff'
         });
         $('#sparklinedash10').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#90ffff'
         });
         $('#sparklinedash11').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#90ffff'
         });
         $('#sparklinedash12').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#90ffff'
         });
         $('#sparklinedash13').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#90ffff'
         });
         $('#sparklinedash14').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#90ffff'
         });
         $('#sparklinedash15').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#90ffff'
         });
         $('#sparklinedash16').sparkline([3, 4, 5, 6, 7, 8, 9, 10], {
             type: 'bar',
             height: '30',
             barWidth: '4',
             resize: true,
             barSpacing: '5',
             barColor: '#90ffff'
         });
     };
     var sparkResize;
     $(window).on("resize", function (e) {
         clearTimeout(sparkResize);
         sparkResize = setTimeout(sparklineLogin, 500);
     });
     sparklineLogin();
 });
