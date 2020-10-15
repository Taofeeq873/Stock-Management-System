

// let x = document.getElementById ("quantity");
//  let y = document.getElementById ("price");

function multiply(x,y) {
    let mult = parseFloat(x) * parseFloat(y);
    return mult;
}
function getQuantity() {
    let x = document.getElementById ("quantity");
    return x.value;
}
function getPrice() {
    let y = document.getElementById ("price");
    return y.value;
}
function multiplyClick() {
    let x = getQuantity();
    let y = getPrice();
    let result = multiply(x,y);
   document.getElementById("total").value = result;
}
multiplyClick();


