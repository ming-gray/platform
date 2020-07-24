/**
 * Koala - 1.1.1 By Junny L Blue 2017-3-3
 * 基于JQuery的工具集, 持续增加中
 */

! function ($) {
    var _win = window;
    var _string = String.prototype;
    var _date = Date.prototype;
    var _doc = _win.document;
    var _loc = _win.location;

    var koala = (function () {
        var koala = function () {
            return new koala.fn.init();
        };
        koala.fn = koala.prototype = {
            init: function (options) {
                return this;
            },
            poweredBy: "Junny.L.Blue",
            koala: "koala baby",
            name: "koala"
        };

        koala.fn.init.prototype = koala.fn;

        $.extend(true, koala, {
            emailformat: /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/,
            spaceanywhere: /\s+/g,
            BIG_NUMBER: 1e+100,
            SMALL_NUMBER: -1e+100,
            koalaTags: {
                hello : function(element){
                    var $span =  $("<span></span>");
                    var name = (!!element)?element.html() || element.attr("name") : koala().koala;
                    $span.html("Hello " + name);
                    return $span;
                }
            },
            initAjax: function (options) {
                options = $.extend({}, {
                    //url: "/s2sh", // 默认URL
                    async: false, // 默认同步加载
                    type: "POST", // 默认使用POST方式
                    cache: false, // 关闭AJAX相应的缓存
                    headers: { // 默认添加请求头
                        "Author": "Junnyblue",
                        "Powered-By": "Junnyblue"
                    },
                    error: function (jqXHR, textStatus, errorMsg) {
                        /*
                         * 出错时默认的处理函数 jqXHR 是经过jQuery封装的XMLHttpRequest对象
                         * textStatus 可能为：
                         * null、"timeout"、"error"、"abort"或"parsererror" errorMsg
                         * 可能为： "Not Found"、"Internal Server Error"等
                         * 提示形如：发送AJAX请求到"/index.html"时出错[404]：Not Found
                         */
                        console.log('Error when sending request to "' + this.url + '", [' + jqXHR.status + ']:' + errorMsg, textStatus);
                    }
                }, options);
                console.log("koala init ajax.")
                $.ajaxSetup(options);
            },
            getRandomString: function (len) {
                len = len || 32;
                var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
                var maxPos = $chars.length;
                var random = '';
                for (i = 0; i < len; i++) {
                    random += $chars.charAt(Math.floor(Math.random() * maxPos));
                }
                return random;
            },
            roll : function(){
                return Math.floor((Math.random()*5050)%101);
            },
            importJS: function (js) {
                var argus = arguments;
                $.ajax({
                    url: js,
                    async: false,
                    dataType: "script",
                    success: function () {
                        var foo = argus[1];
                        if (foo && $.isFunction(foo)) {
                            foo.call(window);
                        }
                    }
                });
            },
            foo: function () {
                alert("Koala + " + koala().koala);
            },
            replace: function (url) {
                _loc.replace(url);
            },
            isIE: function () {
                return "\v" == "v";
            },
            format: function (source) {
                if (arguments.length > 1) {
                    var arg = arguments[1];
                    if (typeof arg === 'string') {
                        for (var i = 1; i < arguments.length; i++) {
                            source = source.replace(new RegExp("\\{" + (i - 1) + "\\}", "g"), arguments[i]);
                        }
                    } else {
                        for (key in arg) {
                            source = source.replace(new RegExp("\\{" + key + "\\}", "g"), arg[key]);
                        }
                    }

                }
                return source;
            },
            isExists: function (obj) {
                var exists = false;
                if (null != obj) {
                    exists = true;
                    if (typeof obj === 'string' || jQuery.isArray(obj) || (obj.jquery && !jQuery.isPlainObject(obj))) {
                        exists = (0 < obj.length);
                    }
                }
                return exists;
            },
            // 实现类似Android中toast的提示信息
            toast: function (options) {
                if (1 == arguments.length && typeof options === 'string') {
                    var msg = options;
                    options = {
                        message: msg
                    };
                }
                options = $.extend({}, {
                    base: "body",
                    speed: 2500,
                    top: function (baseHeight, toaseHeight) {
                        return (baseHeight - toaseHeight) / 2;
                    },
                    left: function (baseWidth, toastWidth) {
                        return (baseWidth - toastWidth) / 2;
                    },
                    callback: function () {}
                }, options);
                var parent = $(options.base);
                if (koala.isExists(parent)) {
                    var toast = $("#toast");
                    if (!koala.isExists(toast)) {
                        toast = $("<DIV>").addClass("toast").attr("id", "toast").appendTo(parent);
                    }

                    toast.stop(true).html(options.message).css({
                        //                        marginLeft: (0 - toast.width()) / 2,
                        //                        marginTop: (0 - toast.height()) / 2,
                        //                        top: options.top,
                        //                        left: options.left,
                        left: options.left(parent.width(), toast.width()),
                        top: options.top(parent.height(), toast.height()),
                        opacity: 1
                    }).show().animate({
                        opacity: "hide"
                    }, options.speed, options.callback);
                }

            },
            tagSetup: function (tags, namespace) {
                var profix = namespace || koala().name;
                if (tags) {
                    $.extend(koala.koalaTags, tags);
                }
                for (tag in koala.koalaTags) {
                    var elem = $(profix + "\\:" + tag);
                    var setup = koala.koalaTags[tag];
                    if (koala.isExists(elem)) {
                        elem.each(function (index, e) {
                            var obj = setup.call(e, $(e));
                            obj && $(e).replaceWith(obj);
                        });

                    }
                }

            }

        });

        return koala;
    })();

    window.koala = window.$K = koala;

    _string.equals = function (otherStr) {
        return otherStr.toString() == this;
    };
    _string.removeSpaces = function () {
        return this.replace(/\s+/g, "");
    };
    _string.startWith = function (str) {
        var reg = new RegExp("^" + str);
        return reg.test(this);
    };
    _string.startWithLetter = function (str) {
        return this.indexOf(str) == 0;
    };

    _string.endWith = function (str) {
        var reg = new RegExp(str + "$");
        return reg.test(this);
    };

    _string.endWithLetter = function (str) {
        return this.lastIndexOf(str) == this.length - 1;
    };
    /**
     * 日期时间格式化字符串
     * 
     * @param {Object}
     *            format ： 事件模式， eg:format="yyyy-MM-dd hh:mm:ss"
     */
    _date.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
        }

        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }

        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }

    /**
     * 去掉字符串前后的指定字符或空格
     * 
     * @param {Object}
     *            synx 需要去掉的字符(如果为特殊字符需要进行转义，如'\\.', '\\$')，如果不指定则为空格
     * @memberOf {TypeName}
     * @return {TypeName} 处理后的字符串
     */
    _string.trim = function (synx) {
        synx = synx || "\\s";
        var reg = new RegExp("(^" + synx + "*)|(" + synx + "*$)", "g");
        return this.toString().replace(reg, '');
    }
}(jQuery);
