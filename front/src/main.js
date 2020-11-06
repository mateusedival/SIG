

btnMap = document.querySelector("#btnMap");
btnForms = document.querySelector('#btnForms');
btnView = document.querySelector('#btnView');
maps = document.querySelector('#map')
forms = document.querySelector('#forms')
view = document.querySelector('#view')
post = document.querySelector('#post')
nomeInput = document.querySelector('#nome')
idadeInput = document.querySelector('#idade')
cursoInput = document.querySelector('#curso')
serieInput = document.querySelector('#serie')
shapeInput = document.querySelector('#shp')
edit = document.querySelector('#edit');
nomeEdit = document.querySelector('#nomeEdit')
idadeEdit = document.querySelector('#idadeEdit')
cursoEdit = document.querySelector('#cursoEdit')
serieEdit= document.querySelector('#serieEdit')
shapeEdit = document.querySelector('#shpEdit')
postEdit = document.querySelector('#postEdit')
cancelEdit = document.querySelector('#cancelEdit')



plantios = []

let state = 'map';

edit.style.display = 'none'
forms.style.display='none'
view.style.display='none'
setDisplay(forms.children,'none')
setDisplay(view.children,'none')
setDisplay(edit.children,'none')

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
    if(state = 'edit')
    {
        edit.style.display = 'none'
        setDisplay(edit.children,'none')
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
    if(state = 'edit')
    {
        edit.style.display = 'none'
        setDisplay(edit.children,'none')
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
    if(state = 'edit')
    {
        edit.style.display = 'none'
        setDisplay(edit.children,'none')
    }
    state = 'view'
    view.style.display = 'block';
    let div = createDiv();
    plantios.push(div)
    view.appendChild(div)
}

function setDisplay(array,state) {
    for (i = 0; i < array.length; i++)
    {
        array[i].style.display = state;
    }
}

function getInputValue(){
  
    let text = [nomeInput.value,idadeInput.value,curso.value,serie.value];
    let shape = shapeInput.files[0];
    const inputs = {
        "text" : text,
        "shape" : shape
    };
    return inputs;
}

function setInputValue(value,div){
    for(i = 0; i < div.length; i++)
    {
        div[i].value = value[i]
    }
}

post.onclick = async function() {
    let inputs = getInputValue();
    console.log(inputs)
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
    div.setAttribute("class","plantio")
    let edit = document.createElement("button");
    edit.setAttribute("class","updateBtn")
    edit.onclick = editClick;
    let remove = document.createElement("button");
    remove.setAttribute("class","updateBtn")
    let text = document.createElement("p");
    text.setAttribute("class","textUpdate")
    div.appendChild(text)
    div.appendChild(edit)
    div.appendChild(remove)
   
    return div;
}

function setView()
{
    const points = async () => {
        const response = await fetch('localhost:8080/plantio', {method: 'GET'});
        const points = await response.json(); 
        return points
    }
    points.forEach((point) => {
        let div = mapDiv(createDiv(),point);
        plantio.push(div)
        view.appendChild(div)
    })

}

function editClick(){
    view.style.display = 'none'
    state = 'edit'

    edit.style.display = 'block'
    setDisplay(edit.children,'block')
}

cancelEdit.onclick = function() {
    edit.style.display = 'none'
    setDisplay(edit.children,'none')

    state = 'view'

    view.style.display = 'block';
}