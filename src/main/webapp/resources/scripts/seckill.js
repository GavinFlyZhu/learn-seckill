var seckill = {
    URL: {
        now : function () {
          return '/seckill/time/now';
        },
        exposer: function(seckillId) {
            return '/seckill/'+seckillId+'/exposer';
        },
        execution: function(seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countDown: function(seckillId, nowTime, startTime, endTime){
        var seckillBox = $('#seckillBox');
        if (nowTime > endTime){
            seckillBox.html('seckill finished');
        } else if (nowTime<startTime){
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown("2018/12/20", function(event){
                var format = event.strftime('Seckill countdown: %DDays %HHour %MMins %SSeconds');
                seckillBox.html(format);
            }).on('finish.countdown', function(){
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },

    handleSeckill: function(seckillId, node){
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id="killBtn">Start</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function(result){
            if(result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']){
                    //start
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);

                    $('#killBtn').one('click', function(){
                        $(this).addClass('disabled');
                        $.post(killUrl, {}, function(result){
                            if (result && result['success']){
                                var seckillResult = result['data'];
                                var state = seckillResult['state'];
                                var stateInfo = seckillResult['stateInfo'];
                                node.html('<span class="label label-success">'+ stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countDown(seckillId, now, start, end);
                }
            }
        });
    },

    detail: {
        init: function (params) {
            var killPhone = $.cookie('killPhone');

            if (!seckill.validatePhone(killPhone)) {
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,
                    backdrop: 'static',
                    keyboard: false
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">wrong phone number</label>').show(300);
                    }
                });
            }

            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function(result) {
               if (result && result['success']) {
                   var nowTime = result['data'];
                   seckill.countDown(seckillId,nowTime,startTime,endTime);
               } else {
                   console.log('result: ' + result);
               }
            });
        }
    }
};