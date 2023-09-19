
// Đối tượng validator
function  Validator(options) {

    //Hàm thực hiện validate
    function validate(inputElement,rule) {
        var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
        var errorMessage = rule.test(inputElement.value);

        if(errorMessage){
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        }else{
            errorElement.innerText = "";
            inputElement.parentElement.classList.remove('invalid');
        }
    }
    // lấy element của form cần validate
    var formElement = document.querySelector(options.form);
    if(formElement){
        options.rules.forEach(function (rule) {
            var  inputElement = formElement.querySelector(rule.selector);

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
Validator.isRequired = function (selector) {
    return {
        selector : selector,
        test: function (value) {
            return value.trim() ? undefined : 'Vui lòng nhập trường này'
        }
    };
}
Validator.isEmail = function (selector) {
    return {
        selector: selector,
        test: function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'Trường này phải là email';
        }
    };
}
    Validator.minLength = function (selector,min) {
        return {
            selector : selector,
            test: function (value) {

                return value.length >= min ? undefined : `vui lòng nhập tối thiểu ${min} kí tự`;
            }
        };
}