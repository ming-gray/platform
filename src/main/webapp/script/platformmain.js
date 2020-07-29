$(function () {
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '年度订单情况汇总'
        },
        tooltip: {},
        legend: {
            data: ['订单数量']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '订单数量',
            type: 'bar',
            data: []
                }]
    };
    myChart.setOption(option);

    // 使用刚指定的配置项和数据显示图表。

    $.post("/platform/staorderbymonth", null, function (data) {
        if (data) {
            var month = data.month;
            var num = data.num;
            myChart.setOption({
                xAxis: {
                    data: month
                },
                series: [{
                    // 根据名字对应到相应的系列
                    name: '销量',
                    data: num
        }]
            });

        }
    }, "json");
});

$(function () {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echarts2'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 10,
            data: ['待接单', '已接单', '生产中', '已排产', '已完成']
        },
        series: [{
            name: '订单信息',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                show: false,
                position: 'center'
            },
            emphasis: {
                label: {
                    show: true,
                    fontSize: '30',
                    fontWeight: 'bold'
                }
            },
            labelLine: {
                show: false
            },
            data: []
                }]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.post("/platform/staorder", null, function (data) {
        if (data) {
            var servicedata = [];
            for (var i = 0; i < 5; i++) {
                var obj = new Object();
                obj.name = data.name[i];
                obj.value = data.value[i];

                servicedata[i] = obj;
            }
            myChart.setOption({
                series: [{
                    data: servicedata
        }]
            });
        }
    }, "json");

});
