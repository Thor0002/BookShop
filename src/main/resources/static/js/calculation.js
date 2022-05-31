function getHeight(form) {
    const inputs = form.querySelectorAll('input');
    const oneInputHeight = parseInt(inputs[0].style.height);
    const margins = parseInt(inputs[0].style.margin);
    const height = inputs.length * (oneInputHeight + 2 * margins);

    return height + 10;
}

function setHeight() {
    const form = document.querySelector('.data');
    const computedHeight = getHeight(form);
    form.style.height = `${computedHeight}px`
}

setHeight();