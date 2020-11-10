const btnMap = document.querySelector("#btnMap");
const btnForms = document.querySelector('#btnForms');
const btnView = document.querySelector('#btnView');
const maps = document.querySelector('#map')
const forms = document.querySelector('#forms')
const view = document.querySelector('#view')
const post = document.querySelector('#post')

const alunoBtn = document.querySelector("#alunoBtn")
const cursoBtn = document.querySelector("#cursoBtn")
const shapeBtn = document.getElementById("shapeBtn")
let alunoForms = document.getElementById("alunoForms")
let cursoForms = document.getElementById("cursoForms")
let shapeForms = document.querySelector("#shapeForms")
const cursoPost = document.getElementById("cursoPost")
const alunoPost = document.getElementById("alunoPost")
const shapePost = document.getElementById("shapePost")

let nomeInput = document.querySelector('#nome')
let idadeInput = document.querySelector('#idade')
let cursoInput = document.querySelector('#curso')
let serieInput = document.querySelector('#serie')
let shapeInput = document.querySelector('#shp')
const edit = document.querySelector('#edit');
let nomeEdit = document.querySelector('#nomeEdit')
let nomeCientificoEdit = document.querySelector('#nomeCientificoEdit')
let dataEdit = document.querySelector('#dataEdit')
const postEdit = document.querySelector('#postEdit')
const cancelEdit = document.querySelector('#cancelEdit')


plantios = []
update = 1;

let state = 'map';
let formsState = 'none';

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
        btnDisplay('none')
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
        maps.style.display = 'none';
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
    btnDisplay('block')
}

btnView.onclick = function() {
    if(state = 'map')
    {
        maps.style.display = 'none';
    }
    if(state = 'forms')
    {
        forms.style.display = 'none';
        btnDisplay('none')
    }
    if(state = 'edit')
    {
        edit.style.display = 'none'
        setDisplay(edit.children,'none')
    }
    state = 'view'
    view.style.display = 'block';
    setView();
}

function btnDisplay(display){
    alunoBtn.style.display = display
    cursoBtn.style.display = display
    shapeBtn.style.display = display
}

alunoBtn.onclick = setForms;
cursoBtn.onclick = setForms;
shapeBtn.onclick = setForms;

function setForms() {
    if(formsState == 'aluno')
        alunoForms.style.display = 'none';
    if(formsState == 'curso')
        cursoForms.style.display = 'none'
    if(formsState == 'shape')
        shapeForms.style.display = 'none'

    let id = this.getAttribute("id");
    if(id == "alunoBtn")
    {
        alunoForms.style.display = 'block';
        formsState = 'aluno'
    }
    else if(id == "cursoBtn")
    {
        cursoForms.style.display = 'block'
        formsState = 'curso'
    }
    else
    {
        shapeForms.style.display = 'block';
        formsState = 'shape'
    }
}
function cancelForms() {
    if(formsState = 'aluno')
         alunoForms.style.display = 'none';
    else if(id == "cursoBtn")
        cursoForms.style.display = 'none'
    else
        shapeForms.style.display = 'none';
    formsState = 'none'
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

function setInputValue(plantio){
   nomeEdit.value = String(plantio.nome)
   nomeCientificoEdit.value = String(plantio.nomeCientifico)
   dataEdit.value = String(plantio.data)
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
    update = 1;
}

function createDiv(point){

    let div = document.createElement("div");
    div.setAttribute("class","plantio")
    div.setAttribute("idPlantio",point.id)
    let edit = document.createElement("button");
    edit.setAttribute("class","updateBtn")
    edit.textContent = "Editar"
    edit.onclick = editClick;
    let remove = document.createElement("button");
    remove.setAttribute("class","updateBtn")
    remove.textContent = "Excluir"
    remove.onclick = removeClick;
    let text = document.createElement("p");
    text.setAttribute("class","textUpdate")
    text.textContent = point.nome 
    div.appendChild(text)
    div.appendChild(edit)
    div.appendChild(remove)
   
    return div;
}

function setView()
{
    if(plantios.length != 0 && update == 0)
        return

    const URL_TO_FETCH = 'http://25.7.142.197:8080/plantio/';

    fetch(URL_TO_FETCH)
    .then(function (response) {
        response.json().then(function (points) {
            points.forEach((point) => {
                let div = createDiv(point);
                plantios.push(point)
                view.appendChild(div)
            })
        });
    })
    .catch(function (err) {
        console.error('Failed retrieving information', err);
    });
    
    update = 0;
}

function editClick() {
    view.style.display = 'none'
    state = 'edit'
    const plantio = plantios.filter((x) => {return x.id == this.parentNode.getAttribute("idplantio")});
    setInputValue(plantio[0])
    edit.style.display = 'block'
    setDisplay(edit.children,'block')
}

function removeClick() {
    this.parentNode.remove();

}


cancelEdit.onclick = function() {
    edit.style.display = 'none'
    setDisplay(edit.children,'none')

    state = 'view'

    view.style.display = 'block';
}