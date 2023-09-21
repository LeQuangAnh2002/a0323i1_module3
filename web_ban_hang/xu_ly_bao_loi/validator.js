
// Đối tượng validator
function  Validator(options) {
    var selectorRule = {};

    //Hàm thực hiện validate
    function validate(inputElement,rule) {
        var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
        var errorMessage;

        // lấy các rules của selector
        var rules = selectorRule[rule.selector];
        //lặp qua tường rule và kiểm tra
        // nếu có lỗi thì dừng việc kiểm tra
        for ( var i = 0; i < rules.length; i++){
            errorMessage = rules[i](inputElement.value);
            if(errorMessage) break;
        }


        if(errorMessage){
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        }else{
            errorElement.innerText = "";
            inputElement.parentElement.classList.remove('invalid');
        }
        return !errorMessage;
    }
    // lấy element của form cần validate
    var formElement = document.querySelector(options.form);
    if(formElement){
        //Khi sumbmit form
            formElement.onsubmit = function(e){
                e.preventDefault();
                var isFormValid = true;

                // thực hiện lặp qua từng rules và validate
                options.rules.forEach(function (rule) {
                    var inputElement = formElement.querySelector(rule.selector);
                   var isValid = validate(inputElement,rule);
                    if(!isValid){
                        isFormValid = false;
                    }
                });



                if (isFormValid){
                    // trường hợp submit với javascript
                    if(typeof options.onSubmit === 'function'){
                        var enableInputs = formElement.querySelectorAll('[name]');

                        var formValues = Array.from(enableInputs).reduce(function (values,input) {
                            return (values[input.name] = input.value) && values;
                        },{});

                        options.onSubmit(formValues);
                    }
                    //trường hợp hợp submit với hình vi mặc định
                    else{
                            formElement.submit();
                    }
                }
            }


        // Lặp qua mỗi rule và xử lý(lắng nghe sự kiên,blur,input,..)
        options.rules.forEach(function (rule) {
            //Lưu lại các  rules cho mỗi input
            if(Array.isArray(selectorRule[rule.selector])){
                selectorRule[rule.selector].push(rule.test);
            }else {
                selectorRule[rule.selector] = [rule.test];
            }


            var inputElement = formElement.querySelector(rule.selector);
            if(inputElement){
                // xử lý trường họp blur khỏi input
                inputElement.onblur = function () {
                        // value : inputElement.value
                        // test func: rule.test
                        validate(inputElement,rule);


                }
                //xử lý mỗi khi người dùng nhập vào input
                inputElement.oninput = function () {
                    var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
                    errorElement.innerText = "";
                    inputElement.parentElement.classList.remove('invalid');
                }
            }
        });

    }
}
// Định nghĩa các rules
//Nguyên tắc của rules:
// 1. khi có lỗi => trả lỗi ra message
// 2. khi hợp lệ => ko trả ra gì cả
Validator.isRequired = function (selector,message) {
    return {
        selector : selector,
        test: function (value) {
            return value.trim() ? undefined : message || 'Vui lòng nhập trường này'
        }
    };
}
Validator.isEmail = function (selector,message) {
    return {
        selector: selector,
        test: function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : message || 'Trường này phải là email';
        }
    };
}
    Validator.minLength = function (selector,min,message) {
        return {
            selector : selector,
            test: function (value) {

                return value.length >= min ? undefined : message || `vui lòng nhập tối thiểu ${min} kí tự`;
            }
        };
}
Validator.isConfirmed = function (selector,getConfirmValue,message) {
        return {
            selector: selector,
            test : function (value) {

                return value === getConfirmValue() ? undefined : message || 'Gía trị nhập vào không chính xác'

            }
        };
}