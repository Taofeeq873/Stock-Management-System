

// let x = document.getElementById ("salesQuantity");
//  let y = document.getElementById ("price");


function getSalesQuantity() {
    let x = document.getElementById ("salesQuantity");
    return x.value;
}
function getPrice() {
    let y = document.getElementById ("price");
    return y.value;
}
function getTax() {
    let z = document.getElementById ("tax");
    return z.value;
}
function multiplyClick() {
    let x = getSalesQuantity();
    let y = getPrice();
    let z = getTax();
    let result = multiply(x,y,z);
   document.getElementById("total").value = result;
}
function multiply(x,y,z) {
    let result = x * y * z;
    let mult =  result / 100;
    let final = parseFloat(mult);
    return final;
}

multiplyClick();


