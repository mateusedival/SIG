btnMap = document.querySelector("#btnMap");
btnForms = document.querySelector('#btnForms');
maps = document.querySelector('#map')
forms = document.querySelector('#forms')

forms.style.display='none'
setDisplay(forms.children,'none')

btnMap.onclick = function() {
    forms.style.display = 'none'
    setDisplay(forms.children,'none')
    if(maps.style.display == 'none') 
        maps.style.display = 'block';
}

btnForms.onclick = function() {
    maps.style.display='none';
    if(forms.style.display == 'none') 
    {
        forms.style.display = 'block';
        setDisplay(forms.children,'block')
    }
}

function setDisplay(array,state) {
    for (i = 0; i < array.length; i++)
    {
        array[i].style.display = state;
    }
}