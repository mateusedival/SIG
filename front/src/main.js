btnMap = document.querySelector("#btnMap");
btnForms = document.querySelector('#btnForms');
maps = document.querySelector('#map')

btnMap.onclick = function() {
    let display =  maps.style.display
    if(display == 'none') 
        maps.style.display = 'block';
}

btnForms.onclick = function() {
    maps.style.display='none';
}