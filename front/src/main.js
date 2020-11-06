

btnMap = document.querySelector("#btnMap");
btnForms = document.querySelector('#btnForms');
btnView = document.querySelector('#btnView');
maps = document.querySelector('#map')
forms = document.querySelector('#forms')
view = document.querySelector('#view')
post = document.querySelector('#post')


let state = 'map';

forms.style.display='none'
view.style.display='none'
setDisplay(forms.children,'none')
setDisplay(view.children,'none')

btnMap.onclick = function() {
    if(state = 'forms')
    {
        forms.style.display = 'none';
        setDisplay(forms.children,'none')
    }
    if(state = 'view')
    {
        view.style.display='none'
    }
    state = 'map'
    maps.style.display = 'block';
}

btnForms.onclick = function() {
    if(state = 'map')
    {
        map.style.display = 'none';
    }
    if(state = 'view')
    {
        view.style.display='none'
    }
    state = 'forms'
    forms.style.display = 'block';
    setDisplay(forms.children,'block')
}

btnView.onclick = function() {
    if(state = 'map')
    {
        map.style.display = 'none';
    }
    if(state = 'forms')
    {
        forms.style.display = 'none';
        setDisplay(forms.children,'none')
    }
    state = 'view'
    view.style.display = 'block';
    let div = createDiv();
    view.appendChild(div)
}

function setDisplay(array,state) {
    for (i = 0; i < array.length; i++)
    {
        array[i].style.display = state;
    }
}

function getInputValue(){
  
    let text = [];
    let shape;
    for(i = 0; i < forms.children.length; i++)
    {
        if(forms.children[i].localName == 'input' && forms.children[i].type != 'file')
            text.push(forms.children[i].value);
        if(forms.children[i].type == 'file')
         shape = forms.children[i].files[0];
    }
    const inputs = {
        "text" : text,
        "shape" : shape
    };
    return inputs;
}

post.onclick = async function() {
    let inputs = getInputValue();

    inputs.text.forEach(x => {
        if(x == "")
            return alert('erro')
    })

    const plantio = {
        "nome" : inputs.text[0],
        "idade" : inputs.text[1],
        "serie" : inputs.text[3],
        "curso" : inputs.text[2],
        "shape" : inputs.shape
    }
    await fetch('localhost:8080/aluno', {
        method: 'POST',
        body: plantio   
    })
}

function createDiv(){
    let div = document.createElement("div");
    div.style.cssText = "border: 1px solid black; height: 50px; width: 100%; display: flex; justify-content: center; border-radius: 15px;";

    let edit = document.createElement("button");
    edit.style.cssText = "border: none; width: 15%; ";
    let remove = document.createElement("button");
    remove.style.cssText = "border: none; width: 15%; ";
    let text = document.createElement("p");
    text.style.cssText = "width: 60%;  border-radius: 15px;";
    div.appendChild(text)
    div.appendChild(edit)
    div.appendChild(remove)
   
    return div;
}