/**
 * jQuery validate plugin 1.0.0
 * 
 * 通用的验证框架，完成异步的前台以及后台的数据验证。 可扩展，可定制，灵活方便，能够节省大量验证编码工作
 * 
 * Copyright (c) 2017 Junny.L.Blue
 * 
 */
;
(function (factory) {
    if (typeof define === "function" && define.amd) {
        // AMD (Register as an anonymous module)
        define(["jquery"], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {
    /* 日期格式 */
    var reg_date = new RegExp("^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)" +
        "(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");
    /* 特殊字符 */
    var reg_special_letters = new RegExp("[(\\s)(\\~)(\\!)(\\@)(\\#)(\\$)(\\%)(\\^)(\\&)(\\*)(\\()(\\))(\\-)(\\+)(\\=)(\\[)(\\])(\\{)(\\})" +
        "(\\|)(\\\)(\\;)(\\:)(\\')(\\\")(\\,)(\\.)(\\/)(\\<)(\\>)(\\?)(\\)]+");

    var DEFULT_VALIDATE_EVENTS = "change";
    var VALIDATIONS_NAME = "validateResults";
    var PROFIX_NAMESPACE = "val_";

    var DEFINED_EVENTS_RESET = "reset";
    var DEFINED_EVENTS_ALIVE = "alive";
    var DEFINED_EVENTS_IGNORE = "ignore";
    /* 全局参数 */
    var validation = $V = $.validation = {
        defaultDateFormat: "yyyy-MM-dd hh:mm:ss",
        // 注册验证类型对应的validator
        validators: {
            required: ".required,[required]",
            email: ".email,[email]",
            digits: ".digits,[digits]",
            format: "[format]",
            date: ".date, [date]",
            standard: ".standard, [standard]",
            gt: {
                target: "[gt]",
                getMessageParameters: function () {
                    var name = $(this).getLabel();
                    var target = $(this).attr('gt');
                    var value = koala.isExists($(target)) ? $(target).getLabel() : target;
                    return {
                        name: name,
                        value: value
                    };
                }
            },
            ge: {
                target: "[ge]",
                getMessageParameters: function () {
                    var name = $(this).getLabel();
                    var target = $(this).attr('ge');
                    var value = koala.isExists($(target)) ? $(target).getLabel() : target;
                    return {
                        name: name,
                        value: value
                    };
                }
            },
            lt: {
                target: "[lt]",
                getMessageParameters: function () {
                    var name = $(this).getLabel();
                    var target = $(this).attr('lt');
                    var value = koala.isExists($(target)) ? $(target).getLabel() : target;
                    return {
                        name: name,
                        value: value
                    };
                }
            },
            le: {
                target: "[le]",
                getMessageParameters: function () {
                    var name = $(this).getLabel();
                    var target = $(this).attr('le');
                    var value = koala.isExists($(target)) ? $(target).getLabel() : target;
                    return {
                        name: name,
                        value: value
                    };
                }
            },
            maxsize: {
                type: "maxsize",
                target: ".maxsize, [maxsize]",
                getMessageParameters: function () {
                    var name = $(this).getLabel();
                    var max = $(this).attr('maxsize') || $(this).attr("max");
                    return {
                        name: name,
                        max: max
                    };
                }
            },
            minsize: {
                type: "minsize",
                target: ".minsize, [minsize]",
                getMessageParameters: function () {
                    var name = $(this).getLabel();
                    var min = $(this).attr('minsize') || $(this).attr("min");
                    return {
                        name: name,
                        min: min
                    };
                }
            },
            range: {
                type: "range",
                target: ".range, [range], [maxvalue], [minvalue]",
                getValue: function (context) {
                    var $this = $(this);
                    var value = $this.val();
                    try {
                        if (koala.isExists(value)) {
                            var range = $this.attr("range") || context.range;
                            var minvalue, maxvalue;
                            (null != $(this).attr("minvalue")) && (minvalue = Number($(this).attr("minvalue"))) || (minvalue = koala.SMALL_NUMBER, $(this).attr("minvalue", ""));
                            (null != $(this).attr("maxvalue")) && (maxvalue = Number($(this).attr("maxvalue"))) || (maxvalue = koala.BIG_NUMBER, $(this).attr("maxvalue", ""));
                            if (range) {
                                var weeny = new Number("1.0e-10");
                                range = range.removeSpaces();
                                var nums = range.match(/\d*,\d*/g)[0].split(",");
                                minvalue = koala.isExists(nums[0]) ? Number(nums[0]) : koala.SMALL_NUMBER;
                                maxvalue = koala.isExists(nums[1]) ? Number(nums[1]) : koala.BIG_NUMBER;
                                if (range.startWithLetter("(")) {
                                    minvalue += weeny;
                                }
                                if (range.endWithLetter(")")) {
                                    maxvalue -= weeny;
                                }
                                $(this).attr("maxvalue", maxvalue);
                                $(this).attr("minvalue", minvalue);
                                $(this).attr("rangeBack", range);
                                $(this).attr("range", "");
                            }
                            return {
                                value: parseInt($this.val()),
                                minvalue: minvalue,
                                maxvalue: maxvalue
                            };
                        } else {
                            return null;
                        }
                    } catch (e) {
                        koala.error(e.stack);
                        return null;
                    }

                },
                getMessageParameters: function () {
                    var $this = $(this);
                    var range = $(this).attr("rangeBack");
                    if (!range) {
                        range = "[" + $this.attr("minvalue") + ", " + $this.attr("maxvalue") + "]";
                    }
                    return {
                        name: $this.getLabel(),
                        range: range
                    };
                }
            },
            equalTo: {
                target: ".equals, [equals], .equalTo, [equalTo]",
                getMessageParameters: function (context) {
                    var name = $(this).getLabel();
                    var target = $(this).attr('equals') || $(this).attr("equalTo") || context.equals;
                    var value = koala.isExists($(target)) ? $(target).getLabel() : target;
                    return {
                        name: name,
                        value: value
                    };
                }
            }
        },
        message: function (msg) {
            this.message = $.extend({}, this.messages, msg);
        },
        /*
         * 默认的验证错误动作 @param {Object} label 出错控件的标示名字，通常是控件所对应label标签中的内容，
         * 或显式设置在控件中的data-title或data-label属性值。也可以在注册validator时用label属性指定。 @param
         * {Object} container 错误提示所放置的控件对象，通常是一个span标签对象，也可以由开发者定制 @param
         * {Object} type 触发验证错误发生的验证类型 @param {Object} message
         * 错误信息文本，支持通配字段。此参数可选，不指定则为通用错误提示 @param {Object} e 触发验证错误发生的验证事件对象
         */
        error: function (message, params, type, container, e) {
            var errorCss = $V.errorCss || {};
            container.css(errorCss);
            container.html(message).show();
        },
        success: function (message, params, type, container, e) {
            container.html("").hide();
        },
        /*
         * 验证类型所对应的验证方法。该方法应返回一个boolean型的值以表明验证结果 @param {Object} value
         * 需要进行验证的值，通常是控件的value属性或innerText，也可以通过指定getValue方法，通过其返回值获取 @memberOf
         * {TypeName} @return {TypeName} true 验证通过 false验证失败
         */
        handlers: {
            input_maxsize: 10,
            input_minsize: 1,
            date: function (value) {
                // console.log("validating date for " + $(this).getLabel() + "...");
                return reg_date.test(value);
            },
            standard: function (value) {
                // console.log("validating standard text for " + $(this).getLabel() + "...");
                return !reg_special_letters.test(value.trim());
            },
            gt: function (value) {
                value = isNaN(value) ? value : Number(value);
                var target = $(this).attr('gt');
                var targetValue = koala.isExists($(target)) ? $(target).val() : target;
                targetValue = isNaN(targetValue) ? targetValue : Number(targetValue);
                return value > targetValue;
            },
            ge: function (value) {
                value = isNaN(value) ? value : Number(value);
                var target = $(this).attr('ge');
                var targetValue = koala.isExists($(target)) ? $(target).val() : target;
                targetValue = isNaN(targetValue) ? targetValue : Number(targetValue);
                return value >= targetValue;
            },
            lt: function (value) {
                value = isNaN(value) ? value : Number(value);
                var target = $(this).attr('lt');
                var targetValue = koala.isExists($(target)) ? $(target).val() : target;
                targetValue = isNaN(targetValue) ? targetValue : Number(targetValue);
                return value < targetValue;
            },
            le: function (value) {
                value = isNaN(value) ? value : Number(value);
                var target = $(this).attr('le');
                var targetValue = koala.isExists($(target)) ? $(target).val() : target;
                targetValue = isNaN(targetValue) ? targetValue : Number(targetValue);
                return value <= targetValue;
            },
            required: function (value) {
                //console.log("validating required for " + $(this).getLabel() + "...");
                value = value.trim();
                var result = !!value;
                if (this.tagName && this.tagName.toUpperCase() == "SELECT") {
                    result = result && value != "0";
                }
                return result;
            },
            range: function (input) {
                var isOK = true;
                if (!isNaN(input.value)) {
                    isOK = input.value >= input.minvalue && input.value <= input.maxvalue;
                }
                return isOK;
            },
            email: function (value) {
                return koala.emailformat.test(value.trim());
            },
            digits: function (value) {
                return !isNaN(value.trim());
            },
            maxsize: function (value) {
                var maxSize = $(this).data('max') || $(this).attr('max') || $(this).attr("maxsize") || $V.handlers.input_maxsize;
                return value.trim().length <= maxSize;
            },
            minsize: function (value) {
                var minSize = $(this).data('min') || $(this).attr('min') || $(this).attr("minsize") || $V.handlers.input_minsize;
                return value.trim().length >= minSize;
            },
            equalTo: function (thisValue, context) {
                var target = context.equals || $(this).attr("equals") || $(this).data("equals");
                var targetValue = koala.isExists($(target)) ? $(target).val() : target;
                return thisValue === targetValue;
            },
            format: function (value, context) {
                var regExp = $(this).attr("format") || context.format;
                return new RegExp(regExp).test(value);
            }
        }
    };
    $.validation.messages = {};
    var init = false;
    /*
     * 用以配置并启动验证框架。默认启动validator中列出的基本验证，也可以在参数中扩展验证类型。
     * 扩展的类型如果和原有验证类型一致，后出现的将覆盖先前定义的验证动作。
     * 
     * 该方法还可以用于定制一些全局参数, 设置的参数会与原有参数合并
     * 
     * @param {Object} config 配置参数设定
     */
    $.validate = $.validationSetup = function (config) {

        $.validation = $.extend(true, $.validation, config);

        if (init == false) {
            var validator = $.validation.validators;
            init = true;
        } else {
            var validator = !!config ? config.validators : null;
        }
        ///- add by Junny, 2018-8-22, 添加提交按钮默认验证行为
        if (koala.isExists($.validation.submit)) {
            var submit = {};
            if (typeof ($.validation.submit) === 'string') {
                submit.element = $.validation.submit;
            } else {
                submit = $.validation.submit;
            }
            submit = $.extend({
                target: "body *",
                events: "click"
            }, submit);

            // 绑定提交前的验证事件， 如果验证不成功，则阻止提交的后续操作
            $(submit.element).on(submit.events + ".submit", function (event) {
                var result = $(submit.target).valResult();
                if (!result.isOK) {
                    result.trigger();
                    // 阻止后续动作
                    event.stopImmediatePropagation();

                    return false;
                }
                return true;
            })
        }


        //:~ end add

        ///- add by junny, 2018-8-21

        $.validation.defaultEvents = $.validation.events || $.validation.defaultEvents;
        //:~ end
        if (validator) {
            for (key in validator) {
                ///- modified by junny, 2018-8-21
                var type = $.validation.validators[key].type || key;
                var val = $.validation.validators[type];
                if (type != key) {
                    val = $.extend(val, $.validation.validators[key]);
                }
                var target = $.validation.validators[key].target || val.target;
                //                
                var param = {
                    type: type
                };
                if (typeof val === "string") { // key : "xxx" 类型
                    param.target = val;

                } else { // key : {target='xxx', type= 'xxx'}

                    param = $.extend(param, val);
                    param.target = target || "." + type;
                }
                //:~ end modify
                $(param.target).off(param.type);
                if (param.delegate) {
                    $(param.target).delegate(param);
                } else {
                    $(param.target).validate(param);
                }

            }
        }
    };
    $.extend($.validation.messages, {
        _self: "这",
        _default: "{0}的输入有误",
        gt: "{name}必须要大于{value}",
        ge: "{name}必须要大于或等于{value}",
        lt: "{name}必须要小于{value}",
        le: "{name}必须要小于或等于{value}",
        range: "{name}超出范围{range}",
        standard: "{0}中包含特殊字符",
        required: "{0}是必填字段",
        remote: "请修正此字段",
        email: "请输入有效的电子邮件地址",
        url: "请输入有效的网址",
        date: "请输入有效的日期",
        dateISO: "请输入有效的日期 (YYYY-MM-DD)",
        inputNum: "请输入数字",
        number: "请输入有效的数字",
        digits: "{0}只能输入数字",
        creditcard: "请输入有效的信用卡号码",
        equalTo: "{name}和{value}不相同",
        extension: "请输入有效的后缀",
        maxsize: "{name}不能超过{max}个字符",
        minsize: "{name}不能少于{min}个字符",
        format: "{0}的格式不正确"
    });

    var p = /\.[\w\-]+/;

    $.fn.extend({
        validations: function (key) {
            var result = this.data(VALIDATIONS_NAME);
            if (result) {
                return !!key ? result.validations[key] : result.validations;
            } else {
                return null;
            }

        },
        /**
         * 向指定控件添加验证动作的方法，同一类验证可同时绑定到多个控件，一个控件上也可以同时绑定多种类型的验证
         * 
         * 调用该方法时，设定参数数量或类型的不同可以产生不同的动作，调用可以是以下格式： 向某控件添加一个验证 1.
         * $(selector).validate(handler[, events[, type]]); 2.
         * $(selector).validate({ type : type, events : events, handler :
         * function(){} }); -- handler :
         * 验证方法句柄，该方法应该返回一个boolean值以表明验证结果，可选，默认是永远为真的验证结果 -- events :
         * string，触发验证执行的控件事件，可选，默认是change事件 -- type :
         * string，指定该验证的类型，可选，系统会生成一个随机的字符串，但不建议这么做 触发某个验证并返回验证结果 3. var isOK =
         * $(selector).validate([type]); -- type : string, 可选，指定验证类型，默认是触发所有验证
         * 
         */
        validate: function () {
            if (!koala.isExists(this)) {
                return;
            }
            /*
             * 如果不设定参数或参数是某种验证类型，则是触发控件上所有（或某种类型）的验证，并返回验证结果<br>
             */
            if (arguments.length == 0 || typeof arguments[0] == 'string') {
                var result = true;
                var args = arguments;
                $(this).each(function (index, element) {
                    var $elem = $(element);
                    if (element.tagName && element.tagName.toUpperCase() == "FORM") {
                        $elem = $(element).find("*");
                    }
                    for (var i = 0; i < $elem.length; i++) {
                        var data = $($elem[i]).data(VALIDATIONS_NAME);
                        if (!!data) {
                            for (key in data.validations) {
                                var type = args[0] || key;
                                var item = data.validations[key];
                                if (!item.ignore && type == key) {
                                    $($elem[i]).trigger(item.events + "." + key);
                                    result = result && item.isOK;
                                    if (!item.isOK) {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                });
                return result;
            }
            /*
             * 如果参数多于1个或参数是某个对象，则是为该控件绑定验证事件
             */
            if (arguments.length > 0 && typeof arguments[0] !== 'string') {
                var target = {
                    events: $V.defaultEvents || DEFULT_VALIDATE_EVENTS
                };
                if ($.isFunction(arguments[0])) {
                    target.handler = arguments[0];
                    target.events = arguments[1] || target.events;
                    target.type = arguments[2];
                    target.success = function () {};
                    target.error = function () {};
                } else {
                    target = $.extend({}, target, arguments[0]);
                }

                var namespace = target.namespace = target.type || PROFIX_NAMESPACE + koala.getRandomString(4);
                var key = target.key = target.events + "." + target.namespace;

                /*
                 * 绑定ignore事件，用以忽略该控件的验证。一般在控件变成disabled时使用。 @param {Object}
                 * event 事件对象 @param {Object} type
                 * 需要忽略的验证类型，如果不指定则会忽略注册在该控件上的所有验证
                 */
                this.on(DEFINED_EVENTS_IGNORE, function (event, type) {
                    type = type || namespace;
                    if (this == event.target && namespace == type) {
                        $(this).validations(type).ignore = true;
                        // console.log("Ignore [element - " + this.id + ", type
                        // - " + type + "]");
                    }
                    return false;
                });
                /*
                 * 绑定重置事件，用以重置该控件的验证状态（置为‘未验证’）。 @param {Object} event事件对象
                 * @param {Object} type 需要重置的验证类型，如果不指定则会重置注册在该控件上的所有验证状态
                 */
                this.on(DEFINED_EVENTS_RESET, function (event, type) {
                    if (this == event.target) {
                        $(this).validations(namespace).isOK = false;
                        $(this).getMessageContainer().hide();
                        // console.log("Reset [element - " + this.id + ", type -
                        // " + namespace + "]");
                    }
                    return false;
                });
                /*
                 * 绑定激活验证事件，用以激活被忽略的验证动作 @param {Object} event 事件对象 @param
                 * {Object} type 被激活的验证类型，如不指定则激活注册在该控件上的所有验证
                 */
                this.on(DEFINED_EVENTS_ALIVE, function (event, type) {
                    type = type || namespace;
                    if (this == event.target && namespace == type) {
                        $(this).validations(type).ignore = false;
                    }
                    return false;
                });

                /*
                 * 为控件绑定验证事件
                 * 
                 * @param {Object} e 事件对象
                 */
                this.on(key, target, function (e) {
                    /* 上下文 */
                    var context = e.data;
                    var validation = $(this).validations(context.namespace);
                    if (this == e.target && !!validation && !validation.ignore) {

                        /* 级联动作 */
                        if (context.cascade) {
                            $(context.cascade).reset(context.cascade_handler);
                        }
                        /* 验证方法，有用户自己定义或使用默认方法 */
                        var handler = context.handler || context.foo || $.validation.handlers[context.namespace] || function () {
                            return true;
                        };
                        /* 验证结果发的处理方法，一般有success和error两种结果，分别对应两个不同的方法调用 */
                        var resultHanlder = context.success || $.validation.success;
                        /* 获取待验证控件值的方法，一般是返回当前控件的value属性或者元素内容，也可以由用户自定义 */
                        var inputHandler = context.getValue || function () {
                            return $(this).val() || $(this).html()
                        };
                        var input = inputHandler.call(this, context);
                        var result = true;
                        /**
                         * 待验证值为null或undefined，除非验证类型是required(必需的)或是委托类型，则视为验证通过
                         * 如果该控件的值是必需的，那么它必须先通过required的验证，否则如果用户没有设置它的值，不应
                         * 认为是验证失败。
                         */
                        if (!!input || context.namespace == 'required' || context.delegate) {
                            result = handler.call(this, input, context, e);
                        }

                        validation.isOK = result = !!result;
                        /*
                         * 获取验证信息中的消息参数，可以由用户指定(实现getMessageParameters方法)，否则默认是这个控件的label值
                         */
                        var messageParameters;
                        if (context.getMessageParameters) {
                            messageParameters = context.getMessageParameters.call(this, context);
                        }
                        messageParameters = messageParameters || context.label || $(this).getLabel();

                        /*
                         * 获取存放消息的容器对象
                         */
                        var msgContainer = $(this).getMessageContainer();

                        if (!result) {
                            resultHanlder = context.fault || context.error || $.validation.error;
                            /* 如果验证失败，则阻止其他事件运行 */
                            e.stopImmediatePropagation();

                            var message = context.message || $V.messages[context.namespace] || $V.messages["_default"];

                            message = koala.format(message, messageParameters);

                        }

                        resultHanlder.call(this, message, messageParameters, context.namespace, msgContainer, e);
                    }
                    return false;
                });

                /* 为控件添加验证状态 */
                return this.each(function () {

                    /* 验证状态对象 */
                    var data = $(this).data(VALIDATIONS_NAME) || {
                        source: this,
                        validations: {}
                    };
                    var result = {
                        events: target.events,
                        /* 验证类型 */
                        type: target.namespace,
                        /* 是否忽略 */
                        ignore: false,
                        /* 验证结果 */
                        isOK: false
                    };
                    /* 设置控件验证状态 */
                    data.validations[target.namespace] = result;

                    $(this).data(VALIDATIONS_NAME, data);

                });
            }
        },
        /**
         * 将验证事件委托给其他控件，一般用以一组控件（如checkbox）的统一验证
         */
        delegate: function (options) {
            options = $.extend({}, {
                events: DEFULT_VALIDATE_EVENTS,
                delegate: "",
                delegate_events: "delegate",
                type: koala.getRandomString(4),
                getKey: function () {
                    return this.delegate_events + "." + this.type
                }
            }, options);
            var target = $(options.delegate) || null;
            if (target) {
                if (!target.validations() || target.validations(options.getKey()) === undefined) {
                    var ops = $.extend({}, options);
                    ops.events = options.delegate_events;
                    target.validate(ops);
                }
                $(this).on(options.events, function () {
                    target.trigger(options.getKey());
                });
            }
        },
        valResult: function () {
            var result = {
                isOK: true
            };
            $(this).each(function (index, element) {
                var data = $(element).data(VALIDATIONS_NAME);
                if (!!data) {
                    for (key in data.validations) {
                        var item = data.validations[key];
                        if (!item.ignore) {
                            $(data["source"]).trigger(item.events + '.' + item.type);
                            if (!item.isOK) {
                                result = {
                                    source: data["source"],
                                    events: item.events + '.' + item.type,
                                    isOK: item.isOK,
                                    trigger: function () {
                                        $(this.source).trigger(this.events);
                                    }
                                };
                                return false;
                            }

                        }
                    }
                }
            });
            return result;
        },
        getLabel: function () {
            var label = $("label[for='" + this.attr("id") + "']").text() || this.data("title") || this.data("label") || validation.messages._self;
            return label.trim().trim(":");
        },
        getMessageContainer: function (name) {
            name = name || "msg";
            var id = this.data(name) || this.attr(name) || this.attr("id") + "_error_" + name;
            var container = $("#" + id);

            if (!koala.isExists(container)) {
                var defaultPlacement = $.validation.errorPlacement;
                if (!defaultPlacement) {

                    container = $("<span></span>").attr("id", id);
                    container.insertAfter(this);
                    this.data(name, id);
                } else {
                    container = $(defaultPlacement);
                }
            }
            return container;
        },
        putValidateMsg: function (msg, errorplacement, name, type) {
            errorplacement = errorplacement || this.getMessageContainer(errorplacement);
            msg = msg || $.validation.error;
            if ($.isFunction(msg)) {
                msg.call(this, name, errorplacement, type);
            } else {
                errorplacement.text(msg).show();
            }
            return errorplacement;
        },
        ignore: function (type) {
            return this.trigger(DEFINED_EVENTS_IGNORE, type);
        },
        alive: function (type) {
            return this.trigger(DEFINED_EVENTS_ALIVE, type);
        },
        reset: function (handler, options) {
            var len = arguments.length;

            if (len == 1) {
                if ($.isFunction(handler)) {
                    options = {};
                    options.handler = handler;
                } else {
                    options = handler;
                }
            }
            if (len == 2) {
                options.handler = handler;
            }
            options = $.extend({}, {
                /* 是否清空控件值的标志位 */
                empty: true,
                handler: function (value) {
                    return value;
                },
                INPUT: function () {
                    if (this.type == "checkbox") {
                        this.checked = false;
                    } else {
                        $(this).val("");
                    }
                },
                SELECT: function () {
                    $(this).val(0);
                },
                TEXTAREA: function () {
                    $(this).html("")
                }
            }, options);
            /* 重置验证状态 */
            this.trigger(DEFINED_EVENTS_RESET);
            return this.each(function (index, e) {
                if (options.empty) {
                    foo = options[e.tagName.toUpperCase()];
                    if (foo) {
                        foo.call(e, this);
                    }
                }
                options.handler.call(e, this);
            });
        }
    });
}));
