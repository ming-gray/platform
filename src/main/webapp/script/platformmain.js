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
                    name: '订单数量',
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

$(function () {
    var myChart = echarts.init(document.getElementById('circle1'));

    $.post("/platform/staeq", null, function (data) {
        if (data) {
            var runRate = (data.runRate * 100).toFixed(2);
            myChart.setOption({
                series: [{
                    data: [
                        {
                            value: runRate,
                            name: '运行率'
                    },

                        {
                            value: 100 - runRate,
                            name: ''
                    }
          ]
        }]
            });
        }
    }, "json");



    myChart.setOption({
        // tooltip: {},
        title: {
            text: "运行率",
            bottom: 0,
            left: 'center',
            textStyle: {
                color: '#389af4',
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: " {b}({d}%) "
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            show: false

        },
        color: ["#389af4", "#dfeaff"],
        series: [
            {
                name: '',
                type: 'pie',
                radius: ['60%', '70%'],
                avoidLabelOverlap: true,
                hoverAnimation: false,
                label: {
                    normal: {
                        show: true,
                        position: 'center',
                        formatter: '{d}%',
                        textStyle: {
                            fontSize: 32
                        }
                    },
                    emphasis: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                center: ['30%', '50%'],
                data: [
                    {
                        value: 0,
                        name: ''
                    },
                    {
                        value: 100,
                        name: ''
                    }
          ]
        }

      ]

    });
});

$(function () {
    var myChart = echarts.init(document.getElementById('circle2'));

    $.post("/platform/staeq", null, function (data) {
        if (data) {
            var failRate = (data.failRate * 100).toFixed(2);
            myChart.setOption({
                series: [{
                    data: [
                        {
                            value: failRate,
                            name: '故障率'
                    },

                        {
                            value: 100 - failRate,
                            name: ''
                    }
          ]
        }]
            });
        }
    }, "json");



    myChart.setOption({
        // tooltip: {},
        title: {
            text: "故障率",
            bottom: 0,
            left: 'center',
            textStyle: {
                color: '#ff8c37',
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: " {b}({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            show: false

        },
        color: ["#ff8c37", "#ffdcc3"],
        series: [
            {
                name: '',
                type: 'pie',
                radius: ['60%', '70%'],
                avoidLabelOverlap: true,
                hoverAnimation: false,
                label: {
                    normal: {
                        show: true,
                        position: 'center',
                        formatter: '{d}%',
                        textStyle: {
                            fontSize: 32
                        }
                    },
                    emphasis: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                center: ['30%', '50%'],
                data: [
                    {
                        value: 0,
                        name: ''
                    },
                    {
                        value: 100,
                        name: ''
                    }
          ]
        }

      ]

    });


});

$(function () {
    var myChart = echarts.init(document.getElementById('circle3'));

    $.post("/platform/staeq", null, function (data) {
        if (data) {
            var avaiRate = (data.avaiRate * 100).toFixed(2);
            myChart.setOption({
                series: [{
                    data: [
                        {
                            value: avaiRate,
                            name: '开机率'
                    },

                        {
                            value: 100 - avaiRate,
                            name: ''
                    }
          ]
        }]
            });
        }
    }, "json");



    myChart.setOption({
        // tooltip: {},
        title: {
            text: "开机率",
            bottom: 0,
            left: 'center',
            textStyle: {
                color: '#a181fc',
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: " {b}({d}%) "
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            show: false

        },
        color: ["#a181fc", "#e3d9fe"],
        series: [
            {
                name: '',
                type: 'pie',
                radius: ['60%', '70%'],
                avoidLabelOverlap: true,
                hoverAnimation: false,
                label: {
                    normal: {
                        show: true,
                        position: 'center',
                        formatter: '{d}%',
                        textStyle: {
                            fontSize: 32
                        }
                    },
                    emphasis: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                center: ['30%', '50%'],
                data: [
                    {
                        value: 0,
                        name: ''
                    },
                    {
                        value: 100,
                        name: ''
                    }
          ]
        }

      ]

    });
});
